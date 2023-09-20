package 다시풀기;

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
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600_말이_되고픈_원숭이 {
	
	static class Point{
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Point [y=");
			builder.append(y);
			builder.append(", x=");
			builder.append(x);
			builder.append("]");
			return builder.toString();
		}
		
		
	}
	
	public static void main(String[] args) throws Exception{
//		String input = "4\r\n" + 
//				"3 2\r\n" + 
//				"0 0 0\n"+
//				"0 0 0" ;;
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new StringReader(input));
		StringTokenizer st;
		

		int K, W, H;
		int[][] map;
		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		int[][][] dp = new int[K+1][H][W];
		
		
		final int INF =  Integer.MAX_VALUE-1;
//		final int INF =  9;
		for (int k = 0; k < K+1; k++) {
			for (int i = 0; i < H; i++) {
				Arrays.fill(dp[k][i], INF);
			}
		}
		// dp[k] = 지금까지 최대 k번 (즉, k번 이하로) 말 동작을 했을때 해당 좌표에 올 수 있는 최솟값
		dp[0][0][0] = 0;
		
		
		
		int[] mdy = {-1, 1, 0, 0};
		int[] mdx = {0, 0, -1, 1};
		int[] hdy = {-2, -1, 1, 2, 2, 1, -1, -2};
		int[] hdx = {1, 2, 2, 1, -1, -2, -2, -1};

//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) {
//				if (map[i][j] == 1) continue;
//				for (int s = 0; s < mdx.length; s++) {
//					
//					int y = i + mdy[s];
//					int x = j + mdx[s];
//					if (isInRange(y, x, map) && map[y][x]!=1) {
//						dp[0][i][j] = Math.min(dp[0][i][j], dp[0][y][x]+1);
//					}
//				}
//			}
//		}
		
		bfs(new Point(0, 0), map, 0, dp, mdy, mdx);
		
		for (int k = 1; k <= K; k++) {
//			System.out.println("k = "+k);
			for (int i = 0; i <H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] == 1) continue;
					if (i==0 && j==0) {
						dp[k][i][j] = 0;
					}
					if (k >= 2) {
						dp[k][i][j] = Math.min(dp[k][i][j], dp[k-2][i][j]);
					}
					for (int m = 0; m < mdx.length; m++) {
						int y = i + mdy[m];
						int x = j + mdx[m];
						if (isInRange(y, x, map) && map[y][x]!=1) {
							dp[k][i][j] = Math.min(dp[k][i][j], dp[k-1][y][x]+1);
							
						}
					}
					
					for (int h = 0; h < hdx.length; h++) {
						int y = i + hdy[h];
						int x = j + hdx[h];
						if (isInRange(y, x, map) && map[y][x]!=1) {
							boolean isNewArea = false;
							if (dp[k][i][j] == INF) isNewArea = true;
							
							int tmp = dp[k][i][j];
							dp[k][i][j] = Math.min(dp[k][i][j], dp[k-1][y][x]+1);
							
							if (dp[k][i][j] != tmp) {
								// 말로 와서 새로운 구역에 오면 원숭이 다시 확인
								bfs(new Point(i, j), map, k, dp, mdy, mdx);
							}
						}
					}
				}
			}
		}
		
		System.out.println(dp[K][H-1][W-1]==INF ? "-1":dp[K][H-1][W-1]);
	}
	
	private static boolean isInRange(int y, int x, int[][] map) {
		int h = map.length;
		int w = map[0].length;
		
		if (y >= 0 && y < h && x >= 0 && x < w) return true;
		return false;
	}
	
	// 원숭이로 가는 길 확인
	private static void bfs(Point start, int[][] map, int k, int[][][] dp, int[] dy, int[] dx ) {
		int h = map.length;
		int w = map[0].length;
		final int INF = Integer.MAX_VALUE -1;
		
		boolean[][] visited = new boolean[h][w];
		
		int before = k == 0? 0 : k-1;
		Queue<Point> q = new ArrayDeque<Point>();
		visited[start.y][start.x] = true;
		q.offer(start);
		
		int depth = dp[k][start.y][start.x] == INF? -1 : -1 + dp[k][start.y][start.x];
		while (!q.isEmpty()) {
			int size = q.size();
			depth++;
			
			for (int ss = 0; ss < size; ss++) {
				Point current = q.poll();
				dp[k][current.y][current.x] = Math.min(dp[k][current.y][current.x], depth);
				
				for (int s = 0; s < dy.length; s++) {
					int y = current.y + dy[s];
					int x = current.x + dx[s];
					
					if (isInRange(y, x, map) && !visited[y][x] && map[y][x] != 1) {
						
						visited[y][x] = true;
						q.offer(new Point(y, x));
					}
				}
			}
		}
	
	}
}
