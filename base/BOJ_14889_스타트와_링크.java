package base;

/*
[링크]
https://www.acmicpc.net/problem/14889

[시간]
16:19 ~

[아이디어]
2CC10 = 184756 -> 1초 내에 가능


1 2 3 4 5 6

1 2 3
1 2 4
1 2 5
1 2 6
1 3 4
1 3 5
1 3 6
1 4 5
1 4 6
1 5 6

-> 10개

15개


1. 입력받으면서 Sij와 Sji를 합친 값으로 배열 만든다
2. 배열에서 앞에서부터 재귀로 뽑는다 -> Permutaion으로 해야함
   - next permutation에서 맨 앞 숫자가 바뀌기 직전까지만 한다.
3. 순열 구하면 잘 구하면 된다...

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와_링크 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N;
		int[][] arr;
		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int length = N/2*(N-1);
		int[] target = new int[length];

		int count = 0;
		for (int i = 0 ; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				target[count++] = arr[i][j] + arr[j][i];
			}
		}

		int[] pick = new int[length];
		for (int i = 0; i < length/2; i++) {
			pick[length-i] = 1;
		}
		int answer = Integer.MAX_VALUE;
		do {
			int[] sum = {0, 0};
			for(int i = 0; i < length; i++) {
				sum[pick[i]] += target[i];
			}
			answer = Math.min(answer, Math.abs(sum[0]-sum[1]));
			if (answer == 0) break;
		} while(nextPermutation(pick));

		System.out.println(answer);
	}

	private static boolean nextPermutation(int[] pick) {
		int size = pick.length;
		int i = size;
		while (i > 0 && pick[i] < pick[i-1])
	}
}