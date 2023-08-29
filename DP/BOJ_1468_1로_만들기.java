package DP;
/*
[링크]
https://www.acmicpc.net/problem/1463
[시간]
14:23~14:29

[아이디어]
dp[n] = n을 만드는 연산을 사용하는 횟수의 최솟값
dp[1] = 0
dp[2] = 1
dp[3] = 1
dp[n] = min(dp[n-1], dp[n/2], dp[n/3]) + 1

[시간복잡도]
O(N)
[실수]

[검색]

[다른 사람 코드]
하향식으로 하면 모든 dp를 채우지 않고도 가능하다.
하향식으로 할 때는 queue를 활용하여 BFS로 한다.
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1468_1로_만들기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N+1];
		if(N >= 2) dp[2] = 1;
		if(N >= 3) dp[3] = 1;
		
		for (int i = 4; i <= N; i++) {
			int min = dp[i-1];
			if (i%2 == 0) min = Math.min(min, dp[i/2]);
			if (i%3 == 0) min = Math.min(min, dp[i/3]);
			dp[i] = min+1;
			
		}
		System.out.println(dp[N]);
	}
}
