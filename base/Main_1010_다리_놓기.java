package base;

/*
[링크]


[시간]


[아이디어]


[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_다리_놓기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[] combinations = new int[m+1];
			
			
			// nCk = n-1Ck-1 + c-1Ck
			for (int i = 0; i < combinations.length; i++) {
				for (int j = i; j >= 0; j--) {
					if (j == i) { 
						combinations[j] = 1;
						continue;
					}
					if (j == 0) { 
						combinations[j] = 1;
						continue;
					}
					combinations[j] = combinations[j]+ combinations[j-1];
				}
			}
			
			sb.append(combinations[n]).append("\n");
		}
		System.out.println(sb);
	}
}