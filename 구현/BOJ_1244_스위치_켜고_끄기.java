package 구현;
/*
https://www.acmicpc.net/problem/1244

// 23.08.01 17:20 시작

그냥 구현 문제다.

남학생(1)
배수가 스위치 번호가 작은동안 바꾼다.

여학생(2)
투포인터: 범위를 늘려가며 같은지 체크한다.
같으면 범위를 늘린다.
for left <= i <= right
	스위치 상태 바꾸기

스위치 상태: boolean으로 표시하고, 출력할떄만 0101로 하자.


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1244_스위치_켜고_끄기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int arrSize = Integer.parseInt(br.readLine());
		boolean[] arr = new boolean[arrSize];
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			if (st.nextToken().equals("1")) {
				arr[i] = true;
			}
		}
		
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			String gender = st.nextToken();
			int switchNum = Integer.parseInt(st.nextToken());
			if (gender.equals("1")) { // 남
//				System.out.println("남");
				int count = 1;
				while (switchNum * count < arrSize) {
					int index = switchNum * count -1;
					arr[index] = !arr[index];
					count++;
					printArr(arr);
//					System.out.println(Arrays.toString(arr));
				}
				
				
			}else { // 여
//				System.out.println("여");
				int left = switchNum-1;
				int right = switchNum-1;
				while (left-1 >=0 && right+1 < arrSize && arr[left-1]==arr[right+1]) {
					left--;
					right++;
					
				}
				
				for (int j = (left); j <=(right); j++) {
					arr[j] = !arr[j];
				}
				printArr(arr);
//				System.out.println(Arrays.toString(arr));
			}
		}
		
		// 출력
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]== false) {
				sb.append("0 ");
			}
			else {
				sb.append("1 ");
			}
			if ( (i+1)%20 == 0 ) {
				sb.append("\n");
			}
		}
		
		System.out.print(sb.toString());
	}
	
	static void printArr(boolean[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]== false) {
				System.out.print("0 ");
			}
			else {
				System.out.print("1 ");
			}
			if ( (i+1)%20 == 0 ) {
				System.out.println();
			}
		}
		System.out.println();
	}
}
