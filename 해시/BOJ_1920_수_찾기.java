package 해시;

/*
[링크]
https://www.acmicpc.net/problem/1920

[시간]


[아이디어]
이분탐색 자바 구현 연습
뭐야 이분탐색으로 하니까 시간초과남.. 그냥 해시로 풀었다

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1920_수_찾기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Map<Integer, Boolean> map = new HashMap<>();

		int  N, M;
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];


		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) {
//			arr[i] = Integer.parseInt(st.nextToken());
			map.put(Integer.parseInt(st.nextToken()), true);
		}


		M = Integer.parseInt(br.readLine());
		int[] target = new int[M];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < M; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}

//		Arrays.sort(arr);
//		System.out.println(Arrays.toString(target));

		for (int i = 0; i < M; i++) {
			if (map.containsKey(target[i])) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}

		System.out.println(sb);
	}
}