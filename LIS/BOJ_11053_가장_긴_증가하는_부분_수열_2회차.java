/*
22:37
2회차



*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 풀이
        
        // 긴 증가하는 부분 수열 길이가 index일때, 수열 마지막 숫자의 최솟값
        int[] minLastNumPerLength = new int[N+1];
        Arrays.fill(minLastNumPerLength, Integer.MAX_VALUE);
        
        // 지금까지 찾은 수열의 최대 길이
        int maxLength = 0; 
        
        for (int i = 0; i < N; i++) {
            // System.out.println(Arrays.toString(minLastNumPerLength));
            int num = arr[i];
            // 해당 숫자를 마지막으로 하는 수열 최대 길이를 구하자
            // 해당 숫자를 마지막으로 하는 수열 최대 길이 = 해당 보다 작은 최댓값 수로 끝나는 수열 최대 길이 + 1
            
            int location = Arrays.binarySearch(minLastNumPerLength, 1, maxLength + 1, num);
            
            // 이미 해당 숫자를 마지막으로 하는 수열 최대 길이 존재
            if (location >= 0) {
                continue;
            }
            
            
            location = -(location+1);
            // System.out.println("num, location, maxLength: "+ num+", "+location+", "+maxLength);
            
            // 현재 찾은 값이 해당 길이보다 작다면
            if (num < minLastNumPerLength[location]) {
                minLastNumPerLength[location] = num;
            }
            
            // 새로운 길이
            if (location > maxLength) {
                maxLength = location;
            }
            
        }
        
        System.out.println(maxLength);
        
        
    }
}
