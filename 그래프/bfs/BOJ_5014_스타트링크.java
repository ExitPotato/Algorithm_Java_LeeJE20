/*
25.04.12

17:00

재귀로 dp 테이블 채우면서 푼다

17: 16 bfs로 바꿈

17:33 다 풀었다.

개선 -> 단계를 int로 체크했던것을 boolean으로 변경 (약 3000kb 개선)
개선 -> q에 단계를 같이 넣음 (4ms 개선, 약 7000kb 더 사용)

처음에 왜 bfs를 떠올리지 못했을까?
처음에는.. dp[n] = n번 계단에 올 때까지 눌러야 하는 버튼의 최소 횟수 이렇게 풀면 될 줄 알았다.
이런 풀이는 dfs 처럼 풀어야 하는데, 풀다보니 한 층에서 위 또는 아래 버튼을 누루는 것은 순서대로 (dfs)가 아니라 동시에 (bfs) 일괄 계산해야 한다고 느껴서 bfs로 전환.

다음에 이런 실수를 하지 않으려면?
버튼을 누르는 것처럼 무언가의 행위가 동시에 이루어져야 하는지 한번 더 고려해볼것. 단계별로, 동시에 이루어져야 한다면 bfs이다.

*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int F, S, G, U, D;
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        boolean[] visited = new boolean[F+1];         
        int[] step = {U, -D};
    
        // bfs
        Queue<int[]> q = new ArrayDeque<>();
        visited[S] = true;
        q.add(new int[]{S, 0}); // 층수, 버튼 누른 횟수
        
        int answer = 0;

q:      while (!q.isEmpty()) {

            int[] currentInfo = q.poll();
            int current = currentInfo[0];
            int level = currentInfo[1];
            
            if (current == G) {
                answer = level;
                break q;
            }
            
            for (int k = 0; k < 2; k++) {
                int next = current + step[k];
                
                if (next > F || next <= 0) {
                    continue;
                }
                
                if (visited[next]) {
                    continue;
                }
                
                visited[next] = true;
                
                q.add(new int[]{next, level + 1});
            }
            
        }
        
        String result = visited[G]? ""+ answer: "use the stairs";
        
        System.out.println(result);
        
    }
}
