package jiyeon.week8;

import java.util.*;
import java.io.*;


public class boj_1477 {
	
	static int N,M,L;
	static int [] arr;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N+2];
		arr[0] = 0; //시작점 추가 
		
		if(N>0) {
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i] = num;
			}
		}
		arr[N+1] = L; // 끝점추가
		//====input 
		
		Arrays.sort(arr);
		
		//매개변수 탐색 
		
		int left = 1; //Divide by zero 때문에 0 -> 1로 설정
		int right = L;
		
		while(left<=right) {
			
			int mid = (left+right) / 2;
			
			if(getCount(mid) > M)
				left = mid + 1;
			else 
				right = mid - 1;
			
		}
		
		System.out.println(left);
		

	}//main
	
	//최대구간이 x일 경우 몇 개의 휴게소를 더 지어야하는지 
	static int getCount(int x) {
		int count = 0;
		for(int i=1; i<N+2; i++ ) {
			count += (arr[i] - arr[i-1] -1)/x;
		}
		
		return count;
		
	}

}
