package 조합;
/*
[링크]
https://www.acmicpc.net/problem/1759
[시간]


[아이디어]

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1759_암호_만들기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int L, C;
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[] vowels = new char[5];
		char[] consonants = new char[21];
		
		int vSize = 0;
		int cSize = 0;
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if ("aeoiu".contains(tmp)) {
				vowels[vSize++] = (char)tmp.charAt(0);
			}
			else {
				consonants[cSize++] = (char)tmp.charAt(0);
			}
		}
		
		int pickedVowelSize = 1;
		int pickedConsonantSize = L-pickedVowelSize;
		
		Arrays.sort(vowels, 0, vSize);
		Arrays.sort(consonants, 0, cSize);
		
		int[] vSelected = new int[vSize];
		int[] cSelected = new int[cSize];
		
		char[] picked = new char[L];
		char[] sortedPicked = new char[L];

		// 반례 검색
		List<String> result = new ArrayList<String>();
		while (pickedConsonantSize > cSize) {
			pickedVowelSize++;
			pickedConsonantSize--;
		}
			
			
		while(pickedVowelSize <= vSize && pickedConsonantSize >= 2 && pickedConsonantSize <= cSize) {
			
			for (int i = 0; i < pickedVowelSize; i++) {
				vSelected[vSize-1-i] = 1;
			}
			
			
			do {
				
				
				int pickedIndex = 0;
				for (int i = 0; i < vSelected.length; i++) {
					if (vSelected[i] == 1) picked[pickedIndex++] = vowels[i];
				}
				
				Arrays.fill(cSelected, 0);
				for (int i = 0; i < pickedConsonantSize; i++) {
					cSelected[cSize-1-i] = 1;
				}
				
				do {
					
					
					
					int pickedConsonantIndex = pickedIndex;

					for (int j = 0; j < cSelected.length; j++) {
						if (cSelected[j] == 1) {

							picked[pickedConsonantIndex++] = consonants[j];
						
						}
						
					}
					
					
					System.arraycopy(picked, 0, sortedPicked, 0, L);
					Arrays.sort(sortedPicked);
					result.add(String.valueOf(sortedPicked));
				}while(nextPermutation(cSelected));
			}while(nextPermutation(vSelected));
			
			// 다음 준비
			pickedVowelSize++;
			pickedConsonantSize = L-pickedVowelSize;
			Arrays.fill(vSelected, 0);
			
		}
		Collections.sort(result);
		for(String s: result) sb.append(s).append("\n");
		System.out.println(sb);
	}

	private static boolean nextPermutation(int[] p) {
		int maxIndex = p.length-1;
		int i = maxIndex;
		while (i > 0 && p[i-1] >= p[i]) i--;
		if ( i <= 0) return false;
		
		int j = maxIndex;
		while(p[i-1] >= p[j]) j--;
		
		swap(p, i-1, j);
		
		int k = maxIndex;
		
		while (i < k) {
			swap(p, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] p, int i, int j) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
		
	}
}
