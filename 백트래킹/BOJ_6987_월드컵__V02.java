package 백트래킹;
/*
[링크]
https://www.acmicpc.net/problem/6987

[시간]
2024-06-15
23:03~ 23:31

[아이디어]
수리적 연산.. 믿을 수 없다. 승 무 패 개수만으로 헤아릴 수 있을까?

15개의 경기를 하나하나 하면서.. 승패 표를 만들어나가야 한다.
이때 결과에서 빼나가면서 모두 0인 표가 만들엊빌 수 있는지를 조사해야 한다.

깊이? 가 15인 트리를 만들어나가는 구조이다.

만약 abc 세 국가라면 아래같은 느낌이다.
      a-b경기  a-c경기  b-c 경기

      a승/b패
      무
      b승/a패
각 단계마다 3가지 경우의 수가 있으므로 총 노드 수는 3*3*3 =27개의 노드.
문제는 depth가 15이지만 조건이 정해져 있으므로 그정도로 깊진 않겠다.
백트레킹인 이유는 가다가 안 되면 앞으로 돌아와야 하기 때문이다.


슈도코드
각 조합을 만든다. 6개의 국가중 2개를 뽑는것. for문 2개로 가능!
함수에서 승, 무, 패를 각각 진행해나가면서 체크한다.

시작 - 23:10



[시간복잡도]


[실수]
1트에 성공!

[검색]


[보완점]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵__V02 {

    public static class GameResult {
        int win;
        int draw;
        int lose;

        public GameResult(int win, int draw, int lose) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        GameResult[][] games = new GameResult[4][6];
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                int w = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                games[i][j] = new GameResult(w, d, l);
            }
        }

        StringBuilder sb = new StringBuilder();

        int[][] combination = new int[15][2];

        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                combination[count][0] = i;
                combination[count][1] = j;
                count++;
            }
        }

        for (GameResult[] game : games) {
            sb.append(isPossible(game, combination, 0)).append(" ");
        }

        System.out.println(sb);
    }

    public static int isPossible(GameResult[] game, int[][] combination, int order) {
        if (order == 15) {
            for (int i = 0; i < 6; i++) {
                if (game[i].win != 0 || game[i].draw != 0 || game[i].lose != 0) {
                    return 0;
                }
            }
            return 1;
        }

        int left = combination[order][0];
        int right = combination[order][1];

        int result = 0;
        // 왼쪽기준
        // 승
        if (game[left].win > 0 && game[right].lose > 0) {
            game[left].win--;
            game[right].lose--;
            result = isPossible(game, combination, order + 1);
            game[left].win++;
            game[right].lose++;

        }
        if (result == 1) {
            return result;
        }
        // 무
        if (game[left].draw > 0 && game[right].draw > 0) {
            game[left].draw--;
            game[right].draw--;
            result = isPossible(game, combination, order + 1);
            game[left].draw++;
            game[right].draw++;

        }
        if (result == 1) {
            return result;
        }
        // 패
        if (game[left].lose > 0 && game[right].win > 0) {
            game[left].lose--;
            game[right].win--;
            result = isPossible(game, combination, order + 1);
            game[left].lose++;
            game[right].win++;
        }
        return result;
    }
}
