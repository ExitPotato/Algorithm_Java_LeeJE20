package 그래프.구축;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 인접행렬 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		int[][] undirectedGraph = new int[V][V];
		int[][] directedGraph = new int[V][V];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			undirectedGraph[from][to] = undirectedGraph[to][from] = 1;
			directedGraph[from][to] = 1;
		}
		
		System.out.println("===== Undirected Graph =====");
		
		for (int[] line : undirectedGraph) {
			System.out.println(Arrays.toString(line));
		}
		
		System.out.println("===== Directed Graph =====");
		
		for (int[] line : directedGraph) {
			System.out.println(Arrays.toString(line));
		}
	}
}
