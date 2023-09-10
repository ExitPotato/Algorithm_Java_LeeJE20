package 그래프.bfs;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD
[시간]


[아이디어]
BFS

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238_Contact {
	static int T, N, M;
	static int[][] graph;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = 10;
		int answer = 0;
		boolean[] visited;
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			graph = new int[101][101];
			visited = new boolean[101];

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N/2; i++) {
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from][to] = 1;
			}
			
			// solve
			Queue<Integer> queue = new ArrayDeque<Integer>();
			queue.offer(M);
			visited[M] = true;
			
			while(!queue.isEmpty()) {
				int size = queue.size();
				answer = 0;
				for (int i = 0; i < size; i++) {
					int current = queue.poll();
					answer = Math.max(answer, current);
					
					for (int j = 1; j <= 100; j++) {
						if (!visited[j] && graph[current][j] == 1) {
							visited[j] = true;
							queue.offer(j);
						}
					}
				}
				
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
