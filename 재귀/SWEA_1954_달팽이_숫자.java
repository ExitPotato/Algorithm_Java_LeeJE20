package 재귀;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_1954_달팽이_숫자 {
	static int T;
	static int N;
	static int[][] arr;
	static int[] moveY = {1, 0, -1, 0};
	static int[] moveX = {0, 1, 0, -1};
	
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int test_case =1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			sb.append("#").append(test_case).append("\n");
			solve(N, 0, 0, 1);
		}
		System.out.println(sb);
	}
	
	static void solve(int size, int startX, int startY, int nextNum) {
		if (size == 1) {
			arr[startX][startY] = nextNum;
		}
		if (size <= 1) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					sb.append(arr[i][j]).append(" ");
				}
				sb.append("\n");
			}
			
			return;
		}
		
		int x = startX;
		int y = startY;
		int dx, dy;
		
		arr[y][x] = nextNum++;
		for (int i = 0; i < 4; i++) {
			dx = moveX[i];
			dy = moveY[i];
			
			for (int j = 0; j < size -1; j++) {
				int X = x+dx;
				int Y = y+dy;
				if (X>=0 && X < N && Y >= 0 && Y < N ) {
					if (arr[X][Y] == 0) {
						arr[X][Y] = nextNum++;
						x = X;
						y = Y;
					}
				}
			}

		}
		solve(size-2, startX+1, startY+1, nextNum);
	}
}
