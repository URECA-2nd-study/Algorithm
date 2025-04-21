import java.io.*;
import java.util.*;

public class Week11_11657 {
	private static int N, M;
	private static ArrayList<int[]>[] arr;
	private static final int INF = Integer.MAX_VALUE;
	private static long[] dist, count;
	private static boolean[] isInQueue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[from].add(new int[] { to, w });
		}
        
        // 음의 싸이클 발생 시 - 1출력
		if (isSpfa(1)) {
			System.out.println(-1);
			return;
		} else {
			for (int i = 2; i <= N; i++) {
				sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
			}
			System.out.println(sb.toString());
			return;
		}

	}

	private static boolean isSpfa(int start) {
		dist = new long[N + 1];
		isInQueue = new boolean[N + 1];
		count = new long[N + 1];

		Arrays.fill(dist, INF);
		dist[start] = 0;

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		isInQueue[start] = true;
		count[start]++;

		while (!q.isEmpty()) {
			int curr = q.poll();
			isInQueue[curr] = false;

			for (int[] w : arr[curr]) {
				if (dist[curr] != INF && dist[w[0]] > dist[curr] + w[1]) {
					dist[w[0]] = dist[curr] + w[1];

					if (!isInQueue[w[0]]) {
						q.offer(w[0]);
						isInQueue[w[0]] = true;
						count[w[0]]++;

						// 음의 사이클 확인
						if (count[w[0]] > N)
							return true;
					}
				}
			}
		}
		return false;
	}
}