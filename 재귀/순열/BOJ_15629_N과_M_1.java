package 재귀.순열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_15629_N과_M_1 {
	static StringBuilder sb = new StringBuilder();
	static int[] picked = new int[9];
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		solve(n, m, "");
		System.out.println(sb);
		
	}
	
	static void solve(int n, int toPick, String pickedStr) {
		if (toPick == 0) {
			sb.append(pickedStr).append("\n");
			return;
		}
		for (int i = 1; i <= n; i++) {
			if (picked[i] == 0) {
				picked[i] = 1;
				pickedStr += (i + " ");
				solve(n, toPick-1, pickedStr);
				picked[i] = 0;
				pickedStr = pickedStr.substring(0, pickedStr.length()-2);
			}
		}
	}
}
