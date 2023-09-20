package 그래프.위상정렬;
/*
[링크]
https://www.acmicpc.net/problem/2252

[시간]


[아이디어]
방금 익힌 위상정렬 연습해보자

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]
1. g[current] = null 안 해도 된다.
2. edges -- 하자마자 0 체크 하면 for문 돌면서 진입차수 0인것 안 찾아도 된다.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.imageio.metadata.IIOInvalidTreeException;

import com.sun.org.apache.xml.internal.utils.SystemIDResolver;

public class Main_2252_즐_세우기3 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new List[N+1];
		int[] count = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if (graph[from] ==  null) graph[from] = new ArrayList<Integer>();
			graph[from].add(to);
			count[to]++;
			
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if(count[i] == 0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			Integer current = queue.poll();
			sb.append(current).append(" ");
			
			if (graph[current] != null) {
				for (int i = 0; i < graph[current].size(); i++) {
					Integer adjVertex = graph[current].get(i);
					count[adjVertex]--;
					if(count[adjVertex] == 0) {
						queue.offer(adjVertex);
					}
				}
			}
		}
		System.out.println(sb);
	}
}
