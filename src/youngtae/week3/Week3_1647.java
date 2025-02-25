package youngtae.week3;

import java.io.*;
import java.util.*;

public class Week3_1647 {

	static int V, E;
	static int[] arr;
	static ArrayList<Node> graph;
	static ArrayList<int[]> mst;

	static class Node implements Comparable<Node> {
		int u;
		int v;
		int w;

		Node(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		V = Integer.parseInt(str.nextToken());
		E = Integer.parseInt(str.nextToken());

		graph = new ArrayList<>();
		mst = new ArrayList<>();
		arr = new int[V+1];
		Arrays.fill(arr, -1);

		for(int i = 0; i < E; i++) {
			str = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(str.nextToken());
			int v = Integer.parseInt(str.nextToken());
			int w = Integer.parseInt(str.nextToken());

			graph.add(new Node(u, v, w));
		}

		Collections.sort(graph);

		for(Node node : graph) {
			if(mst.size() == V-2) {
				break;
			}
			mst(node);
		}

		int result = 0;
		for(int[] iArr : mst) {
			result += iArr[2];
		}



		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void mst(Node node) {
		int u = node.u;
		int v = node.v;
		int w = node.w;

		if(find(u) == find(v)) {
			return;
		}

		if(union(u, v)) {
			mst.add(new int[] {u, v, w});
		}
	}

	private static int find(int u) {
		if(arr[u] < 0) {
			return u;
		}

		return arr[u] = find(arr[u]);
	}

	private static boolean union(int u, int v) {
		u = find(u);
		v = find(v);

		if(u == v) {
			return false;
		}

		if(arr[v] < arr[u]) {
			int tmp = u;
			u = v;
			v = tmp;
		}

		if(arr[u] == arr[v]) {
			arr[u]--;
		}

		arr[v] = u;
		return true;
	}

}