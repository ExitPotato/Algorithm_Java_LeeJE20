package 백트래킹;


/*
https://www.acmicpc.net/problem/9663

백트래킹
0,0 0,1 0,2 0,3
1,0 1,1 1,2 1,3
2,0 2,1 2,2 2,3
3,0 3,1 3,2 3,3

우상향 대각선: 합이 일정
좌하향 대각선: 차가 일정

실수: 배열 안에 인덱스를 잘못 적었다.

다른사람 코드: 비트마스킹으로 좀 더 개선 가능하다.


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9633_N_Queen {
	static int N, answer;
	static boolean[] colCheck;
	static boolean[] rightUpCrossCheck;
	static boolean[] rightDownCrossCheck;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		

		N = Integer.parseInt(br.readLine());
		
		colCheck = new boolean[N];
		rightUpCrossCheck = new boolean[N*2-1];
		rightDownCrossCheck = new boolean[N*2-1];
		
		backTracking(0);
		System.out.println(answer);
		
	}
	
	public static void backTracking(int row) {
		if (row == N) {
			answer++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!isAvailable(row, i)) continue;
			colCheck[i] = true;
			rightUpCrossCheck[row+i] = true;
			rightDownCrossCheck[row-i+N-1] = true;
			backTracking(row+1);
			colCheck[i] = false;
			rightUpCrossCheck[row+i] = false;
			rightDownCrossCheck[row-i+N-1] = false;
			
		}
	}
	
	public static boolean isAvailable(int row, int i) {
		// 세로 체크
		if(colCheck[i]) return false;
		
		// 우상향 대각선 체크
		if(rightUpCrossCheck[row+i]) return false;
		
		// 우하향 대각선 체크
		if(rightDownCrossCheck[row-i+N-1]) return false;
		
		return true;
	}
}
