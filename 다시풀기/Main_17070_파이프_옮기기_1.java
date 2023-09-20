package 다시풀기;

/*
[링크]
https://www.acmicpc.net/problem/17070

[시간]
13:30~

[아이디어]
현재 칸에 올 수 있는 방ㅇ법

가로: 왼쪽에서 온다  (왼쪽)
세로: 위쪽에서 온다 (위)
대각: 왼쪽 위 대각에서 온다 (위, 왼쪽이 비어있어야)

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.time.Year;
import java.util.StringTokenizer;

import com.sun.corba.se.impl.presentation.rmi.DynamicAccessPermission;

public class Main_17070_파이프_옮기기_1{
	public static void main(String[] args) throws Exception{
		
		String input = "3\r\n" + 
				"0 0 0\r\n" + 
				"0 0 0\r\n" + 
				"0 0 0";
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new StringReader(input));
		StringTokenizer st;

		int N;
		int[][] arr;
		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		int[] dy = {-1, 0, -1};
		int[] dx = {0, -1, -1};
		
		
		
		
		int[][][] dp = new int[3][N][N];
		dp[0][0][1] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.println(i+" : "+j);
				// k = 0: 가로 상태
				// 
				for (int k = 0; k < 3; k++) {
					// 대각선 확인
					System.out.println("         "+k);
					int y = i + dy[2];
					int x = j + dx[2];
					if (isInRange(y, x, arr) && arr[y][x]!=1) {
						if (k!=2) {
							dp[k][i][j] += dp[k][y][x];
						}else {
							if (isInRange(i-1, j, arr) && arr[i-1][j]!=1 && (isInRange(i, j-1, arr) && arr[i][j-1]!=1)) {
								dp[k][i][j] += dp[k][y][x];
							}
						}
					}
					
					// k != 1 이면 왼쪽에서
					if (k!=1) {
						y = i + dy[1];
						x = j + dx[1];
						if (isInRange(y, x, arr) && arr[y][x]!=1) {
							dp[k][i][j] += dp[k][y][x];
						}
					}
					
					// k != 0 이면 위에어 접근
					if (k!=0) {
						y = i + dy[0];
						x = j + dx[0];
						if (isInRange(y, x, arr) && arr[y][x]!=1) {
							dp[k][i][j] += dp[0][y][x];
						}
					}
				}
			}
		}
		
		int sum = 0;
		for (int l = 0; l < 3; l++) {
			sum += dp[N-1][N-1][l];
		}
		System.out.println(sum);
		
	}

	private static boolean isInRange(int y, int x, int[][] arr) {
		int n = arr.length;
		if (y>= 0 && y < n && x >= 0 && x < n) return true;
		return false;
	}
}