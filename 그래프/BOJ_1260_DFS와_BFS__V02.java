package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
[링크]
https://www.acmicpc.net/problem/1260

[시간]
2024.06.14 16:35~2

[아이디어]
정점에 비해 간선이 많지 않으므로 인접리스트 이용

[시간복잡도]
O(N+E)

[실수]
1. 그래프 구축할 떄 List 객체를 안 만들고 시작했다..
2. String.strip()은 java 11 이상의 함수다!
3. BFS에서 PQ를 썼다. 순서대로 꺼내야 하므로 그냥 Q를 써야 한다.


[검색]
리스트 정렬방법
- Collections.sort(list);
- Collections.sort(list, Collections.reverseOrder());

- list.sort(Comparator.naturalOrder());
- list.sort(Comparator.reverseOrder());

[보완점]
BFS에서 Q에서 꺼낼 때 size를 썼는데 안 해도 된다.
DFS를 스택으로도 할 수 있다.
*/

public class BOJ_1260_DFS와_BFS__V02 {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        int N, M, V;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N + 1];

        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            graph[i].sort(Comparator.naturalOrder());
        }

        // DFS
        boolean[] dfsVisited = new boolean[N + 1];
        dfs(graph, V, dfsVisited, sb);

        sb.append("\n");

        // BFS
        boolean[] bfsVisited = new boolean[N + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        bfsVisited[V] = true;
        q.add(V);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int now = q.poll();
                sb.append(now).append(" ");

                for (Integer next: graph[now]){
                    if (bfsVisited[next]) continue;
                    bfsVisited[next] = true;
                    q.add(next);
                }
            }
        }

        // 출력
        System.out.println(sb);
    }

    public static void dfs(List<Integer>[] graph, int now, boolean[] visited, StringBuilder sb) {
        if (visited[now]) {
            return;
        }
        visited[now] = true;
        sb.append(now).append(" ");
        for (Integer next : graph[now]) {
            if (visited[next]) continue;
            dfs(graph, next, visited, sb);
        }
    }

}
