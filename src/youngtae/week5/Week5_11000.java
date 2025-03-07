package youngtae.week5;

import java.io.*;
import java.util.*;

public class Week5_11000 {

	static int N, result;
	static int[][] arr;
	static int[] ed;
	static boolean[] isUsed;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		ed = new int[N];
		isUsed = new boolean[N];
		result = 0;

		for(int i = 0; i < N; i++) {
			str = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(str.nextToken());
			int v = Integer.parseInt(str.nextToken());

			arr[i][0] = u;
			arr[i][1] = v;
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			// 시작 시간이 pq(종료시간)보다 크다면 이어서 가능하다는 뜻.
			if (!pq.isEmpty() && pq.peek() <= arr[i][0]) {
				// 기존 강의실 배정을 제거하고
				pq.poll();
			}
			// 새로운 타임으로 대체 || 새로운 강의실 배정
			pq.offer(arr[i][1]);
		}

		sb.append(pq.size());

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

}