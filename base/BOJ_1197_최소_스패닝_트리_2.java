package base;

/*
[링크]


[시간]


[아이디어]
크루스칼

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197_최소_스패닝_트리_2 {
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		long weight;
		
		public Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
		
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		Edge[] graph = new Edge[E];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Long.parseLong(st.nextToken());
			
			pq.offer(new Edge(from, to, weight));
		}
		
		// start
//		Arrays.sort(graph);
		int[] parents = new int[V+1];
		int pickedCount = 0;
		boolean[] visited = new boolean[V+1];
		int answer = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union(edge.from, edge.to, parents)) {
				answer+= edge.weight;
//				if (!visited[edge.from]) {
//					visited[edge.from] = true;
//					pickedCount++;
//				}
//				if (!visited[edge.to]) {
//					visited[edge.to] = true;
//					pickedCount++;
//				}
//				if (pickedCount == V) {
//					break;
//				}
				if (++pickedCount == V-1) break;
				
			}
		}
		System.out.println(answer);
	}
	
	private static int find(int i, int[] parents) {
		if (parents[i] == 0) return i;
		return parents[i] = find(parents[i], parents);
	}
	
	private static boolean union(int a, int b, int[] parents) {
		int aRoot = find(a, parents);
		int bRoot = find(b, parents);
		
		if (aRoot == bRoot) return false;
		
		parents[aRoot] = bRoot;
		return true;
	}
	
}
