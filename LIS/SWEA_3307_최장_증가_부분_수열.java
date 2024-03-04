package LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3307_최장_증가_부분_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());

            }

            // n번째 요소가 마지막일때 들어갈 수 있는 최소 숫자
            int[] dp = new int[N];

            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = arr[0];

            int count = 0;
            for (int i = 1; i < arr.length; i++) {
                int found = Arrays.binarySearch(dp, 0, i, arr[i]);
                int idx = Math.abs(found+1);
                dp[idx] = arr[i];
                count = Math.max(count, idx+1);
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
