package base;

/*
[링크]
https://www.acmicpc.net/problem/17472

[시간]
16:05~

[아이디어]
음~ 일단 모든 섬을 연결해야 하니까 최소신장트리로 만들어야 겠군
프림이나 크루스칼 알고리즘을 쓰면 되겠다.

1. 간선 길이를 미리 구해둬서
2. 프림이나 크루스칼 쓴다.

1. 간선 길이 구하기:
	흠... 각 면으로부터 직진하면서 구할 수밖에 없겠다
2. 크루스칼

	섬이 6개니까 간선 개수가 매우 적을 것-> 크루스칼 쓰자
	그래프 구축: 간선 리스트
	2-1. 최초, 모든 간선을 가중치에 따라 오름차순으로 정렬

		→ make set

	2-2. 가중치가 가장 낮은 간선부터 선택하면서 트리 증가
		- 사이클이 존재하면 남아있는 간선 중 그 다음으로 가중치가 낮은 간선 선택
		- 간선 선택 → union
		- 사이클 존재하면
			- 방근 선택된 정점들은 이미 집합에 있음
			- find(정점1), find(정점2)를 하면 같은 대표자가 나올 것임
			- 즉, union 연산을 시도했을때 return false가 됨
	2-3. n-1개의 간선이 선택될 때까지 2를 반복

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17472_다리_만들기_2 {
	public static void main(String[] args) throws Exception{
		String input = "7 8\n" +
				"0 0 0 0 0 0 1 1\n" +
				"1 1 0 0 0 0 1 1\n" +
				"1 1 0 0 0 0 0 0\n" +
				"1 1 0 0 0 1 1 0\n" +
				"0 0 0 0 0 1 1 0\n" +
				"0 0 0 0 0 0 0 0\n" +
				"1 1 1 1 1 1 1 1";

//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new StringReader(input));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N, M;
		int[][] map;


		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};


		int islandCount = 0;

		// 섬 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					islandCount ++;
					dfs(map, i, j, dy, dx, islandCount+1, N, M);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}

		int[][] bridges = new int[islandCount][islandCount];

		for (int i = 0; i < islandCount; i++) {
			Arrays.fill(bridges[i], Integer.MAX_VALUE);
		}

		// 섬 간 최소거리 직선 다리 놓기 -> 그래프 구축
		// 섬 가장자리 돌면서 다리 구하려고 했는데 그냥 for 문 돌면서 찾으면 된다..


		System.out.println("bridges: ");
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(bridges[i]));
		}
	}

	private static void dfs(int[][] map, int i, int j, int[] dy, int[] dx, int visited, int N, int M){
		if (map[i][j] == visited){
			return;
		}

		map[i][j] = visited;
		for (int k = 0; k < 4; k++) {
			int y = i + dy[k];
			int x = j + dx[k];

			if (y < 0 || y >= N || x < 0 || x >= M) continue;
			if (map[y][x] == 1) {
				dfs(map, y, x, dy, dx, visited, N, M);
			}


		}

	}
}