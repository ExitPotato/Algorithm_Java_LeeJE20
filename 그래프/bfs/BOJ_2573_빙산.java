package 그래프.bfs;

/*
[링크]
https://www.acmicpc.net/problem/2573

[시간]
16:12~

[아이디어]
1. 입력받을때 모든 땅을 리스트에 넣는다.
2. 리스트 돌면서(while) map 체크 -> newMap에 새로운 지도 그리고, 땅 값 업데이트. checked에 체크
	만약 땅 값이 0이면 리스트 제일 마지막 값을 현재 위치로 가져옴, i++안함, size--
3. newMap을 map에 넣기 (주소값만 넣어도 될 듯)
4. 리스트 돌면서 (for) map 체크 -> bfs로 덩어리 찾기. checked에 체크
5. 루프를 다 돌았을 떄 덩어리가 2개 이상이면 종료



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
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {

	static class Node {
		int y;
		int x;
		// int value;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
			// this.value = value;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N, M;
		int[][] map;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		List<Node> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					list.add(new Node(i, j));
				}
			}
		}

		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};


		int listSize = list.size();
		int piece = 1; // 빙산 덩어리 개수
		int yearPassed = 0;

		// 2. 리스트 돌면서(while) map 체크 -> newMap에 새로운 지도 그리고, 땅 값 업데이트. checked에 체크
		// 만약 땅 값이 0이면 리스트 제일 마지막 값을 현재 위치로 가져옴, i++안함, size--
		while (listSize > 0 && piece <= 1) {
			yearPassed++;
			boolean[][] checked = new boolean[N][M];
			int[][] newMap = new int[N][M];

			int i = 0;

			// 녹이기
			while(i < listSize) {
				Node n = list.get(i);
				int meltCount = 0;

				// 상하좌우 체크
				for (int k = 0; k < 4; k++) {
					int ny = n.y + dy[k];
					int nx = n.x + dx[k];

					if (isInRange(ny, nx, N, M) && map[ny][nx] == 0) {
						meltCount++;
					}
				}

				int value = Math.max(map[n.y][n.x] - meltCount, 0);
				newMap[n.y][n.x] = value;

				if (value == 0) {
					listSize--;
					list.set(i, list.get(listSize));
				} else {
					i++;
				}
			}

			// map 업데이트
			// for (int j = 0; j < N; j++) {
			// 	map[j] = Arrays.copyOf(newMap[j], M);
			// }
			map = newMap;

			// map 출력
			// System.out.println();
			// for (int j = 0; j < N; j++) {
			// 	System.out.println(Arrays.toString(map[j]));
			// }

			piece = 0;
			// 조각 개수 세기
			for (int j = 0; j < listSize; j++) {
				Node n = list.get(j);
				if (checked[n.y][n.x]) continue;

				piece++;

				Queue<Node> pq = new ArrayDeque<>();
				checked[n.y][n.x] = true;
				pq.add(n);

				while (!pq.isEmpty()) {
					Node now = pq.poll();

					// 상하좌우 연결된 것 찾기
					for (int k = 0; k < 4; k++) {
						int ny = now.y + dy[k];
						int nx = now.x + dx[k];

						if (isInRange(ny, nx, N, M) && map[ny][nx] != 0) {

							if (checked[ny][nx]) continue;
							checked[ny][nx] = true;
							pq.add(new Node(ny, nx));
						}
					}
				}
			}


			// System.out.println("piece: "+piece);
		}


		if (piece <= 1) {
			yearPassed = 0;
		}

		System.out.println(yearPassed);
	}


	public static boolean isInRange(int y, int x, int N, int M) {
		if (y >= 0 && y < N && x >= 0 && x < M) {
			return true;
		}
		return false;
	}
}