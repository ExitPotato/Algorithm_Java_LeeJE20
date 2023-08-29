package 그래프.위상정렬;
/*
[링크]
https://www.acmicpc.net/problem/2252

[시간]


[아이디어]
방금 익힌 위상정렬 연습해보자

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
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252_즐_세우기 {
	
	private static class Node{
		int n;
		Node next;
		public Node(int n, Node next) {
			this.n = n;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N, M;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Node g[] = new Node[N+1];
		
		
		// 집입 차수 카운트
		int edges[] = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			g[a] = new Node(b, g[a]);
			edges[b]++;
		}
		
		// 진입 차수가 0인 노드를 큐에 넣는다.
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for (int i = 1; i <= N; i++) {
			if (edges[i] == 0) {
				edges[i] = -1; //방문 처리
				q.offer(i);
			}
		}
		
		while (!q.isEmpty()) {
			// 큐에 있는 노드를 꺼내서 진출 간선 제거
			int current = q.poll();

			sb.append(current).append(" ");
			
			// 연결된 노드
			for (Node tmp = g[current]; tmp != null; tmp = tmp.next) {
				edges[tmp.n] --;
				
			}
			
			g[current] = null;
			
			for (int i = 1; i <= N; i++) {
				if (edges[i] == 0) {
					edges[i] = -1; //방문 처리
					q.offer(i);
				}
			}
		}
		
		System.out.println(sb);

	}
}
