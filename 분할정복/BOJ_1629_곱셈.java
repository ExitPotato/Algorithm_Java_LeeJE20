package com.company.Algorithm_Java_LeeJE20.분할정복;

/*
[링크]
https://www.acmicpc.net/problem/1629

[시간]
00:23~00:33

[아이디어]
분할정복

[시간복잡도]
lon(B)

[실수]
int로 하면 안 되는데 long으로 하니가 풀림

[검색]
(A * B) mod C = (A mod C * B mod C) mod C

[다른 사람 코드]
똑같다

*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629_곱셈 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();


		st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());

		System.out.println(power(A, B, C));
	}

	private static long power(long A, long B, long C){
		if(B==1) return A % C;
		long tmp = power(A, B/2, C) % C;
		if (B % 2 == 0){
			return (tmp*tmp)%C;
		} else{
			return ((tmp*tmp)%C * (A %C))%C;
		}
	}
}