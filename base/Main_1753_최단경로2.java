package base;
/*
[링크]

[시간]


[아이디어]

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

import sun.nio.cs.ext.ISCII91;

public class Main_1753_최단경로2 {
	
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			
			return Integer.compare(this.weight, o.weight);
		}
		
		
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int V, E;
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());
		
		Node[] graph = new Node[V+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from] = new Node(to, weight, graph[from]);
		}
		
		int[] minDistance = new int[V+1];
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		boolean[] visited = new boolean[V+1];
		
		minDistance[start] = 0;
		visited[start] = true;
		
		Queue<Integer> queue = new PriorityQueue<>();

		queue.add(start);
		
		while(!queue.isEmpty()) {
			Integer current = queue.poll();
			
			if (visited[current]) {
				continue;
			}
			
			
		}
		
		// 출력
		for (int i = 1; i <= V; i++) {
			sb.append(visited[i]?minDistance[i]:"INF").append("\n");
		}
		System.out.println(sb);
	}
}
