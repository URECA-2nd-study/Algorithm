package youngtae.week5;

import java.io.*;
import java.util.*;

/**
 * union-find로 시도한 방법
 * 메모리 초과로 인한 실패 코드
 */
public class Week5_11000_Wrong {

	static int N;
	static int[] arr, result;
	static HashMap<Integer, Integer> map = new HashMap<>();
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		N = Integer.parseInt(br.readLine());
		arr = new int[1000000000];
		Arrays.fill(arr, -1);

		for(int i = 0; i < N; i++) {
			str = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(str.nextToken());
			int v = Integer.parseInt(str.nextToken());

			union(u, v);
			map.put(u, 0);
			map.put(v, 0);
		}

		for(int keys : map.keySet()) {
			set.add(find(keys));
		}

		sb.append(set.size());
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static int find(int u) {
		if(arr[u] < 0) {
			return u;
		}

		return arr[u] = find(arr[u]);
	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);

		if(u == v) {
			return;
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
	}

}