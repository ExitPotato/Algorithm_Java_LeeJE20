package 그래프.bfs;
/*
[링크]
https://www.acmicpc.net/problem/1697
[시간]


[아이디어]
BFS로 풀면 된다.
현재 상태가 노드이다.


[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_숨바꼭질 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N, K;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new ArrayDeque<>();
		Map<Integer, Boolean> visited = new HashMap<>();
		visited.put(N, true);
		q.add(N);
		
		int answer= -1;
bfs:		while(!q.isEmpty()) {
			answer++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int current = q.poll();
				if (current == K) {
					System.out.println(answer);
					return;
				}
				
				int next = current-1;
				if (!visited.containsKey(next)) {
					visited.put(next, true);
					q.add(next);
				}
				next = current+1;
				if (!visited.containsKey(next)) {
					visited.put(next, true);
					q.add(next);
				}
				next = current*2;
				if (!visited.containsKey(next) && current  < K) {
					visited.put(next, true);
					q.add(next);
				}
						
			}
			
			
		}
	}
}
