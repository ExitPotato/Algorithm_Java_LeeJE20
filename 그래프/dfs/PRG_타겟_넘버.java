/*
https://school.programmers.co.kr/learn/courses/30/lessons/43165

16:31 ~ 

[아이디어]
1. dfs로 해보자
완탐하자

숫자 20개 -> 2^20승: 1048576 -> 1초안에 가능

2. BFS도 가능한가? 몰라

3. 그냥 중복순열이기도 함.

[시간복잡도]
숫자 20개 -> 2^20승: 1048576 -> 1초안에 가능

[실수]
dfs에서 newAnswerCount를 안 만들고 answerCount에 계속 더해서 값이 이상하게 나옴
값을 새로 할당해줘야함
-> 파라미터로 받은 값은 다음 함수로 넘기기 할 때 값 변동하지 않도록 주의


[배운점]

[개선점]
리턴 값에 바로 함수 2개 돌리면서 sum할 수도 있다 


**/

class Solution {
    public int answer = 0;
    
    public int solution(int[] numbers, int target) {
        int curretIndex = -1;
        int sum = 0;
        int answerCount = 0;
        answerCount += dfs(target, numbers, curretIndex + 1, sum, true, answerCount);
        answerCount += dfs(target, numbers, curretIndex + 1, sum, false, answerCount);
        // System.out.println(answerCount);
        return answerCount;
    }
    
    public int dfs(int target, int[] numbers, int currentIndex, int sum, boolean isMinus, int answerCount) {
        sum = isMinus ? sum - numbers[currentIndex] : sum + numbers[currentIndex];
        if (currentIndex == numbers.length-1 && sum == target) {
            answer += 1;
            return 1;
        }
        if (currentIndex == numbers.length-1) {
            return 0;
        }
        int newAnswerCount = 0;
        
        newAnswerCount += dfs(target, numbers, currentIndex + 1, sum, true, answerCount);
        newAnswerCount += dfs(target, numbers, currentIndex + 1, sum, false, answerCount);

        return newAnswerCount;
        
    }
}
