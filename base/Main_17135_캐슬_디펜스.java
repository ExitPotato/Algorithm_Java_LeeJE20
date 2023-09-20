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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_17135_캐슬_디펜스 {
	static class Point {
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
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N, M;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int distance = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 뽑은 궁수 위치(인덱스)
		List<int[]> archersLocation = new ArrayList<int[]>();
		
		archersLocation = getArchersLocation(M);
		int maxDeadEnemyCount = 0;
		for (int[] archers : archersLocation) {
			int deadEnemyCount = playGame(N, M, distance, arr, archers);
			maxDeadEnemyCount = Math.max(maxDeadEnemyCount, deadEnemyCount);
		}
		
		System.out.println(maxDeadEnemyCount);
	}

	private static int playGame(int n, int m, int distance, int[][] arr, int[] archers) {
		
		// 현재 archer가 있는 row
		int archersRow = n;
		
		int[][] map = copyArr(n, m, arr);
		int deadEnemyCount = 0;
		
		while(isPlayable(n, m, map, archersRow)) {
			deadEnemyCount += playTurn(n, m, distance, archers, archersRow, map);
			archersRow--;
		}
		
		return deadEnemyCount;
		
	}

	private static int playTurn(int n, int m, int distance, int[] archers, int archersRow, int[][] map) {
		Set<Point> enemyTarget = new HashSet<>();
		
		for (int archer : archers) {
			Point target = getTargetEnemy(n, m, distance, archer, archersRow, map);
			if (target != null) {
				enemyTarget.add(target);
			}
			
		}
		
		int enemyCount = enemyTarget.size();
		
		for (Point enemy : enemyTarget) {
			killEnemy(map, enemy);
		}
		return enemyCount;
	}

	private static void killEnemy(int[][] map, Point enemy) {
		map[enemy.y][enemy.x] = 0;
	}

	private static Point getTargetEnemy(int n, int m, int distance, int archer, int archersRow, int[][] map) {
		int[] dy = {0, -1, 0};
		int[] dx = {-1, 0, 1};
		
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		
		int depth = 0;
		visited[archersRow-1][archer] = true;
		q.offer(new Point(archersRow-1, archer));
		
		Point target = null;
		
q:		while (!q.isEmpty()) {
			int size = q.size();
			depth++;
			if (depth > distance) {
				break;
			}
			
			for (int i = 0; i < size; i++) {
				Point current = q.poll();
				if (map[current.y][current.x] == 1) {
					target = current;
					break q;
				}
				
				for (int k = 0; k < dy.length; k++) {
					int y = current.y + dy[k];
					int x = current.x + dx[k];
					
					if (!inRange(y, x, n, m)) continue;
					if (visited[y][x]) continue;
					
					visited[y][x] = true;
					q.offer(new Point(y, x));
				}
			}
			
			
		}
		return target;
	}

	private static boolean inRange(int y, int x, int n, int m) {
		if (y >= 0 && y < n && x >= 0 && x < m) return true;
		return false;
	}

	private static boolean isPlayable(int n, int m, int[][] map, int archersRow) {
		if (archersRow == n) return true;
		if (archersRow <= 0) return false;
		for (int i = 0; i < m; i++) {
			// 적이 도달했으면 적 제거
			// 문제 잘못 읽음
			if(map[archersRow][i] == 1) {
				map[archersRow][i] = 0;
			}
		}
		return true;
	}

	private static int[][] copyArr(int n, int m, int[][] arr) {
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			System.arraycopy(arr[i], 0, map[i], 0, m);
		}
		return map;
	}

	private static List<int[]> getArchersLocation(int m) {
		List<int[]> result = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			for (int j = i+1; j < m; j++) {
				for (int k = j+1; k < m; k++) {
					int[] p = new int[3];
					p[0] = i;
					p[1] = j;
					p[2] = k;
					result.add(p);
				}
				
			}
		}
		
		return result;
	}
}