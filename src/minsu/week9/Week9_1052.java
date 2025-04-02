import java.io.*;
import java.util.*;

public class Week9_1052 {
	private static int N, K;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(simulate());
	}

    /*
    * N에 1씩 계속 더 해주면서 비트 1의 갯수가 K개 이하가 될 때 까지 while문을 돌리면 된다.
    */

	private static int simulate() {
		int cnt = 0;
		int bottle = N;
		
		while(Integer.bitCount(bottle) > K) {
			cnt++;
			bottle = N + cnt;
		}
		return cnt;
	}
}