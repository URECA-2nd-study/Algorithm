package youngtae.week5;

import java.io.*;
import java.util.*;

public class Week5_10330 {

	static int N, M, one, zero, result;
	static int[] arr, tmp, ost, zst;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());

		arr = new int[N];
		str = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(str.nextToken());
			if(num == 1) {
				one += 1;
			} else {
				zero += 1;
			}
			arr[i] = num;
		}

		tmp = new int[M];
		str = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			tmp[i] = Integer.parseInt(str.nextToken());
		}

		ost = new int[N];
		zst = new int[N];

		// 0 또는 1로 시작하는 경우의 수 배열 만들기
		init(ost, true);
		init(zst, false);

		result = Integer.MAX_VALUE;
		if(count(ost)) {  // 0과 1의 개수가 실제 배열 개수와 일치하는지 확인
			// 개수가 일치한다면 직접 이동시키면서 최소 이동 값 확인
			result = Math.min(result, move(ost));
		}
		if(count(zst)) {
			// 이동시키기
			result = Math.min(result, move(zst));
		}
		sb.append(result);

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static int move(int[] arr2) {
		int count = 0;
		for(int i = 0; i < N; i++) {
			if(arr2[i] != arr[i]) {
				for(int j = i+1; j < N; j++) {
					if(arr2[i] != arr2[j] && arr2[j] != arr[j]) {
						int tmp = arr2[j];
						arr2[j] = arr2[i];
						arr2[i] = tmp;
						count += j - i;
						break;
					}
				}
			}
		}
		return count;
	}

	private static boolean count(int[] arr) {
		int arrOne = 0;
		int arrZero = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 1) {
				arrOne += 1;
				continue;
			}

			arrZero += 1;
		}


		return one == arrOne && zero == arrZero;
	}

	private static void init(int[] arr, boolean b) {
		int count = 0;
		int tIndex = 0;
		int tNum = tmp[tIndex];
		boolean flag = b;
		for(int i = 0; i < N; i++) {
			if(flag) {
				arr[i] = 1;
			} else {
				arr[i] = 0;
			}

			count ++;

			if(count == tNum && tIndex < M-1) {
				count = 0;
				tNum = tmp[++tIndex];
				flag = !flag;
			}
		}
	}
}