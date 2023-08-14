package 조합;


/*
https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYmp6Fk6f5EDFARi&contestProbId=AW8Wj7cqbY0DFAXN&probBoxId=AYnS4_oaSLYDFARi+&type=PROBLEM&problemBoxTitle=8%EC%9B%94+8%EC%9D%BC&problemBoxCnt=++2+
[아이디어]
조합문제
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_한빈이와_Spot_Mart {
	static int max;
	static int answer;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		
		for (int t = 0; t < T; t++){
			int n, m;
			answer = -1;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++){
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// solve
			combination(0, 0, n, m, 0);
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void combination(int count, int start, int N, int M, int snackSum) {		
		if(count == 2) {
//			System.out.println(snackSum);
			if (snackSum <= M) {
				answer = Math.max(answer, snackSum);
			}
			return;
		}
		for (int i = start; i < N; i++) {
			
			combination(count+1, i+1, N, M, snackSum+arr[i]);
		}
		
	}
}
