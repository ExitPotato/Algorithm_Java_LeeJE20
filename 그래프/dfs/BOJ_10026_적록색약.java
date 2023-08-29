package 그래프.dfs;
/*
[링크]
https://www.acmicpc.net/problem/10026
[시간]
17:20~

[아이디어]
DFS로 하자
찾은건 'A'로 만들자


[시간복잡도]


[실수]


[검색]


[다른 사람 코드]


*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_10026_적록색약 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N;
		char[][] arr;
		N = Integer.parseInt(br.readLine());
	
		char visited = '0';
		char rgVisited = '1';
		
		arr = new char[N][N];

		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		
		int[] rgbCount = new int[3];
		Map<Character, Integer> map = new HashMap<>();
		map.put('R', 0);
		map.put('G', 1);
		map.put('B', 2);
		
		
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == visited || arr[i][j] == rgVisited) continue;
				
				rgbCount[map.get(arr[i][j])]++;
				dfs1(arr[i][j], N, arr, i, j, visited, rgVisited);
			}
		}
		
		int rgRegionCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == visited) continue;
				
				rgRegionCount++;
				dfs2(arr[i][j], N, arr, i, j, visited);
			}
		}
		
		
		sb.append(rgbCount[0]+rgbCount[1]+rgbCount[2]).append(" ").append(rgRegionCount+rgbCount[2]);
		System.out.println(sb);
	}

	private static void dfs1(char target, int N, char[][] arr, int i, int j, char visited, char rgVisited) {
		if (arr[i][j] == visited || arr[i][j] == rgVisited) return;
		
		if (arr[i][j] == 'R' || arr[i][j] == 'G') {
			arr[i][j] = rgVisited;
		} else {
			arr[i][j] = visited;
		}
//		
//		System.out.println();
//		for (int k = 0; k < arr.length; k++) {
//			System.out.println(Arrays.toString(arr[k]));
//		}
		
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		
		for (int k = 0; k < 4; k++) {
			int y = i + dy[k];
			int x = j + dx[k];
			
			if (y<0 || y >= N || x <0 || x >= N) continue;
			
			if(arr[y][x] == target) {
				dfs1(target, N, arr, y, x, visited, rgVisited);
			}
		}
		
	}
	
	private static void dfs2(char target, int N, char[][] arr, int i, int j, char visited) {
		if (arr[i][j] == visited) return;
		arr[i][j] = visited;
		
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		
		for (int k = 0; k < 4; k++) {
			int y = i + dy[k];
			int x = j + dx[k];
			
			if (y<0 || y >= N || x <0 || x >= N) continue;
			
			if(arr[y][x] == target) {
				dfs2(target, N, arr, y, x, visited);
			}
		}
		
	}
}
