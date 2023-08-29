package 시뮬레이션;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc

[시간]
17:44 시작

[아이디어]
구현하자.

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_1873_상호의_배틀필드 {
	static int T, H, W, N;
	static char[][] world;
	static StringBuilder sb = new StringBuilder();
	static int cx, cy, cdy, cdx; //전차 위치, 방향
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Map<Character, Integer> map = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		map.put('U', 0);
		map.put('D', 1);
		map.put('L', 2);
		map.put('R', 3);
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			world = new char[H][W];
			for (int i = 0; i < H; i++) {
				world[i] = br.readLine().toCharArray();
			}
			
			N = Integer.parseInt(br.readLine());
			char[] commands = br.readLine().toCharArray();
			
			// 전차찾기
findCar:	for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					char c = world[i][j];
					cy = i;
					cx = j;
					if (c == '^') {
						cdy = 1;
						break findCar;
					} else if (c == 'v') {
						cdy = -1;
						break findCar;
					} else if (c == '<') {
						cdx = -1;
						break findCar;
					} else if (c == '>') {
						cdx = 1;
						break findCar;
					}
				}
			}
			
			// 구현
			for (char c : commands) {
				if (c == 'S') { // shoot
					int newY = cy+cdy;
					int newX = cx+cdx;
					while(newY >= 0 && newY < H && newX >= 0 && newX < W) {
						if (world[newY][newX] == '*') {
							world[newY][newX] = '.';
							break;
						}else if (world[newY][newX] == '#') {
							break;
						}
						newY += cdy;
						newX += cdx;
					}
					continue;
				}
				
				// 방향 전환
				cdy = dy[map.get(c)];
				cdx = dx[map.get(c)];
				
				int newY = cy+cdy;
				int newX = cx+cdx;
				
				if (cdy == -1) { // 상
					world[cy][cx] = '^';
				}else if (cdy == 1) { // 하
					world[cy][cx] = 'v';
				}else if (cdx == -1) { // 좌
					world[cy][cx] = '<';
				}else{ // 우
					world[cy][cx] = '>';
				}
				
				// 월드 안인지
				if(newY < 0 || newY >= H || newX < 0 || newX >= W) continue;
				
				// 평지면 이동
				if(world[newY][newX] == '.') {
					world[cy][cx] = '.';
					
					cy = newY;
					cx = newX;
					
					if (cdy == 1) { // 상
						world[cy][cx] = '^';
					}else if (cdy == -1) { // 하
						world[cy][cx] = 'v';
					}else if (cdx == -1) { // 좌
						world[cy][cx] = '<';
					}else{ // 우
						world[cy][cx] = '>';
					}
				}
				
				
			}
			
			// 출력
			for (char[] line : world) {
				for (char c : line) {
					sb.append(c);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
}
