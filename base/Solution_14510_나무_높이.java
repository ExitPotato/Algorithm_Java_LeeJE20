package base;

/*
[링크]
https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AYFofW8qpXYDFAR4
[시간]


[아이디어]
0, 1, 2를 조합하여 n 만들기

2
0 0 -> 3일
    0  1  2
0일

대충 1, 2만 남기고 채우기
그 다음부터 1+2 개수만큼씩 빼기

1만 남았을 경우 -> 1일 3일 이렇게 줘야함
1이 1개: 1
1이 2개: 101
1이 3개: 10101
2n-1

2가 1개: 0 2 이런식 -> 2 일
2가 2개: 1 2 1 이런식 3일
2가 3개: 1 2 1 2 이런식 4일
2가 4개: 1 2 1 2 0 2 6일
2가 5개: 1 2 1 2 1 2 1 7일
2가 6개: 1 2 1 2 1 2 1 2 8일
2가 7개: 1 2 1 2 1 2 1 2 0 2 10일
개수+(개수+2)/3


[시간복잡도]

[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_14510_나무_높이 {
	static int T, N, M;
	static int[] trees;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
//			System.out.println("================");
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());

			trees = new int[N];
			int maxHeight = 0;
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				int height = Integer.parseInt(st.nextToken());
				trees[i] = height;
				maxHeight = Math.max(height, maxHeight);
			}

			// solve

//			System.out.println(maxHeight);

			// 2와 1이 총 몇 개 필요한지
			int[] leftHeightCount = new int[3];
			for (int i = 0; i < N; i++) {
				int height = maxHeight - trees[i];
//				System.out.print(height+", ");

				int share = height /2;
				int remainder = height%2;
				leftHeightCount[1] += remainder;
				leftHeightCount[2] += share;
			}

			// 1, 2만 남기기
			int days = 0;
//			System.out.println();
//			System.out.println("1, 2 몇 개 필요");
//			System.out.println(Arrays.toString(trees));
//			System.out.println(days);

			// 1 또는 2만 남기기
			int toDeleteHeightCount = Math.min(leftHeightCount[1], leftHeightCount[2]);
			leftHeightCount[1] -= toDeleteHeightCount;
			leftHeightCount[2] -= toDeleteHeightCount;
			days+= (toDeleteHeightCount*2);

//			System.out.println("1 또는 2만 남기기");
//			System.out.println(Arrays.toString(leftHeightCount));
//			System.out.println(days);


			if (leftHeightCount[1] == 0 && leftHeightCount[2] == 0){
//				System.out.println("0만 남은 경우");
			}
			else if(leftHeightCount[1]>0){// 1만 남은 경우
//				System.out.println("1만 남은 경우");
				int left = leftHeightCount[1];
				days += (left*2 -1);
			}else{ // 2만 남은 경우
//				System.out.println("2만 남은 경우");
				int left = leftHeightCount[2];
				int addedDays = left+(left+2)/3;
//				System.out.println(addedDays);
				days += addedDays;
			}
			sb.append(days).append("\n");
		}
		System.out.println(sb);
	}
}
