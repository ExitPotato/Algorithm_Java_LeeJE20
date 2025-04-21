/*
다시풀기 2회차
BFS

구현시작 22:09
끝: 22: 20
*/

import java.util.*;

class Solution {
    public int solution(int[][] maps) {

        
        int lengthY = maps.length;
        int lengthX = maps[0].length;
        
        int[] dy = {0, 0, -1, 1};
        int[] dx = {-1, 1, 0, 0};
        
        Queue<int[]> q = new ArrayDeque<>(); // y, x, depth
        
        q.add(new int[]{0,0,1});
        
        int answer = -1;
        while(!q.isEmpty()) {
            int[] current = q.poll();
            int cy = current[0];
            int cx = current[1];
            int depth = current[2];
            
            if (cy == lengthY -1 && cx == lengthX - 1) {
                answer = depth;
                break;
            }
            
            for (int k = 0; k < 4; k++) {
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                
                // map 범위
                if (ny >= lengthY || ny < 0 || nx >= lengthX || nx < 0) {
                    continue;
                }
                
                // 방문
                if (maps[ny][nx] == 0) {
                    continue;
                }
                
                maps[ny][nx] = 0;
                q.add(new int[]{ny, nx, depth+1});
            }
        }
        
        return answer;
    }
}
