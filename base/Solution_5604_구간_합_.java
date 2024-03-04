package base;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGGNB6cnEDFAUo
[시간]
15:28~

[아이디어]
int배열로 구한다.
int[32]
[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5604_구간_합_ {
	static long T;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			long A = Integer.parseInt(st.nextToken());
			long B = Integer.parseInt(st.nextToken());

			// solve
			int[] aSum;
			int[] bSum;



			aSum = getSum( A);
			bSum = getSum(B);

			int[] result = minusArray(bSum, aSum);

			int sum = 0;
			for (int r: result
				 ) {
				sum += r;
			}
			sb.append(sum).append("\n");
			
		}
		System.out.println(sb);
	}

	private static int[] minusArray(int[] b, int[] a) {
		int length = b.length-1;
		int[] answer = new int[32];
		for (int i = length; i >= 0 ; i--) {
			int result = b[i]*a[i];
			if (result < 0){
				b[i-1]--;
				result += 10;
			}
			answer[i] = result;
		}
		return answer;
	}

	private static int[] getSum(long a) {
		boolean isOdd = true;
		if (a % 2L ==0) isOdd = false;
		
		long even = a;
		long odd = a-1L;
		if (isOdd){
			even = a-1L;
			odd = a;
		}
		
		long half = even / 2;
		
		int[] halfArr = makeArray(half);
		int[] oddArr = makeArray(odd);
		
		return arrayMultiply(halfArr, oddArr);

	}


	private static int[] arrayMultiply(int[] a, int[] b) {
		int[] answer = new int[32];

		int length = a.length-1;
		int next  = 0;
		for (int i = length; i >= 0 ; i--) {
			int result = a[i]*b[i];
			result += next;
			int remainder = result % 10;
			int share = result / 10;
			answer[i] = remainder;
			next = share;
		}

		return answer;
	}

	private static int[] makeArray(long a) {
		int[] result = new int[32];
		
		long left = a;
		final long DEXIMAL = 10;
		int index = result.length-1;
		while (left != 0){
			int remainder = (int) (left % DEXIMAL);
			result[index] = remainder;
			index--;
			left = left / DEXIMAL;
		}
		
		return result;
	}
	
	
}
