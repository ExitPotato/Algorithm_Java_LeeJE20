class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i < food.length; i++) {
            int target = food[i]/2;
            for (int j = 0; j < target; j++) {
                sb.append(i);
            }
        }
        
        StringBuilder last = new StringBuilder(sb).reverse();
        String answer = sb.append(0).append(last).toString();
        return answer;
    }
}
