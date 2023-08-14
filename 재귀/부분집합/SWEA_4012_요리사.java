package 재귀.부분집합;

/*
[링크]
https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYmp6Fk6f5EDFARi&contestProbId=AWT-lPB6dHUDFAVT&probBoxId=AYndnB5KMSUDFARi+&type=PROBLEM&problemBoxTitle=8%EC%9B%94+10%EC%9D%BC&problemBoxCnt=++3+

[시간]


[아이디어]
DP 가방 문제로 풀면 될 것 같은데 풀이가 생각 안 남....
그래서 부분집합으로 풀어보자.

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class SWEA_4012_요리사 {
	static int T, N, L;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	static boolean[] picked;
	static int minDistance;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// solve
			picked = new boolean[N];
			minDistance = Integer.MAX_VALUE;
			combination(0, N/2, 0);
			sb.append(minDistance).append("\n");
		}
		System.out.println(sb);	
	}

	static void combination(int count, int target, int start) {
		if (count >= target) {
			
			int sumA = 0;
			int sumB = 0;
		
			int[] arrA = new int[target];
			int[] arrB = new int[target];
			
			int a = 0;
			int b = 0;

			for (int i = 0; i < N; i++) {
				if(picked[i]) arrA[a++] = i;
				else arrB[b++] = i;
			}
			
			for (int i = 0; i<target; i++) {
				for (int j = i+1; j < target; j++) {
					sumA += arr[arrA[i]][arrA[j]];
					sumA += arr[arrA[j]][arrA[i]];
					sumB += arr[arrB[i]][arrB[j]];
					sumB += arr[arrB[j]][arrB[i]];
				}
			}
			
			minDistance = Math.min(minDistance, Math.abs(sumA-sumB));
			
			return;
		}
		
		for (int i = start; i < N; i++) {
		
			picked[i] = true;
			combination(count+1, target, i+1);
			picked[i] = false;
		}
		
	}
}
