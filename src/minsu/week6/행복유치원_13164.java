import java.io.*;
import java.util.*;

public class 행복유치원_13164 {

	private static int N, K;
	private static int[] nums;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(cal());

	}

	private static int cal() {

        // 인접한 학생들의 키 차이를 담는 배열
		int[] diff = new int[N - 1];

        // 키 차이를 저장합니다.
		for (int i = 0; i < N - 1; i++) {
			diff[i] = nums[i + 1] - nums[i];
		}

        // 키 차이를 오름차순으로 정렬합니다.
		Arrays.sort(diff);

        // 그룹이 하나 일 때 키 차이는 맨 처음과 맨 끝 사람의 차이
        // total 값으로 제공합니다.
		int total = nums[N - 1] - nums[0];

        // total에서 diff의 맨 뒤의 값부터 K - 1개의 값을 빼주면 최소 비용이 나온다.
		for (int i = 0; i < K - 1; i++) {

			total -= diff[N - 2 - i];

		}

		return total;
	}

}
