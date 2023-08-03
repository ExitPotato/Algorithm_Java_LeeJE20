package 정렬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class SWEA_1208_Flatten
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T= 10;

		int[]arr = new int[100];

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int dumpCount = Integer.parseInt(br.readLine());

			// 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 풀이
			int gap = 999;
			Arrays.sort(arr);
			//while dumpCount>0 and gap >1
			while (dumpCount>0 && gap >1) {
				// sort(오름차순)
				// max(마지막 요소) -= 1
				arr[arr.length-1]-=1;
				// min(첫번째 요소_ += 1
				arr[0]+=1;
				// gap = max-min
				Arrays.sort(arr);
				gap = arr[arr.length-1] - arr[0];
				dumpCount--;
			}
			
			sb.append("#").append(test_case).append(" ").append(gap).append("\n");
		}
		System.out.println(sb);
	}
}