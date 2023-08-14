package 부분집합;

/*

공집합을 제외한 부분집합을 구하자.
sourMul, bitterSum은 파라미터로 넘기면서 구하자.
부분집합을 다 만들면 최솟값(answerMin)을 계산하자.


[시간복잡도]
O(N^2)이지만 N이 최대 10이므로 가능

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961_도영이가_만든_맛있는_음식 {
	static int[][] arr;
	static int answerMin = Integer.MAX_VALUE;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][2]; // 신맛 쓴맛
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		powerSet(0, 1, 0, 0);
		
		System.out.println(answerMin);
	}
	
	
	// count: 선택 횟수
	static void powerSet(int count, int sourMul, int bitterSum, int selectedCount) {
		if (count == N ) {
			if (selectedCount != 0)
				answerMin = Math.min(answerMin, Math.abs(sourMul-bitterSum));
			return;
		}
		// 선택함
		powerSet(count+1, sourMul*arr[count][0], bitterSum+arr[count][1], selectedCount+1);
		// 선택안함
		powerSet(count+1, sourMul, bitterSum, selectedCount);
	}
}
