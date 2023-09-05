package 그래프.다익스트라;

/*
[링크]
https://www.acmicpc.net/problem/1238

[시간]


[아이디어]
플로이드 워셜인것 같은데..
다익스트라로 풀면 시간복잡도 넘을 줄 알았는데 괜찮았다.

[시간복잡도]


[실수]
다익스트라 구현 시 첫 노드를 visited먼저 하고 시작해서 답이 0 나왔다.

[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {
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
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [vertex=");
			builder.append(vertex);
			builder.append(", weight=");
			builder.append(weight);
			builder.append(", Next=");
			builder.append(next);
			builder.append("]");
			return builder.toString();
		}
		@Override
		public int compareTo(Node o) {
			
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N, M, X;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Node[] graph = new Node[N+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			
			graph[from] = new Node(to, weight, graph[from]);
		}
		
		
		
		int answer = 0;
		// 다익스트라
		for (int i = 1; i <= N; i++) {
			int start = i;
			int end = X;
			int sum = 0;
			sum = dijkstra(start, end, graph, N);
			sum += dijkstra(end, start, graph, N);
			answer = Math.max(answer, sum);
		}
		System.out.println(answer);
		
	}

	private static int dijkstra(int start, int end, Node[] graph, int maxVertex) {
		
		boolean[] visited = new boolean[maxVertex+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int[] minDistance = new int[maxVertex+1];
		
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		
		
		minDistance[start] = 0;
//		visited[start] = true;
		pq.offer(new Node(start, 0));
		
	
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			if (visited[current.vertex]) continue;
			if (current.vertex == end) {
				break;
			}
			visited[current.vertex] = true;
			for(Node tmp = graph[current.vertex]; tmp!=null; tmp=tmp.next) {
				if (visited[tmp.vertex]) continue;
				
				minDistance[tmp.vertex] = Math.min(minDistance[tmp.vertex], minDistance[current.vertex]+ tmp.weight);
				pq.offer(new Node(tmp.vertex, minDistance[tmp.vertex]));
				
			}
		}
		
		return minDistance[end];
	}


}
