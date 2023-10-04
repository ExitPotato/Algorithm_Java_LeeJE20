package base;

/*
[링크]


[시간]


[아이디어]


[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ_14502_연구소 {
	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N, M;
		int[][] arr;
		
		List<Point> virus = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		int count = N*M;

		int leftSpaceCount = count;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2){
					virus.add(new Point(i, j));
				} else if (arr[i][j] == 1) {
					leftSpaceCount--;
				}
			}
		}
		int maxLeftSpaceCount = leftSpaceCount;

		for (int a = 0; a < count; a++) {
			int a_y = a/M;
			int a_x = a%M;
			if (arr[a_y][a_x] != 0) continue;
			for (int b = a+1; b < count; b++) {
				int b_y = b/M;
				int b_x = b%M;
				if (arr[b_y][b_x] != 0) continue;
				for (int c = b+1; c < count; c++) {
					int tmpLeftSpaceCount = leftSpaceCount;
					int c_y = c/M;
					int c_x = c%M;
					if (arr[c_y][c_x] != 0) continue;

					int[] y = {a_y, b_y, c_y};
					int[] x = {a_x, b_y, c_y};
					int[][] copyMap = new int[N][M];

					for (int i = 0; i < N; i++) {
						System.arraycopy(arr[i], 0, copyMap[i], 0, M);
					}

					// dfs
					for (Point p:virus) {
						copyMap[p.y][p.x] = 0;
						leftSpaceCount -= dfs(copyMap, p.y, p.x, N, M, 0);

					}

					maxLeftSpaceCount = Math.max(maxLeftSpaceCount, tmpLeftSpaceCount);
				}
			}
		}

		System.out.println(maxLeftSpaceCount);
		
	}
	private static int dfs(int[][] map, int y, int x, int N, int M, int count){


		// 이미 방문한 곳이면
		if (map[y][x] != 0) return count;
		map[y][x] = 2;
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		int return_count = count;

		for (int i = 0; i < 4; i++) {
			int yy = y+dy[i];
			int xx = x + dx[i];

			if (yy < 0 || yy >= N || xx < 0 || xx >= M) continue;
			if (map[yy][xx] != 0) continue;
			return_count = dfs(map, yy, xx, N, M, count+1);
		}

		return return_count;
	}
}