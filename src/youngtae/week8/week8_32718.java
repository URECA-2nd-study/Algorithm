package youngtae.week8;
import java.io.*;
import java.util.*;

public class week8_32718 {

	static int N, K, T;
	static int[] queue, a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		K = Integer.parseInt(str.nextToken());
		T = Integer.parseInt(str.nextToken());

		queue = new int[N];
		a = new int[T];

		str = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			queue[i] = Integer.parseInt(str.nextToken()) % K;
		}

		Arrays.sort(queue);

		str = new StringTokenizer(br.readLine());
		for(int j = 0; j < T; j++) {
			a[j] = Integer.parseInt(str.nextToken());
		}

		int tmp = 0;

		for(int i = 0; i < T; i++) {
			tmp = (tmp+a[i]) % K;

			int max = K-1-tmp;

			int b = binarySearch(max);
			if(b == -1) sb.append((queue[N-1]+tmp) % K).append(" ");
			else sb.append((queue[b]+tmp) % K).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
	}

	private static int binarySearch(int tmp) {
		int st = 0;
		int ed = N-1;

		int result = -1;
		while(st <= ed) {
			int mid = (ed+st) / 2;

			if(queue[mid] <= tmp) {
				result = mid;
				st = mid + 1;
			}
			else ed = mid - 1;
		}

		return result;
	}
}
