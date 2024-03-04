package DP;

/**
 https://www.acmicpc.net/problem/15989

 dp[n][m] = 1~n까지의 숫자를 이용하여 m을 만들 수 있는 경우의 수
 -1	0	1	2	3			4			5
 1   0	1	1	1	1			1			1
 2	0	1	1	2	dp[1][3]+	dp[1][4]	dp[1][5]+
 2없
 dp[2][1]+	dp[2][2]+	dp[2][3]
 2있
 = 2			= 3			= 1+2 = 3
 3		1	1	2	dp[2][3]+	dp[2][4]+	dp[2][5] +
 3없
 dp[3][0]	dp[3][1]	dp[3][2]
 3있
 = 3			= 3+1 = 4	= 3+2 = 5
 **/
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.BufferedReader;

class Main_15989_1_2_3_더하기_4{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[4][100001];
        Arrays.fill(dp[1], 1);
        dp[2][1] = 1;
        dp[2][2] = 2;
        dp[2][3] = 2;
        dp[3][1] = 1;
        dp[3][2] = 2;
        dp[3][3] = 3;

        int filledNum = 3; // 채워진 지점
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            if (n <= filledNum) {
                sb.append(dp[3][n]).append("\n");
                continue;
            }

            for(int i = filledNum+1; i <= n; i++) {
                dp[2][i] = dp[1][i] + dp[2][i-2];
                dp[3][i] = dp[2][i] + dp[3][i-3];
            }
        }
        System.out.println(sb);

    }
}