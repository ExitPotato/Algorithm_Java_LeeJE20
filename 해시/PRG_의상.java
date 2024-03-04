/**
16:39 ~ 16: 59

간단한 해시 문제
정답: 해시 개수들의곱 -1

[시간복잡도]
옷의 개수 n
해시 넣기 -> O(n)

[배운점] 
Map에서 foreach 루프 돌기 가능

[다른 사람 코드]
문제 조건 중 같은 이름을 가진 의상은 존재하지 않습니다 조건 -> 옷의 개수만 저장해도 됨
**/

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(String[][] clothes) {
        Map <String, List<String>> map = new HashMap <> ();
        for (String[] cloth: clothes) {
            if (map.containsKey(cloth[1])) {
                map.get(cloth[1]).add(cloth[0]);
            }
            else {
                List<String> list = new ArrayList<>();
                list.add(cloth[0]);
                map.put(cloth[1], list);
            }
        }
        
        int answer = 1;
        
        for(List<String> list: map.values()) {
            answer *= (list.size() + 1);
        }
        
        
        return answer - 1;
    }
}
