package 그래프.다익스트라;

/*
[링크]
https://www.acmicpc.net/problem/9205

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
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_92055_맥주_마시면서_걸어가기 {
	private static int MIN_LOCATION = -32768;
	private static int MAX_LOCATION = 32767;

	private static int STEP = 50;
	private static class Point{
		int y;
		int x;

		int index;
		int weight;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Point{" +
					"y=" + y +
					", x=" + x +
					", weight=" + weight +
					'}';
		}
	}

	private static class Node implements Comparable<Node>{
		int index;
		int weight;

		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Node{" +
					"index=" + index +
					", weight=" + weight +
					'}';
		}
	}
	public static void main(String[] args) throws Exception{
//
//		String input = "2\n" +
//				"2\n" +
//				"0 0\n" +
//				"1000 0\n" +
//				"1000 1000\n" +
//				"2000 1000\n" +
//				"2\n" +
//				"0 0\n" +
//				"1000 0\n" +
//				"2000 1000\n" +
//				"2000 2000";

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new StringReader(input));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T, N;
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Point home = new Point (y, x);



			Point[] targets = new Point[N+2];
			targets[0] = home;

			for (int i = 2; i < N+2; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				targets[i] = new Point(y, x);
			}

			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			Point festival = new Point (y, x);

			targets[1] = festival;

			// 그래프 구축
			int[][] graph = new int[N+2][N+2];
			int beers = 20;
			for (int i = 0; i < N+2; i++) {
				for (int j = i+1; j < N+2; j++) {
					int distance = getDistance(targets[i], targets[j]);
					if (distance <= beers * STEP){
						graph[i][j] = graph[j][i] = distance;
					}else{
						graph[i][j] = graph[j][i] = Integer.MAX_VALUE;
					}

				}
			}



			// 다익스트라
			int minVisit[] = new int[N+2];
			boolean[] visited = new boolean[N+2];
			Arrays.fill(minVisit, Integer.MAX_VALUE);

			PriorityQueue<Node> pq = new PriorityQueue<>();
			minVisit[0] = 0;

			pq.offer(new Node(0,0));

			boolean success = false;
			while (!pq.isEmpty()){
//				System.out.println(Arrays.toString(minVisit));
				Node current = pq.poll();
//				System.out.println(current);
				// 페스티벌
				if (current.index == 1){
					success = true;
					break;
				}

				// 인접 지역 이동
				for (int i = 1; i < N+2; i++) {
					if (visited[i]) {
						continue;
					}
					if (graph[current.index][i] > beers * STEP) {
						continue;
					}
					visited[i] = true;
					minVisit[i] = Math.min(minVisit[i], minVisit[current.index] + graph[current.index][i]);
					pq.add(new Node(i, minVisit[i]));
				}
			}

			if (success) {
				sb.append("happy").append("\n");
			} else {
				sb.append("sad").append("\n");
			}

		}
		System.out.println(sb);
	}

	private static int getDistance(Point a, Point b){
		return Math.abs(a.y-b.y)+Math.abs(a.x-b.x);
	}
}