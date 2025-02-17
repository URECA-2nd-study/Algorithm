import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M; // N은 노드, M은 testcase
	static final int LOG = 17;
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;
	static int[] depth;
	static int[][] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N + 1];
		depth = new int[N + 1];
		parents = new int[LOG + 1][N + 1];
		visited = new boolean[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		bfs(1);
		
		findAncestors();
		
		M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(root);
		depth[root] = 0;
		visited[root] = true;
		
		while (!queue.isEmpty()) {
			int curNode = queue.poll();
			
			for (int nextNode : adjList[curNode]) {
				if (!visited[nextNode]) {
					visited[nextNode] = true;
					parents[0][nextNode] = curNode;
					depth[nextNode] = depth[curNode] + 1;
					queue.add(nextNode);
				}
			}
		}
	}
	
	static void findAncestors() {
		for (int i = 1; i <= LOG; i++) {
			for (int j = 1; j <= N; j++) {
				parents[i][j] = parents[i - 1][parents[i - 1][j]];
			}
		}
	}
	
	static int lca(int a, int b) {
		if (depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		// 1. a, b의 depth 맞추기
		for (int k = LOG; k >= 0; k--) {
			if (depth[b] - depth[a] >= (1 << k)) {
				b = parents[k][b];
			}
		}
		
		if (a == b) return a;
		
		for (int k = LOG; k >= 0; k--) {
			if (parents[k][a] != parents[k][b]) {
				a = parents[k][a];
				b = parents[k][b];
			}
		}
		
		return parents[0][a];
	}

}
