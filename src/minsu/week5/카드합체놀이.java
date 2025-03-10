import java.io.*;
import java.util.*;

public class 카드합체놀이 {

	private static long[] heap;
	private static int N, M, size;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		heap = new long[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			push(Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(game());

	}

	private static long game() {

		for (int i = 0; i < M; i++) {
			long numA = pop();
			long numB = pop();
			
			push(numA + numB);
			push(numA + numB);
		}
		
		long sum = 0;
		
		while(size != 0) {
			sum += pop();
		}
		
		return sum;

	}

	private static void swap(int i, int j) {
		long tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;

	}

	private static void push(long data) {
		heap[++size] = data;

		int p = size / 2;
		int ch = size;

		while (ch != 1 && heap[p] > heap[ch]) {

			swap(p, ch);

			ch = p;
			p = ch / 2;

		}
	}

	private static long pop() {

		long num = heap[1];

		heap[1] = heap[size--];

		int p = 1;
		int ch = p * 2;

		if (ch + 1 <= size && heap[ch] > heap[ch + 1]) {
			ch++;
		}

		while (ch <= size && heap[ch] < heap[p]) {
			swap(p, ch);

			p = ch;
			ch = p * 2;

			if (ch + 1 <= size && heap[ch] > heap[ch + 1]) {
				ch++;
			}
		}

		return num;
	}

}