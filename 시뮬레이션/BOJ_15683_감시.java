package 시뮬레이션;
/*
[링크]
https://www.acmicpc.net/problem/15683

[시간]
14:30~

[아이디어]
완점탐색. 시뮬레이션?!
중복조합으로 모든 경우를 계산해보자.


[시간복잡도]
8개의 CCTV 4방향 경우의 수: 4^8 = 65536
시간복잡도 안 걸린다.

백트래킹같은 문제다.


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	// cctv번호, 방향 경우의수, CCTV가 보고있는 방향들
	static int[][][] direction = {
			{{}},
			{{0}, {1}, {2}, {3}},
			{{0, 1}, {2, 3}},
			{{0, 2}, {0, 3}, {1, 2}, {1, 3}},
			{{1, 2, 3}, {0, 2, 3}, {0, 1, 3}, {0, 1, 2}},
			{{0, 1, 2, 3}}
	};
	
	// cctv 번호에 따른 방향 경우의 수
	static int[] directionCount = {0, 4, 2, 4, 4, 1};
	
	static int answer;
	
	//cctv번호, y좌표, x좌표
	static List<int[]> cctvs = new ArrayList<int[]>();
					
	static int maxMarkedCount = 0;
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
		
		
		int markedCount = 0;
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 6) {
					markedCount++;
				}
				
				if (arr[i][j] != 0 && arr[i][j] != 6) {
					cctvs.add(new int[] {arr[i][j], i, j});
					markedCount++;
				}
			}
		}
		
		solve(arr, 0, cctvs.size(), markedCount);
		
		int answer = N*M - maxMarkedCount;
		System.out.println(answer);
	}
	
	private static void solve(int[][] arr, int cctvIdx, int cctvSize, int markedCount) {
		if (cctvIdx == cctvSize) {
			maxMarkedCount = Math.max(markedCount, maxMarkedCount);
			return;
		}
		
//		int[] viewPerDirection = new int[4];
		int cctvNo = cctvs.get(cctvIdx)[0];
		int y = cctvs.get(cctvIdx)[1];
		int x = cctvs.get(cctvIdx)[2];
		
		int n = arr.length;
		int m = arr[0].length;
		int[][] world = new int[n][m];
		
		// 방향 경우의 수
		int d = directionCount[cctvNo];
		
		for (int r = 0; r < d; r++) { // 경우의 수 만큼
			int [] comb = direction[cctvNo][r]; // 체크할 방향들
			
			// array copy해서 백트래킹에 용이하게
			for (int ii = 0; ii < n; ii++) { 
				System.arraycopy(arr[ii], 0, world[ii], 0, m);
			}
			
			// 체크한 지역 수
			int tmp = 0;
			
			
			for (int c = 0; c < comb.length; c++) { // 방향에 따른 월드 체크
				int k = comb[c];
				int i = dy[k] + y;
				int j = dx[k] + x;
				
				while (i>= 0 && i <n && j >= 0 && j < m) {
					// 이미 체크했다면
					if(world[i][j] == 8) {
						// 다음 준비
						i += dy[k];
						j += dx[k];
						continue;
					}
					// 다른 CCTV라면
					if (world[i][j] == 1 ||
						world[i][j] == 2 ||
						world[i][j] == 3 ||
						world[i][j] == 4 ||
						world[i][j] == 5 ) {
						// 다음 준비
						i += dy[k];
						j += dx[k];
						continue;
					}
					
					// 진행불가
					if ( world[i][j] == 6) break;
					
					// 방문 체크
					tmp++;
					world[i][j] = 8;
					// 다음 준비
					i += dy[k];
					j += dx[k];
				}

			}
			
			// comb 간 것 출력
//			for(int l = 0; l < n; l++) {
//				System.out.println(Arrays.toString(world[l]));
//			}
//			System.out.println(markedCount+tmp);
//			System.out.println();
			
			solve(world, cctvIdx+1, cctvSize, markedCount+tmp);	
		}
	}
}
