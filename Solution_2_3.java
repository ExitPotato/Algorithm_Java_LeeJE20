package swtest;
/*
 그래프
 
 소문난 7공주
 
 cell을 그래프로 나타내기
 cell중 4개 뽑기
 4개가 인접한지 확인 (bfs)
 편익 계산
 
 [시간복잡도]
 15C4 = 1365
 BFS
 편익계산 => 가능
 4000^2 = 16000000 
 int 범위:  2147483647
 
 int 가능
 
 
 
 
 0, 0)

 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_2_3 {
	static class Point{
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

	}
	static int maxBenefit;
	static int w;
	static int h;
	static int[][] cells;
	
	static int[] visitedCell = new int[4];
	static int[] visitedScore = new int[4];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			maxBenefit= 0;
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			cells = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					cells[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[] visited = new boolean[w*h];
			
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					visited[getIndex(i, j)] = true;
					visitedCell[0] = getIndex(i, j);
					visitedScore[0] = cells[i][j];
					
					
					dfs(i, j, visited, 0, cells[i][j]);
					
					visited[getIndex(i, j)] = false;
					visitedCell[0] = -1;
					visitedScore[0] = -1;
					
					
					// ㅜ
					int tmp = getSpecial_1(i, j);
					maxBenefit = Math.max(maxBenefit, tmp);
					// ㅗ
					tmp = getSpecial_2(i, j);
					maxBenefit = Math.max(maxBenefit, tmp);
					
				}
				
			}
			
				
			
			// 비용 편익 출력
			sb.append((int)Math.pow(maxBenefit, 2)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static Integer getIndex(int r, int c) {
		// key 계산 = Integer: Point.r*h + Point.c
		return  r*w + c;
	}
	
	private static Point getPoint(int i, int w) {
		// r = i를 w로 나눈 몫
		// c = i를 w로 나눈 나머지
				
		return new Point(i/w, i%w);
	}

	private static void dfs(int i, int j, boolean[] visited, int visitedIdx, int visitedBenefit) {
		
//		// 가지치기: visitedBenefit가 max보다 크다면 return
//		if (visitedBenefit>=maxBenefit) return;
		// 기저: 4회 방문했다면 max 계산
		
		
		
		
		
		
//		int nowBenefit = visitedBenefit + cells[i][j];
		
		if (visitedIdx == 3) {
			
			maxBenefit = Math.max(maxBenefit, visitedBenefit);
//			System.out.println(Arrays.toString(visitedCell));
//			System.out.println(Arrays.toString(visitedScore));
			return;
		}
		
		// dy, dx 배열
		int[] dy = {-1, 0, 1, 0, -1, -1};
		if (j %2 == 1) {
			dy[0] = 0;
			dy[1] = 1;
			dy[2] = 1;
			dy[3] = 1;
			dy[4] = 0;
			dy[5] = -1;
		}
		int[] dx = {1, 1, 0, -1, -1, 0};
		
		// dy 길이동안
		for (int k = 0; k < dx.length; k++) {
			// 새 좌표 구하기
			int r = i + dy[k];
			int c = j + dx[k];
			// 새 좌표가 배열 안에 있다면
			if (r>= 0 && r < h && c >= 0 && c < w) {
				// visited 체크
				if (visited[getIndex(r, c)]) continue;
				
				visited[getIndex(r, c)] = true;
				visitedCell[visitedIdx+1] = getIndex(r, c);
				visitedScore[visitedIdx+1] = cells[r][c];
				// 재귀
				dfs(r, c, visited, visitedIdx + 1, visitedBenefit + cells[r][c]);
				visited[getIndex(r, c)] = false;
				visitedCell[visitedIdx+1] = -1;
				visitedScore[visitedIdx+1] = -1;
				
			}
				
		}

		
		
	}
	
	// ㅜ 모양
	private static int getSpecial_1(int i, int j) {
		int[] dy = {-1, 0, -1};
		if (j %2 == 1) {
			dy[0] = 0;
			dy[1] = 1;
			dy[2] = 0;
		}
		int[] dx = {1, 0, -1};
		
		int tmpSum = cells[i][j];
		for (int k = 0; k < dx.length; k++) {
			// 새 좌표 구하기
			int r = i + dy[k];
			int c = j + dx[k];
			// 밖에 벗어나면
			if (!(r>= 0 && r < h && c >= 0 && c < w)) return 0;
			
			tmpSum+= cells[r][c];
		}
		return tmpSum;
	}
	

	// ㅗ 모양
	private static int getSpecial_2(int i, int j) {
		int[] dy = {0, -1, 0};
		if (j %2 == 1) {
			dy[0] = 1;
			dy[1] = 0;
			dy[2] = 1;
		}
		int[] dx = {1, 0, -1};
		
		int tmpSum = cells[i][j];
		for (int k = 0; k < dx.length; k++) {
			// 새 좌표 구하기
			int r = i + dy[k];
			int c = j + dx[k];
			// 밖에 벗어나면
			if (!(r>= 0 && r < h && c >= 0 && c < w)) return 0;
			
			tmpSum+= cells[r][c];
		}
		return tmpSum;
	}
}
