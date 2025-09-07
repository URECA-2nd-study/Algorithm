package minsu.week16;

import java.io.*;
import java.util.*;

public class Week16_20008 {
	private static int N, HP, answer;
	private static int[][] info;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		HP = Integer.parseInt(st.nextToken());
		answer = Integer.MAX_VALUE;

		info = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
		simulate(0, HP, new int[N]);
		System.out.println(answer);
	}

	private static void simulate(int time, int currHP, int[] timeInfo) {
		if (time >= answer) {
			return;
		}

		if (currHP <= 0) {
			answer = Math.min(answer, time);
			return;
		}

		boolean damage = false;
		for (int i = 0; i < N; i++) {
			if (timeInfo[i] <= time) {
				damage = true;
				int tmp = timeInfo[i];
				timeInfo[i] = time + info[i][0];
				simulate(time + 1, currHP - info[i][1], timeInfo);
				timeInfo[i] = tmp;
			}
		}

		if (!damage) {
			simulate(time + 1, currHP, timeInfo);
		}
	}
}
