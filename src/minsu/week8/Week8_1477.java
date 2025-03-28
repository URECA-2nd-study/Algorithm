import java.io.*;
import java.util.*;

public class Week8_1477 {
	private static int N, M, L;
	private static int[] arr;
	private static int[] dif;
	private static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		arr = new int[N + 2];
		arr[0] = 0;
		arr[N + 1] = L;
		cnt = M;

        // N이 1 이상일 경우에만
		if (N > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}

        // 휴게소 위치를 정렬 시켜켜준다.
		Arrays.sort(arr);
		dif = new int[N + 1];
        
        // 앞 뒤 휴게소별로 차이를 담는다.
		for (int i = 0; i < arr.length - 1; i++) {
			dif[i] = arr[i + 1] - arr[i];
		}
		System.out.println(binarySerach());
	}
    
	private static int binarySerach() {
		int s = 1;
		int e = L;
		int result = 0;

		while (s <= e) {
			int mid = s + ((e - s) >> 1);
            // int mid = s + ((e - s) / 2);

			if (check(mid)) {
				result = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return result;
	}

    // 휴게소와 휴게소 사이 몇 간격 마다 새로운 휴게소를 설치할 수 있는지 확인하는 메서드
	private static boolean check(int mid) {
		int count = 0;

		for (int i = 0; i <= N; i++) {
			count += ((dif[i] - 1) / mid);
		}
		return count <= cnt;
	}

}