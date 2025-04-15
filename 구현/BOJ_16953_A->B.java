/*
bfs로 풀면 되겠군..


10:06 시작
10:19 제출
1트에 성공

1로 끝나는 수는 x에 1을 붙인 수이다.

끝에서부터 bfs를 해야겠다. 도착지점에서 출발지점으로 갈 수 있는지
근데 이걸 bfs로 하는게 말이 되나.. 그냥 하면 되는거 아닌가??
그리디처럼.. 그냥 1로 끝나는 수면 바로 1 떼버리고 그런 식으로..??
짝수면 나누기 2하고..

*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        int step = 1;
        boolean BChanged = false;
        while (A < B) {
            step++;
            BChanged = false;
            if (B % 10 == 1) {
                B /= 10;
                BChanged = true;
                continue;
            }
            
            if (B % 2 == 0) {
                B /= 2;
                BChanged = true;
                continue;
            }
            
            if (!BChanged) {
                break;
            }
        }
        
        int answer = A == B ? step : -1;
        System.out.println(answer);
    }
}
