package minsu.week2;

import java.io.*;
import java.util.*;

public class 포도주시식 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];
		int[] dp = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		dp[1] = nums[1];
		
		if(N >= 2) {
			
			dp[2] = nums[1] + nums[2];
			for (int i = 3; i <= N; i++) {
				dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + nums[i], dp[i - 3] + nums[i - 1] + nums[i]));
			}
			
		}

		
		
		System.out.println(dp[N]);
	}

}
