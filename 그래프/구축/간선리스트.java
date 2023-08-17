package 그래프.구축;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 간선리스트 {
	
	static class Edge{
		int from;
		int to;
		
		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Edge [from=");
			builder.append(from);
			builder.append(", to=");
			builder.append(to);
			builder.append("]");
			return builder.toString();
		}
		
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		Edge[] undirectedGraph = new Edge[E * 2];
		Edge[] directedGraph = new Edge[E];
		
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			undirectedGraph[i * 2] = new Edge(from, to);
			undirectedGraph[i * 2 + 1] = new Edge(to, from);
			
			directedGraph[i] = new Edge(from, to);
		}
		
		System.out.println("===== Undirected Graph =====");
		
		for (Edge line : undirectedGraph) {
			System.out.println(line);
		}
		
		System.out.println("===== Directed Graph =====");
		
		for (Edge line: directedGraph) {
			System.out.println(line);
		}
	}
}
