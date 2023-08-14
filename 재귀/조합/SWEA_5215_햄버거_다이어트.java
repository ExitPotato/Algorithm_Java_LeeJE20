package 재귀.조합;

/*
[링크]
https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYmp6Fk6f5EDFARi&contestProbId=AWT-lPB6dHUDFAVT&probBoxId=AYndnB5KMSUDFARi+&type=PROBLEM&problemBoxTitle=8%EC%9B%94+10%EC%9D%BC&problemBoxCnt=++3+

[시간]


[아이디어]
DP 가방 문제로 풀면 될 것 같은데 풀이가 생각 안 남....
그래서 부분집합으로 풀어보자.

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int score;
	int cal;
	
	public Node(int score, int cal) {
		this.score = score;
		this.cal = cal;
	}
	
	
}

public class SWEA_5215_햄버거_다이어트 {
	static int T, N, L;
	static Node[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			arr = new Node[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// solve
			int answer = 0;
			for (int i = 0; i < (1<<N); i++) {
				int sumScore = 0;
				int sumCal = 0;
				for (int j = 0; j < N; j++) {
					if ((i & (1<<j)) == 0) continue;
					sumScore += arr[j].score;
					sumCal += arr[j].cal;
					
					if (sumCal > L) break;
				}
				
				if (sumCal > L) continue;
				
				answer = Math.max(sumScore, answer);
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);	
	}
}
