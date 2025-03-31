import java.io.*;
import java.util.*;

public class Solution {
	private static int N, K, T;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()) % K;
		}

		Arrays.sort(arr);

		st = new StringTokenizer(br.readLine());
		int x = 0;

		for (int i = 0; i < T; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			x = (x + tmp) % K;
			sb.append(getMax(x) + " ");
		}
		System.out.println(sb.toString());
	}

	private static int getMax(int x) {
		int t = K - x;
		int idx = lowerBound(t);
		int result1 = -1;
		int result2 = -1;

		if (idx > 0) {
			result1 = arr[idx - 1] + x;
		}

		if (idx < N) {
			result2 = arr[N - 1] + x - K;
		}

		return Math.max(result1, result2);

	}

	private static int lowerBound(int t) {
		int s = 0;
		int e = N;

		while (s != e) {
			int mid = s + ((e - s) >> 1);

			if (arr[mid] >= t) {
				e = mid;
			} else {
				s = mid + 1;
			}
		}

		return s;
	}
}