import java.io.*;
import java.util.*;;

public class Week18_동전1 {
	private static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] dp = new int[K + 1];
		dp[0] = 1;

		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());

			for (int j = x; j <= K; j++) {
				dp[j] = dp[j - x] + dp[j];
			}
		}
		System.out.println(dp[K]);
	}
}
