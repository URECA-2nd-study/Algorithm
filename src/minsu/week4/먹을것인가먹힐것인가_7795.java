import java.io.*;
import java.util.*;

public class 먹을것인가먹힐것인가_7795 {

	private static int N, M;
	private static int[] numsA, numsB;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			numsA = new int[N];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				numsA[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(numsA);

			numsB = new int[M];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < M; i++) {
				numsB[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(numsB);

			System.out.println(search());
		}

	}

	private static int search() {

		int cnt = 0;

		int aP = N - 1;
		int bP = M - 1;

		while (true) {

			if (aP < 0 || bP < 0) {
				break;
			}
			
			
			if(numsA[aP] > numsB[bP]) {
				cnt += bP + 1;
				aP--;
			} else {
				bP--;
			}
			
			
		}

		return cnt;

	}

}