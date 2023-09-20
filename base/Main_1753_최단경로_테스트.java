import java.util.*;
import java.io.*;

public class Main_1753_최단경로_테스트 {

	static int V,E,K;

	static class Node implements Comparable<Node>{
		int idx, weight;

		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", weight=" + weight + "]\n";
		}
		
		
		
	}
	
	
	public static void main(String[] args) throws Exception{
		
		Random r = new Random();
		
	   while(true) {	
		StringBuilder sb = new StringBuilder(); //==> 테케 저장
		sb.append("5 7\n");
		sb.append("1\n");
		for(int i=0; i<7; i++) {//간선의 정보
//			sb.append((r.nextInt(5)+1)+" "+ (r.nextInt(5)+1)+" "+(r.nextInt(5)+10)+"\n");
			   int a = (r.nextInt(5)+1);
			   int b;
			   do {
			         b = (r.nextInt(5)+1);
			   }while(a==b);
			   
			   int c = (r.nextInt(5)+10);
			   sb.append(a+" "+ b+" "+  c+"\n");
		}
		   System.out.println(sb.toString());
			String main1Answer = main1(new BufferedReader(new StringReader(sb.toString())));  //new InputStreamReader(sb.toString().)));
			String main2Answer = main2(new BufferedReader(new StringReader(sb.toString())));  //new InputStreamReader(sb.toString().)));
			
			if(!main1Answer.equals(main2Answer)) break;
		  
	   }//while
		
	}//main메서드
	

	public static String main1(BufferedReader b) throws Exception{

		BufferedReader br = b; //new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		List<Node>[] adjList = new List[V+1];
		int[] result = new int[V+1];
		for(int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<>();
			result[i] = Integer.MAX_VALUE;
		}

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Node(v,w));
		}

		result[K] = 0;
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(K,0));
		boolean[] visited = new boolean[V+1];
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			visited[cur.idx] = true;
			for(Node node : adjList[cur.idx]) {
				if(!visited[node.idx] && 
						result[cur.idx]+node.weight < result[node.idx]) {
					result[node.idx] = result[cur.idx]+node.weight;
					node.weight = result[node.idx];
					q.offer(node);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; i++) {
			if(result[i] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(result[i]).append("\n");
		}
		System.out.println(sb);
		return sb.toString();
	}//main1==>정답
	
	
	public static String main2(BufferedReader b) throws Exception{
		
		BufferedReader br = b;//new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		List<Node>[] adjList = new List[V+1];
		int[] result = new int[V+1];
		for(int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<>();
			result[i] = Integer.MAX_VALUE;
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Node(v,w));
		}
		
		result[K] = 0;
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(K,0));
		boolean[] visited = new boolean[V+1];
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			visited[cur.idx] = true;
			for(Node node : adjList[cur.idx]) {
				if(!visited[node.idx] && 
						result[cur.idx]+node.weight < result[node.idx]) {
					result[node.idx] = result[cur.idx]+node.weight;
//					node.weight = result[node.idx];
					q.offer(node);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; i++) {
			if(result[i] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(result[i]).append("\n");
		}
		System.out.println(sb);
		return sb.toString();
	}//main2 ==> 오답

}






