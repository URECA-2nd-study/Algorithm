package youngtae.week3;

import java.io.*;
import java.util.*;

public class Week3_2225 {

	static int N, K;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		K = Integer.parseInt(str.nextToken());

		dp = new int[K+1][N+1];

		for(int i = 0; i <= K; i++) {
			dp[i][0] = 1;
		}

		for(int i = 1; i <= K; i++) {
			for(int j = 1; j <= N; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_000;
			}
		}

		/**
		 * 스터디 당일 시도했던 점화식
		 * 거의 거의 맞았는데 조금 더 침착하게 했으면 풀었을지도,,
		 *
		 *
		 * dp = new int[K+1];
		 * dp[0] = 0;
		 * dp[1] = 1;
		 * dp[2] = N+1 + 1;
		 *
		 * for(int i = 3; i <= K; i++) {
		 * 		if(K % 2 == 0) {
		 *      	dp[i] = dp[i-1] + dp[i - 1] * i - dp[i-2] -1 ;
		 *		} else {
		 *      	dp[i] = dp[i - 1] + (N+1)*3;
		 *		}
		 * }
		 */


		sb.append(dp[K][N] % 1_000_000_000);


		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}