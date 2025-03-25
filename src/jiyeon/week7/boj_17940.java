package baekjoon;

import java.io.*;
import java.util.*;

public class boj_17940 {
	
	/*
	 * 최적의 출근 경로 : (1) 환승 횟수를 최소로하는 경로 중 /// (2) 소요시간이 가장 짧은 경로 
	 * 
	 * dist 배열은 최소 갱신 테이블로 
	 * 	 - dist[i][0] :  출발지에서 i 번째 역으로 가는 최소 환승 횟수
	 * 	 - dist[i][1] : 출발지에서 i 번째 역으로 가는 최소환승 횟수 중 최소 비용을 의미한다.
	 * 
	 * PriorityQueue의 우선순위 1. 환승 여부 2. 이동 시간 
	 * 
	 * */

	
	static int N,M; //지하철 수 , 도착지
	static int[][] dist ; //최소 갱신 테이블 
	static int[] subwayCompany; //각 지하철 운영 회사
	static boolean [] visited; //노드 방문 여부 
	static ArrayList<ArrayList<Subway>> graph = new ArrayList<>() ; //ArrayList.get(i) == i번째 지하철 역의 연결된 정보 
	static final int INF = 1001;
	
	static class Subway implements Comparable<Subway>{
		
		private int transfer; //환승 여부 (0:환승 X , 1:환승 O)
		private int nextSub; //연결된 지하철역
		private int time; //연결된 지하철 역으로 가는 시간
		
		public Subway(int transfer, int nextSub, int time) {
			this.transfer = transfer;
			this.nextSub = nextSub;
			this.time = time;
		}


		@Override
		public int compareTo(Subway o1) {
			if(this.transfer == o1.transfer)
				return this.time - o1.time;
			
			return this.transfer - o1.transfer;
		}
		
		
	}//class - subway
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N][2];
		visited = new boolean[N];
		subwayCompany = new int[N];
		
		for(int i=0; i<N; i++) {
			subwayCompany[i] = Integer.parseInt(br.readLine());
			graph.add(new ArrayList<Subway>());
			dist[i][0] = INF;
			dist[i][1] = INF;
		} // init
		
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				
				int time = Integer.parseInt(st.nextToken());
				if (time == 0) continue; //지하철이 연결되지 않은 경우 건너뛰기
				int transfer = 0;
				if(subwayCompany[i]!=subwayCompany[j]) transfer = 1; // 각 지하철의 운영 회사가 다를 경우 환승
				int nextSub = j;
				
				graph.get(i).add(new Subway(transfer,nextSub,time));
				
			}
			
		} //input
		
		
		//다익스트라 진행 
		
		dij();
		
		System.out.println(dist[M][0]+" "+dist[M][1]);
		
		

	}//main
	
	
	
	//다익스트라 
	
	static void dij() {
		
		PriorityQueue<Subway> pq = new PriorityQueue<>();
		pq.add(new Subway(0,0,0));
		dist[0][0]=0;
		dist[0][1]=0;
		
		while(!pq.isEmpty()) {
			
			Subway current = pq.poll();
			
			if(visited[current.nextSub])
				continue;

			
			visited[current.nextSub] = true;
			
			for(Subway next : graph.get(current.nextSub)) {
				int newTransfer = current.transfer+next.transfer;
				int newTime = current.time+next.time;
				
				if(dist[next.nextSub][0]>newTransfer || (dist[next.nextSub][0]==newTransfer && dist[next.nextSub][1]>newTime)) {
					
					dist[next.nextSub][0] = newTransfer;
					dist[next.nextSub][1] = newTime;
					pq.add(new Subway(newTransfer,next.nextSub,newTime));
					
				}
				
			}
			
		}
		
	}//dij
	
	

}
