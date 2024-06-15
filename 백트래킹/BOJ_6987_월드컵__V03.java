package 백트래킹;
/*
[링크]
https://www.acmicpc.net/problem/6987

[시간]
23:33~23:44

[아이디어]
리팩터링

[시간복잡도]


[실수]
배열에서 게임 결과 가져올 때 아래 코드에서 두번째 요소를 둘 다 0으로 쓰는 실수...
int leftResult = results[i][0];
int rightResult = results[i][1];

[검색]


[보완점]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵__V03 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][][] games = new int[4][6][3];
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    games[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int[][] combination = new int[15][2];

        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                combination[count][0] = i;
                combination[count][1] = j;
                count++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int[][] game : games) {
            sb.append(isPossible(game, combination, 0)).append(" ");
        }

        System.out.println(sb);
    }

    public static int isPossible(int[][] game, int[][] combination, int order) {
        if (order == 15) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game[i][j] != 0)
                        return 0;
                }
            }
            return 1;
        }

        int left = combination[order][0];
        int right = combination[order][1];

        int answer = 0;
        // 왼쪽 기준 게임 결과 - 승, 무, 패
        int[][] results = {{0, 2}, {1, 1}, {2, 0}};

        for (int i = 0; i < 3; i++) {
            int leftResult = results[i][0];
            int rightResult = results[i][1];

            // 가능한 게임이라면
            if (game[left][leftResult] > 0 && game[right][rightResult] > 0) {
                game[left][leftResult]--;
                game[right][rightResult]--;
                answer = isPossible(game, combination, order + 1);
                if (answer == 1) {
                    return answer;
                }
                game[left][leftResult]++;
                game[right][rightResult]++;
            }
        }

        return answer;
    }
}
