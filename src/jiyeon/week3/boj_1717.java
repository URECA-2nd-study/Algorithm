package jiyeon.week3;

import java.util.*;
import java.io.*;


/*
 *To do 
 *
 *1. 루트 부모 찾는 함수 
 *2. 합치는 함수 => 합치면서 루트 부모 찾아야함 
 *3. a,b가 같은 집합에 포함되어있는지 확인하는 함수 (""루트 "" 부모가 같은지 확인) 
 * 
 * */


public class boj_1717 {
	
	static int n,m; 
	static int [] parent; 
	

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int [n+1];
		
		for(int i =0; i<=n; i++) {
			parent[i] = i; //초기화 (부모 노드는 자기 자신)
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(flag == 0) {
				union(a,b);
			}
			
			if (flag==1) {
				if(isSame(a, b)) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
			}
			
		}
		
		
	}
	
	
	//루트 부모 찾는 함수 
	
	static int find(int index) {
		if(index == parent[index])
			return index;
		
		return parent[index] = find(parent[index]);
	}
	
	
	//합치는 함수 
	static void union(int a, int b) {
		
		int aP = find(a); //a의 루트 부모 
		int bP = find(b); //b의 루트 부모 
		
		if(aP>bP) 
			parent[aP] = bP;
		else 
			parent[bP] = aP;
		
	}
	
	//같은 집합인지 확인하는 함수 
	
	static boolean isSame(int a, int b) {
		int aP = find(a); //a의 루트 부모 
		int bP = find(b); //b의 루트 부모 
	
		if(aP == bP)
			return true;
		
		return false;
	}

}
