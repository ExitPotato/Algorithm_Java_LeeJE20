package 백트래킹;

/*
[링크]
https://www.acmicpc.net/problem/6987

[시간]
14:24 ~ 16:01

[아이디어]
알고리즘 분류에 백트래킹이라고 나와있어서 생각해봄.
a-b 승 / 무 / 패
a-c 승 / 무 / 패
a-d 승 / 무 / 패
...

조합으로 국가 선정하고, 점수표 만들어나간다.

[시간복잡도]
4가지 결과표 * 15경기 * 승/무/패 3가지
-> 대충 O(180)으로 가능!
 

[실수]
마지막에 지금까지 계산한 결과와 결과표를 비교하지 않았다.
=> 문제 조건 꼼꼼히 보기

[검색]
왜 수리적으로 못 푸는가?
이미 싸웠던 국가와 다시 싸울 수 없는데, 그것을 수식화하기 어렵다.

[다른 사람 코드]
1. 조합을 굳이 nextPermutaion까지 쓸 필요 없이 for문 써도 된다.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵 {
	static StringBuilder sb = new StringBuilder();
	static int[][] games =new int[15][2]; // 게임 조합
	static int answer = 0; // 결과표 가능 여부
	static int[][] score; //
	static int[][] target; // 현재 결과표
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		target = new int[6][3];
		
		// 조합 구하기
		int[] isSelected = {0,0,0,0,1,1}; 
		int index = 0;
		do {
			int country1 = -1;
			int country2 = -1;
			
			for (int i = 0; i < 6; i++) {
				if(isSelected[i] == 1) {
					if(country1 == -1) {
						country1 = i;
					} else {
						country2 = i;
					}
				}
			}
			games[index][0] = country1;
			games[index][1] = country2;
			index++;
		}while(nextPermutaion(isSelected));

		
		// solve
		for(int t = 0; t < 4; t++) {
			// 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					target[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 중간중간 계산해나가는 점수표
			score = new int[6][3];
			// 게임 번호
			int gameIndex = 0;
			answer = 0;
			dfs(0);
			sb.append(answer).append(" ");

		}
		System.out.println(sb);
	}

	private static void dfs(int gameIndex) {
		if (gameIndex == 15) {
			// score와 target 같은지
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (target[i][j] != score[i][j]) return;
				}
			}
			answer = 1;
			return;
		}
		
		int country1 = games[gameIndex][0];
		int country2 = games[gameIndex][1];
		
		// country1 기준 승, 무, 패
		for (int i = 0; i < 3; i++) {
			if (!isAvailable(country1, country2, i)) continue;
			score[country1][i]++;
			score[country2][2-i]++;
			dfs(gameIndex + 1);
			score[country1][i]--;
			score[country2][2-i]--;
			
		}
	}
	
	private static boolean isAvailable(int country1, int country2,  int gameResult) {
		if (score[country1][gameResult] >= target[country1][gameResult]) return false;
		if (score[country2][2-gameResult] >= target[country2][2-gameResult]) return false;
		return true;
	}
	
	private static boolean nextPermutaion(int[] p) {
		int maxIndex = p.length - 1;
		int i = maxIndex;
		while(i > 0 && p[i-1] >= p[i]) i--;
		if (i == 0) return false;
		
		int j = maxIndex;
		while(p[i-1] >= p[j]) j--;
		
		swap(p, i-1, j);
		
		
		j = maxIndex;
		while (i < j) {
			swap(p, i++, j--);
		}
		return true;
	}
	
	private static void swap(int[]p, int i, int j) {
		int tmp= p[i];
		p[i] = p[j];
		p[j] = tmp;
	}
}
