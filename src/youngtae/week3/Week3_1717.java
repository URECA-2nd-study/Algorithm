package youngtae.week3;

import java.io.*;
import java.util.*;

public class Week3_1717 {

	static int n, m;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());

		arr = new int[n+1];
		Arrays.fill(arr, -1);

		int cmd = 0;
		int a = 0;
		int b = 0;

		for(int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine());

			cmd = Integer.parseInt(str.nextToken());
			a = Integer.parseInt(str.nextToken());
			b = Integer.parseInt(str.nextToken());

			if(cmd == 0) {
				union(a, b);
			} else {
				if(find(a) == find(b)) {
					sb.append("YES\n");
					continue;
				}
				sb.append("NO\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static int find(int v) {
		if(arr[v] < 0) {
			return v;
		}

		return arr[v] = find(arr[v]);
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
}