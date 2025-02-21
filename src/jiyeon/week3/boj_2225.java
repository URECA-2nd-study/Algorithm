package jiyeon.week3;

import java.io.*;
import java.util.*;

/*
 * dp[i][j] = 정수 i를 j개 이용해서 만드는 경우의 수 
 * 
 * */

public class boj_2225 {
	
	static long[][] dp; 
	static int mod = 1000000000;

	public static void main(String[] args) throws IOException {

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		dp = new long [N+1][K+1];
		
		for(int i=0; i<=N; i++) {
			dp[i][1] = 1;
		}//초기화 
		
		for(int i=1; i<=K; i++) {
			dp[0][i] = 1;
		}//초기화 
	
		for(int i=1; i<=N; i++) {
			for(int j=2; j<=K; j++) {
				dp[i][j] = (dp[i][j-1] + dp[i-1][j])%mod;
			}
		}

	
		System.out.println(dp[N][K]%mod);
		
		
	}

}
