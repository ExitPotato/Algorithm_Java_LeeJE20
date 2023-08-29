package 시뮬레이션;
/*
[링크]

[시간]
13:09~ 15:34

[아이디어]
조합 -> dfs2번 돌면서 조합 내용들이 같은 그룹인지 체크



[시간복잡도]


[실수]
redPopulation += population[redRegion[j]];
population[j]로 해서 답이 잘 안 나오고 있었다..

인덱스를 잘못 넣었다.
배열이 깊어지면 헷갈리는 것 같다.

[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링 {
	
	static boolean searchComplete;
	static int visitCount =0;
	
	
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
		StringBuilder sb = new StringBuilder();
		
		int N, M;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		// 도시의 인구 수
		int[] population = new int[N];
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		Node[] graph = new Node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < size; j++) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				graph[i] = new Node(tmp, graph[i]);
			}
		}
		
//		for(Node n: graph) {
//			System.out.println(n);
//		}
		
		int answer = Integer.MAX_VALUE;
		
		int[] isSelected = new int[N];
		for (int i = 1; i <= N/2; i++) {
			// isSelected 0으로 채우기
			Arrays.fill(isSelected, 0);
			// isSelected 뒤부터 1로 i개 채우기
			for (int j = 0; j < i; j++) {
				isSelected[N-1-j] = 1;
			}
			
			do {
				int[] redRegion = new int[i];
				int[] blueRegion = new int[N-i];
				
				int rIdx = 0;
				int bIdx = 0;
				for (int j = 0; j < N; j++) {
					if(isSelected[j] == 1) {
						redRegion[rIdx++] = j;
					}else {
						blueRegion[bIdx++] = j;
					}
				}

//				// 각각 Region에 대해 dfs 하기
//				// dfs해서 모두 방문 가능하면 true 리턴

				searchComplete = false;
				visitCount = 0;
				dfs(redRegion[0], redRegion, graph, new boolean[N]);
				boolean redSearchComplete = searchComplete;
				
				searchComplete = false;
				visitCount = 0;
				dfs(blueRegion[0], blueRegion, graph, new boolean[N]);
				boolean blueSearchComplete = searchComplete;
				
				if ( redSearchComplete && blueSearchComplete) {
					int redPopulation = 0;
					int bluePopulation = 0;
					// 인구수 차 (절댓값) 구하고 최솟값 구하기
					for (int j = 0; j < redRegion.length; j++) {
						redPopulation += population[redRegion[j]];
					}
					for (int j = 0; j < blueRegion.length; j++) {
						bluePopulation += population[blueRegion[j]];
					}
			
					answer = Math.min(answer, Math.abs(redPopulation-bluePopulation));
				}
						
			}while(nextPermutaion(isSelected));
		}
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}



	private static boolean dfs(int currentVertex, int[] redRegion, Node[] graph, boolean[] visited) {
		// 이미 방문했다면
		if (visited[currentVertex]) return false;
		
		// 방문체크
		visited[currentVertex] = true;
		visitCount ++;
		// 방문횟수가 채워졌다면
		if (visitCount == redRegion.length) {
			for (int i = 0; i < redRegion.length; i++) {
				// 구역을 전부 방문하지 않았다면
				if(!visited[redRegion[i]]) return false; 
			}
			searchComplete = true;
			return true;
		}
		
		// 연결된 노드 찾기
		for (Node tmp = graph[currentVertex]; tmp != null; tmp = tmp.next) {
			
			boolean isSameRegion = false;
			for (int i = 0; i < redRegion.length; i++) {
				if (tmp.vertex == redRegion[i]) {
					isSameRegion = true;
					break;
				}
			}
			// 만약 다른 지역 노드라면 
			if (!isSameRegion) continue;
			
			//이미 방문했다면
			if (visited[tmp.vertex]) continue;
			
			// 접근
			dfs(tmp.vertex, redRegion, graph, visited);
			if (searchComplete) return true;
		}
		
		
		return false;
	}



	private static boolean nextPermutaion(int[] p) {
		int maxIdx = p.length-1;
		int i =maxIdx;
		while( i > 0 && p[i-1]>=p[i]) i--;
		
		if(i <= 0) return false;
		
		int j = maxIdx;
		while(p[i-1] >= p[j]) j--;
		
		swap(p, i-1, j);
		
		j = maxIdx;
		while (i < j) {
			swap(p, i++, j--);
		}
		
		return true;
	}

	private static void swap(int[] p, int i, int j) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
		
	}
}
