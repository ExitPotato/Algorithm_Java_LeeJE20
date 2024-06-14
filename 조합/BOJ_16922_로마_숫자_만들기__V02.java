package 조합;

/*
[링크]
https://www.acmicpc.net/problem/16922

[시간]
24.06.15 12:38~ 01:21

[아이디어]
음.. 중복조합인가?
서로 다른 수를 구해야 하는데, 서로 다른 문자가 같은 숫자를 나타낼 수 있을까?
직관으로는 안 될 것 같다.

GPT한테 물어보니 1, 5, 10, 50은 서로가 동시에 배수이지 않다. (대충 다르다는 말이다)
그러므로 더하거나 빼서 같은 값을 만들 수 없다.

다음 수식을 만족하는 값이 있을까?
A + 5B + 10C + 50D = a + 5b + 10c + 50d
정리하면 다음 수식을 만족하는 값이 있을까?
(A-a) + 5(B-b) + 10(C-c) + 50 (D-d) = 0

즉 다음 문제와 같다.
q + 5w + 10e + 50r = 0 을 만족하는 q, w, e, r이 존재하는가?

1, 5, 10, 50은 전부 다르므로 (정수 계수의 선형 결합이 0이 되려면, 각 항목의 계수는 독립적이어야 합니다)
위 수식을 만족하려면 q, w, e, r 은 모두 0이어야 한다.
즉, A=a, B=b, C=c, D=d가 성립한다.
즉, 서로다른 문자가 같은 숫자를 나타낼 수 없다.

라고 GPT가 했는데 중복처리를 해야 문제가 풀린다...!! 뭐냐 GPT 날 속였어...
교훈: 수학적으로 잘 모르겠으면 직감으로 하지 말고 그냥 코드로 안전하게 해결하자.

[시간복잡도]
4H20

84ms
12076kb

[실수]
최종 결과 개수를 구할 때 result를 파라미터로 넣고 다음처럼 했다.

func (result) {
    for() {
        result += func(result);
    }
    return result
}

이렇게 하면 result 값이 배열을 돌면서 계속 바뀌므로 원하는 값이 나올 수 없다.

순조부에서 개수 구할때는 기저조건에서 리턴에서 1 리턴하고, for문 부분에서 그 값들을 더해서 for문 이후에 리턴한다.


[검색]
교훈: 수학적으로 잘 모르겠으면 직감으로 하지 말고 그냥 코드로 안전하게 해결하자.

[보완점]
중복수 체크 시 최대 수가 20*50 = 1000 이므로 Map 대신 그냥 배열로 해도 된다.


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_16922_로마_숫자_만들기__V02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] target = {1, 5, 10, 50};
        Map<Integer, Boolean> selected = new HashMap<>();
        System.out.println(combinationWRepetition(target,  N, 0, 0, selected));
    }

    public static int combinationWRepetition(int[] target, int leftPickCount, int startLetter, int pickedSum, Map<Integer, Boolean> selected) {
        if (leftPickCount == 0) {
            if (selected.containsKey(pickedSum)){
                return 0;
            }
            selected.put(pickedSum, true);
            return 1;
        }

        int letterCount = target.length;
        int result = 0;
        for (int i = startLetter; i < letterCount; i++) {
            result += combinationWRepetition(target,leftPickCount - 1, i, pickedSum + target[i], selected);
        }
        return result;
    }
}
