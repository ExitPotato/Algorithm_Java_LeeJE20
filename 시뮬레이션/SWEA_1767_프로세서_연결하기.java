package 시뮬레이션;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf

[시간]


[아이디어]
백트래킹 

[시간복잡도]


[실수]
코어가 연결되지 않는 경우를 헤아리지 않았다.
=> 백트래킹 문제 풀 때  아무것도 안 하고 다음으로 넘어가는 경우도 생각한다.

[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서_연결하기 {
	
	static class Point{
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point{" +
				"r=" + r +
				", c=" + c +
				'}';
		}
	}

	
	static int T, N, M;
	static int[][] map;
	static int maxConnectedCores;
	static int minLineSum;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			maxConnectedCores = 0;
			minLineSum = Integer.MAX_VALUE;
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			
			List<Point> cores= new ArrayList<>();
			int visitedCores = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 코어 입력 받기
					if (map[i][j] == 1){
						// 만약 코어가 이미 전원 흐르고 있다면 연결된 코어 개수 증가
						if(i == 0 || i == N-1 || j == 0 || j == N-1) {
							visitedCores++;
						}else {
							// 아니라면 확인할 코어 목록에 넣기
							cores.add(new Point(i, j));
						}
					}
				}
			}
			
			// solve
			int startIdx = 0;
			int lineSum = 0;
			int connectedCores = 0;
			backTracking(cores, startIdx, lineSum, connectedCores, map);
			
			sb.append(minLineSum).append("\n");

		}
		System.out.println(sb);
		
	}
	private static void backTracking(List<Point> cores, int coreIdx, int lineSum, int connectedCores, int[][] map) {
		if (coreIdx == cores.size()) {
			if (maxConnectedCores < connectedCores) {
				maxConnectedCores = connectedCores;
				minLineSum = lineSum;
			}else if (maxConnectedCores == connectedCores) {
				minLineSum = Math.min(minLineSum, lineSum);
			}
			return;
		}
		
		
		// idx 번째 연결되지 않은 코어에 대해
		Point current = cores.get(coreIdx);
		// 미연결 후 재귀 
		backTracking(cores, coreIdx+1, lineSum, connectedCores, map);
		
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		// 상, 하, 좌, 우 연결 가능한지 확인하기
		for (int k = 0; k < dx.length; k++) {
			// 새 좌표가 범위 안이면 && 좌표값이 0이면 좌표 계속 이동
			int y = current.r + dy[k];
			int x = current.c + dx[k];
			
			boolean canConnect = true;
			while (y>= 0 && y < N && x >= 0 && x <N) {
				if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 3) canConnect = false;
				y += dy[k];
				x += dx[k];
			}
			
			if (!canConnect) continue;
			
			// 연결 가능하면 연결 표시
			y = current.r + dy[k];
			x = current.c + dx[k];
			
			int[][] copyMap = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, N);
				
			}
			copyMap[current.r][current.c] = 2;
			int addedLine = 0;
			while (y>= 0 && y < N && x >= 0 && x <N) {
				copyMap[y][x] = 3;
				addedLine++;
				y += dy[k];
				x += dx[k];
			}			
			if(addedLine > 0) {
				backTracking(cores, coreIdx+1, lineSum+addedLine, connectedCores+1, copyMap);
			}
		}
	}
}
