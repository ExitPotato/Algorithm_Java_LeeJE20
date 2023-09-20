package swtest;
/*
 �׷���
 
 �ҹ��� 7����
 
 cell�� �׷����� ��Ÿ����
 cell�� 4�� �̱�
 4���� �������� Ȯ�� (bfs)
 ���� ���
 
 [�ð����⵵]
 15C4 = 1365
 BFS
 ���Ͱ�� => ����
 4000^2 = 16000000 
 int ����:  2147483647
 
 int ����
 
 
 
 
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
					
					
					// ��
					int tmp = getSpecial_1(i, j);
					maxBenefit = Math.max(maxBenefit, tmp);
					// ��
					tmp = getSpecial_2(i, j);
					maxBenefit = Math.max(maxBenefit, tmp);
					
				}
				
			}
			
				
			
			// ��� ���� ���
			sb.append((int)Math.pow(maxBenefit, 2)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static Integer getIndex(int r, int c) {
		// key ��� = Integer: Point.r*h + Point.c
		return  r*w + c;
	}
	
	private static Point getPoint(int i, int w) {
		// r = i�� w�� ���� ��
		// c = i�� w�� ���� ������
				
		return new Point(i/w, i%w);
	}

	private static void dfs(int i, int j, boolean[] visited, int visitedIdx, int visitedBenefit) {
		
//		// ����ġ��: visitedBenefit�� max���� ũ�ٸ� return
//		if (visitedBenefit>=maxBenefit) return;
		// ����: 4ȸ �湮�ߴٸ� max ���
		
		
		
		
		
		
//		int nowBenefit = visitedBenefit + cells[i][j];
		
		if (visitedIdx == 3) {
			
			maxBenefit = Math.max(maxBenefit, visitedBenefit);
//			System.out.println(Arrays.toString(visitedCell));
//			System.out.println(Arrays.toString(visitedScore));
			return;
		}
		
		// dy, dx �迭
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
		
		// dy ���̵���
		for (int k = 0; k < dx.length; k++) {
			// �� ��ǥ ���ϱ�
			int r = i + dy[k];
			int c = j + dx[k];
			// �� ��ǥ�� �迭 �ȿ� �ִٸ�
			if (r>= 0 && r < h && c >= 0 && c < w) {
				// visited üũ
				if (visited[getIndex(r, c)]) continue;
				
				visited[getIndex(r, c)] = true;
				visitedCell[visitedIdx+1] = getIndex(r, c);
				visitedScore[visitedIdx+1] = cells[r][c];
				// ���
				dfs(r, c, visited, visitedIdx + 1, visitedBenefit + cells[r][c]);
				visited[getIndex(r, c)] = false;
				visitedCell[visitedIdx+1] = -1;
				visitedScore[visitedIdx+1] = -1;
				
			}
				
		}

		
		
	}
	
	// �� ���
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
			// �� ��ǥ ���ϱ�
			int r = i + dy[k];
			int c = j + dx[k];
			// �ۿ� �����
			if (!(r>= 0 && r < h && c >= 0 && c < w)) return 0;
			
			tmpSum+= cells[r][c];
		}
		return tmpSum;
	}
	

	// �� ���
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
			// �� ��ǥ ���ϱ�
			int r = i + dy[k];
			int c = j + dx[k];
			// �ۿ� �����
			if (!(r>= 0 && r < h && c >= 0 && c < w)) return 0;
			
			tmpSum+= cells[r][c];
		}
		return tmpSum;
	}
}
