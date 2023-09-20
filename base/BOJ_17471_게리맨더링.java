package base;

/*
[링크]
https://www.acmicpc.net/problem/17471

[시간]


[아이디어]
조합으로 구역 선택
bfs로 마을 연결 확인
연결되어 있으면 population 차 구하기

[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class BOJ_17471_게리맨더링 {
	
	static String input = "6\r\n" + 
			"5 2 3 4 1 2\r\n" + 
			"2 2 4\r\n" + 
			"4 1 3 6 5\r\n" + 
			"2 4 2\r\n" + 
			"2 1 3\r\n" + 
			"1 2\r\n" + 
			"1 2";
	
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new StringReader(input));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N;
		int[][] graph;
		int[] population;
		N = Integer.parseInt(br.readLine());
		graph = new int[N][];
		population = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			graph[i] = new int[n];
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken()) -1;
			}
			
			System.out.println(Arrays.toString(graph[i]));
		}
		
		
		
		// 선거구 나누기
		// nC1~nCn/2까지 넥퍼로 구해서 두 그룹 만들기
		int[] toSelect = new int[N];
		List<List<Integer>> sections = new ArrayList<List<Integer>>();
		for (int i = 1; i <= toSelect.length/2; i++) {
			Arrays.fill(toSelect, 0);
		
			for (int j = 0; j < i; j++) {
				toSelect[toSelect.length-1-j] = 1;
			}
			
			do {
				for (int j = 0; j < toSelect.length; j++) {
					if (toSelect[j] == 1) {
						
					}
				}
			} while (nextPermutaion(toSelect));
			
		}

		
		// 선거구 A와 B 분리
		
		// DFS로 전부 연결됐는지 확인
		
		// 연결 됐으면 인구수 구하기
		
		
		
		System.out.println(sb);
	}

	private static boolean nextPermutaion(int[] p) {
		int length = p.length-1;
		int i = length;
		while (i > 0 && p[i-1]>=p[i]) i--;
		if (i <= 0) return false;
		int j = length;
		while (p[i-1] >= p[j] )j-- ;
		swap(i-1, j, p);
		
		j = length;
		while (i < j) {
			swap(i++, j--, p);
		}
		return true;
	}
	
	private static void swap(int i, int j, int[] p ) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}
}