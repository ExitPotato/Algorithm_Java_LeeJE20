package 그래프.다익스트라;
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
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

public class BOJ_1753_최단경로 {
	
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
		
		minDistance[start] = 0;
		
		
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		int visitedCount = 0;
		
		boolean[] visited = new boolean[V+1];
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			if(visited[current.vertex]) continue;
			
			visitedCount++;
			visited[current.vertex] = true;
			
			if(visitedCount == V) {
				break;
			}
			
			for(Node tmp = graph[current.vertex]; tmp != null; tmp = tmp.next) {
				if(visited[tmp.vertex]) continue;
				minDistance[tmp.vertex] = Math.min(minDistance[tmp.vertex], minDistance[current.vertex]+tmp.weight);
				pq.offer(new Node(tmp.vertex, minDistance[tmp.vertex]));
			}
		}

		// 출력
		for (int i = 1; i <= V; i++) {
			sb.append(visited[i]?minDistance[i]:"INF").append("\n");
		}
		System.out.println(sb);
	}
}
