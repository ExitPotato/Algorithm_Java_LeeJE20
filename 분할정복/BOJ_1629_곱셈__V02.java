package 분할정복;



/*
[링크]
https://www.acmicpc.net/problem/1629

자연수 A를 B번 곱한 수를 알고 싶다. 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다.
첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.

[시간]
2024-06-15 23:53 ~ 00:04

[아이디어]
B를 반씩 나눠가면서 한다

B가 1이면
결과 = A

B가 짝수라면
결과 = A^(B/2) * A*(B/2)

B가 홀수라면

결과 = A^(B/2) * A*(B/2) * A^1

분할정복 문제.
풀이시작: 23:56

[시간복잡도]


[실수]
B 가 홀수일 때 A를 한번 더 곱하는 과정에서 실수로 A 대신 half를 곱했다..
if (B % 2 == 1) {
    result = (result * A) % C;
}

[검색]


[보완점]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629_곱셈__V02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        A = A % C;
        System.out.println(divideAndConquer(A, B, C));
    }

    public static long divideAndConquer(int A, int B, int C) {
        if (B == 1) {
            return A;
        }

        long half = divideAndConquer(A, B / 2, C) % C;
        long result = (half * half) % C;
        if (B % 2 == 1) {
            result = (result * A) % C;
        }
        return result;
    }
}
