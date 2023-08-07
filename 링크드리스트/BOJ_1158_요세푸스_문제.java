package 링크드리스트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	int value;
	Node next;
	
	Node(int value, Node next){
		this.value = value;
		this.next = next;
	}
}

public class BOJ_1158_요세푸스_문제 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Node tail = new Node(n, null);
		Node head = tail;
		for (int i = n-1; i >= 1; i--) {
			head = new Node(i, head);
		}
		
		
		tail.next = head;
		Node start = new Node(0, head);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		int count = 0; // 제거한 인원 수
		int index = 0;
		Node tmp = start;
		Node before = tmp;
		while (count < n) {

			for (int i = 0; i < k; i++) {
				before = tmp;
				tmp = tmp.next;
			}

			sb.append(tmp.value);
			before.next = tmp.next;
			count++;
			if (count < n) {
				sb.append(", ");
			}
			
		}
		sb.append(">");
		System.out.println(sb);
	}
}
