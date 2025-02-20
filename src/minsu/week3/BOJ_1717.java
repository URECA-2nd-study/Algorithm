import java.io.*;
import java.util.*;

public class BOJ_1717 {

	private static int N, M;
	private static int[] p;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int state = Integer.parseInt(st.nextToken());
			int numA = Integer.parseInt(st.nextToken());
			int numB = Integer.parseInt(st.nextToken());

			if (state == 0) {
				union(findP(numA), findP(numB));
			} else {
				String str = "NO";
				if (isSameP(findP(numA), findP(numB))) {
					str = "YES";
				}

				sb.append(str).append("\n");
			}
		}
		
		System.out.println(sb.toString());

	}

	private static boolean isSameP(int A, int B) {
		if (A == B)
			return true;
		return false;
	}

	private static void union(int A, int B) {
		p[B] = A;
	}

	private static int findP(int x) {
		if (x != p[x]) {
			p[x] = findP(p[x]);
		}

		return p[x];
	}

}
