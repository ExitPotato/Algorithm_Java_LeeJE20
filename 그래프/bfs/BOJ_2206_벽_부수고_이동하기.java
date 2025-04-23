/*
25.04.23 22: 28

틀림. 질문게시판봄

이유는 벽을 먼저 부수고 간 것이 먼저 도착하는 경우 때문입니다.
그러면 벽을 부수지 않은 것이 늦게 도착할 경우에 방문 처리에 의해 무시되어 통과하지 못하게 됩니다.

2트에 성공

TODO: if문 부분 코드 개선. 왜 768초 나오지??

참고: https://www.acmicpc.net/source/83407333

*/
import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int map[][] = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp[j] - '0';
            }
        }
        
        // printMap(map);
        
        // 풀이
        
        final int WALL = 1;
        final int EMPTY = 0;
        
        // 도착지점
        final int STOP_Y = N - 1;
        final int STOP_X = M - 1; 
        
        // int[4]: y좌표, x좌표, 이동 칸수, 지금까지 벽 부순 횟수
        final int Y = 0;
        final int X = 1;
        final int STEP = 2;
        final int BREAK_WALL = 3;
        
        final int BREAK_WALL_TRUE = 1;
        final int BREAK_WALL_FALSE = 0;
        Queue<int[]> q = new ArrayDeque<>();
        
        boolean[][][] visited = new boolean[N][M][2];
        
        
        int answer = -1;
        
        if (map[0][0] == EMPTY) {
            q.add(new int[]{0, 0, 1, 0});
            visited[0][0][BREAK_WALL_FALSE] = true;
        }
        
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        
        while(!q.isEmpty()) {
            int[] current = q.poll();
            int cy = current[Y];
            int cx = current[X];
            int cStep = current[STEP];
            int cBreakWall = current[BREAK_WALL];
            
            if (cy == STOP_Y && cx == STOP_X) {
                answer = cStep;
                break;
            }
            
            for (int k = 0; k < 4; k++) {
                int ny = cy + dy[k];
                int nx = cx + dx[k];
                
                // 범위 체크
                if (ny < 0 || ny > STOP_Y || nx < 0 || nx > STOP_X) {
                    continue;
                }
                
                // // 벽 뚫은 적 있고 방문함
                // if (cBreakWall == BREAK_WALL_TRUE && visited[ny][nx][BREAK_WALL_TRUE]) {
                    // continue;
                // }
                
                // // 벽 뚫은 적 없고 방문함
                // if (cBreakWall == BREAK_WALL_FALSE && visited[ny][nx][BREAK_WALL_FALSE]) {
                    // continue;
                // }
                
                
                // int nBreakWall = cBreakWall;
                
                if (cBreakWall == BREAK_WALL_FALSE && map[ny][nx] == WALL && !visited[ny][nx][BREAK_WALL_TRUE]) {
                    // 근데 벽 안 뚫고 더 빨리 올 수 있는 방법이 있다면 굳이 안 뚫어도 됨
                    if (!visited[ny][nx][BREAK_WALL_FALSE]) {
                        q.add(new int[]{ny, nx, cStep+1, BREAK_WALL_TRUE});
                        visited[ny][nx][BREAK_WALL_TRUE] = true;
                        continue;
                    }
                }
                
                // 벽 뚫은 적 없고 갈 수 있음
                if (cBreakWall == BREAK_WALL_FALSE && map[ny][nx] == EMPTY && !visited[ny][nx][BREAK_WALL_FALSE]) {
                    q.add(new int[]{ny, nx, cStep+1, BREAK_WALL_FALSE});
                    visited[ny][nx][BREAK_WALL_FALSE] = true;
                    continue;
                }
                
                // 벽 뚫은 적 있고 갈 수 있음
                if (cBreakWall == BREAK_WALL_TRUE && map[ny][nx] == EMPTY && !visited[ny][nx][BREAK_WALL_TRUE]) {
                    q.add(new int[]{ny, nx, cStep+1, BREAK_WALL_TRUE});
                    visited[ny][nx][BREAK_WALL_TRUE] = true;
                    continue;
                }
            }
        }
        
        System.out.println(answer);
    }
    
    static void printMap(int[][] map) {
        int y = map.length;
        int x = map[0].length;
        
        for (int i = 0; i < y; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
