package jiyeon.week11;

import java.util.*;
import java.io.*;

public class boj_11657 {
	
	static int N, M; 
	static ArrayList<Node> graph = new ArrayList<>();
	static long [] dist; 
	static long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new long[N+1];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.add(new Node(u,w,v));
		}//input
		
		if(!bellmanFord(1)) {
			System.out.println(-1); //음수사이클이 발생하는 경우 최단 경로 없으므로 -1  
		}
		else {
			for(int i=2; i<=N; i++) {
				
				if(dist[i] == INF)
					System.out.println(-1); //dist 배열의 값이 INF인 경우 경로가 없으므로 -1 
				else {
					System.out.println(dist[i]);
				}
			}
		}
	}//main 
	
	
	//벨만 포드 알고리즘 
	static boolean bellmanFord(int start) {
		
		for(int i = 1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				
				Node current = graph.get(j);
				
				if(dist[current.u] == INF)
					continue;
				
				if(dist[current.u]+ current.w < dist[current.v]) {
					dist[current.v] = dist[current.u]+ current.w ; //최단 거리 갱신 
			
					if(i == N) {
						return false; //음수 사이클 발생
					}
					
				}
			}
		}
		
		return true; 
	}
	
	
	 static class Node {
		 
		 private int u; //시작 노드
		 private int v; //끝 노드
		 private int w; //가중치
		 
		 public Node(int u, int v, int w) {
			 this.u = u;
			 this.v = v;
			 this.w = w;
		 }
		 
	 }//inner class 

}
