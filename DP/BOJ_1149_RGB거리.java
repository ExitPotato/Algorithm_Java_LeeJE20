package DP;
/*
[링크]
https://www.acmicpc.net/problem/1149

[시간]
14:31~14:38

[아이디어]
dp[n][0] = n번 집을 r로 칠할때 전체 비용의 최솟값

dp[n][i] = min(dp[n-1][(i+1)%3], dp[n-1][ (i+2)%3]) + arr[n][i]


[시간복잡도]
O(n)

[실수]
n 루프를 0부터 돌아서 dp[n-1] 탐색 시 오류 발생
-> dp 문제는 루프 시작 위치 잘 보기

[검색]


[다른 사람 코드]
dp를 안 쓰고 arr에 업데이트 해가면서 할 수도 있다.

*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N;
		int[][] arr;
		N = Integer.parseInt(br.readLine());

		arr = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N][3];
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];
		
		for (int n = 1; n < N; n++) {
			for (int i = 0; i < 3; i++) {
				dp[n][i] = Math.min(dp[n-1][(i+1)%3], dp[n-1][(i+2)%3]) + arr[n][i];
			}
		}
		
		System.out.println((int)Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
	}
}
