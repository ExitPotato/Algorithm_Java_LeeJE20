import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        
        Arrays.sort(score);
        int index = score.length - m;
        int answer = 0;
        while (index >= 0) {
            answer += score[index] * m;
            index -= m;
        }
        return answer;
    }
}
