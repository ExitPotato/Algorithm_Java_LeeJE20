package 시뮬레이션;

/*
[링크]
https://www.acmicpc.net/problem/2636

[시간]
13:44~14:14

[아이디어]
테두리 -> 상하좌우 체크

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

public class BOJ_2636_치즈 {
	public static void main(String[] args) throws Exception{
//		String input = "13 12\n" +
//				"0 0 0 0 0 0 0 0 0 0 0 0\n" +
//				"0 0 0 0 0 0 0 0 0 0 0 0\n" +
//				"0 0 0 0 0 0 0 1 1 0 0 0\n" +
//				"0 1 1 1 0 0 0 1 1 0 0 0\n" +
//				"0 1 1 1 1 1 1 0 0 0 0 0\n" +
//				"0 1 1 1 1 1 0 1 1 0 0 0\n" +
//				"0 1 1 1 1 0 0 1 1 0 0 0\n" +
//				"0 0 1 1 0 0 0 1 1 0 0 0\n" +
//				"0 0 1 1 1 1 1 1 1 0 0 0\n" +
//				"0 0 1 1 1 1 1 1 1 0 0 0\n" +
//				"0 0 1 1 1 1 1 1 1 0 0 0\n" +
//				"0 0 1 1 1 1 1 1 1 0 0 0\n" +
//				"0 0 0 0 0 0 0 0 0 0 0 0";
//		BufferedReader br = new BufferedReader(new StringReader(input));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int  N, M;
		int[][] map;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		int leftCheese = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0){
					leftCheese++;
				}
			}
		}

		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};




		// 치즈 녹이기
		int step = 0;
		int edgeNum = 2; // 가장자리 숫자
		int noCheeseArea = 0; // 현재 단계의 치즈 아닌 구역 표시
		int beforeStepLeftCheesse = leftCheese;
		while (leftCheese > 0){



			beforeStepLeftCheesse = leftCheese;
			step++;
			noCheeseArea--;

			dfs(0, 0, map, noCheeseArea, dy, dx);

			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < M-1; j++) {
					if (map[i][j]==1){
						boolean isEdge = false;
						for (int k = 0; k < 4; k++) {
							int y = i + dy[k];
							int x = j + dx[k];
							if(map[y][x] == noCheeseArea){
								isEdge = true;
								break;
							}
						}
						if(isEdge){
							map[i][j] = edgeNum; // 가장자리 표시
							leftCheese--;
						}
					}

				}
			}


		}
		System.out.println(step);
		System.out.println(beforeStepLeftCheesse);
	}

	private static void dfs(int i, int j, int[][] map, int noCheeseArea, int[] dy, int[] dx) {
		if (map[i][j] == noCheeseArea) return;
		map[i][j] = noCheeseArea;

		int row = map.length;
		int column = map[0].length;
		for (int k = 0; k < 4; k++) {
			int r = i+dy[k];
			int c = j+dx[k];
			if (r >= 0 && r < row && c >= 0 && c < column){
				if (map[r][c] == 1) continue;
				dfs(r, c, map, noCheeseArea, dy, dx);
			}

		}
	}
}