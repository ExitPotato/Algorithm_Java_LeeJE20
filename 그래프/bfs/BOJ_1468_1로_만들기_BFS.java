package 그래프.bfs;
/*
[링크]
https://www.acmicpc.net/problem/1463
[시간]
14:40~15:01

[아이디어]
bfs로 완전탐색

[시간복잡도]

[실수]

[검색]

[다른 사람 코드]

*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1468_1로_만들기_BFS {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean visited[] = new boolean[N+1];
		q.offer(N);
		int depth = -1;
		while(!q.isEmpty()) {
			int size = q.size();
			
			
			depth++;
			
			
			for (int i = 0; i < size; i++) {	
				int current = q.poll();
				if (current == 1) {
					System.out.println(depth);
					return;
				}
				if (current % 3 == 0 && !visited[current/3]) {
					visited[current/3] = true;
					q.offer(current/3);
				}
				if (current % 2 == 0 && !visited[current/2]) {
					visited[current/2] = true;
					q.offer(current/2);
				}
				if (current >1 && !visited[current-1]) {
					visited[current-1] = true;
					q.offer(current-1);
				}
			}
			
			
		}
	}
}
