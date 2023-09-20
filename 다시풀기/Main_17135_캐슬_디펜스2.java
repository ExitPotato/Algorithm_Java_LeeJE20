package 다시풀기;
/*
[링크]
https://www.acmicpc.net/problem/17135

[시간]
16:17~

[아이디어]
궁수 3명 자리 3중루프로 구한다.

각 궁수 자리마다 함수 돌린다.


궁수 0, 1, 2
각 턴마다 궁수 너비우선 탐색 -> 적이 있으면 멈춤

세번의 너비우선 탐색 끝나면 적 지우기

적 위치 한칸 전진
성에 도착하면 게임 끝


[시간복잡도]
15c3 = 455
120
(3375 + 120 ) 455 = 159만

가능
[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import com.sun.org.apache.xpath.internal.operations.Equals;

public class Main_17135_캐슬_디펜스2 {
	
	private static class Position{
		int row;
		int col;
		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public boolean equals(Position p) {
			if (this.row == p.row && this.col == p.col) return true;
			return false;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Position [row=");
			builder.append(row);
			builder.append(", col=");
			builder.append(col);
			builder.append("]");
			return builder.toString();
		}
		
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N, M, D;
		int[][] map;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		
		// 궁수 자리 경우의 수
		int[] archers = new int[3];
		for (int i = 0; i < M; i++) {
			archers[0] = i;
			for (int j = i+1; j < M; j++) {
				archers[1] = j;
				for (int k = j+1; k < M; k++) {
					archers[2] = k;
					System.out.println(Arrays.toString(archers));
					int tmp = game(N, M, D, map, archers);
					answer = Math.max(answer, tmp);
				}
			}
		}
		
		System.out.println(answer);
	}

	private static int game(int n, int m, int d, int[][] map, int[] archers) {
		int[][] world = new int[n][m];
		for (int i = 0; i < n; i++) {
			 System.arraycopy(map[i], 0, world[i], 0, m);
//			 System.out.println(Arrays.toString(world[i]));
		}
		
		//	각 턴마다 궁수 너비우선 탐색 -> 적이 있으면 멈춤
		
		boolean gameEnd = false;
		int deadEnemiesCount = 0; 
		List<Position> enemies= new ArrayList<>();
		
		while(!gameEnd) {
			enemies.clear();
			
			for (int i = 0; i < archers.length; i++) {
				
				Position enemy = bfs(world, d, archers[i], n);
//				System.out.println(i+": "+enemy);
				boolean hasSameEnemy = false;
				for (int j = 0; j < enemies.size(); j++) {
//					System.out.println(enemies.get(j));
					if(enemy != null && enemies.get(j).equals(enemy)) {
						hasSameEnemy = true;
						break;
					}
				}
				if (enemy != null) enemies.add(enemy);
	
			}
			
			// 죽은 적 수
			deadEnemiesCount += enemies.size();
			
			System.out.println("적 처리 전");
			for(int i = 0; i < world.length; i++) {
				System.out.println(Arrays.toString(world[i]));
			}
			
			// 적 처리
			for (Position p : enemies) {
				System.out.println("적 위치 "+ p.row+" : "+p.col);
				world[p.row][p.col] = 0;
			}
			
			System.out.println("적 처리 후");
			for(int i = 0; i < world.length; i++) {
				System.out.println(Arrays.toString(world[i]));
			}
		}
		return deadEnemiesCount;
	}

	
	private static Position bfs(int[][] world, int d, int archer, int n, int m) {
		

		
		return null;
	}
}
