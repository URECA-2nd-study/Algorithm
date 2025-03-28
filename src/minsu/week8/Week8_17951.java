import java.io.*;
import java.util.*;

public class Main {
	private static int N, K, score;
	private static int[] sc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sc = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sc[i] = Integer.parseInt(st.nextToken());
			score += sc[i];
		}

		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int s = 0;
		int e = score + 1;
		int result = 0;

		while (s != e) {
			int mid = s + (e - s) / 2;

			if (isPoss(mid)) {
				result = mid;
				s = mid + 1;
			} else {
				e = mid;
			}
		}
		return result;
	}

	private static boolean isPoss(int mid) {
		int cnt = 0;
		int total = 0;

		for (int w : sc) {
			total += w;
			if (total >= mid) {
				cnt++;
				total = 0;
			}
		}
		return cnt >= K;
	}
}