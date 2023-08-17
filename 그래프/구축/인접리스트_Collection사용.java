package 그래프.구축;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 인접리스트_Collection사용 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
	
		List<Integer>[] undirectedGraph = (List<Integer>[]) new ArrayList[V];
		List<Integer>[] directedGraph = (List<Integer>[]) new ArrayList[V];
		
		for (int i = 0; i < V; i++) {
			undirectedGraph[i] = new ArrayList<Integer>();
			directedGraph[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			undirectedGraph[from].add(to);
			undirectedGraph[to].add(from);
			
			directedGraph[from].add(to);
			
		}
		
		System.out.println("===== Undirected Graph =====");
		
		for (List<Integer> line : undirectedGraph) {
			System.out.println(line);
		}
		
		System.out.println("===== Directed Graph =====");
		
		for (List<Integer> line : directedGraph) {
			System.out.println(line);
		}
	}
}
