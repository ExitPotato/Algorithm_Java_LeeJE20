package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11659_구간_합_구하기_4 {
	static int N, M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
	
		int start, last;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			last = Integer.parseInt(st.nextToken());
			
			if (start == 1) {
				sb.append(arr[last-1]).append("\n");
			}
			else {
				sb.append(arr[last-1] - arr[start-2]).append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
