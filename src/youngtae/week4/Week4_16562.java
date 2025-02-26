package youngtae.week4;

import java.io.*;
import java.util.*;

public class Week4_16562 {

	static final int INF = Integer.MAX_VALUE;
	static int N, M, K;
	static int[] arr, cost;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());
		K = Integer.parseInt(str.nextToken());

		arr = new int[N+1];
		cost = new int[N+1];
		graph = new ArrayList<>();

		str = new StringTokenizer(br.readLine());

		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for(int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(str.nextToken());
		}

		Arrays.fill(arr, -1);		// union by rank 사전 작업

		for(int i = 0; i < M; i++) {
			str = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(str.nextToken());
			int v = Integer.parseInt(str.nextToken());

			union(u, v);
		}

		for(int i = 1; i <= N; i++) {
			graph.get(find(i)).add(i);		// 인접 리스트를 통해 자신의 대빵 집단에 넣기
		}

		int total = 0;

		for(ArrayList<Integer> list : graph) {
			int min = INF;
			for(int index : list) {
				min = Math.min(min, cost[index]);		// 집단 중 친구 비용 최소화 찾기
			}

			total += min == INF ? 0 : min;		// 인접 리스트가 Null == INF 처리
		}

		if(total <= K) {
			sb.append(total);
		} else {
			sb.append("Oh no");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if(a == b) {
			return;
		}

		if(arr[b] < arr[a]) {		// a가 b보다 작으면 a,b swap  * 대빵은 음수 값!
			int tmp = a;
			a = b;
			b = tmp;
		}

		if(arr[a] == arr[b]) {  // 대빵 rank 증가
			arr[a]--;
		}

		arr[b] = a;
	}

	private static int find(int a) {
		if(arr[a] < 0) {
			return a;
		}

		return arr[a] = find(arr[a]);		// 경로 압축
	}
}