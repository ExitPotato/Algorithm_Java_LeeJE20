package 시뮬레이션;
/*
[링크]

[시간]


[아이디어]
처음에 카테고리 개수 셀 때 set 썼는데 시간초과 나서 카테고리 개수도 직접 계산해주는 식으로 바꿈


[시간복잡도]
O(N)

[실수]
초밥 카테고리 개수를 쿠폰 있으면 ++ 했는데, 그래서 다음번 초밥 카테고리 개수에 영향을 줬다
=> 새로운 변수 만들어서 해결


[검색]
array를 set으로 바꾸는 법
-> array가 프리미티브가 아닐때 new Set(Arrays.asList(arr))

[다른 사람 코드]
1. 스시 배열에 마지막에 도는 K-1을 추가사고 시작할 수도 있다.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_15961_회전_초밥 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		StringBuilder sb = new StringBuilder();
		
		int N, d, k, c;
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int[] sushi = new int[N];
		
		
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int [] picked = new int[k];
		
		int answer = 0;
		
		int pickedCount = 0;
		
		int pickedCouponCount = 0;
		
		
		int[] pickedCategory = new int[d+1];
		int pickedCategoryCount = 0;
		for(int i = 0, pIdx = -1; i< N+k; i++ ) {
			int sIdx = i%N;
			
			pIdx = ++pIdx % k;
			// 넣을 스시
			int currentSushi = sushi[sIdx];
			// 지울 스시
			int deleteSushi = picked[pIdx];
			
			// 넣을 스시가 쿠폰과 같다면
			if(currentSushi == c) pickedCouponCount++;
			// 지울 스시가 쿠폰과 같다면
			if(pickedCount >= k && deleteSushi == c) pickedCouponCount--;
			
			// 스시 카테고리 줄이기
			if (pickedCategory[deleteSushi] > 0) {
				pickedCategory[deleteSushi]--;
				if (pickedCategory[deleteSushi] == 0) pickedCategoryCount--;
			}			
			
			// 원형 배열
			picked[pIdx] = currentSushi;
			
			// 새로운 카테고리라면 
			if (pickedCategory[currentSushi] == 0) {
				pickedCategoryCount++;
			}
			pickedCategory[currentSushi]++;
			
			pickedCount++;
			if (pickedCount<k) continue; // 선택한 초밥이 k개 미만이면
			
		
			
			answer = Math.max(answer, pickedCouponCount == 0 ? (pickedCategoryCount+1): pickedCategoryCount);
			
		}
		System.out.println(answer);
	}
}
