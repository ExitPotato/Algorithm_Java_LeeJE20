package base;

/*
[링크]
https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYmp6Fk6f5EDFARi&contestProbId=AWXRQm6qfL0DFAUo&probBoxId=AYr9fL0Kw0gDFAQI+&type=PROBLEM&problemBoxTitle=10%EC%9B%94+5%EC%9D%BC&problemBoxCnt=3

[시간]
14:29~

[아이디어]
완전탐색 백트래킹으로 풀어야겠다.
dfs다 우와~


[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5656_벽돌_깨기 {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "1\n" +
				"1 8 9\n" +
				"0 0 0 0 0 0 0 0 \n" +
				"0 0 0 1 0 0 0 0\n" +
				"0 0 0 1 0 0 0 0\n" +
				"0 0 0 1 0 0 0 0\n" +
				"0 1 0 1 0 0 0 0\n" +
				"0 1 0 1 0 0 0 1\n" +
				"4 1 0 9 1 1 0 1\n" +
				"1 1 1 1 1 1 1 1\n" +
				"1 1 1 1 1 1 1 1";
		BufferedReader br = new BufferedReader(new StringReader(input));
		StringTokenizer st;
		int T, N, W, H;
		int[][] map;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <	W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// solve

			int minBrickCount = Integer.MAX_VALUE;

			minBrickCount = backTracking(N, W, H, map);
		}
		System.out.println(sb);
	}

	private static int backTracking(int N, int W, int H, int[][] map) {
		if (N <= 0){
			return countLeftBricks(W, H, map);
		}
		int result = 0;
		int[][] copyMap = new int[H][W];

		for (int i = 0; i < W; i++) {

			copyArray(map, copyMap);

			shoot(i, N, W, H, copyMap);
			int tmp = backTracking(N-1, W, H, copyMap);
			result = Math.min(result, tmp);
		}

		return result;
	}

	private static void copyArray(int[][] from, int[][] to) {
		int h = from.length;
		int w = from[0].length;
		for (int j = 0; j < h; j++) {
			System.arraycopy(from[j], 0, to[j], 0, w);
		}
	}

	private static void shoot(int column, int n, int w, int h, int[][] map) {

		int row = -1;
		for (int r = 0; r < h; r++) {
			if (map[r][column]!=0){
				row = r;
				break;
			}
		}

		// column에 벽돌이 없는 경우
		if (row == -1){
			return;
		}

		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		int[][] removedMap = new int[h][w];
		copyArray(map, removedMap);

		System.out.println("\n\nshoot: "+row+", "+column);
		// 벽돌 없애기
		checkBricksToBeRemoved(map, removedMap, row, column, h, w, dy, dx);

		// 벽돌 아래로 떨어뜨리기
		dropBricks(removedMap, h, w);

	}

	private static void dropBricks(int[][] map, int h, int w) {
		for (int i = h-1; i >= 0; i--) {
			int dropCount = 0;
			for (int j = 0; j < w; j++) {

			}

		}
	}

	private static void checkBricksToBeRemoved(int[][] map, int[][] removedMap, int row, int column, int h, int w, int[] dy, int[] dx) {
		int removeRange = map[row][column] - 1;
		int y = row;
		int x = column;
		removedMap[y][x] = 0;

		System.out.println();
		for (int i = 0; i < h; i++) {
			System.out.println(Arrays.toString(removedMap[i]));

		}

		if (removeRange <= 0) return;


		for (int k = 0; k < 4; k++) {
			// 실수: y, x 초기화를 안 했다.
			y = row;
			x = column;
			for (int i = 0; i < removeRange; i++) {
				y += dy[k];
				x += dx[k];
				if (!isInRange(y, x, removedMap)) continue;
				if (removedMap[y][x] == 0) continue;
				checkBricksToBeRemoved(map, removedMap, y, x, h, w, dy, dx);
			}
		}
	}

	private static boolean isInRange(int y, int x, int[][] map) {
		int h = map.length;
		int w = map[0].length;
		if (y >= 0 && y < h && x >= 0 && x < w){
			return true;
		}
		return false;
	}

	private static int countLeftBricks(int w, int h, int[][] map) {
		int result = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(map[i][j] == 0) continue;
				result++;
			}
		}
		return result;
	}
}
