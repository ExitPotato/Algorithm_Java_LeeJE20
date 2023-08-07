package 완전탐색;



/*
https://www.acmicpc.net/problem/1339

[아이디어]
입력받고 문자수만큼 9부터 뽑는다.
전체 경우의 수 다 해서 완전탐색이 제일 먼저 생각난다...



[시간복잡도]
N은 10까지다.
10! = 3백6십만쯤 

1억번에 1초

완전탐색해도 되겠다.

순열 만들면서 체크하자.

[다른 사람 코드에서 배울 점]



*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class BOJ_1339_단어_수학 {

	static Map<Character, Integer> map = new HashMap<>();
	static int N;
	static String[] input;
	static int[] picked = new int[10];
	static boolean[] isSelected = new boolean[10];
	static int size = 0;
	
	static int maxAnswer = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new String[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = br.readLine();
		}
		
		

		
		Set<Character> set = new HashSet<>();
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <input[i].length(); j++) {
				set.add(input[i].charAt(j));
			}
		}
		size = set.size();
		
		Integer i = 0;
		for (Character c: set) {
			map.put(c, i++);
		}
//		System.out.println("size : "+size);
		
		// 입력 받고 알파벳 개수
		// 9부터 알파벳 개수만큼 순열 구하기
		
		
		
//		System.out.println(map.entrySet());
		
		permutation(0);
		
		System.out.println(maxAnswer);
	}
	
	public static void permutation(int count) {
		if (count >= size) {
			int sum = 0;
			for(String word: input) {
				int wordNum = 0;
				for (int i = 0; i < word.length(); i++) {
					wordNum = wordNum*10 + picked[map.get(word.charAt(i))];
					
				}
				
//				System.out.println(word+" : "+wordNum);
				sum += wordNum;
			}
			
			maxAnswer = Math.max(sum, maxAnswer);
			
			return;
		}
		
		for (int i = 9; i > 9-size; i--) {
			if (isSelected[i] == true) continue;
			isSelected[i] = true;
			picked[count] = i;
			permutation(count + 1);
			isSelected[i] = false;
		}
	}
	
	
}
