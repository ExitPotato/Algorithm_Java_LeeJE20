package 트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1233_사칙연산_유효성_검사 {
	static char[] tree;
	static StringBuilder sb = new StringBuilder();
	static int size;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int t = 1; t <= 10; t++) {
			size = Integer.parseInt(br.readLine());
			tree = new char[size+1];
			for (int i = 1; i <= size; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				tree[i] = st.nextToken().charAt(0);
			}
			sb.append("#").append(t).append(" ");
			if(isValid(1)) {
				sb.append("1\n");
			}
			else {
				sb.append("0\n");
			}
		}
		System.out.println(sb);
	}
	
	// dfs
	// 해당 노드를 루트로 하는 트리가 유효한 사칙인지
	public static boolean isValid(int index) {
		if (index * 2 > size) { // 리프라면
			if (Character.isDigit(tree[index])) {
				return true;
			}
			else {
				return false;
			}
		}
		
		if (! isValid(index * 2)) {
			return false;
		}
		
		if (Character.isDigit(tree[index])) {
			return false;
		}
		
		if (! isValid(index *2 + 1)) {
			return false;
		}
		
		return true;
	}
}
