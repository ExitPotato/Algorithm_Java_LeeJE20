class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 1; // 1
        for (int i = 2; i <= number; i++) {
            int count = 2; // 1과 자기자신
            for (int j = 2; j <= (int) Math.sqrt(i); j++) {
                if (i % j == 0) {
                    count += 2;
                }
                
                if (j * j == i) {
                    count -= 1;
                }
            }
            
            answer += (count > limit ? power: count);    
        }
        
        return answer;
    }
}
    
