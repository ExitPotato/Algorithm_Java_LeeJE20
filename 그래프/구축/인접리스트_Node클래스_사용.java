package 그래프.구축;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 인접리스트_Node클래스_사용 {
	static class Node{
		int vertex;
		Node next;
		
		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [vertex=");
			builder.append(vertex);
			builder.append(", next=");
			builder.append(next);
			builder.append("]");
			return builder.toString();
		}
		
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
	
		Node[] undirectedGraph = new Node[V];
		Node[] directedGraph = new Node[V];
		
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			undirectedGraph[from] = new Node(to, undirectedGraph[from]);
			undirectedGraph[to] = new Node(from, undirectedGraph[to]);
			
			directedGraph[from] = new Node(to, directedGraph[from]);
			
		}
		
		System.out.println("===== Undirected Graph =====");
		
		for (Node node : undirectedGraph) {
			System.out.println(node);
		}
		
		System.out.println("===== Directed Graph =====");
		
		for (Node node : directedGraph) {
			System.out.println(node);
		}
	}
}
