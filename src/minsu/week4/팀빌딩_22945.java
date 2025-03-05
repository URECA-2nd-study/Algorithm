import java.io.*;
import java.util.*;

public class 팀빌딩_22945 {

	private static int N;
	private static int[] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(search());
	}

	private static long search() {

		long max = 0;

		int lp = 0;
		int rp = N - 1;

		while (lp != rp) {
			
			max = Math.max(max, Math.min(nums[lp], nums[rp]) * (rp- lp - 1));

			if (nums[lp] > nums[rp]) {

				rp--;

			} else {

				lp++;

			}

		}
		
		return max;

	}

}