package youngtae.week9;

import java.io.*;
import java.util.*;

public class week9_1052 {

	static int N, K, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		K = Integer.parseInt(str.nextToken());

		result = 0;
		while(true) {

			// 1의 개수 == 최소로 만들 수 있는 물병의 수
			if(Integer.bitCount(N) <= K) break;

			N++;
			result++;
		}

		bw.write(String.valueOf(result));
		bw.flush();
	}
}

/**
 * Greedy 풀이
 */
// public class week9_1052 {
//
// 	static int N, K, result;
//
// 	public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
// 		StringTokenizer str;
//
// 		result = -1;
// 		str = new StringTokenizer(br.readLine());
// 		N = Integer.parseInt(str.nextToken());
// 		K = Integer.parseInt(str.nextToken());
//
// 		sol();
// 		bw.write(String.valueOf(result));
//
// 		bw.flush();
// 	}
//
// 	private static void sol() {
//		// 처음부터 K개 이하로 물병인지 검증
// 		if(N <= K) {
// 			result = 0;
// 			return;
// 		}
//
// 		int buy = 0;
//
// 		while(true) {
//			// 초기 물병 + 구매 물병 = 총 물병
// 			int total = N + buy;
// 			int count = 0;
//			// 최대로 물병 합치기
// 			while(total > 0) {
// 				if(total % 2 != 0) {
// 					count++;
// 				}
// 				total /= 2;
// 			}
//			// 남은 물병
// 			if(count <= K) break;
//
// 			buy++;
// 		}
//
// 		result = buy;
// 	}
//
// }