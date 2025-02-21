package jiyeon.week3;


import java.util.*;
import java.io.*;


/*
 * N개를 이분할 하는 최소 간선의 수 = N-2 
 * 
 * */


public class boj_1647 {
	
	static class Road implements Comparable<Road>{
		
		int[] node; //연결하는 집
		int cost; //유지비 
		
		public Road(int A, int B, int C) {
			this.node = new int[2];
			this.node[0] = A;
			this.node[1] = B;
			this.cost = C;
			
		}

		@Override
		public int compareTo(Road o) {
			return this.cost - o.cost;
		}
	}
	
	
	static int n,m;
	static int [] parent;//사이클 확인용 배열 
	static List<Road> list = new ArrayList<>();
	static int ans = 0;
	static int bigCost = 0;

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			parent[i] = i;
		}//사이클 배열 초기화
		
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			list.add(new Road(n1,n2,value));
			
		}//도로 입력 받기 
		
		
		//비용이 적은 순서로 정렬 
		Collections.sort(list);
		
		
		//최저 비용 순으로 그래프 연결
		for(int i=0; i<m; i++) {
			int house1 = list.get(i).node[0];
			int house2 = list.get(i).node[1];
			int cost = list.get(i).cost;
			
			if(isSameParent(house1, house2)) //사이클이 형성되면 건너뛰기 
				continue;
			else {
				union(house1,house2);
				ans += cost;
				bigCost = cost;
			}
		}
		
		
		//두 마을로 분리하기 위해 연결된 길 중 제일 비용이 큰 값 빼기
		
		System.out.println(ans-bigCost);

		
	}//main 
	
	
	static int find(int index) {
		if(index == parent[index])
			return index;
		
		return parent[index] = find(parent[index]);
		
	}
	
	
	static void union(int a, int b) {
		
		int aP = find(a);
		int bP = find(b);
		
		if(aP>bP)
			parent[aP] = bP;
		else
			parent[bP] = aP;
		
	}
	
	static boolean isSameParent(int a, int b) {
		
		int ap = find(a);
		int bp = find(b);
		
		if(ap==bp)
			return true;
		
		return false;
	}
	
	
	

}
