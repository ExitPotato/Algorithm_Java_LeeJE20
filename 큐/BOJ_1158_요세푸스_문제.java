package 큐;
/*
https://www.acmicpc.net/problem/1158

큐로 구현


 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1158_요세푸스_문제 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			q.offer(i+1);
		}
		
		Integer now;
		while (!q.isEmpty()) {
			
			for (int i = 0; i < k-1; i++) {
				now = q.poll();
				q.offer(now);
			}
			now = q.poll();
			sb.append(now);
			if (!q.isEmpty()) {
				sb.append(", ");
			}
		}
		
		sb.append(">");
		System.out.println(sb);
	}
}
