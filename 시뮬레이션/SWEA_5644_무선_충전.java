package 시뮬레이션;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo

[시간]
14:38~16:31

[아이디어]
BFS

지도에 BFS로 송신탑 범위를 적어둔다.
비트마스킹으로 해당 위치에 적용중인 송신탑 번호를 적는다.

0 -> 없음
1-> 1번 송신탑 범위
11-> 1번, 2번 송신탑 범위



배열은 지도와 visited 2개 이용.

이후 초마다 사람 움직이며 BC 범위 안에 있는지 적는다.
BC 위치가 겹치면 큰 BC 쪽에 붙는다.

사람과 BC이 모두 겹치면 BC가 큰 순서대로 나눠갖는다.


[시간복잡도]
정점 N개, 전체 간선의 수 N^2개
O(N^2)

[실수]
0초에서 시작

이동 후 1초 연산을 해야하는데, 1초 연산 후 이동을 해서 미묘하게 꼬였다.
=> 이동 순서를 정확히 따지자.

[검색]


[다른 사람 코드]
1. 처음 시작할 때 움직이는걸 이동 0을 주어 해당 위치에서 시작하게 했다. 대신 타임은 +1 했다.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.IntPredicate;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;


public class SWEA_5644_무선_충전 {

	static class AP implements Comparable<AP>{
		int x;
		int y;
		int scope;
		int power;
		
		public AP(int x, int y, int scope, int power) {
			super();
			this.x = x;
			this.y = y;
			this.scope = scope;
			this.power = power;
		}

		@Override
		public int compareTo(AP o) {
			
			return -(this.power - o.power);
		}
		
		
	}
	
	static int T, time, bcCount;
	static int[][] world;
	static boolean[][] visited;
	static StringBuilder sb = new StringBuilder();
	static int answer = 0;
//	static Map<Integer, Integer> bcPower;
	static AP[] aps;
	static int[] ApPower;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		
		for (int t = 1; t <= T; t++) {
			answer = 0;
//			bcPower = new HashMap<>();
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(br.readLine());
			time = Integer.parseInt(st.nextToken());
			bcCount = Integer.parseInt(st.nextToken());
			
			
			
			// 이동 정보
			int[] dx = {0, 0, 1, 0, -1};
			int[] dy = {0, -1, 0, 1, 0};
			int[] moveA = new int[time];
			int[] moveB = new int[time];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < time; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < time; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			// 월드
			world = new int[10][10];
			
			
			// AP 정보
			ApPower = new int[bcCount];
			aps = new AP[bcCount];
			for(int i = 0; i < bcCount; i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken()) -1;
				int y = Integer.parseInt(st.nextToken()) -1;
				int scope = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				
				aps[i] = new AP(x, y, scope, power);
			}
			
			// AP 가 큰 순서대로 정렬
			// 나중에 해당 AP가 존재한다면 바로 해당 AP가 최대임
			Arrays.sort(aps);
			
			ApPower = new int[bcCount];
			for (int i = 0; i < bcCount; i++) {
				ApPower[i] = aps[i].power;
			}

			
			for (int i = 0; i < bcCount; i++) {
				visited = new boolean[10][10];
				bfs(aps[i].y, aps[i].x, aps[i].scope, i);
			}
			
			
			int currentAY = 0;
			int currentAX = 0;
			int currentBY = 9;
			int currentBX = 9;
			
			getBC(currentAY, currentAX, currentBY, currentBX);
			
			for (int i = 0; i < time; i++) {
//				System.out.print(i+1+" : ");
				currentAY += dy[moveA[i]];
				currentAX += dx[moveA[i]];
				currentBY += dy[moveB[i]];
				currentBX += dx[moveB[i]];
				getBC(currentAY, currentAX, currentBY, currentBX);
				
				
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	
	private static void getBC(int AY, int AX, int BY, int BX) {
		int APower = 0;
		int BPower = 0;
		
		
		int ABc = -1;
		int BBc = -1;
		// A 체크
		for (int bc = 0; bc < bcCount; bc++) {
//			// 해당 bc가 있다면
			if ((world[AY][AX] & 1 << bc) != 0) {
				ABc = bc;
				break;
			}
		}
		
		// B 체크
		for (int bc = 0; bc < bcCount; bc++) {
//			// 해당 bc가 있다면
			if ((world[BY][BX] & 1 << bc) != 0) {
				BBc = bc;
				break;
			}
		}
		
		// A와 B가 같은 존에 있다면
		if (ABc == BBc && ABc != -1) {
			int Asecond = -1;
			int Bsecond = -1;
			
			// A 체크
			for (int bc = ABc+1; bc < bcCount; bc++) {
//				// 해당 bc가 있다면
				if ((world[AY][AX] & 1 << bc) != 0) {
					Asecond = bc;
					break;
				}
			}
			
			// B 체크
			for (int bc = BBc+1; bc < bcCount; bc++) {
//				// 해당 bc가 있다면
				if ((world[BY][BX] & 1 << bc) != 0) {
					Bsecond = bc;
					break;
				}
			}
			
			// 둘 다 세컨드가 있다면
			if (Asecond != -1 && Bsecond != -1) {
				answer+= ApPower[ABc];
				answer += ApPower[Math.min(Asecond, Bsecond)];
				
//				System.out.println( ApPower[ABc]+" "+ApPower[Math.min(Asecond, Bsecond)]);
			}
			// A만 세컨드가 있다면
			else if (Asecond != -1 && Bsecond == -1) {
				answer+= ApPower[ABc];
				answer+= ApPower[Asecond];
//				System.out.println( ApPower[Asecond]+" "+ApPower[ABc]);
			}
			// B만 세컨드가 있다면
			else if (Asecond == -1 && Bsecond != -1) {
				answer+= ApPower[ABc];
				answer+= ApPower[Bsecond];
//				System.out.println( ApPower[ABc]+" "+ApPower[Bsecond]);
			}
			// 둘 다 세컨드가 없다면
			else {
				answer+= ApPower[ABc];
//				System.out.println( ApPower[ABc]/2+" "+ApPower[ABc]/2);
			}
			return;
		}
		
		// A가 존에 있다면
		if (ABc != -1) {
			answer+= ApPower[ABc];
//			System.out.print(ApPower[ABc] + " ");
		}
		else {
//			System.out.print(0 + " ");
		}
		
		// B가 존에 있다면
		if(BBc != -1) {
			answer+= ApPower[BBc];
//			System.out.println(ApPower[BBc] + " ");
		}
		else {
//			System.out.println(0 + " ");
		}
		
	}


	private static void bfs(int y, int x, int scope, int bcNum) {
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		visited[y][x] = true;
		world[y][x] = world[y][x] | 1 << bcNum;
		
		q.add(new int[] {y, x});
		
		int[] dy = {0, 0, 1, -1};
		int[] dx = {1, -1, 0, 0};
		
		for (int i = 0; i < scope; i++) {
			int size = q.size();
			for (int j = 0; j <size; j++) {
				int[] current = q.poll();
				int cy = current[0];
				int cx = current[1];
				
				for (int k = 0; k < 4; k++) {
					int yy = dy[k];
					int xx = dx[k];
					
					int newY = cy+yy;
					int newX = cx+xx;
					
					// 범위
					if (!(newX>=0 && newX < 10 && newY >=0 && newY <10)) continue;
					
					visited[newY][newX] = true;
					world[newY][newX] = world[newY][newX] | 1 << bcNum;
//					String binaryNum = Integer.toBinaryString(world[newY][newX]);

					
					q.add(new int[] {newY, newX});
				}
			}
		}
		
	}


}
