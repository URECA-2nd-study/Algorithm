import java.util.*;
import java.io.*;

public class 비트문자열재배열하기 {

	private static int N, M;

	private static int[] nums, n;
	private static ArrayList<Integer> comA = new ArrayList<>();
	private static ArrayList<Integer> comB = new ArrayList<>();
	private static boolean[] isPos;
	private static int min = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];

		st = new StringTokenizer(br.readLine());

		int[] cnt = new int[2];
		int[] aCnt = new int[2];
		int[] bCnt = new int[2];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			cnt[nums[i]]++;
		}

		int[] len = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			len[i] = Integer.parseInt(st.nextToken());
		}

		int preA = 1;
		int preB = 0;

		for (int i = 0; i < M; i++) {

			int tmp = len[i];

			for (int j = 0; j < tmp; j++) {

				if (preA == 1) {
					int t = 0;
					comA.add(t);
					aCnt[t]++;
				} else {
					int t = 1;
					comA.add(t);
					aCnt[t]++;
				}

				if (preB == 1) {
					int t = 0;
					comB.add(t);
					bCnt[t]++;
				} else {
					int t = 1;
					comB.add(t);
					bCnt[t]++;
				}

			}

			if (preA == 1)
				preA = 0;
			else if (preA == 0)
				preA = 1;

			if (preB == 1)
				preB = 0;
			else if (preB == 0)
				preB = 1;

		}

		isPos = new boolean[2];

		for (int i = 0; i < 2; i++) {

			isPos[i] = true;

			for (int j = 0; j < 2; j++) {

				if (i == 0 && cnt[j] != aCnt[j]) {
					isPos[i] = false;
				} else if (i == 1 && cnt[j] != bCnt[j]) {
					isPos[i] = false;
				}

			}

		}

		for (int i = 0; i < 2; i++) {
			if (isPos[i]) {
				compare(i);
			}
		}

		System.out.println(min);

	}

	private static void compare(int idx) {
		n = nums.clone();

		ArrayList<Integer> arr;
		if (idx == 0) {
			arr = comA;
		} else {
			arr = comB;
		}
		int sum = 0;

		for (int i = 0; i < N - 1; i++) {

			if (n[i] == arr.get(i))
				continue;

			int cnt = 1;
			int j = 1;

			while (i + j < N && n[i] == n[i + j]) {
				j++;
				cnt++;
			}
			sum += cnt;
			swap(i, i + j);

		}

		min = Math.min(min, sum);

	}

	private static void swap(int i, int j) {
		int tmp = n[i];
		n[i] = n[j];
		n[j] = tmp;
	}

}