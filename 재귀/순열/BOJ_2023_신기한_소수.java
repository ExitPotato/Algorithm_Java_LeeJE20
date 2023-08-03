package 재귀.순열;

/*
https://www.acmicpc.net/problem/2023

[아이디어]
첫 자리가 0이 아닌 중복순열
백트래킹
중복순열 구하면서 앞에서부터 소수인지 판별
소수 아니면 백트래킹


[시간복잡도]
O(n!) -> n이 10이므로 가능!
[공간복잡도]
가능

[검색]
소수인지 검사하는 방법
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2023_신기한_소수 {
	static boolean[] arr;
	static int N;
	static int sqrt;
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		solve("");
		System.out.println(sb);
		
	}
	
	// 중복순열, 0으로 시작하면 안됨
	static void solve(String picked) {
		if (picked.length() == N) {
			if (isPrime(Integer.parseInt(picked))) {
				sb.append(picked).append("\n");
			}
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			picked += i;
			if (picked.charAt(0) != '0' && isPrime(Integer.parseInt(picked))) 
				solve(picked);
			picked = picked.substring(0, picked.length()-1);
		}
		
	}
	
	static boolean isPrime(int n) {
		if (n == 1) return false;
		if (n == 2) return true;
		
		sqrt = (int)Math.sqrt((double)n);
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}
}
