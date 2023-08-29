package 백트래킹;

/*
[링크]


[시간]


[아이디어]
그리디
위에서 시작하면서 위를 향하도록 채워나간다.

[시간복잡도]
O(N*M)

[실수]
백트래킹 결과 찾으면 다음 요소 탐색 안 해야 하는데 여길 빼먹음
=> 백트래킹 구현 시 결과 찾으면 다음 요소 탐색이 더 필요한지 체크할 것

[검색]


[다른 사람 코드]
1. arr에 넣을 때 br.readLine().toCharArray()를 사용했다.
2. dfs에서 boolean을 리턴하여 다음 탐색을 계속할지 조절할 수도 있다.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109_빵집 {
	static char[][] arr;
	static int answer = 0;
	static int N, M;
	static boolean goDfs = true;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		// 첫번째 열에서 빈칸 부분 찾기
		int startRow=-1;
		for (int i = 0; i < N; i++) {
			if (arr[i][0]=='.') {
				startRow = i;
				arr[i][0] = 'x';
				goDfs = true;
				dfs(startRow, 0);
			}
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int r, int c) {
		
		for (int i = -1; i <= 1; i++) {
			if (!goDfs) return;
			int rr = i+r;
			// 범위 체크
			if (rr<0 || rr >=N) continue;
			
			// 건물인지
			if(arr[rr][c+1] == 'x') continue;
			
			arr[rr][c+1] = 'x';
			if (c+1 == M-1) { // 기저
				answer++;
				goDfs = false;
				return;
			}
			dfs(rr, c+1);
			
		}
	}
}
