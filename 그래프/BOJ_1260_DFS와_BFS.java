package 그래프;
/*
[링크]

[시간]


[아이디어]

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1260_DFS와_BFS {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N, M, V;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken())-1;
		
		List<List<Integer>> g = new ArrayList<List<Integer>>();
		
		for (int i = 0; i < N; i++) {
			g.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken())-1;
			int n2 = Integer.parseInt(st.nextToken())-1;
			
			g.get(n1).add(n2);
			g.get(n2).add(n1);
		}
		
		
		for (int i = 0; i < N; i++) {
			g.get(i).sort(Comparator.naturalOrder());
		}
		
		
		boolean[] visited = new boolean[N];
		
		Deque<Integer> dq = new ArrayDeque<Integer>();
		
		// dfs
		dq.addLast(V);

		while(!dq.isEmpty()) {
			Integer current = dq.pollLast();
			if (visited[current]) continue;
			visited[current] = true;
			
			sb.append(current+1).append(" ");
			for(int i = g.get(current).size()-1; i >= 0; i--) {
				Integer now = g.get(current).get(i);	
				dq.addLast(now);
				
			}
		}
		
		sb.append("\n");
		
		//bfs
//		visited = new boolean[N];
		Arrays.fill(visited, false);
		
		dq.addLast(V);
		visited[V] = true;
		while(!dq.isEmpty()) {
			Integer current = dq.pollFirst();
			sb.append(current+1).append(" ");
			for(int i = 0; i < g.get(current).size(); i++) {
				Integer now = g.get(current).get(i);
				if (visited[now]) continue;
				
				visited[now] = true;
				dq.addLast(now);
			}
		}
		
		System.out.println(sb);
		
	}
}
