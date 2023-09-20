package 다시풀기;
/*
[링크]
https://www.acmicpc.net/problem/17281
[시간]
15:21~

[아이디어]
순열을 구한다음 최댓값을 구하자.
[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.PrimitiveIterator.OfDouble;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class BOJ_17281_야구 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] scores = new int[N][9]; 
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxScore = play(scores, N);
	
		System.out.println(maxScore);
	}

	private static int play(int[][] score, int inning) {
		// n번째 타자는 playerByOrder[n]
		int[] playerByOrder = new int[9];
		playerByOrder[3] = 0;
		int maxScore = 0;
		int[] pickedOrder = {1, 2, 3, 4, 5, 6, 7, 8}; 
		
		do {
			int pointPerPermutaion = 0; // 이번 permutaion 점수
			// 선수 순서 정하기
			for (int i = 0; i < pickedOrder.length; i++) {
				if (i <= 2) playerByOrder[i] = pickedOrder[i];
				if (i >= 3) playerByOrder[i+1] = pickedOrder[i];
			}
			int playerIdx = -1;
			System.out.println(Arrays.toString(playerByOrder));
			for (int r = 0; r < inning; r++) {
				System.out.println(" =============================== inning: "+r);
				boolean[] ru = new boolean[4];
				// out이 3회 이하인동안
				int out = 0;
				while(out < 3) {
					// idx 루수에 사람이 있는지
					
					
					// 몇 번 인덱스 플레이어 차례인지
					playerIdx = ++playerIdx % 9 ;
					
					// 타자 결과
					int hitResult = score[r][playerByOrder[playerIdx]];
					
					System.out.println("playerIdx: hit = "+playerIdx+" : "+hitResult);
					
					if(hitResult == 0) {
						out++;
						continue;
					}
					
					ru[0] = true;
					// 루 이동시키기
					
					System.out.println(Arrays.toString(ru));
					System.out.println("#######################");
					for (int i = 3; i >= 0; i--) {
						
						// 루에 사람이 있으면
						if(ru[i]) {
							// 이동할 루 번호
							int no = i+hitResult;
							// 홈에 들어간다면
							if (no >= 4){
								// 점수 증가
								pointPerPermutaion++;
								// 이동시키기 (현재위치 비우기)
								ru[i] = false;
							}
							else { // 홈에 못 들어간다면
								// 이동시키기 
								ru[no] = true;
							}
						}
						System.out.println(i+" 루 이동");
						System.out.println(Arrays.toString(ru));
					}
					ru[0] = false;
				}
			}
			maxScore = Math.max(maxScore, pointPerPermutaion);
			break;
		} while (nextPermutaion(pickedOrder));
		
		
		return maxScore;
	}

	private static boolean nextPermutaion(int[] p) {
		int n = p.length - 1;
		int i = n;
		while (i > 0 && p[i-1]>=p[i]) i--;
		if(i<=0) return false;
		int j = n;
		while(p[i-1]>=p[j]) j--;
		swap(p, i-1, j);
		j=n;
		while(i<j) {
			swap(p, i++, j--);
		}
		
		return true;
	}

	private static void swap(int[] p, int i, int j) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
		
	}

	private static int getPlayer(int i) {
		
		return i % 9;
	}
}
