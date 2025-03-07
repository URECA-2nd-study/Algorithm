package youngtae.week5;

import java.io.*;
import java.util.*;

public class Week5_15903 {

	static int N, M;
	static long result;
	static PriorityQueue<Long> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());

		str = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			pq.add(Long.parseLong(str.nextToken()));
		}

		// 작은 점수를 만들기 위해 최소 값 2개(x, y)를 뽑아 더한다.
		for(int i = 0; i < M; i++) {
			long x = pq.poll();
			long y = pq.poll();

			// x, y 값 덮어쓰기
			for(int j = 0; j < 2; j++) {
				pq.add(x+y);
			}
		}

		for(long i : pq) {
			result += i;
		}

		sb.append(result);

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}


}