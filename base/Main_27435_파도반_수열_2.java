package base;

/*
[링크]
https://www.acmicpc.net/problem/27435

[시간]


[아이디어]


[시간복잡도]


[실수]


[검색]
https://colabear754.tistory.com/163

[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_27435_파도반_수열_2 {
	private static long DIVIDE = 998_244_353;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		long t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			long num = Long.parseLong(br.readLine());
			long[][] answer = getPadovan(num);
			sb.append(answer[2][1]).append("\n");
		}
		System.out.println(sb);
	}

	// 분할정복
	private static long[][] getPadovan(long num) {
		if (num == 1){
			return new long[][] {
					{0, 1, 0},
					{0, 0, 1},
					{1, 1, 0}
			};
		}
		long[][] half = getPadovan(num/2);
		long[][] tmp = matmul(half, half);

		if (num % 2 == 0){
			return tmp;
		}
		else{
			return  matmul(tmp, getPadovan(1));
		}
	}

	private static long[][] matmul(long[][] m1, long[][] m2) {
		long[][] result = new long[][] {
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0}
		};

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				long sum = 0;
				for (int k = 0; k < 3; k++) {
					long tmp = (m1[i][k] * m2[k][j]) % DIVIDE;
					sum += tmp;
					sum %= DIVIDE;
				}
				sum %= DIVIDE;
				result[i][j] = sum;
			}
		}
		return result;
	}
}