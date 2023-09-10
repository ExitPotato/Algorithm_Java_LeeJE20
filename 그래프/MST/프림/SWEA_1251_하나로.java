package 그래프.MST.프림;

/*
[링크]

[시간]
14:17~ 15:19

[아이디어]
프림으로 하자. (방금 배웠으니까)

[시간복잡도]


[실수]
visited 체크를 비트마스킹으로 했더니 overflow 발생
=> visited를 체크할 것이 많으면 boolean 배열이 더 좋다.

[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class SWEA_1251_하나로 {
	static class Node implements Comparable<Node>{
		int vertex;
		double weight;
		public Node(int vertex, double weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			
			return Double.compare(this.weight, o.weight);
		}
		
		
	}

	static int T, N, M;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			int[] xPoint = new int[N];
			int[] yPoint = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				xPoint[i]= Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				yPoint[i]= Integer.parseInt(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			
			// 그래프 구축
			double[][] graph = new double[N][N];
			
			for (int i = 0; i <N; i++) {
				int x1 = xPoint[i];
				int y1 = yPoint[i];
				for (int j = i+1; j < N; j++) {
					int x2 = xPoint[j];
					int y2 = yPoint[j];
					
					double w = getWeight(E, x1, y1, x2, y2);
					
					graph[i][j] = w;
					graph[j][i] = w;
				}
				
			}
			
			// 프림
			
			// 구축한 트리로부터 idx 정점까지의 최소 비용
			double[] minWeights = new double[N];
			Arrays.fill(minWeights, Double.MAX_VALUE);
			// 시작 정점
			int start = 0;
			
			// 시작 정점 비용 초기화
			minWeights[start] = 0;
			
			// 이미 방문한 정점 수
			int visitedVertexCount = 0;
			
			// 갈 수 있는 최소 비용의 정점을 꺼내주는 pq
			Queue<Node> pq =  new PriorityQueue<Node>();
			
			pq.offer(new Node(start, 0));
			
			boolean[] visited = new boolean[N];
			
			double result = 0;
			while(!pq.isEmpty()) {
				Node current = pq.poll();
				
				// 이미 방문했다면
				if (visited[current.vertex]) continue;
				
				// 방문 체크
				visited[current.vertex] = true;
				
				// 결과 더하기
				result += minWeights[current.vertex];
				
				// 모든 노드 방문 체크했는지
				if (++visitedVertexCount == N) {
					break;
				}
				
				// 모든 인접 정점에 대하여
				for (int i = 0; i < N; i++) {
					// 인접이 아니면 continue
					if (graph[current.vertex][i] == 0) continue;
					
					// 방문했었다면 continue
					if (visited[i]) continue;
					
					// 최소비용이 아니라면 continue
					if (graph[current.vertex][i] >= minWeights[i]) continue;
					
					// 트리에서 i로가는 새로운 최소 비용 등록
					minWeights[i] = graph[current.vertex][i];
					
					// 다음 최소 비용 간선을 갖는 정점을 찾기 위해 큐에 등록
					pq.offer(new Node(i, minWeights[i]));
				}
			}
			
			sb.append((long)(result+0.5)).append("\n");
			
		}
		System.out.println(sb);
	}
	
	private static double getWeight(double e, int x1, int y1, int x2, int y2) {
		return (Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2))*e;
	}
}
