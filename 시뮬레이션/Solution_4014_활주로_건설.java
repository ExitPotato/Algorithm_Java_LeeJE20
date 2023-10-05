package 시뮬레이션;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH

[시간]
11:38~13:03

[아이디어]
완탐으로 구현하자!
한줄씩 체크하면서 카운팅 해야겠다.
높이가 낮아졌을때, 낮은 높이가 몇 칸씩 나오는지 카운팅해야한다.
높이가 높아지는 것을 체크하기 위해 현재 높이가 몇 칸 지속되는지 카운팅 한다.
이미 경사로로 쓰인 길이는 체크하지 않는다.

[시간복잡도]
이중 for문 2번씩 돌면서 체크 -> O(n^2)
n이 최대 20임
가능!

[실수]
높이가 바뀌었을때 지금까지 높이의 개수를 1로 해야하는데 0으로 했다.

[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로_건설 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T, N, X;
		int[][] map;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {

			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <	N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// solve
			int answer = 0;
			// 가로 방향
			answer += solve(N, X, map, false);
			// 세로 방향
			answer += solve(N, X, map, true);

			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static int solve(int N, int X, int[][] map, boolean isColumn){
		int answer = 0;
		for (int i = 0; i < N; i++) {
			// 첫번째 높이
			int beforeHeight = map[i][0];
			if (isColumn) {
				beforeHeight = map[0][i];
			}
			int count = 0;
			boolean isSuccess = true;

			for (int j = 0; j < N; j++) {
				int currentHeight = map[i][j];
				if (isColumn) {
					currentHeight = map[j][i];
				}

				// 높이 값이 그대로라면
				if (currentHeight == beforeHeight){
					count++;
					continue;
				}

				// 높이 값이 바뀌었다면

				// 높이 변화값이 2 이상이면 fail
				if (Math.abs(currentHeight - beforeHeight) >= 2) {
					isSuccess = false;
					break;
				}

				// 이미 높이가 낮아졌는데 경사로를 놓지 못하는 경우
				if (count < 0) {
					isSuccess = false;
					break;
				}

				// 높이가 낮아졌다면 다음 높이의 수를 세어야 함
				if (currentHeight < beforeHeight){
					count = -X+1; // 지금칸이 한 칸이니까 +1 해야함
					beforeHeight = currentHeight;
					continue;
				}

				// 높이가 높아졌다면 지금까지 높이의 수를 줄여야 함
				if (currentHeight > beforeHeight) {
					if (count - X < 0) {
						isSuccess = false;
						break;
					}
					count = 1; // 지금칸이 한 칸이니까 1로 해야함
					beforeHeight = currentHeight;

				}
			}
			// 높이가 낮아졌는데 경사로를 놓지 못하는 경우
			if (count < 0) {
				isSuccess = false;
			}

			if(isSuccess) {
				answer++;
			}
		}
		return  answer;
	}
}
