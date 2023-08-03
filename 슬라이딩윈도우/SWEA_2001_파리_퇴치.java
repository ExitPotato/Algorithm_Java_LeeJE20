
/*
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PzOCKAigDFAUq

[아이디어]
슬라이딩 윈도우인데 2차원 배열이다.
ㄹ모양처럼 꼬불꼬불하게 윈도우 가는건 너무 어렵다.

부분합을 구해서 윈도우 내의 합을 구하자.
부분합 방향: 가로
윈도우 이동 방향: 세로

[시간복잡도]
O(n^3)
테스트 케이스가 작아서 괜찮을 것 같다.


[실수]
1. 최댓값을 구해야 하는데 최솟값을 구했다/
	-> 문제 요구사항을 정확히 파악해야 한다.
2. 출력 문제 번호를 0번부터 출력했다.
	-> 출력 형식이 문제에 맞는지 확인해야 한다.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2001_파리_퇴치 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		int[][] arr;
		for (int t = 0; t < T; t++){
			int n, m, answer = 0;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			arr = new int[n][n];
			for(int i = 0; i < n; i++){
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++){
					if(j == 0)
						arr[i][j] = Integer.parseInt(st.nextToken());
					else
						arr[i][j] = arr[i][j-1] + Integer.parseInt(st.nextToken());
				}
			}

			// solve
			//  한 줄당 슬라이딩 윈도우 이동 횟수
			int size = n-m+1;
			// c, r = 왼쪽 위 꼭짓점 좌표
			for(int c = 0; c < size; c++){
				for(int r = 0; r < size; r++){
					int sum = 0;
					if (c==0){
						for(int i = 0; i < m; i++){
							sum += arr[r+i][c+m-1];
						}
					} else{
						for(int i = 0; i < m; i++){
							sum += (arr[r+i][c+m-1] - arr[r+i][c-1]);
						}
					}
					answer = Math.max(answer, sum);
				}
			}
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}
