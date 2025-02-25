import java.io.*;
import java.util.*;

public class BOJ_24542 {

	private static int N, M;
	private static int[] p;

	public static void main(String[] args) throws IOException {
        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int numA = Integer.parseInt(st.nextToken());
			int numB = Integer.parseInt(st.nextToken());
			union(findP(numA), findP(numB));
		}

		int[] cnt = update();
        


		System.out.println(output(cnt));
	}
    
    private static long output(int[] cnt) {
        long sum = 1;
		for (int i = 1; i < cnt.length; i++) {
			if (cnt[i] > 0) {
				sum = (sum * cnt[i]) % 1000000007;
			}
		}
        
        return sum;
    }
    
    private static int[] update() {
        int[] cnt = new int[N + 1];
        
        for (int i = 1; i < p.length; i++) {
            p[i] = findP(p[i]);
			cnt[p[i]]++;
		}
        
        return cnt;
    }

	private static void union(int A, int B) {
		p[B] = A;
	}

	private static int findP(int x) {
		if (x != p[x]) {
			p[x] = findP(p[x]);
		}

		return p[x];
	}

}
