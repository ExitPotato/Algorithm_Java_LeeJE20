package 투포인터;

/*
[링크]
https://www.acmicpc.net/problem/2467

[시간]

[아이디어]
맨끝+맨첨 => 농도
농도가 0이면 출력

농도가 0보다 작으면 ->왼쪽 인덱스를 제일 왼쪽 제외하고 재설정.
	재설정 시 이분탐색 활용
	임시 농도가 농도보다 절댓값 작을때까지 계속 -> 없으면 농도였을때 값으로 출력

-100 -2 -1 1 103

-100 + 103 = 3 : 0보다 크므로 오른쪽 인덱스를 왼쪽으로 옮겨야함.
왼쪽 인덱스 고정, 오른쪽 인덱스 찾기 -> 어떤 값으로 해도 3보다 절댓값이 크다.


[시간복잡도]


[실수]


[검색]
1. 투포인터 풀이 -> 양쪽끝에서 적당히 찾으면 됨 o(n)
2. 이분탐색 -> nlogn으로 풀면 됨. 인덱스 하나 고정시켜두고 하나씩 찾기

[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467_용액 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N;
		int[] arr;
		N = Integer.parseInt(br.readLine().trim());

		st = new StringTokenizer(br.readLine().trim());
		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N-1;
		int target = arr[left]+arr[right];
		int[] answer = {arr[left], arr[right]};
		if (target == 0 ) {
			System.out.println(arr[left] + " "+ arr[right]);
		}
		int newTarget = target;
		while(left < right) {
			newTarget = arr[left]+arr[right];
			if (newTarget == 0) {
				answer[0] = arr[left];
				answer[1] = arr[right];
				break;
			}

			if (Math.abs(newTarget) < Math.abs(target)){
				target = newTarget;
				answer[0] = arr[left];
				answer[1] = arr[right];
			}

			if (newTarget < 0) {
				left++;
			} else {
				right--;
			}
		}

		System.out.println(answer[0] + " "+ answer[1]);
	}
}