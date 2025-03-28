package youngtae.week8;
import java.io.*;
import java.util.*;

public class week8_17951 {

	static int N, M, min, sum;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());

		arr = new int[N];
		min = Integer.MAX_VALUE;
		sum = 0;

		str = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			sum += arr[i] = Integer.parseInt(str.nextToken());
			min = Math.min(min, arr[i]);
		}

		sb.append(binarySearch(min, sum));

		bw.write(sb.toString());
		bw.flush();
	}

	private static int binarySearch(int st, int ed) {
		int mid = 0;
		while(st <= ed) {
			mid = (st+ed) / 2; // 시험 평균 점수

			if(sol(mid)) {
				ed = mid -1;
			} else {
				st = mid + 1;
			}
		}

		return ed;
	}

	private static boolean sol(int mid) {
		int group = 0;
		int sum = 0;

		// 맨 앞 시험지부터 합계를 만들어서 mid보다 크거나 같으면 그룹 생성
		for(int i = 0; i < N; i++) {
			sum += arr[i];
			if(sum >= mid) {
				group++;
				sum = 0;
			}
		}

		return group < M;
	}

}