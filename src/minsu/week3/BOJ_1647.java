import java.io.*;
import java.util.*;

public class BOJ_1647 {

	private static int N, M;

	private static class Edge implements Comparable<Edge> {
		int from, to, w;

		
		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}



		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

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

		Edge[] edges = new Edge[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(from, to, w);
		}

		Arrays.sort(edges);

		System.out.println(devide(edges));

	}

	private static int devide(Edge[] edges) {

		int sum = 0;
		int pick = 0;
		for (int i = 0; i < M; i++) {
			Edge curr = edges[i];

			int px = findP(curr.from);
			int py = findP(curr.to);

			if (px != py) {
				union(px, py);
				sum += curr.w;
				pick++;
			}

			if (pick == N - 1) {
				sum -= curr.w;
				break;
			}
		}

		return sum;

	}

	private static int findP(int x) {
		if (x != p[x]) {
			p[x] = findP(p[x]);
		}

		return p[x];
	}

	private static void union(int x, int y) {
		p[y] = x;
	}

}
