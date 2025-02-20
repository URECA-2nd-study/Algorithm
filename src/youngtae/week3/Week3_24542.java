package youngtae.week3;

import java.io.*;
import java.util.*;

public class Week3_24542 {

	static int n, m;
	static int[] arr, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		arr = new int[n+1];
		answer = new int[n+1];
		Arrays.fill(arr, -1);

		for(int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(str.nextToken());
			int v = Integer.parseInt(str.nextToken());
			union(u, v);
		}

		for(int i = 1; i <= n; i++) {
			int num = find(i);
			if(num == -1) {
				continue;
			}
			if(num > 0) {
				answer[num]++;
			}
		}

		long result = 1;
		for(int i = 1; i <= n; i++) {
			int num = answer[i];
			if(num == 0) {
				continue;
			}
			result = (result * num) % 1_000_000_007;
		}

		sb.append(result % 1_000_000_007);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);

		if(u == v) {
			return;
		}

		if(arr[v] < arr[u]) {
			int tmp = 0;
			tmp = u;
			u = v;
			v = tmp;
		}

		if(arr[u] == arr[v]) {
			arr[u]--;
		}

		arr[v] = u;
	}

	private static int find(int u) {
		if(arr[u] < 0) {
			return u;
		}

		return arr[u] = find(arr[u]);
	}

}