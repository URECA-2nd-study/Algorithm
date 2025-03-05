package week4;

import java.util.*;
import java.io.*;


/*
 * 1. start, end pointer를 지정한다. start 포인터는 배열의 맨 처음부터 시작, end 포인터는 배열의 맨 마지막 부터 역순으로 이동한다.
 * 2. dev[start] > dev[end] 이면 end --; 아니면 start ++;
 * 3. start>end 까지 반복
 * 
 * */


public class boj_22945 {
	
	static int start, end, max, N; 
	static int[] dev;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		dev = new int[N];
		start = 0;
		end = N-1;
		max = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			dev[i] = Integer.parseInt(st.nextToken());
		}//입력
		
		for(int i=0; i<N; i++) {
			
			max = Math.max((end-start-1)*Math.min(dev[start], dev[end]), max);
			
			if(dev[start]>= dev[end])
				end--;
			else
				start ++; 
			
			if(start>end)
				break;
			
		}
		
		System.out.println(max);
	}

}
