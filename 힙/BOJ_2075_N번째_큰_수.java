package 힙;

/*
[링크]
https://www.acmicpc.net/problem/2075

[시간]


[아이디어]
맨 윗줄을 최소 힙에 넣고,최소 힙에서 하나 뺄 때마다 해당 인덱스의 포인터를 아래로 내림.
 힙에 넣을 때 Node로 넣어야 함.
 value, index
Comparable 구현 -> value 순 정렬

[시간복잡도]
최소 힙 -> n log n


[실수]


[검색]
java Comparable 구현방법 및 Comparator과의 비교
자바 힙 사용 방법

[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2075_N번째_큰_수 {
	public static class Node implements Comparable<Node>{
		public int value;
		public int index;


		@Override
		public int compareTo(Node o) {
			return -Integer.compare(value, o.value);

		}

		public Node(int value, int index) {
			this.value = value;
			this.index = index;
		}

		@Override
		public String toString() {
			return "Node{" +
					"value=" + value +
					", index=" + index +
					'}';
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N;
		int[][] arr;
		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		PriorityQueue<Node> maxHeap = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (i == N-1) {
					maxHeap.add(new Node(arr[i][j], j));
				}
			}
		}

		int[] rowIndex = new int[N];
		Arrays.fill(rowIndex, N-1);
		int answer = 0;
		for(int i = 0; i < N; i++){
			Node node = maxHeap.poll();
//			System.out.println("poll");
//			System.out.println(node);
			answer = node.value;
			int targetIndex = node.index;
			rowIndex[targetIndex]--;
			if (rowIndex[targetIndex] >= 0)
				maxHeap.add(new Node(arr[rowIndex[targetIndex]][targetIndex], targetIndex));

//			System.out.println(maxHeap);
		}

		System.out.println(answer);
	}
}