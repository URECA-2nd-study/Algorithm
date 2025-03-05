import java.io.*;
import java.util.*;

public class 귀여운_라이언_15565 {

	private static int N, K;
	private static int[] nums;
	private static int min = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		search();

		if (min != 987654321) {
			System.out.println(min);
		} else {
			System.out.println(-1);
		}

	}

	private static void search() {

		int lp = 0;
		int rp = 0;

		int cnt = 0;

		while (rp < N) {

			if (nums[rp] == 1)
				cnt++;

			while (cnt == K) {

				min = Math.min(min, rp - lp + 1);

				if (nums[lp] == 1)
					cnt--;
				lp++;

				while (nums[lp] != 1) {
					lp++;
				}

			}

			rp++;

		}

	}

}