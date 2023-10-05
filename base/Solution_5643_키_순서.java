package base;

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


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Solution_5643_키_순서 {
	private static class Node{
		int vertex;
		Node next;
	}
	static int T, N, M;
	static Node[][] rightGraph;
	static Node[][] leftGraph;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		String input = "\n" +
				"1\n" +
				"6\n" +
				"6\n" +
				"1 5\n" +
				"3 4\n" +
				"5 4\n" +
				"4 2\n" +
				"4 6\n" +
				"5 2";
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new StringReader(input));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][M];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <	M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// solve
			
		}
		System.out.println(sb);
	}
}
