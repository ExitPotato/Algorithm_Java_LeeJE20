package 그래프.bfs;
/*
[링크]
https://www.acmicpc.net/problem/2178
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
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로_탐색 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N, M;
		char[][] arr;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		int[] dy = {1, -1, 0, 0};
		int[] dx = {0, 0, 1, -1};
		
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {0,0});
		arr[0][0] = '0';
		int answer = 0;
bfs:	while(!q.isEmpty()) {
			answer++;
			int size = q.size();
			for(int i = 0; i < size; i++) {
				int[] current = q.poll();
				int cy = current[0];
				int cx = current[1];
				
				if (cy == N-1 && cx == M-1) break bfs;
				
				for(int k = 0; k < 4; k++) {
					int yy = dy[k];
					int xx = dx[k];
					
					int y = cy+yy;
					int x = cx+xx;
					
					
					// 범위
					if(x < 0 || x >= M || y < 0 || y >= N) continue;
					// 방문
					if (arr[y][x] == '0') continue;
					
					arr[y][x] = '0';
					q.offer(new int[] {y,x});
				}
			}
		}
		
		System.out.println(answer);
		
	}
}
