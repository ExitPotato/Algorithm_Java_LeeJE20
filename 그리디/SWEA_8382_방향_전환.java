
/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWyNQrCahHcDFAVP&

[아이디어]
재귀

dy dx를 2차원 배열로 해두고, 가로세로 지정은 루프 돌때마다 xor해주면 될듯.

그리디

위/아래 중 목적지와 가까운 쪽으로 이동한다.
가로/세로 중 목적지와 가까운 쪽으로 이동한다.

[시간]
21:09 ~ 21:57

[실수]
1. 문제를 대충 봐서 배열 판 안에서 bfs하는 문제인줄 알았다
=> 문제 읽기를 꼼꼼히 하자

2. 중간 좌표에서 목적지까지의 값을 비교해야 하는데, 실수로 start와 비교했다..
=> 변수명을 잘못쓸 때가 종종 있다. 쉬운 문제라도 주석을 먼저 작성하고 코드 쓰기를 습관화하자.


[다른사람코드]
수학적으로 작성한 코드가 가장 빠르다.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;


public class SWEA_8382_방향_전환 {
	static class Point{
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Point{" +
				"y=" + y +
				", x=" + x +
				'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Point point = (Point)o;
			return y == point.y && x == point.x;
		}

		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			Point start = new Point(y1, x1);
			Point end = new Point(y2, x2);

			int answer = 0;
			int minMove = Integer.MAX_VALUE;

			int[][] dy = {{-1, 1}, {0, 0}};
			int[][] dx = {{0, 0}, {-1, 1}};

			int moveIdx = 0;
			int depth = 0;

			int moveCount = solve(moveIdx, depth, start, end, dy, dx );
			minMove = Math.min(minMove,moveCount);
			moveIdx = getNextMove(moveIdx);
			moveCount = solve(moveIdx, depth, start, end, dy, dx );
			minMove = Math.min(minMove,moveCount);

			sb.append(minMove).append("\n");
		}
		System.out.println(sb);
	}

	private static int solve(int moveIdx, int depth, Point start, Point end, int[][] dy, int[][] dx) {
		if(start.equals(end)){
			return depth;
		}

		// distance, y, x
		int[] minDistancePoint = {Integer.MAX_VALUE, 0, 0};
		for (int i = 0; i < dy[moveIdx].length; i++) {
			int y = start.y + dy[moveIdx][i];
			int x = start.x + dx[moveIdx][i];

			int distance = getDistance(end, new Point(y, x));

			if (distance <= minDistancePoint[0]){
				minDistancePoint[0] = distance;
				minDistancePoint[1] = y;
				minDistancePoint[2] = x;
			}
		}

		int tmpDepth = solve(getNextMove(moveIdx), depth+1, new Point(minDistancePoint[1], minDistancePoint[2]), end, dy, dx);

		return tmpDepth;
	}

	private static int getDistance(Point start, Point point) {
		return Math.abs(start.y - point.y) + Math.abs(start.x - point.x);
	}

	private static int getNextMove(int moveIdx) {
		return moveIdx^1;
	}
}
