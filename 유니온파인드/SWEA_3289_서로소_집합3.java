package 유니온파인드;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr

[시간]


[아이디어]
유니온 파인드 연습용 문제

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3289_서로소_집합3 {
	static int T, N, M;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		
		int[] parents;
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1]; // 대표 정보 저장
			Arrays.fill(parents, -1);
			// --> parents 값을 -1로 초기화
			// 조회시 -1이면 처음으로 대장(대표)을 찾는다.
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				switch (command) {
				case 0: // 합치기
					
					break;
				case 1: 
					// 두 노드의 루트노드 비교하기
					// 같은 집합: 1, 다른 집합: 0
					sb.append(find(a, parents) == find(b, parents) ? '1' : '0');
					break;
				}
				
			}
			
			
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
	
	// 부모 (대표자) 찾기
	private static int find(int a, int[] parents) {
		if (parents[a] == -1) // 대표가 자기자신!
			return a;
		return parents[a] = find(parents[a], parents);
		
	}
	
	private static boolean union(int a, int b, int[] parents) {
		int pa = find(a, parents);
		int pb = find(b, parents);
		
		if(pa != pb) // 서로 다른 집합이라면
			parents[pb] = pa;
		return false;
	}
}
