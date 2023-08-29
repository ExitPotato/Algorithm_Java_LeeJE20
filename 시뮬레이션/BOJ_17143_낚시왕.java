package 시뮬레이션;
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
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {
	static class Point{
		int r;
		int c;
		
		
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Point [r=");
			builder.append(r);
			builder.append(", c=");
			builder.append(c);
			builder.append("]");
			return builder.toString();
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
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
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
		
		
	}
	
	static class Shark implements Comparable<Shark>{
		Point point;
		int size;
		int velocity;
		int direction;
		Integer idx;
		
		public Shark(int idx, int r, int c,  int size, int velocity, int direction) {
			this.idx = idx;
			this.point = new Point(r, c);
			this.size = size;
			this.velocity = velocity;
			this.direction = direction;
		}
		
		boolean isBigger(Shark s) {
			if (s == null) return true;
			if (this.size > s.size) return true;
			return false;
		}
		
		@Override
		public String toString() {
			
			return "Sh"+direction+(char)idx.intValue();
		}

		@Override
		public int compareTo(Shark o) {
			if (o == null) return -Integer.compare(this.size, Integer.MAX_VALUE);
			return -Integer.compare(this.size, o.size);
		}
	}

	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	static Shark[][] map;
	static int R, C, M;
	static Map<Integer, Shark> sharks;
	// static List<PriorityQueue<Shark>> sharksToEat;
	static Map<Point, PriorityQueue<Shark>> sharksToMove;
	
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new Shark[R][C];
		

		int r, c, s, d, z;
		sharks = new HashMap<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) -1;
			c = Integer.parseInt(st.nextToken()) -1;
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken()) -1;
			z = Integer.parseInt(st.nextToken());
			
			// 속도 최적화
			if (d ==0 || d == 1) {
				s %= R*2-2;
			}else {
				s %= C*2-2;
			}
			
			Shark shark = new Shark(i+'A', r, c, z, s, d);
			map[r][c] = shark;
			sharks.put(i+'A', shark);
		}
		
		for (int i = 0; i < C; i++) {
			// 상어 잡기
			
//			System.out.println("===================== 초: "+(i+1));
			catchShark(i);
			
			
			// 상어 이동
			sharksToMove = new HashMap<>();
			for (Entry<Integer, Shark> e: sharks.entrySet()) {
				Shark shark = e.getValue();
				moveAllShark(shark, map);
			}
			
			// 상어 먹기
			for (Entry<Point, PriorityQueue<Shark>> e : sharksToMove.entrySet()) {
				Point key = e.getKey();
				PriorityQueue<Shark> sharkPQ = e.getValue();
				
				eatShark(sharkPQ, map, key);
			}
			
			for (int row = 0; row < map.length; row++) {
				System.arraycopy(map[row], 0, map[row], 0, C);
			}
		}
		
//		for (int row = 0; row < map.length; row++) {
//			System.out.println(Arrays.toString(map[row]));
//		}
		
		System.out.println(answer);
	}



	private static void moveAllShark(Shark shark, Shark[][] pool) {
		// 상어 위치 확인
		
		int r=  shark.point.r;
		int c = shark.point.c;
		
		// 상어 위치 삭제
		map[r][c] = null;
		
		// 상어 방향 확인
		int d = shark.direction;
		
		// 더할 변수
		int y = dy[d];
		int x = dx[d];
		
		// 총 속력칸만큼 이동
		for (int i = 0; i < shark.velocity; i++) {
			// 이동할 칸 계산 (한 칸 이동)
			int nr = r + y;
			int nc = c + x;
			
			// 이동할 칸이 범위 밖이면(벽에 닿으면) 
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
				// 방향 전환 (0-1, 2-3)
				if (d == 0) d = 1;
				else if (d == 1) d = 0;
				else if (d == 2) d = 3;
				else if (d == 3) d = 2;
				shark.direction = d;
				// 더할 변수 다시 계산
				y = dy[d];
				x = dx[d];
				
				
				
				// 이동할 칸 다시 계산
				nr = r + y;
				nc = c + x;
			}
			r = nr;
			c = nc;
		}
		
		// 이동할 칸에 넣기
		Point point = new Point(r, c);
		// pq가 있다면
		if (sharksToMove.get(point) != null) {
			sharksToMove.get(point).add(shark);
		}else {
			PriorityQueue<Shark> priorityQueue = new PriorityQueue<>();
			priorityQueue.add(shark);
			sharksToMove.put(point, priorityQueue);
		}
//		System.out.println("h");
	}

	
	private static void eatShark(PriorityQueue<Shark> pQueue, Shark[][] pool, Point point) {
		Shark biggestShark = pQueue.poll();
		locateShark(biggestShark, point, pool);
		
		// 실수: 남은 상어 map에서 처리 안 함
		while(!pQueue.isEmpty()) {
		
			Shark smallShark = pQueue.poll();
//			pool[smallShark.point.r][smallShark.point.c] = null;
			sharks.remove(smallShark.idx);
		}
	}
	
	private static void locateShark(Shark s, Point newPoint, Shark[][] pool) {
		// 기존 위치 null
//		pool[s.point.r][s.point.c] = null;
		
		// 상어 위치 저장
		s.point = newPoint;
		
		// 상어 위치 바꾸기
		pool[newPoint.r][newPoint.c] = s;
	}
	
	private static void catchShark(int column) {
		for (int r = 0; r < R; r++) {
			// 상어가 있다면
			if (map[r][column]!=null) {
				Shark shark = map[r][column];
				answer += shark.size;
				map[r][column] = null;
				sharks.remove(shark.idx);
				return;
			}
		}
	}
}

