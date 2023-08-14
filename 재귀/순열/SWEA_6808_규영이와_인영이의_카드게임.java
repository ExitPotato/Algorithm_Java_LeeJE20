package 재귀.순열;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0&categoryId=AWgv9va6HnkDFAW0&categoryType=CODE
[시간]


[아이디어]
순열
nextPermutation

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_6808_규영이와_인영이의_카드게임 {
	static int T;
	static boolean[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			arr = new boolean[18];
			for (int i = 0; i < 9; i++) {
				arr[Integer.parseInt(st.nextToken()) -1] = true;
			}
			
			int[] arrMy = new int[9];
			int[] arrTarget = new int[9];
			int a = 0;
			int b = 0;
			for(int i = 0; i < 18; i++) {
				if (arr[i]) arrMy[a++] = i+1;
				else arrTarget[b++] = i+1;
			}
			
			// solve
			int winCount = 0;
			int loseCount = 0;
			Arrays.sort(arrTarget);
			do {
//				System.out.println(Arrays.toString(arrTarget));
				int sumA = 0;
				int sumB = 0;
				for (int i = 0; i < 9; i++) {
					if (arrMy[i] > arrTarget[i]) sumA += (arrMy[i]+arrTarget[i]);
					else if (arrMy[i] < arrTarget[i]) sumB += (arrMy[i]+arrTarget[i]);
				}
				
				if (sumA > sumB) winCount++;
				else if (sumB > sumA) loseCount++;
				
			}while(nextPermutation(arrTarget));
			sb.append(winCount).append(" ").append(loseCount).append("\n");
		}
		
		System.out.println(sb);
		
	}
	private static boolean nextPermutation(int[] p) {
		int i = p.length-1;
		while(i > 0 && p[i-1] > p[i] ) i--;
		if (i <= 0)
			return false;
		
		int j = p.length -1;
		while(p[i-1] >p[j]) j--;
		
		swap(p, i-1, j);
		
		int k = p.length -1;
		while(i<k) {
			swap(p, i++, k--);
		}
		
		return true;
		
		
	}
	
	private static void swap(int[]p, int i, int j) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}
}
