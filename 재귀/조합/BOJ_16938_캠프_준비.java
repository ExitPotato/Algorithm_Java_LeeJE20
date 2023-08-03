package 재귀.조합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16938
// 조합 -> nC2~nCn까지
// 문제를 배열에 넣고 인덱스를 이용해 문제 뽑기
// 리스트 돌면서 문제 난이도 합 구하기
// 리스트 돌면서 최대 최소 난이도 차 구하기

/*

[시간복잡도]
O(n!) 조합


[다른 사람 코드에서 배울 점]
조합 목록 만들면서 조건에 맞는지 아닌지 검사할 수 있다.
min, max 구할 때 Math 모듈 사용


*/


public class BOJ_16938_캠프_준비 {
	static List<List<Integer>> list = new ArrayList<>();
	static int[] levels;
	static int N, L, R, X;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		levels = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < levels.length; i++) {
			levels[i] = Integer.parseInt(st.nextToken());
		}
		
		// solve
		
		// 조합 목록 만들기
		List<Integer> tmp = new ArrayList<Integer>();
		for (int i = 2; i <= N; i++) {
			combination(N, i, 0, tmp);
		}
		
		// 정답
		int answer = 0;
		
		for (List<Integer> now: list) {
			// max 구하기, min ,문제 난이도 합 구하기
			Integer maxLevel = 0;
			Integer minLevel = Integer.MAX_VALUE;
			Integer sum = 0;
			
			for (Integer lv : now) {
				if (lv > maxLevel) {
					maxLevel = lv;
				}
				if (lv < minLevel) {
					minLevel = lv;
				}
				sum += lv;
			}	
			if (sum >= L && sum <= R && maxLevel-minLevel >= X) {
				answer++;
			}
			
		}	
		System.out.println(answer);

	}
	
	// n: 주어진 몇 개 중에서
	// r: 몇 개를 더 뽑을지
	public static void combination(int n, int r, int startIdx, List<Integer> picked) {
		if (r == 0) {
			list.add(new ArrayList<Integer>(picked));			
			return;
		}
		
		for(int i = startIdx; i < n; i++) {
			picked.add(levels[i]);
			combination(n, r-1, i+1, picked);
			picked.remove(picked.size()-1);
		}
	}
}
