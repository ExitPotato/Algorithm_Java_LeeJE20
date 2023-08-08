package 큐;

/*
[다른 사람 풀이]
1, 큐를 배열로 선언(size는 n) -> 원형 큐
2. 제거: head++, head %= n
*/

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2164_카드2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Queue<Integer> q = new ArrayDeque();
		for (Integer i = 1; i <= n; i++){
			q.add(i);
		}


		while(q.size()>1){
			q.poll();
			q.add(q.poll());
		}

		System.out.println(q.poll());
	}
}
