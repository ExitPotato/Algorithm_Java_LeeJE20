package 순열;

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
nextPermutaion

[다른 사람 코드]
그냥 재귀가 더 빠름..

*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
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

		int[] pick = new int[N];
		for (int i = 0; i < N/2; i++) {
			pick[N-i-1] = 1;
		}
		int answer = Integer.MAX_VALUE;
		do {
			if (pick[0] == 1) break;
			// System.out.println(Arrays.toString(pick));
			int[] sum = {0, 0};
			int[][] team = new int[2][N/2];
			int[] teamCount = {0, 0};
			for(int i = 0; i < N; i++) {
				int teamIndex = pick[i];
				team[teamIndex][teamCount[teamIndex]++] = i;
			}

			// for (int i = 0; i < 2; i++) {
			// 	System.out.println("team "+i+": "+ Arrays.toString(team[i]) +" sum:" + sum[i]);
			// }
			for (int t = 0; t < 2; t++) {
				for (int i = 0; i < N/2; i++) {
					for (int j = i+1; j < N/2; j++) {
						// member
						int m1 = team[t][i];
						int m2 = team[t][j];
						// System.out.println("조합: "+m1 + " "+m2);
						sum[t] += (arr[m1][m2]+arr[m2][m1]);
					}
				}
			}
			answer = Math.min(answer, Math.abs(sum[0]-sum[1]));
			if (answer == 0) break;
		} while(nextPermutation(pick));

		System.out.println(answer);
	}

	private static boolean nextPermutation(int[] pick) {
		int size = pick.length-1;
		int i = size;
		// 내림차순이 깨지는 부분을 찾는다
		// 내림차순이면 i--
		while (i > 0 && pick[i-1] >= pick[i]) i--;
		if (i == 0) return false;
		// 현재 i는 꼭짓점 부분이다.

		int j = size;
		// i-1 보다 큰 가장 오른쪽 값을 찾는다
		while (pick[i-1] >= pick[j]) j--;

		// 위치 변경
		swap(i-1, j, pick);

		// i 값부터 마지막 값까지 내림차순으로 되어 있음 -> 오름차순으로 변경
		//
		int k = size;
		while (i < k) swap(i++, k--, pick);

		return true;
	}

	private static void swap(int i, int j, int[] pick) {
		int tmp = pick[i];
		pick[i] = pick[j];
		pick[j] = tmp;
	}
}