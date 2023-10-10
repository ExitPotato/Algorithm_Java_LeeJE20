package 시뮬레이션;

/*
[링크]
https://www.acmicpc.net/problem/17144

[시간]
11:24~13:44

[아이디어]
시뮬레이션! 함수 나눠서 잘 구현하자.

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지_안녕 {
	private static class Point{
		int y;
		int x;

		int value;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public Point(int y, int x, int value) {
			this.y = y;
			this.x = x;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Point{" +
					"y=" + y +
					", x=" + x +
					'}';
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int R, C, T;
		int[][] map;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		Point airFresherTop = null;
		Point airFresherBottom = null;

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1){
					if(airFresherTop==null){
						airFresherTop = new Point(i, j);
					}else{
						airFresherBottom = new Point(i, j);
					}
				}
			}
		}

		boolean isBottom = true;
		List<Point> dusts = new ArrayList<>();
		for (int t = 0; t < T; t++) {
			dusts.clear();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j] != 0 && map[i][j] != -1){
						dusts.add(new Point(i, j, map[i][j]));
					}
				}
			}
			spreadDust(R, C, map, dusts);
			cleanDust(R, C, map, airFresherTop, !isBottom);
			cleanDust(R, C, map, airFresherBottom, isBottom);


//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
		}


		int answer = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					answer += map[i][j];
			}

		}

		System.out.println(answer);


	}

	private static void cleanDust(int r, int c, int[][] map, Point airFresher, boolean isBottom) {
		int[] dy = {-1, 0, 1, 0};
		int[] dx = {0, 1, 0, -1};
		if (isBottom){
			dy[0] = 1;
			dy[2] = -1;
		}
		int directionIndex = 0;


		Point before = airFresher;
		Point now = new Point(before.y+dy[directionIndex], before.x+dx[directionIndex]);
		while (isInRange(now.y, now.x, map) && !isAirFresher(now.y, now.x, map )){
			if (!isAirFresher(before.y, before.x, map )) {
				map[before.y][before.x] = map[now.y][now.x];
			}
			int nextY = now.y+dy[directionIndex];
			int nextX = now.x+dx[directionIndex];
			if (!isInRange(nextY, nextX, map) || !isInAirFresherRange(nextY, nextX, map, airFresher, isBottom)){
				directionIndex++;
				nextY = now.y+dy[directionIndex];
				nextX = now.x+dx[directionIndex];
			}

			before = now;
			now = new Point(nextY, nextX);
		}
		map[before.y][before.x] = 0;
	}

	private static boolean isInAirFresherRange(int nextY, int nextX, int[][] map, Point airFresher, boolean isBottom) {
		if (!isInRange(nextY, nextX, map)) return false;

		if (isBottom){
			return nextY >= airFresher.y;
		}else {
			return nextY <= airFresher.y;
		}

	}

	private static void spreadDust(int r, int c, int[][] map, List<Point> dusts) {
		int[][] newMap = new int[r][c];
		for (Point p: dusts) {
			spreadOneDust(r, c, map, p);
		}
	}

	private static void spreadOneDust(int r, int c, int[][] map, Point p) {
		final int[] dy = {-1, 1, 0, 0};
		final int[] dx = {0, 0, -1, 1};

		int spreadAmount = p.value/5;

		int spreadDirectionCount = 0;

		for (int i = 0; i < 4; i++) {
			int y = p.y + dy[i];
			int x = p.x + dx[i];
			if (isInRange(y, x, map) && !isAirFresher(y, x, map)){
				spreadDirectionCount++;
				map[y][x] += spreadAmount;
			}
		}
		int leftAmount = map[p.y][p.x] - spreadAmount * spreadDirectionCount;
		map[p.y][p.x] = leftAmount;
	}

	private static boolean isAirFresher(int y, int x, int[][] map) {
        return map[y][x] == -1;
    }

	private static boolean isInRange(int y, int x, int[][] map) {
		int r = map.length;
		int c = map[0].length;

        return y >= 0 && y < r && x >= 0 && x < c;
    }
}