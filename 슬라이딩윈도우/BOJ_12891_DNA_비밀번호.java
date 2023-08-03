package 슬라이딩윈도우;

/*
https://www.acmicpc.net/problem/12891

[아이디어]
누적합 문제로 풀 수 있을 것 같은데?
new arr[N][4]
arr[N][0] = N번째까지의 A 개수


012345
CCTGGATTG

a[2]-a[0]
3-2=1

[시간복잡도]
O(n) 한 번 훑으면서 가능

[공간복잡도]
4바이트 * S(문자열 길이) * 4
가능

[검색]

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_12891_DNA_비밀번호 {
	static int[][] arr;
	static int S, P, A, C, G, T;
	
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		
		String dna = br.readLine();
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 각 문자의 최소 개수
		int[] minCharCount = {A, C, G, T};
		
		// ACGT의 arr배열에서의 인덱스
		Map<Character, Integer> map = new HashMap<>();
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);
		arr = new int[S][4];
		
		int answer = 0;
		for (int i = 0; i < S; i++) {
			// 이전 개수
			for (int j = 0; j < 4; j++) {
				if (i != 0) {
					arr[i][j] = arr[i-1][j];
				}
			}
			
			// 이번 문자 개수 세기
			if (i == 0) {
				arr[i][map.get(dna.charAt(i))] = 1;
			}
			else {
				arr[i][map.get(dna.charAt(i))] = 1 + arr[i-1][map.get(dna.charAt(i))];
				
			}
			
			// 검사
			int startIdx = i-P;
			if (startIdx == -1) {
				boolean pass = true;
				for (int j = 0; j<4; j++) {
					if (!(arr[i][j] >= minCharCount[j])) {
						pass = false;
						break;
					}
					
				}
				if (pass) {
					answer++;
				}
			}
			else if (startIdx >=0) {
				boolean pass = true;
				for (int j = 0; j<4; j++) {
					if (!(arr[i][j] - arr[startIdx][j] >= minCharCount[j])) {
						pass = false;
						break;
					}
					
				}
				if (pass) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
}
