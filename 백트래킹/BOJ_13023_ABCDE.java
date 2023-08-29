package 백트래킹;
/*
[링크]

[시간]
15:48~ 16:06

[아이디어]
그래프에서 깊이가 4인 노드가 있는지 찾는 문제다.
백트래킹으로 한다.

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;

class Node{
	int vertex;
	Node next;
	
	public Node(int vertex, Node next) {
		this.vertex = vertex;
		this.next = next;
	}
	
	
}

public class BOJ_13023_ABCDE {
	
	static boolean found = false;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N, M;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Node[] g = new Node[N];
		boolean[] visited = new boolean[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			g[from] = new Node(to, g[from]);
			g[to] = new Node(from, g[to]);
		}
		
		for (int i = 0; i < N; i++) {
			if (!found)
				dfs(i, g, visited, 0);
			else break;
		}
		
		if (!found) System.out.println(0);
	}
	private static void dfs(int i, Node[] g, boolean[] visited, int count) {
		if(found) return;
		if (count==4) {
			found = true;
			System.out.println(1);
			return;
		}
		if (visited[i]) return;
		
		
		
		
		for (Node tmp = g[i]; tmp != null; tmp = tmp.next) {
			if (!visited[tmp.vertex]) {
				// 방문체크
				visited[i]= true;
				dfs(tmp.vertex, g, visited, count+1);
				visited[i]= false;
			}
		}
		
	}
}
