package 유니온파인드;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr

[시간]


[아이디어]
유니온 파인드 연습용 문제

[시간복잡도]


[실수]
parents[aRoot] = bRoot; 를 parents[aRoot] = parents[bRoot]로 했다.
parents가 0으로 초기화되어 있으므로 이러면 부모가 0으로 된다....


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SWEA_3289_서로소_집합 {
	static int T, N, M;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[] parents = new int[n+1];
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (command == 0) {
					union(a, b, parents);
				}else {
					boolean isSameGroup = (find(a, parents) == find(b, parents));
					sb.append(isSameGroup?'1':'0');
				}
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
	private static boolean union(int a, int b, int[] parents) {
		int aRoot = find(a, parents);
		int bRoot = find(b, parents);
		
		// 다른 그룹
		if (aRoot == bRoot) {
			return false;
		}
		
		// b트리에 a 삽입
		parents[aRoot] = bRoot;
		return true;
		
	}
	private static int find(int a, int[] parents) {
		if (parents[a]==0) return a;
		return parents[a] = find(parents[a], parents);
	}
}
