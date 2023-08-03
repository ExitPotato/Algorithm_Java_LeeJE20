package 재귀.조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650_N과_M_2 {
	static int N, M;
	static int[] picked;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		picked = new int[M];
		combination(0, 1);
		System.out.println(sb);
		
	}
	
	static void combination(int pickedCount, int startNum) {
		if (pickedCount == M) {
			for (int i:picked)
				sb.append(i).append(" ");
			sb.append("\n");
			return;
		}
		
		for (int i = startNum; i <= N; i++) {
			picked[pickedCount] = i;
			combination(pickedCount+1, i+1);
		}
		
	}
}
