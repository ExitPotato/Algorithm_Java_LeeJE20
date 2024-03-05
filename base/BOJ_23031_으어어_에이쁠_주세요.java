package base;

/*
[링크]
https://www.acmicpc.net/problem/23031

[시간]
16:33~

[아이디어]
구현

map에 스위치가 켜져있는지 표시해두자.

좀비는 현재 위치,이동할 방향을 알고 있어야 함.

순서
1. 행동 읽어오기
2. 아리 행동
	- F: 앞으로 갈 수 있다면 앞으로 한 칸
	- L, R: 방향만 전환
	- 스위치가 있다면 map에 스위치 켜기 -> L로 바꾸기 (light)
3. 루프돌면서 모든 좀비이동
	- 앞으로 갈 수 있다면 앞으로 한 칸
	- 앞으로 갈 수 없다면 방향 전환

	- 좀비 == 아리 && 불 꺼짐 (L이 아님)
		실패

기절이 없으면 성공

class Person:
	int di: 방향 인덱스 (directionIndex)
	int y: 현재 위치
	int x: 현재 위치

아래, 오른쪽, 위, 왼쪽
dy = {1, 0, -1, 0}
dx = {0, 1, -, -1}

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_23031_으어어_에이쁠_주세요 {
	public static class Person {
		@Override
		public String toString() {
			return "Person{" +
					"di=" + di +
					", y=" + y +
					", x=" + x +
					'}';
		}

		// 방향 인덱스 (directionIndex)
		int di;
		// 현재 위치
		int y;
		// 현재 위치
		int x;

		public Person(int di, int y, int x) {
			this.di = di;
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N;
		N = Integer.parseInt(br.readLine().trim());
		char[] actions = br.readLine().trim().toCharArray();
		char[][] map = new char[N][N];

		List<Person> zombies = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().trim().toCharArray();
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'Z') {
					zombies.add(new Person(0, i, j));
				}
			}
		}

		// 아래, 오른쪽, 위, 왼쪽
		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};

		char a = actions[0];

		String answer = "Phew...";

		Person ary = new Person(0, 0, 0);
play:		for (int i = 0; i < actions.length; ++i) {
			a = actions[i];
//			1. 행동 읽어오기
//			2. 아리 행동
//			- F: 앞으로 갈 수 있다면 앞으로 한 칸
			if (a == 'F'){
				if(canGoFront(ary, N)) {
					goFront(ary);
//					- 스위치가 있다면 map에 스위치 켜기 -> L로 바꾸기 (light)
					if(map[ary.y][ary.x] == 'S') {
						turnOnSwitch(map, ary.y, ary.x);
					}
				}
			} else {
//				- L, R: 방향만 전환
				turnDirection(ary, a);
			}
//					3. 루프돌면서 모든 좀비이동
//					- 앞으로 갈 수 있다면 앞으로 한 칸

//			System.out.println("차수: "+i + " action: "+a);
//			System.out.println("ARY: "+ary);
			for (Person z: zombies) {
				// 아리가 이동하자마자 좀비랑 만났는지 체크해야한다.
				if (z.y == ary.y && z.x == ary.x && map[z.y][z.x] != 'L'){
					answer = "Aaaaaah!";
					break play;
				}

				if(canGoFront(z, N)) {
					goFront(z);
				} else {
					// - 앞으로 갈 수 없다면 방향 전환
					turnDirection(z, 'B');
				}

				// - 좀비 == 아리 && 불 꺼짐 (L이 아님) : 실패
				if (z.y == ary.y && z.x == ary.x && map[z.y][z.x] != 'L'){
					answer = "Aaaaaah!";
					break play;
				}
//				System.out.println("  "+z);

			}




		}



		System.out.println(answer);
	}

	public static boolean canGoFront(Person p, int size) {
		// 아래, 오른쪽, 위, 왼쪽
		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};

		int ny = p.y + dy[p.di];
		int nx = p.x + dx[p.di];
		if (isInMap(ny, nx, size)) {
			return true;
		}

		return false;
	}
	public static void goFront(Person p) {
		// 아래, 오른쪽, 위, 왼쪽
		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};
		int ny = p.y + dy[p.di];
		int nx = p.x + dx[p.di];
		p.y = ny;
		p.x = nx;
	}

	public static void turnDirection (Person p, char direction ) {
		int interval = 0;
		if (direction == 'L') interval = -1;
		else if (direction == 'R') interval = 1;
		else if (direction == 'B') interval = 2;

		int di = p.di;
		int newIndex = (di+4+interval) % 4;
		p.di = newIndex;
	}

	public static boolean isInMap(int y, int x, int size) {
		if (y >= 0 && y < size && x >= 0 && x < size) {
			return true;
		}
		return false;
	}
	public static void turnOnSwitch (char[][] map, int y, int x) {
		int size = map.length;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int ny = y + i;
				int nx = x + j;
				if (isInMap(ny, nx, size)){
					map[ny][nx] = 'L';
				}
			}
		}
	}
}