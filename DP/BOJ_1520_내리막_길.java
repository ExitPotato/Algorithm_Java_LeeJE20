package DP;

/*
[링크]
https://www.acmicpc.net/problem/1520

[시간]
11:45~15:11

[아이디어]
dp: 첫 번째 구역에서 해당 구역까지 올 수 있는 경우의 수

solve(y, x){
	if (처음 좌표)
		return 1;
	
	
	tmp = solve(사방 좌표) 합
	
	return tmp;
}

dp문제 풀때 어려우면 재귀로 먼저 풀이해보고, 나중에 dp를 덧붙이는 식으로 풀이해보자.
[시간복잡도]


[실수]


[검색]
https://yabmoons.tistory.com/340
풀이 모르겠어서 검색해봤다.

DP[a][b] = (a, b)에서는 (N - 1, M - 1) 까지 c개의 경로로 도달할 수 있다


리턴값: x, y에서 목적지까지 n개의 경로로 도달 가능하다.
int DFS(int x, int y)
{
    if (x == N - 1 && y == M - 1) return 1;
    if (DP[x][y] != -1) return DP[x][y];
 
    DP[x][y] = 0;
	
	// 사방탐색~~
    	DP[x][y] = DP[x][y] + DFS(nx, ny);
    
    return DP[x][y];
}


[다른 사람 코드]
solve에서 retun 1 부분을 그냥 dp[0][0]은 1로 설정하고 시작해도 된다.

*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1520_내리막_길 {
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N, M;
		int[][] arr;
		

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][M];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		int answer = solve(N-1, M-1, arr);
		System.out.println(answer);
	}

	// 첫 번째 구역에서 해당 구역(i, j)까지 올 수 있는 경우의 수
	private static int solve(int i, int j, int[][] arr) {

		if (i==0 && j == 0) {
			return 1;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		int n = arr.length;
		int m = arr[0].length;
		
		
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		
		int tmp = 0;
		for (int k = 0; k < dx.length; k++) {
			int y = i+dy[k];
			int x = j+dx[k];
			if (y < 0 || y >= n || x < 0 || x >= m) continue;
			if (arr[i][j] >= arr[y][x]) continue;
			tmp += solve(y, x, arr);
		}
		dp[i][j] = tmp;
		return tmp;
	}
}