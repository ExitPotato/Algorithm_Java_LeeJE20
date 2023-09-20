package 그래프.위상정렬;
/*
[링크]
https://www.acmicpc.net/problem/2252

[시간]
11:43~

[아이디어]
위상정렬을 빠르게 구현해보자

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]
1. g[current] = null 안 해도 된다.
2. edges -- 하자마자 0 체크 하면 for문 돌면서 진입차수 0인것 안 찾아도 된다.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_즐_세우기2 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<Integer, List<Integer>> g = new HashMap<>();
		
		int[] count = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			List<Integer> list = g.get(a);
			if (list == null) {
				list = new ArrayList<Integer>();
			}
			list.add(b);
			count[b]++;
			g.put(a, list);
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (count[i] == 0) q.offer(i);
			
		}
		
		while (!q.isEmpty()) {
			int current = q.poll();
			sb.append(current).append(" ");
			List<Integer> adjNodes = g.get(current);
			if (adjNodes == null) continue;
			for (Integer e : adjNodes) {
				count[e]--;
				if (count[e] == 0) q.offer(e);
			}
		}
		
		System.out.println(sb);
	}
	
}
