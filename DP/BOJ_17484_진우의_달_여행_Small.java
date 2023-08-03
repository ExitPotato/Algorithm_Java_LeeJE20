package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;





public class BOJ_17484_진우의_달_여행_Small {
	public static int n;
	public static int m;
	public static int[][] arr;
	public static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		arr = new int[n][m];
		// 마지막 인덱스 3: 기존값왼쪽, 직진, 오른쪽
		dp = new int[n][m][3];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		// dp 초기화
		for (int j = 0; j < m; j++) {
			for (int k = 0; k < 3; k++) {
				dp[0][j][k] = arr[0][j];
			}	
		}
		// solve
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				//                  0    1     2
				// 마지막 인덱스 3: 기존값왼쪽, 직진, 오른쪽 으로 온 경우
				
				// 왼쪽으로 온 경우: 이전값은 직진이나 오른쪽 가능
				dp[i][j][0] = Math.min(getDp(i-1, j, 1), getDp(i-1, j+1, 2));
				// 직진으로온 경우: 
				dp[i][j][1] = Math.min(getDp(i-1, j-1, 0), getDp(i-1, j+1, 2));
				// 오른쪽으로 온 경우
				dp[i][j][2] = Math.min(getDp(i-1, j-1, 0), getDp(i-1, j, 1));
				
				// 현재 위치 값 더해주기
				dp[i][j][0] += arr[i][j];
				dp[i][j][1] += arr[i][j];
				dp[i][j][2] += arr[i][j];
			}		
		}
		
		int answer = Integer.MAX_VALUE;
		for (int j = 0; j < m; j++) {
			for (int k = 0; k < 3; k++ ) {
				answer = Math.min(answer, dp[n-1][j][k]);
			}
		}
		
		System.out.println(answer);
	}
	
	public static int getDp(int i, int j, int k) {
		if (j >= 0 && j < m) {
			return dp[i][j][k];
		}
		else {
			return Integer.MAX_VALUE;
		}
	}
}
