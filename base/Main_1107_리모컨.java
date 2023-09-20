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

public class Main_1107_리모컨 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int K, N, M;
		int[] arr;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		arr = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
}