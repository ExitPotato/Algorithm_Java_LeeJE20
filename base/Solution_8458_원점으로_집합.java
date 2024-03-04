package base;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWzaq5KKk_ADFAVU
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

public class Solution_8458_원점으로_집합 {
	static int T, N, M;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
tc:		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			int number = 0;
			for (int i = 0; i < N; i++) {

				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int tmp = Math.abs(x)+Math.abs(y);
				if (i == 0 ) number = tmp;
				else if (tmp % 2 != number %2) {
					sb.append("-1\n");
					continue tc;
				} else {
					number = Math.max(number, tmp);
				}
			}
			// solve

			int i = 1;
			int sum = 0;
			while (sum < number){

				sum += i;
				i++;
			}

			int left = sum - number;
//			System.out.println("number : i = "+number+" : "+i);


			if (sum == number){
				sb.append(i-1).append("\n");
			}
			else if ( left%2 == 0 ){  // 짝수면
				if (i%2 == 0) sb.append(i).append("\n");
				else sb.append(i+1).append("\n");
			}else{ // 홀수면
				if (i%2 == 0) sb.append(i+1).append("\n");
				else sb.append(i).append("\n");

			}
			
		}
		System.out.println(sb);
	}
}
