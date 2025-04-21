
/*
3트
23:27 시작
페이지교체 알고리즘이다.


배열에 넣어서 하면 되겠다. N이 20이라서 정렬 많이 해도됨.

어떤 학생이 배열에 있는지 찾기 -> 배열 크기가 20이라서 그냥 루프 돌면서 확인해.

int[N][3] photoes= n번 사진틀에는 x번 학생이 y번 추천받고 z시간에 마지막으로 추천받음


23: 38 구현 시작


문제를 잘못 이해했다!!
학생들 중 게시된 지 가장 오래된 사진을 삭제한다 -> 마지막으로 추천받은 시간 말하는게 아님!! 시간을 갱신해주면 안 되겠다..

12: 10 종료..

TODO: 학생 찾을 때 해시로 찾을 수도 있겠다
*/

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int voteCount = Integer.parseInt(st.nextToken());
    
        int[] votes = new int[voteCount];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < voteCount; i++) {
            votes[i] = Integer.parseInt(st.nextToken());
        }
        
        // 풀이
        final int STUDENT = 0;
        final int UPVOTES = 1;
        final int TIME = 2;
        
        int[][] photoes = new int[N][3];
        
        for (int i = 0; i < voteCount; i++) {
            int currentStudent = votes[i];
            
            // (새로운) 학생을 넣을 수 있는 인덱스
            int insertIndex = -1;
            boolean updateTime = true;
            
            // 배열에 있으면 넣기 or 마지막에 추가    
            for (int n = 0; n < N; n++) {
                // 배열에 존재
                if (photoes[n][STUDENT] == currentStudent ) {
                    insertIndex = n;
                    updateTime = false;
                    break;
                }
                if (insertIndex == -1 && photoes[n][STUDENT] == 0) {
                    insertIndex = n;
                }
            }
            
            
            if (insertIndex == -1) {
                // 배열에 없음 -> 정렬하고 내쫓기
                Arrays.sort(photoes, (o1, o2) -> {
                    // 추천횟수 같으면 오래된 사진 삭제
                    if (o1[UPVOTES] == o2[UPVOTES]) {
                        return o1[TIME] - o2[TIME];
                    }
                    return o1[UPVOTES] - o2[UPVOTES];
                });
                
                // System.out.println("정렬 결과");
                // for (int n = 0; n < N; n++) {
                    // System.out.println("학생, 추천, 시간: "+ photoes[n][STUDENT]+ ", " 
                    // + photoes[n][UPVOTES] + ", "
                    // + photoes[n][TIME ])    ;
                // }
                
                insertIndex = 0;
                photoes[insertIndex][UPVOTES] = 0;               
            }
            
            photoes[insertIndex][STUDENT] = currentStudent;
            photoes[insertIndex][UPVOTES]++;
            if (updateTime){
                photoes[insertIndex][TIME] = i;
            }
            
        }
        
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = photoes[i][STUDENT];
        }
        
        Arrays.sort(answer);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (answer[i] == 0) continue; // 비어있는 사진틀 제외
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
