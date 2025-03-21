import java.io.*;
import java.util.*;

public class Week7_20160 {

	static final int INF = Integer.MAX_VALUE;
	static int V, E, st, result;
	static int[] arr, dist1, dist2;
	static ArrayList<Node>[] graph;
	static int[][] dist3;
	static boolean[] isUsed;

	static class Node implements Comparable<Node> {
		int index;
		int cost;

		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		V = Integer.parseInt(str.nextToken());
		E = Integer.parseInt(str.nextToken());

		graph = new ArrayList[V+1];
		dist1 = new int[V+1];
		dist2 = new int[V+1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		int u = 0, v = 0, c = 0;
		for(int i = 0; i < E; i++) {
			str = new StringTokenizer(br.readLine());
			u = Integer.parseInt(str.nextToken());
			v = Integer.parseInt(str.nextToken());
			c = Integer.parseInt(str.nextToken());

			graph[u].add(new Node(v, c));
			graph[v].add(new Node(u, c));
		}

		arr = new int[11];
		str = new StringTokenizer(br.readLine());
		for(int i = 1; i <= 10; i++) {
			arr[i] = Integer.parseInt(str.nextToken());
		}

		st = Integer.parseInt(br.readLine());

		dist3 = new int[V+1][V+1];
		isUsed = new boolean[V+1];

		// dist1 - 야쿠르트
		// dsit2 - 나
		// sum - 야쿠르트 이동경로 걸리는 시간
		int sum = 0;
		int st1 = 0;
		result = INF;
		//나
		Arrays.fill(dist2, INF);
		dijkstra2(st);

		for(int i = 1; i <= 10; i++) {
			if(i == 1) { // 맨처음 시작 정점과 자신의 정점이 같다면
				if(arr[i] == st) result = Math.min(result, st);
				st1 = arr[i];
				continue;
			}

			//야구르트
			if(!isUsed[st1]) { // 최단경로 저장한게 없다면
				Arrays.fill(dist1, INF);
				dijkstra1(st1); // st -> ed 최단경로
				dist3[st1] = dist1.clone(); // st출발 최단경로 값 저장
				isUsed[st1] = true;
			}
			sum += dist1[st1 = dist1[arr[i]] == INF ? st1 : arr[i]];

			if(sum >= dist2[st1]) result = Math.min(result, st1);
		}

		bw.write(String.valueOf(result == INF ? -1 : result));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void dijkstra2(int st) {
		PriorityQueue<Node> pq = new PriorityQueue();
		pq.add(new Node(st, 0));
		dist2[st] = 0;

		while(!pq.isEmpty()) {
			Node now = pq.poll();

			if(dist2[now.index] < now.cost) continue;

			for(Node next : graph[now.index]) {
				int newCost = dist2[now.index] + next.cost;

				if(dist2[next.index] > newCost) {
					dist2[next.index] = newCost;
					pq.add(new Node(next.index, newCost));
				}
			}
		}
	}

	private static void dijkstra1(int st1) {
		PriorityQueue<Node> pq = new PriorityQueue();
		pq.add(new Node(st1, 0));
		dist1[st1] = 0;

		while(!pq.isEmpty()) {
			Node now = pq.poll();

			if(dist1[now.index] < now.cost) continue;

			for(Node next : graph[now.index]) {
				int newCost = dist1[now.index] + next.cost;

				if(dist1[next.index] > newCost) {
					dist1[next.index] = newCost;
					pq.add(new Node(next.index, newCost));
				}
			}
		}
	}
}