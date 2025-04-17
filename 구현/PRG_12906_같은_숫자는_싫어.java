import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        
        int before = -1;
        List<Integer> result = new ArrayList<>();
        for (int a: arr) {
            if (a == before) continue;
            result.add(a);
            before = a;
        }
        
        int[] answer = result.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}
