package 스택;


/*
https://www.acmicpc.net/problem/6198

10  3  7  4  12 2   
[1][2][3][4][5][6]			sum
					<- 10	 0
10					<- 3	 0
10  3				<- 7	 +1	
10	 
10  7				<- 4	 +1
10  7  4            <- 12	 +2
10	7
10
 
12					<- 2
12  2						 +1

[아이디어]
탑 문제랑 비슷하다.
스택에 넣기 전에 값 비교하고, 안에 있는 값 뺄 것 있으면 빼고, 아니면 현재 값 넣는다.

[실수]
범위가 int를 벗어나서 처음에 틀렸다.
덧셈, 곱셈 등이 결과로 나오는 경우 범위를 확인해봐야 한다.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_6198_옥상_정원_꾸미기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Stack<Integer> s = new Stack<>();
		
		long sum = 0;
		
		for (int i = 0; i < n; i++) {
			Integer num = Integer.parseInt(br.readLine());
//			if (s.empty()) {
//				s.add(num);
//				continue;
//			}
//			
//			if (!s.empty() && s.peek() > num) {
//				sum += (s.size());
//				s.add(num);
//				System.out.println(s);
//				continue;
//			}
			while(!s.empty() && s.peek() <= num) {
				s.pop();	
			}
			sum += (s.size());
			s.add(num);
		}
		
		System.out.println(sum);
	}
}
