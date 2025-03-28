package jiyeon.week8;

import java.util.*;
import java.math.*;
import java.io.*; 


public class boj_17951 {
	
	static int N,K,sum;
	static int [] scores ;
	static int left = 0, right = 0;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		scores = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			scores[i] = num;
			right += num;
			
		}//input

		while(left<=right) {
			
			int mid = (left+right) / 2;
			if(getCount(mid) > K) {
				left = mid + 1;
			}
			else 
				right = mid - 1;
			
		}

		System.out.println(right);
		
	}//main

	
	//점수가 x일 경우 시험지를 몇개로 나눌 수 있는지
	static int getCount(int x) {
		
		int sum = 0; 
		int count = 1; 
		
		for(int i=0; i<N; i++) {
			
			if(sum+scores[i] >= x ) {
				sum = 0;
				count ++ ;
			}
			else {
				sum += scores[i];
			}
		}
		
		return count;
	}

}
