package 문자열매칭.KMP;

/*
[링크]
https://www.acmicpc.net/problem/1786

[시간]
14:47 ~ 15:55

[아이디어]
KMP

[시간복잡도]


[실수]


[검색]
반례
실패함수를 잘못 작성했다.
실패 함수 작성 시 이전 값보다 큰 것이 아니라 접두사와 접미사 개수가 몇 개가 같은지를 적어둬야 함

[다른 사람 코드]
1. i가 끝까지 돌면 끝나므로 for문을 사용할 수도 있다.
	https://www.acmicpc.net/source/19053052

*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1786_찾기 {
	public static void main(String[] args) throws Exception{
//		String input = "aaaaa\n" +
//				"aaaaaa";
//			        "abacabab";

//				"ababacab";
//				"a";

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new StringReader(input));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String T = br.readLine();
		String P = br.readLine();



		int[] table = new int[P.length()];

		// make table
		int i = 1;
		int j = 0;
		while (i < P.length()){
//			System.out.println("i, j, P[i], P[j] = " + i +", "+ j +", "+ P.charAt(i) +", " + P.charAt(j));
			if (P.charAt(i) == P.charAt(j)){
				// 여기 틀림
				// 이전 값보다 큰 것이 아니라 접두사와 접미사 개수가 몇 개가 같은지를 적어둬야 함
//				table[i] = table[i - 1] + 1;
				table[i] = j + 1;
				i++;
				j++;
			} else {
				j = j-1;
				if (j < 0 ) {
					j = 0;
					i++;
					continue;
				}

				// 어디서부터 다시 찾아야 할지
				// 패턴 매칭이 되어 있는 다음부터
				j = table[j];
			}
		}
//		System.out.println(Arrays.toString(table));

		// search text
		i = 0;
		j = 0;
		int count = 0;
		List<Integer> matchingIndex = new ArrayList<>();
		while (i < T.length()){
//			System.out.println("i, j, T[i], P[j] = " + i +", "+ j +", "+ T.charAt(i) +", " + P.charAt(j));
			if (T.charAt(i) == P.charAt(j)){
				i++;
				j++;
				// 모든 문자열이 매칭됐다면
				if (j >= P.length()){
					count++;
					matchingIndex.add(i-P.length()+1);
					j = table[j-1];
				}
			} else {
				j = j-1;
				if (j < 0 ) {
					j = 0;
					i++;
					continue;
				}

				// 어디서부터 다시 찾아야 할지
				// 패턴 매칭이 되어 있는 다음부터
				j = table[j];
			}
		}

		sb.append(count).append("\n");
		for (Integer index : matchingIndex
		) {
			sb.append(index).append(" ");
		}

		System.out.println(sb);
	}
}