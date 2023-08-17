package 그래프.dfs;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD

[시간]


[아이디어]
완전탐색
dfs와 백트래킹
백트래킹 하면서 파라미터로 sum을 들고 다닌다.

[시간복잡도]
정점 N개, 전체 간선의 수 N^2개
O(N^2)

[실수]

[검색]


[다른 사람 코드]
중간에 sum이 min을 넘으면 바로 백트래킹한다.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SWEA_1247_최적_경로 {
	static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int T, N, M;
	static Node[] graph;
	static boolean[] visited;
	static int answer;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			sb.append("#").append(t).append(" ");
			N =  Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			graph = new Node[N];
			
			
			Node home = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Node company = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			for (int i = 0; i < N; i++) {
				graph[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// solve
			answer = Integer.MAX_VALUE;
			visited = new boolean[N];
			dfs(home, company, 0);
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
		
	}
	private static void dfs(Node current, Node company, int sum) {
		boolean visitedAll = true;
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visitedAll = false;
				break;
			}
		}
		
		if(visitedAll) {
			int length = Math.abs(current.x - company.x) + Math.abs(current.y - company.y);
			answer = Math.min(answer, sum+length);
			return;
		}
		
		
		for (int i = 0; i < N; i++) {
			// 방문했다면
			if (visited[i]) continue;
			
			// 방문 안했으면
			visited[i] = true;
			int length = Math.abs(current.x - graph[i].x) + Math.abs(current.y - graph[i].y);
			dfs(graph[i], company, sum+length);
			visited[i] = false;
		}
	}

}
