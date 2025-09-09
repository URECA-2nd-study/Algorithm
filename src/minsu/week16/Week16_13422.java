package minsu.week16;

import java.io.*;
import java.util.*;

public class Week16_13422 {
	private static int N, M, K;
	private static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			nums = new int[N + 1];
			int[] prefix = new int[2 * N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i <= 2 * N; i++) {
				int idx = i;
				if (idx > N) {
					idx = i - N;
				}
				prefix[i] = prefix[i - 1] + nums[idx];
			}
			
			sb.append(cal(prefix)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int cal(int[] prefix) {
		int answer = 0;
		
		for (int i = 1; i <= N; i++) {
			int s = i;
			int e = s + M - 1;
			
			int sum = prefix[e] - prefix[s - 1];
			if(sum < K) {
				answer++;
			}
		}
		return M == N ? answer / N : answer;
	}
}
