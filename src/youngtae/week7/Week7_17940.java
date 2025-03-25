import java.io.*;
import java.util.*;

public class Week7_17940 {

	static int N, M;
	static final int INF = Integer.MAX_VALUE;
	static long[] dist, changeCount;
	static boolean[] company;
	static ArrayList<Node>[] graph;

	static class Node implements Comparable<Node> {
		int index;
		long cost;
		long change;

		Node(int index, long cost, long change) {
			this.index = index;
			this.cost = cost;
			this.change = change;
		}

		@Override
		public int compareTo(Node o) {
			if(this.change == o.change) return Long.compare(this.cost, o.cost);

			return Long.compare(this.change, o.change);
		}
	}


	/**
	 * 0 - A, 1 - B
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());

		graph = new ArrayList[N];
		dist = new long[N];
		changeCount = new long[N];
		company = new boolean[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = INF;
			changeCount[i] = INF;
			if(Integer.parseInt(br.readLine()) == 1) company[i] = true;
		}

		int cost = 0;
		for(int i = 0; i < N; i++) {
			str = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				cost = Integer.parseInt(str.nextToken());
				if(cost > 0) graph[i].add(new Node(j, cost, company[i] == company[j] ? 0 : 1));
			}
		}

		dijkstra(0);
		bw.write(changeCount[M] + " " + dist[M]);

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dijkstra(int st) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(st, 0, 0));
		dist[st] = 0;
		changeCount[st] = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();

			for(Node next : graph[now.index]) {
				long newChange = changeCount[now.index] + next.change;
				long newCost = dist[now.index] + next.cost;

				if(changeCount[next.index] > newChange) { // 환승 최단 거리
					changeCount[next.index] = newChange;
					dist[next.index] = newCost;  // 환승이 최단이라면 거리는 신경안씀
					pq.add(new Node(next.index, dist[next.index], changeCount[next.index]));
					continue;
				}

				if(changeCount[next.index] == newChange && dist[next.index] > newCost) { // 환승이 똑같고 거리가 최단이라면
					dist[next.index] = newCost;
					pq.add(new Node(next.index, dist[next.index], changeCount[next.index]));
				}
			}
		}
	}

}