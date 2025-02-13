package minsu.week2;

import java.io.*;
import java.util.*;

public class RGB거리 {

	private static int[][] dp;
	private static int[][] rgb;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		dp = new int[N][3];

		rgb = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			rgb[i][0] = Integer.parseInt(st.nextToken());
			rgb[i][1] = Integer.parseInt(st.nextToken());
			rgb[i][2] = Integer.parseInt(st.nextToken());

		}
		
		dp[0][0] = rgb[0][0];
		dp[0][1] = rgb[0][1];
		dp[0][2] = rgb[0][2];

		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + rgb[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + rgb[i][1];
			dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + rgb[i][2];
		}
		
		System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
		

	}
	
}
