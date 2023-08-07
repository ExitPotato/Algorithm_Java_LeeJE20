package 스택;



/*
[아이디어]

결과 배열 변수;

뒤에서부터 훑는다.
스택이 비어있다면: 넣고 끝
스택이 있다면: 스택에 있는 값보다 현재 값이 같거나 높으면 결과에 현재 높이를 넣는다. 


[시간복잡도]
O(n)

[실수]
1. s.peek() 하기 전에 s.empty 체크를 안 해서 null pointer exception
	-> 자료구조 사용 시에는 

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Node{
	int index;
	int value;
	
	Node(int index, int value){
		this.index = index;
		this.value = value;
	}
}

public class BOJ_2493_탑 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] answer = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Node> s = new Stack<>();
		
		for (int i = N-1; i >=0; i--) {
			Node n = new Node(i, arr[i]);
			if (s.empty()) {
				s.add(n);
				continue;
			}
			
			while (!s.empty() && s.peek().value <= n.value) {
				answer[s.peek().index] = n.index+1;
				s.pop();
			}
			s.add(n);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i : answer) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}
