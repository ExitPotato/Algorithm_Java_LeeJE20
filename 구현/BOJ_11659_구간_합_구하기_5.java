package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11659_구간_합_구하기_5 {
	static int N, M;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (j == 0) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					continue;
				}
				arr[i][j] = arr[i][j-1] + Integer.parseInt(st.nextToken());
			}
			
		}

		int x1, y1, x2, y2;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken()) - 1; //0
			y1 = Integer.parseInt(st.nextToken()) - 1; //1
			x2 = Integer.parseInt(st.nextToken()) - 1; //0
			y2 = Integer.parseInt(st.nextToken()) - 1; //1
			int sum = 0;
			for (int j = x1; j <= x2; j++) {
				if (y1 == 0) {
					sum += arr[j][y2];
				}
				else {
					sum += (arr[j][y2] - arr[j][y1-1]);
				}
			}
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb);
	}
}
