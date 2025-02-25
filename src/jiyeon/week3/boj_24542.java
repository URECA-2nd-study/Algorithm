package jiyeon.week3;

import java.util.*;
import java.io.*;

/*
 * 1. 정답은 각 그래프의 정점의 개수를 곱한 거
 * 2. 주의 ) 시간복잡도 O(N) 안에 풀어야함 
 * */


public class boj_24542 {
	
	static int n, m;
	static int [] arr;
	static int parent;

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1];
		
		for(int i=1; i<=n; i++)
			arr[i] = i;
		
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			unionParent(n1,n2);
		}
		
		
		//해시맵을 이용하여 각 그래프의 정점을 count
		HashMap<Integer,Integer> dict = new HashMap<>();
		
		for(int i=1; i<=n; i++) {
			int root = setParent(i);
			dict.put(root, (dict.getOrDefault(root, 0)+1)%1000000007);
		}
		
		
		long answer = 1;
		
		for(int key : dict.keySet()) {
			if(key==0)
				continue;
			answer = (answer*dict.get(key))%1000000007;
		}
		
		System.out.println(answer%1000000007);
		
	}
	
	//root parent를 찾는 함수 
	static int setParent(int index) {
		if(index == arr[index]) {
			return index;
		}

		return arr[index] = setParent(arr[index]);
		
	}
	
	//union 함수 
	static void unionParent(int n1, int n2) {
		
		int a = setParent(n1);
		int b  = setParent(n2);
		
		//작은 노드를 기준으로 부모 임명
		if(a>b) {
			arr[a] = b;
		}
		else {
			arr[b] = a;
		}
	}

}
