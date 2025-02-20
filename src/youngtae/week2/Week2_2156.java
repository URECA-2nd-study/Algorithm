package youngtae.week2;

import java.io.*;
import java.util.*;

public class Week2_2156 {

	static int N;
	static int[] wines;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		wines = new int[N+1];
		dp = new int[N+1];

		for(int i = 1; i <= N; i++) {
			wines[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 1; i <= N; i++) {
			if(i <= 2) {
				dp[i] = dp[i-1]+wines[i];
				continue;
			}
			//dp[i] = 직전까지 2잔 연속 마시기, i-3번째의 마시기 최대치+직전 포두주+현재 포도주, i-2번째의 마시기 최대치 + 현재 포도주
			dp[i] = Math.max(dp[i-1], Math.max((dp[i-3]+wines[i-1]+wines[i]), (dp[i-2]+wines[i])));
		}

		sb.append(dp[N]);

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

}
