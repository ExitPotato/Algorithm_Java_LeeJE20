package base;

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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {

	static class Node {
		int y;
		int x;
		int value;

		public Node(int y, int x, int value) {
			this.y = y;
			this.x = x;
			this.value = value;
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
					list.add(new Node(i, j, map[i][j]));
				}
			}
		}



		// 2. 리스트 돌면서(while) map 체크 -> newMap에 새로운 지도 그리고, 땅 값 업데이트. checked에 체크
		// 만약 땅 값이 0이면 리스트 제일 마지막 값을 현재 위치로 가져옴, i++안함, size--
		while (true) {

		}
	}
}