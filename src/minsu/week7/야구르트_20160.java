import java.io.*;
import java.util.*;

/*
* 야구르트 아줌마가 각 노드를 방문했을 때 걸리는 시간을 구하고
* 내가 출발 노드로부터 갈 수 있는 노드들을 최단 시간으로 갈 수 있는 것을 구해서
* 비교
*/

public class 야구르트_20160 {
	private static int V, E, ps;
	private static ArrayList<int[]>[] arr;
	private static int[] info;
	private static long[] time;
	private static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		arr = new ArrayList[V + 1];
		info = new int[11];
		time = new long[11];

		for (int i = 1; i <= V; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[from].add(new int[] { to, w });
			arr[to].add(new int[] { from, w });
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 10; i++) {
			info[i] = Integer.parseInt(st.nextToken());
		}

        // 현재 요구르트 아줌마가 갈 수 없는 곳이라면
        // 다음 노드로 건너간다.
		for (int i = 1; i < 10; i++) {
			int j = i + 1;

			while (j < 11) {
				long dis = yagoo(info[i], info[j]);

				if (dis == INF) {
					time[j] = INF;
					j++;
				} else {
					time[j] = time[i] + dis;
					break;
				}
			}
			i = j - 1;
		}

		ps = Integer.parseInt(br.readLine());
		long[] dist = findYagoo(ps, new long[V + 1]);

		int result = -1;
        // 가장 작은 노드를 구하기 위해
        // 뒤에서부터 읽음
        // 앞에서부터 읽으면 그냥 구했을 때 break; 조건 넣어주면 된다.
		for (int i = V; i > 0; i--) {
			if (dist[i] == INF)
				continue;

			if (isPoss(i, dist[i])) {
				result = i;
			}
		}
		System.out.println(result);
	}

	private static boolean isPoss(int stand, long t) {
		for (int i = 1; i <= 10; i++) {
			if (info[i] == stand && time[i] >= t && time[i] < INF) {
				return true;
			}
		}

		return false;
	}


    // 야구르트 아줌마를 찾으러가는 내가
    // 출발지로부터 갈 수 있는 노드들로 갈 수 있는 최단 시간을 구하는 다익스트라 메서드
	private static long[] findYagoo(int st, long[] dist) {
		Arrays.fill(dist, INF);
		boolean[] visited = new boolean[V + 1];
		dist[st] = 0;

		PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {

			@Override
			public int compare(long[] o1, long[] o2) {
				return (int) (o1[1] - o2[1]);
			}
		});

		pq.offer(new long[] { st, 0 });

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int idx = (int) curr[0];

			if (visited[idx])
				continue;

			visited[idx] = true;

			for (int[] w : arr[idx]) {
				if (dist[w[0]] > dist[idx] + w[1]) {
					dist[w[0]] = dist[idx] + w[1];
					pq.offer(new long[] { w[0], dist[w[0]] });
				}
			}
		}

		return dist;
	}

    // 야구르트 아줌마가 현재 노드에서 다음 노드로 가는 최단 거리를 구하는 메서드
    // 못가는 곳은 INF 리턴
	private static long yagoo(int st, int end) {
		long[] dist = new long[V + 1];
		boolean[] visited = new boolean[V + 1];
		Arrays.fill(dist, INF);
		dist[st] = 0;

		PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {

			@Override
			public int compare(long[] o1, long[] o2) {
				return (int) (o1[1] - o2[1]);
			}
		});

		pq.offer(new long[] { st, 0 });

		while (!pq.isEmpty()) {

			long[] curr = pq.poll();
			int idx = (int) curr[0];
			if (visited[idx])
				continue;

			if (idx == end) {
				return curr[1];
			}

			visited[idx] = true;

			for (int[] w : arr[idx]) {
				if (dist[w[0]] > dist[idx] + w[1]) {
					dist[w[0]] = dist[idx] + w[1];
					pq.offer(new long[] { w[0], dist[w[0]] });
				}
			}

		}

		return INF;
	}
}
