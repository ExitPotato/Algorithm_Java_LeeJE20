package 그래프.MST.프림;

/*
[링크]
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb

[시간]
13:26~

[아이디어]
프림 알고리즘 연습

[시간복잡도]


[실수]
long을 안해서 틀렸다.

[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class SWEA_3124_최소_스패닝_트리 {
	
	static class Node implements Comparable<Node>{
		int vertex;
		long weight;
		Node next;
		
		public Node(int vertex, long weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
		
		@Override
		public int compareTo(Node o) {
			
			return Long.compare(this.weight, o.weight);
		}
		
		
	}
	
	static int T, V, E;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			Node[] graph = new Node[V+1];
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				graph[b] = new Node(a, c, graph[b]);
				graph[a] = new Node(b, c, graph[a]);
			}
			
			// solve
			long result = 0;
			// 이미 구축된 트리에서 idx 정정으로 갈 때의 최소 간선
			long[] minEdges = new long[V+1]; 
			Arrays.fill(minEdges, Integer.MAX_VALUE);
			int startNode = 1;
			minEdges[startNode] = 0;
			
			boolean[] visited = new boolean[V+1];
			int visitedNodeCount = 0;
			
			Queue<Node> pQueue = new PriorityQueue<>();
			pQueue.offer(new Node(startNode, 0, null));
			
			while (!pQueue.isEmpty()) {
				// 꺼내기
				Node current = pQueue.poll();
				
				// 이미 방문했다면 continue
				if(visited[current.vertex]) continue;
				
				// 방문 체크
				visited[current.vertex] = true;
				
				// MST 비용 추가
				result += minEdges[current.vertex];
				
				// 모든 정점 방문했다면
				if (++visitedNodeCount == V) {
					break;
				}
				
				// 새로 추가된 노드의 인접한 노드 중에서
				for (Node tmp = graph[current.vertex]; tmp != null; tmp = tmp.next) {
					// 방문한 노드라면 continue
					if (visited[tmp.vertex]) continue;
					
					if (!(tmp.weight < minEdges[tmp.vertex])) continue;
					
					// 현재 노드에서 인접 노드 tmp로 가는 비용이 현재까지 구한 모든 tmp로 가는 비용보다 작다면
					minEdges[tmp.vertex] = tmp.weight;
					pQueue.offer(new Node(tmp.vertex, minEdges[tmp.vertex], null));
				
				}
				
			}
			
			sb.append(result).append("\n");
			
			
		}
		System.out.println(sb);
	}
}
