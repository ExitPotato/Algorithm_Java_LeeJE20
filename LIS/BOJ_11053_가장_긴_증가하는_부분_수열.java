/*
25.04.22 답지 보고 풀었음

TODO: list 안 쓰고 배열로 하면서 binarySearch는 인덱스 줘서 하기, 안 쓰는 변수 청소
*/
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()) + 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        
        for (int i = 1 ; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 풀이
        
        // index번째 숫자로 끝났을 때의 수열 최대 길이
        int[] maxLengthEndsI = new int[N];
        
        // 수열 최대 길이가 index일때의 마지막 숫자의 최솟값
        // 인덱스: 수열의 최대 길이
        // 값: 인덱스 길이일때 최소의 마지막 수
        List<Integer> minNumberWithLengthI = new ArrayList<>();
        minNumberWithLengthI.add(0);
        
        for (int i = 1 ; i < N; i++) {
            int num = arr[i];
            
            // 마지막 숫자 목록에서 해당 숫자가 들어갈 수 있는 위치를 찾는다.
            // [현재 숫자보다 작은 가장 큰 수]로 끝나는 수열의 최대 길이를 찾기 위함
            
            // 예시
            // 인덱스(길이)  0 1 2 3 4 5
            // 값(최소 숫자) 0 1 3 5 6 7 
            int[] minNumberPerLength = minNumberWithLengthI.stream()
                .mapToInt(x -> x)
                .toArray();
            int location = Arrays.binarySearch(minNumberPerLength, num);
            // location = location >= 0 ? location : -(location + 1);
            if(location >= 0) {
                // 이미 현재 숫자로 끝나는 최장 수열 찾음
                continue;
            }
            int insertPosition = -(location + 1);
            
            // 
            if (insertPosition >= minNumberWithLengthI.size()) {
                minNumberWithLengthI.add(num);
            } else {
                // 해당 길이 수열에서 더 작은 수로 끝나는 경우 발견
                minNumberWithLengthI.set(insertPosition, num);
            }
        }
        
        System.out.println(minNumberWithLengthI.size()-1);
    }
}
