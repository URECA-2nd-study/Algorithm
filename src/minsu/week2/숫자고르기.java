package minsu.week2;

import java.io.*;
import java.util.*;

public class 숫자고르기 {

	private static int[] p;
	private static int[] dfsVisited, visited;
	private static ArrayList<Integer> result = new ArrayList<>();
	private static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		visited = new int[(N >> 5) + 1];

		p = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			p[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			if ((visited[i >> 5] & (1 << (i & 31))) == 0) {
				dfsVisited = new int[(N >> 5) + 1];
				int[] tmp = p.clone();
				dfs(i, tmp);
			}
		}

		sb.append(result.size()).append("\n");
		Collections.sort(result);
		for (int r : result) {
			sb.append(r).append("\n");
		}
		
		System.out.println(sb.toString());

	}

	private static int dfs(int num, int[] tmp) {

		if ((dfsVisited[tmp[num] >> 5] & (1 << (tmp[num] & 31))) == 0) {
			dfsVisited[num >> 5] |= (1 << (num & 31));
			tmp[num] = dfs(tmp[num], tmp);
		}

		if (num == tmp[num] && (visited[tmp[num] >> 5] & (1 << (tmp[num] & 31))) == 0) {
			visited[num >> 5] |= (1 << (num & 31));
			result.add(num);
		}
		
		return tmp[num];

	}
	
}
