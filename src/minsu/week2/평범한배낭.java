package minsu.week2;

import java.io.*;
import java.util.*;

public class 평범한배낭 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[K + 1][N + 1];

		int[][] items = new int[N + 1][2];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int k = 1; k <= K; k++) {

			for (int n = 1; n <= N; n++) {

				if (items[n][0] <= k) {
					dp[k][n] = Math.max(dp[k][n - 1], dp[k - items[n][0]][n - 1] + items[n][1]);
				} else {
					dp[k][n] = dp[k][n - 1];
				}

			}

		}
		
		Arrays.sort(dp[N]);
		System.out.println(dp[K][N]);
		
	}

}
