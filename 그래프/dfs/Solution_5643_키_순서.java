package 그래프.dfs;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo

[시간]


[아이디어]
한 노드 기준으로 진입 기준으로 dfs, 진출 기준으로 dfs해서 모든 노드 훑을 수 있으면 ok

다른 아이디어
위상정렬했을때 위치가 고정되는 노드의 수를 찾는다
-> 근데 방법 검색해봐도 잘 안 나옴. 완전탐색으로 하는건 별로일 것 같다...
https://dev.gmarket.com/21


[시간복잡도]
엄청 오래 걸릴 것 같은데?
dfs* 노드 개수
흠...

dfs 말고 플루이드 워셜 쓰면 좀 더 나을려나?
-> 챗gpt 한테 물어보니까 그냥 연결됐는지 알아보는건 dfs가 더 낫다고 한다.


[실수]
dfs로 양방향으로 체크할 때 or로 해야하는데 and로 했다.

[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5643_키_순서 {

	static int T, N, M;

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");


			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			int[][] graph = new int[N+1][N+1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from][to] = 1;
			}
			// solve

			int answer = 0;
			for (int i = 1; i <= N; i++) {
				boolean[] visited = new boolean[N+1];
				boolean[] visited_reverse = new boolean[N+1];

				// 정방향
				dfs(i, visited, false, graph, N);
				// 역방향
				dfs(i, visited_reverse, true, graph, N);

				boolean isSuccess = true;
				for (int j = 1; j <= N; j++) {
					// 실수: 둘 하나라도 방문했으면 괜찮음 -> or로 체크해야함
					if (!(visited[j] | visited_reverse[j])){
						isSuccess = false;
						break;
					}
				}
				if (isSuccess) {
					answer++;
				}
			}
			sb.append(answer).append("\n");

		}
		System.out.println(sb);
	}

	private static void dfs(int now, boolean[] visited, boolean isReverse, int[][] graph, int N) {
		if (visited[now]) return;
		visited[now] = true;

		for (int i = 1; i <= N ; i++) {
			int edge = graph[now][i];
			if (isReverse){
				edge = graph[i][now];
			}
			// 간선 존재 여부
			if (edge != 1) continue;
			// 방문 여부
			if (visited[i]) continue;
			dfs(i, visited, isReverse, graph, N);
		}
	}
}
