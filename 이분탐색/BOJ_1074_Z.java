package 이분탐색;

/*
[링크]
https://www.acmicpc.net/problem/1074

[아이디어]
이분탐색+재귀로 푼다.

[시간복잡도]
O(N)

[실수]
midY, midX가 2 사분면에 있는줄 알았는데 4 사분면이었다.
=> 이분탐색 할 때 중간의 left인지 right인지 신경써야 한다.

[검색]
자바는 unsigned int가 없다. 와우!

[다른 사람 코드]
나랑 똑같이 풀었음

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class BOJ_1074_Z {
	static int N, r, c;
	static int[][] arr;
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		solve(0, 0, (int)Math.pow(2, N), 0);
		System.out.println(answer);
	}

	static void solve(int y, int x, int size, int num) {

		if (r== y && c == x) {
			answer = num;
			return;
		}

		int halfSize = size/2;
		int midY = y + halfSize;
		int midX = x + halfSize;

		int nextY = 0, nextX = 0;

		if (midY > r && midX > c) { // 2사분면
			nextY = y;
			nextX = x;
		}
		else if (midY > r && midX <= c) {// 1사분면
			nextY = y;
			nextX = midX;
			num += halfSize*halfSize;
		}
		else if (midY <= r && midX > c) { // 3사분면
			nextY = midY;
			nextX = x;
			num += halfSize*halfSize*2;
		}
		else if (midY <= r && midX <= c) { // 4사분면
			nextY = midY;
			nextX = midX;
			num += halfSize*halfSize*3;
		}

		solve(nextY, nextX, halfSize, num);
	}
}
