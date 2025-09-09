import java.io.*;
import java.util.*;;

public class Week17_2631 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		int[] arr = new int[N];
		arr[0] = nums[0];
		int idx = 1;
		for (int i = 1; i < N; i++) {
			if (arr[idx - 1] < nums[i]) {
				arr[idx++] = nums[i];
			} else {
				int sidx = binarySearch(0, idx - 1, nums[i], arr);
				arr[sidx] = nums[i];
			}
		}
		System.out.println(N - idx);
	}

	private static int binarySearch(int s, int e, int target, int[] arr) {
		while (s <= e) {
			int mid = (s + e) / 2;

			if (arr[mid] < target) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return s;
	}
}
