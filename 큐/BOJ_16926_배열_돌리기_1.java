package 큐;

/*
https://www.acmicpc.net/problem/16926

[아이디어]
요세푸스 문제랑 비슷하다.
껍질 부분 큐에 넣고 R번 넣고빼기 한다.
안으로 들어가는 것은 재귀로 한다.

[시간복잡도]
O(nr) 또는 O(mr)


[다른 사람 풀이]
재귀로 안 돌고 for문으로 들어가면서 풀었다.
문제 조건에서 m또는 n 중 작은 수는 짝수라는 조건이 있어서 조건문으로 풀기 좀 더 좋을듯하다.

[실수]
N, M, R을 대문자로 하기 귀찮아서 n, m, r로 했는데
배열 인덱스 접근하는 로컬변수 r과 섞여서 이상한 값이 나왔다.
-> 문제에서 주는 값은 귀찮아도 대문자로 하자.

*/


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_16926_배열_돌리기_1 {
	static int[][] arr;
	static int n, m, R;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//solve
		int colSize = n;
		int rowSize = m;
		int gap = 0;
		Queue<Integer> q = new ArrayDeque<>();
		while (Math.min(colSize, rowSize) > 0) {

			int c = gap;
			int r = gap;
			for(int i = 0; i < rowSize-1; i++) {
				q.offer(arr[c][r+i]);
			}
			
			for(int i = 0; i < colSize-1; i++) {
				q.offer(arr[c+i][r+rowSize-1]);
			}
			
			for(int i = 0; i < rowSize-1; i++) {
				q.offer(arr[c+colSize-1][r+rowSize-1-i]);
			}
			
			for(int i = 0; i < colSize-1; i++) {
				q.offer(arr[c+colSize-1-i][r]);
			}

			for(int i = 0; i < R; i++) {
				q.offer(q.poll());
			}
			
			for(int i = 0; i < rowSize-1; i++) {
				arr[c][r+i] = q.poll();
			}
			
			for(int i = 0; i < colSize-1; i++) {
				arr[c+i][r+rowSize-1] = q.poll();
			}
			
			for(int i = 0; i < rowSize-1; i++) {
				arr[c+colSize-1][r+rowSize-1-i] = q.poll();
			}
			
			for(int i = 0; i < colSize-1; i++) {
				arr[c+colSize-1-i][r] = q.poll();
			}
			
			gap++;
			colSize = n - gap*2;
			rowSize = m - gap*2;
		}
		
		// print
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(arr[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}