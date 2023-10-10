package 시뮬레이션;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH
[시간]
14:18~ 16:35

[아이디어]
원형 배열 사용
인덱스 0: 빨간 위치
시계방향 회전: 인덱스 +1
반시계방향 회전: 인덱스 -1
오른쪽 극: 인덱스 2
왼쪽 극: 인덱스 6
[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4013_특이한_자석 {
	static int T, K;
	static int[][] magnets;

	final static int MAGNET_COUNT = 4;
	final static int BLADE_COUNT = 8;
	final static int RIGHT = 2;
	final static int LEFT = 6;

	final static int CLOCKWISE = 1;
	final static int COUNTER_CLOCKWISE = -1;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			K = Integer.parseInt(br.readLine());


			magnets = new int[MAGNET_COUNT][BLADE_COUNT];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <	8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] orders = new int[K][2];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int magnet = Integer.parseInt(st.nextToken()) -1;
				int direction = Integer.parseInt(st.nextToken());
				orders[i][0] = magnet;
				orders[i][1] = direction;
			}

			// solve
			int[] indexes = {0, 0, 0, 0};
			for (int [] order:orders) {
				int magnetNo = order[0];
				int direction = order[1];
				rotateOrder(magnetNo, direction, indexes);


//				for (int i = 0; i < 4; i++) {
//					System.out.println();
//					for (int j = 0; j < 8; j++) {
//						int startIndex = indexes[i];
//
//						int index = (startIndex+j)%BLADE_COUNT;
//
//						System.out.print(magnets[i][index]+" ");
//					}
//				}
			}

			int answer = 0;
			int[] score = {1, 2, 4, 8};
			for (int i = 0; i < MAGNET_COUNT; i++) {
				answer += magnets[i][indexes[i]] == 1 ? score[i]:0;
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void rotateOrder(int magnet, int direction, int[] indexes) {
		// 0-1, 1-2, 2-3, 4-0 자석
		boolean[] isConnected = new boolean[4];
		for (int m = 0; m < MAGNET_COUNT-1; m++) {
			int nextMagnetNo = (m+1) % MAGNET_COUNT;
			int[] nowMagnet = magnets[m];
			int[] nextMagnet = magnets[nextMagnetNo];
			int nowStartIndex = indexes[m];
			int nextStartIndex = indexes[nextMagnetNo];
			int nowRightIndex = (nowStartIndex + RIGHT)%BLADE_COUNT;
			int nextLeftIndex = (nextStartIndex + LEFT)%BLADE_COUNT;
			if (nowMagnet[nowRightIndex] != nextMagnet[nextLeftIndex]){
				isConnected[m] = true;
			}
		}

//		System.out.println("isConnected");
//		System.out.println(Arrays.toString(isConnected));

		// 각 자석 별 돌릴 방향, 0은 안 돌림
		int[] rotateDirection = new int[4];


		int nextDirection = getNextDirection(direction);
		rotateDirection[magnet] = nextDirection;
		nextDirection = getNextDirection(nextDirection);


		boolean isLeft = true;
		getRotateDirection(magnet, nextDirection, isConnected, rotateDirection, !isLeft);
		getRotateDirection(magnet, nextDirection, isConnected, rotateDirection, isLeft);

//		System.out.println("rotateDirection");
//		System.out.println(Arrays.toString(rotateDirection));

		// 돌리기
		for (int i = 0; i < MAGNET_COUNT; i++) {
			indexes[i] = (indexes[i]+rotateDirection[i]+BLADE_COUNT)%BLADE_COUNT;
		}



	}

	private static void getRotateDirection(int magnet, int direction, boolean[] isConnected, int[] rotateDirection, boolean isLeft) {
		int step = 1;
		if (isLeft) step = -1;

		int nextDirection = direction;
		int magnetNo = magnet;
		while (magnetNo < MAGNET_COUNT && magnetNo >=0){

//			System.out.println(magnetNo);
			if (!isLeft && !isConnected[magnetNo]) break;
			if (isLeft && magnetNo+step >= 0 && !isConnected[magnetNo+step]) break;
			if (magnetNo+step >= 0 && magnetNo+step < MAGNET_COUNT) {
				rotateDirection[magnetNo + step] = nextDirection;
			}
			nextDirection = getNextDirection(nextDirection);
			magnetNo += step;
		}
	}

	private static int getNextDirection(int direction) {
		return direction==CLOCKWISE?COUNTER_CLOCKWISE:CLOCKWISE;
	}
}
