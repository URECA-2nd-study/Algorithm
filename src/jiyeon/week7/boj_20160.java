package baekjoon;

import java.io.*;
import java.util.*;

public class boj_20160 {
	
	
	/*
	 * 아줌마가 출발지에서 i번째 지점으로 가는 <누적> 시간과 내가 출발지에서 i번째 지점으로 가는 시간을 비교한다. 
	 *  => 내가 가는 시간이 더 빠르거나 같으면 해당 지점 번호를 반환 , 여러 개면 제일 작은 번호를 반환 
	 * 
	 * 주의할 점 : 아주머니가 이동 하실 때 i->i+1번째로 가는 방법이 없으면 i+2지점으로 감 
	 * 주의할 점 2 : visited배열과 dist 배열은 이동할 때마다 초기화 해줘야함
	 * 
	 * 출발지로부터 다익스트라 한 결과, dist[목적지] = INF 라면 해당 목적지까지 갈 수 없다는 뜻이다.
	 * 
	 * */
	
	static int V,E,myStart, answer;
	static long time; //야쿠르트 아줌마의 이동 시간 누적합 
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static boolean [] visited, visited2; //야쿠르트 아줌마, 나의 방문 배열 
	static int [] dist, dist2; //야쿠르트 아줌마의 최소거리 배열 , 나의 최소 거리 배열
	static int [] order = new int[10]; //야쿠르트 아줌마의 경로
	
	static class Node implements Comparable<Node>{
		
		private int v;
		private int w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		
		@Override
		public int compareTo(Node o1) {
			
			if(this.w == o1.w)
				return this.v - o1.v;
			
			return this.w - o1.w;
		}

	} //inner class : Node
	
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); //정점
		E = Integer.parseInt(st.nextToken()); //간선 
		answer = Integer.MAX_VALUE;
		
		visited = new boolean[V+1];
		visited2 = new boolean[V+1];
		dist = new int[V+1];
		dist2 = new int[V+1];
		
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<Node>());
			dist[i] = Integer.MAX_VALUE;
			dist2[i] = Integer.MAX_VALUE;
		}//init
		
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			//양방향 연결
			graph.get(u).add(new Node(v,w));
			graph.get(v).add(new Node(u,w));
			
		}//graph input
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<10; i++)
			order[i] = Integer.parseInt(st.nextToken());
		
		myStart = Integer.parseInt(br.readLine()); //나의 시작 지점
		//input 
		
		if(order[0]==myStart){
			System.out.println(myStart); //아줌마와 나의 맨 처음 시작 지점이 같을 경우 탐색하지 않고 결과 반환
		}
		else {
			
			int yogurtGirlStart = order[0]; //야구르트 아줌마 출발 지점
			
			for(int i=1; i<10; i++) {
				
				//방문 배열, 거리 배열 초기화 
				Arrays.fill(visited, false);
				Arrays.fill(visited2, false);
				Arrays.fill(dist, Integer.MAX_VALUE);
				Arrays.fill(dist2, Integer.MAX_VALUE);
				
				int yogurtGirlEnd = order[i]; //야구르트 아줌마 도착 지점 
				
				dij(yogurtGirlStart,yogurtGirlEnd,dist,visited); 
				dij(myStart,yogurtGirlEnd,dist2,visited2);
				
				if(dist[yogurtGirlEnd] == Integer.MAX_VALUE) {
					continue;
				}//야쿠르트 아줌마가 이동하실 수 없는 경우 
				
				yogurtGirlStart = yogurtGirlEnd; //아줌마가 이동하실 수 있을 때만 시작 지점 갱신
				
				if(dist2[yogurtGirlEnd] == Integer.MAX_VALUE) {
					continue; 
				}//내가 이동할 수 없는 경우
				
				
				time += dist[yogurtGirlEnd];

				if(time>=dist2[yogurtGirlEnd])
					answer = Math.min(answer,yogurtGirlEnd);
				
			}
			
			
			if(answer==Integer.MAX_VALUE)
				answer = -1;
			
			System.out.println(answer);
			
		}
		
	}//main
	
	
	//다익스트라 메서드 
	static void dij(int start, int end, int[] dist, boolean [] visited) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(start,0));
		
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			
			Node current = pq.poll();
			
			if(visited[current.v])
				continue;
			
			visited[current.v] = true;
			
			for(Node next : graph.get(current.v)) {
				
				if(dist[next.v]> current.w+next.w) {
					
					dist[next.v] = current.w+next.w;
					pq.add(new Node(next.v,current.w+next.w));
					
				}
				
			}
			
		}

	}//dij

}
