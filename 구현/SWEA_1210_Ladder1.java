package 구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SWEA_1210_Ladder1
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T= 10;

		int[][] ladder = new int[100][100];

		// 좌, 우, 위
		int[] dy = {0, 0, -1};
		int[] dx = {-1, 1, 0};
		

		for(int test_case = 1; test_case <= T; test_case++)
		{
			br.readLine();
			int nowX = 0;
			int nowY = ladder.length-1;
			// 입력
			for (int i = 0; i < ladder.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < ladder.length; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					if (ladder[i][j] == 2) {
						nowX = j;
					}
				}
			}
			
			// 풀이
			while (nowY != 0) {
				for (int i = 0; i < dx.length; i++) {
					int x = dx[i];
					int y = dy[i];
					int newX = x + nowX;
					int newY = y + nowY;
//					System.out.println("현재 위치  "+nowY+" : "+nowX);
					
					if (newX >= 0 && newX < ladder.length && newY >= 0 && newY < ladder.length) {
//						System.out.println("이동할 곳"+newY+" : "+newX + " = "+ladder[newY][newX]);
						if (ladder[newY][newX] == 1) {
//							System.out.println("이동 "+newY+" : "+newX);
							ladder[newY][newX] = 0;
							nowX = newX;
							nowY = newY;
						}
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(nowX).append("\n");
		}
		System.out.println(sb);
	}
}