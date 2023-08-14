package 트리;

/*
[링크]
https://www.acmicpc.net/problem/1240

[시간]
13:33 시작

[아이디어]
트리 두 노드 사이의 거리
그래프로 입력 받아서 너비우선 탐색을 한다.

[시간복잡도]

[공간복잡도]

[실수]


[검색]

[다른 사람 코드]
1. 노드별 높이를 구한다
2. 두 노드의 높이를 맞춘다. (높이 낮은 쪽은 부모로 거슬러 올라감)
3. 두 노드의 공통 조상을 구한다.
4. 답을 구한다.
*/



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node{
	int no;
	int weight;
	
	public Node(int no, int weight) {
		this.no = no;
		this.weight = weight;
	}

}


public class BOJ_1240_노드사이의_거리 {
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	
		int [][] graph = new int[n][n];
		boolean [] isVisited;

		
		// 그래프 구축
		int n1, n2, w;
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken()) -1;
			n2 = Integer.parseInt(st.nextToken())-1;
			w = Integer.parseInt(st.nextToken());
			graph[n1][n2] = w;
			graph[n2][n1] = w;
		}
		
		Deque<Node> s;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken()) -1;
			n2 = Integer.parseInt(st.nextToken()) -1;
			isVisited = new boolean[n];
			
			s = new ArrayDeque<>();
			
			isVisited[n1] = true;
			s.addLast(new Node(n1, 0));
			boolean isFound = false;
			
			while(!isFound) {
				Node now = s.pollLast();
			
				int[] tmp = graph[now.no];
				for(int j = 0; j < tmp.length; j++) {
					if (isVisited[j]) continue;
					if (tmp[j] == 0) continue; // 간선이 없다면
					
					if (j == n2) {
						sb.append(now.weight+tmp[j]).append("\n");
						isFound = true;
					}
					else {
						//스택에 넣기
						isVisited[j] = true;
						s.add(new Node(j, now.weight+tmp[j]));
					}
				}
			}
		}
		
		System.out.println(sb);
	}
}
