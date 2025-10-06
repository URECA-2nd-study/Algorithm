import java.io.*;
import java.util.*;;

public class Week18_소형기관차 {
	private static int N, Q;
	private static int[] nums, prefix;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		nums = new int[N + 1];
		prefix = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			prefix[i] = prefix[i - 1] + nums[i];
		}

		Q = Integer.parseInt(br.readLine());

		int[][] dp = new int[4][N + 1];
		for (int train = 1; train <= 3; train++) {

			for (int i = Q; i <= N; i++) {
				dp[train][i] = Math.max(dp[train][i - 1], dp[train - 1][i - Q] + (prefix[i] - prefix[i - Q]));
			}
		}
		System.out.println(dp[3][N]);
        
	}
}
