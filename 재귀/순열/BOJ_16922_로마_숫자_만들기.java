package 재귀.순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_16922_로마_숫자_만들기 {
	static int count = 0;
	static int MAX_VALUE = 4;
	static int[] arr =  {1, 5, 10, 50};
	static Map<Integer, Boolean> map = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int input = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < MAX_VALUE; i++) {
			solve(arr[i], ""+i, i, input-1);
		}
		
		System.out.println(count);
		
	}
	
	public static void solve(int sum, String picked, int pick, int left) {
		if (left == 0) {
//			System.out.println(picked +" : "+sum );
			if (!map.containsKey(sum)) {
				map.put(sum, true);
				count ++;
			}
			
		}
		else {
			for (int i = pick; i < MAX_VALUE; i++) {
				solve(sum + arr[i], picked+i, i, left-1);
			}
		}
	}
}
