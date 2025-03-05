package youngtae.week4;

import java.io.*;
import java.util.*;

public class Week4_22945 {

	static int N, result;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer str = new StringTokenizer(br.readLine());

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str.nextToken());
		}

		if(N == 2) {		// N==2 -> 두 개발자 사이에 존재하는 개발자 수 == 0  ==> 2 * 0 = 0
			result = 0;
		} else {
			sol();
		}

		sb.append(result);

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void sol() {
		int st = 0;
		int ed = N-1;

		while(st < ed) {
			result = Math.max(result, ((ed-st-1) * Math.min(arr[st], arr[ed])));

			if(arr[st] <= arr[ed]) {	// A, B 중 더 작은 개발자 능력치가 조금이라도 커야 최대값이 되기 때문에 작은 개발자의 능력치를 변경시킴
				st++;
			} else {
				ed--;
			}
		}
	}

}