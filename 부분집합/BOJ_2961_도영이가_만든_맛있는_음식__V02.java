package 부분집합;

/*
[링크]
https://www.acmicpc.net/problem/2961

[시간]
24-06-15 22:16 ~ 22:51

[아이디어]
모두 다 계산해보자. 브루투포스!
재료를 어떤 것이든 1개 이상 선택해야하고, 중복 선택 불가
부분집합문제!

요리의 신맛과 쓴맛 범위 : int 이내다.

a b c

0 0 0   0
0 0 1   1
0 1 0   2
0 1 1   3
1 0 0   4
1 0 1   5
1 1 0   6
1 1 1   7

각 숫자마다 001, 010, 100과 비트 연산하면 된다.
001 = 1<<0
010 = 1<<1
100 = 1<<2

[시간복잡도]
O(2^N)

[실수]
비트마스킹 부분 코드.
어떤 원소가 집합에 있는지 확인할 때 전체 경우의 수를 for loop 돌리면서 비스 확인 해야한다. 실수로 N과 했다.

[검색]


[보완점]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961_도영이가_만든_맛있는_음식__V02 {

    public static class Ingredient {
        int sour;
        int bitter;

        Ingredient(int s, int b) {
            sour = s;
            bitter = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Ingredient[] ingredients = new Ingredient[N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ingredients[i] = new Ingredient(s, b);
        }

        // 전체 경우
        int whole = 1 << N;

        int answer = Integer.MAX_VALUE;

        for (int i = 1; i < whole; i++) {
            int sour = 1;
            int bitter = 0;
            // 어떤 재료 있는지 검사
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    sour *= ingredients[j].sour;
                    bitter += ingredients[j].bitter;
                }
            }
            answer = Math.min(answer, Math.abs(sour - bitter));
        }

        System.out.println(answer);
    }
}
