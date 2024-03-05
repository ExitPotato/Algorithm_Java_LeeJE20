package com.company.Algorithm_Java_LeeJE20.큐;

/*
[링크]
https://www.acmicpc.net/problem/20437

[시간]


[아이디어]

s s s a s s
3

min: 3
max 4

s:  2 4 5

a: 3

해시<문자, 큐>
큐 길이를 K개로 제한하면서 푼다.
큐 길이가 K개 이상이 되면 하나 빼고 맨 앞 값과 새로 넣는 값으로 문자열 길이 연산한다.


[시간복잡도]
O(n)

[실수]


[검색]


[다른 사람 코드]
1. 해시를 안 쓰고 알파벳 - 'a'을 인덱스로 쓸 수 있다
2. 문자열을 char[] charArr = W.toCharArray(); 로 할 수 있다.

[개선점]
원형 큐 사용
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20437_문자열_게임_2 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T, K;
		String W;
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			boolean found = false;
			int minAnswer = Integer.MAX_VALUE;
			int maxAnswer = 0;
			W = br.readLine();
			K = Integer.parseInt(br.readLine());

			Map<Character, Queue<Integer>> map = new HashMap<>();

			int length = W.length();
			for (int i = 0; i < length; i++) {
				char c = W.charAt(i);

				Queue<Integer> q = map.get(c);

				if (q == null) {
					q = new ArrayDeque<>();
					map.put(c, q);
				}

				q.add(i);
				if (q.size() == K) {
					int front = q.poll();
					int tempLength = i - front + 1;
					if (tempLength > maxAnswer) {
						maxAnswer = tempLength;
						found = true;
					}
					if (tempLength < minAnswer) {
						minAnswer = tempLength;
						found = true;
					}
				}
				// map.put(c, q);
			}

			if(found)
				sb.append(minAnswer).append(" ").append(maxAnswer).append("\n");
			else sb.append("-1\n");
		}

		System.out.println(sb);
	}
}