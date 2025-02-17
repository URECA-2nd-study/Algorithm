import java.io.*;
import java.util.*;

public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		init();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (command == 0) {
				union(a, b);
			} else {
				if (find(a) == find(b)) bw.write("YES\n");
				else bw.write("NO\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		parent[bRoot] = aRoot;
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}
	
	static void init() {
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}

}
