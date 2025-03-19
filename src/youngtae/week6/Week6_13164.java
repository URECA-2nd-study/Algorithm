package youngtae.week6;

import java.io.*;
import java.util.*;

public class Week6_13164 {

	static int N, K, result;
	static int[] arr;
	static List<Integer> arr2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer str = new StringTokenizer(br.readLine());

		N = Integer.parseInt(str.nextToken());
		K = Integer.parseInt(str.nextToken());
		arr = new int[N];

		str = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str.nextToken());
		}

		arr2 = new ArrayList<>();
		for(int i = 0; i < N-1; i++) {
			arr2.add(arr[i+1] - arr[i]);
		}

		Collections.sort(arr2);
		for(int i = 0; i < N-K; i++) {
			result += arr2.get(i);
		}

		bw.write(String.valueOf(result));
		bw.flush();
		br.close();
		bw.close();
	}

}