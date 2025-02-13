package jiyeon.week2;

import java.io.*;

public class boj_2156 {
	
	/*
	 * wines[i] = i번째 포도주의 양
	 * dp[i] = 1~i번째 포도주까지 주어졌을 때 최대로 많이 마실 수 있는 포도주의 양
	 * 
	 * 포도주를 마시는 경우의 수 (i-3,i-2,i-1 순서)
	 * 1. OOX
	 * 2. OXO
	 * 3. XOO
	 * 4. XXO
	 * 5. XOX
	 * 6. OXX
	 * => 4,5,6은 어차피 최대값이 될 수 없어서 고려 XX
	 * 
	 * 1번,2번의 경우, i번째 와인을 마셔야 MAX
	 * 3번의 경우, i번째 와인을 마시면 안됨
	 * 
	 * 1. dp[i] = dp[i-2]+wines[i]
	 * 2. dp[i] = dp[i-3]+wines[i-1]+wines[i]
	 * 3. dp[i] = dp[i-1] 
	 * => 중에 Max 값이 dp[i]
	 * 
	 * */

	static long [] dp;
	static int [] wines;
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		//index 1부터 고려하기위해 n+1
		dp = new long [n+1];
		wines = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			wines[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1] = wines[1];

		if(n>=2) {
			
			for(int i=2; i<=n; i++) {
				if(i==2)
					dp[2] = wines[1]+wines[2];
				else {
					dp[i] = Math.max(Math.max(dp[i-2]+wines[i], dp[i-3]+wines[i-1]+wines[i]), dp[i-1]);
				}
				
			}
			
		}
		System.out.println(dp[n]);

		
	}

}
