package jiyeon.week9;

import java.io.*;
import java.util.*;

public class boj_1052 {
	
	static int N, K, count;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); 
		//입력
		
		while(true) {
			
			int oneCount = Integer.bitCount(N); //1의 개수 
			
			if(oneCount<=K) {
				break;
			}
			
			int idx = Integer.numberOfTrailingZeros(N); //오른쪽부터 0의 위치를 탐색 -> 가장  오른쪽에 있는 0의 인덱스를 반환
			N += 1<<idx;
			count += 1<<idx;
			
		}
		
		System.out.println(count);

	}//main

}
