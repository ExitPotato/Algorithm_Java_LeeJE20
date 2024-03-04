package 그래프.dfs;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU
[시간]


[아이디어]
dfs 몇 개의 그룹?

유니온 파인드 그룹 개수


[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Solution_7465_창용_마을_무리의_개수 {
	static int T, N, M;

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
//		String input = "2\n" +
//				"6 5\n" +
//				"1 2\n" +
//				"2 5\n" +
//				"5 1\n" +
//				"3 4\n" +
//				"4 6\n" +
//				"6 8\n" +
//				"1 2\n" +
//				"2 5\n" +
//				"5 1\n" +
//				"3 4\n" +
//				"4 6\n" +
//				"5 4\n" +
//				"2 4\n" +
//				"2 3";
//		BufferedReader br = new BufferedReader(new StringReader(input));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int[][] graph = new int[N][N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				graph[from][to] = 1;
				graph[to][from] = 1;
			}
			// solve
			boolean[] visited = new boolean[N];
			int answer = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) continue;
				answer++;
				dfs(i, visited, graph);
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int i, boolean[] visited, int[][] graph) {
		if (visited[i]) return;
		visited[i] = true;
		int size = graph.length;
		for (int j = 0; j < size; j++) {
			if(graph[i][j]==0) continue;
			if(visited[j]) continue;
			dfs(j, visited, graph);

		}
	}
}
