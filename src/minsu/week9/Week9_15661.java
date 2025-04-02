import java.io.*;
import java.util.*;

public class Week9_15661 {
	private static int N, total, ans;
	private static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
        // 그냥 ans 숫자는 키감 좋은 숫자로 지정했습니다.
        // Integer.MaxValue 해도 무방
		ans = 987654321;
		total = 0;

        // 각 번호 행렬의 총합을 행열의 0번 인덱스에 총합을 넣어줌
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				arr[0][j] += arr[i][j];
				arr[i][0] += arr[i][j];
				total += arr[i][j];
			}
		}

		combi(1, 0, total);
		System.out.println(ans);
	}

	private static void combi(int idx, int cnt, int sum) {
        // 모든 번호까지 내려갔거나, 뽑은 숫자가 N의 절반까지 뽑았을 경우
        // if (idx == N + 1 || cnt == (N / 2))
		if (idx == N + 1 || cnt == (N >> 1)) {
            // ans 최신화
			ans = Math.min(ans, Math.abs(sum));

		} else {
            // 숫자를 스타트팀에 넣을 경우
            // total에서 현재 번호의 각 행렬 총합을 빼줌
            // 그것이 바로 스타트팀과 링크팀의 차이를 나타냄.
            // 왜인지 궁금하면 찾아오샘요.
			combi(idx + 1, cnt + 1, sum - (arr[0][idx] + arr[idx][0]));
            // 안넣을 경우
			combi(idx + 1, cnt, sum);
		}

	}
}

// 비트 마스킹 버전 코드
// 효율적이지는 않은 것 같습니다.
// import java.io.*;
// import java.util.*;

// public class Main {
// 	private static int N, ans;
// 	private static int[][] arr;

// 	public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringBuilder sb = new StringBuilder();
// 		StringTokenizer st = null;

// 		N = Integer.parseInt(br.readLine());
// 		arr = new int[N + 1][N + 1];
// 		ans = 987654321;

// 		for (int i = 1; i <= N; i++) {
// 			st = new StringTokenizer(br.readLine());
// 			for (int j = 1; j <= N; j++) {
// 				arr[i][j] = Integer.parseInt(st.nextToken());
// 			}
// 		}

// 		for (int i = 1; i <= (N >> 1) + 1; i++) {
// 			combi(1, 0, i, 0);
// 		}

// 		System.out.println(ans);
// 	}

// 	private static void combi(int idx, int depth, int M, int pick) {

// 		if (depth == M) {
// 			int num = divideTeam(pick);
// 			ans = Math.min(ans, num);
// 		} else {

// 			for (int i = idx; i <= N - M + depth; i++) {
// 				pick |= (1 << i);
// 				combi(i + 1, depth + 1, M, pick);
// 				pick ^= (1 << i);
// 			}
// 		}

// 	}

// 	private static int divideTeam(int pick) {
// 		StringBuilder sb = new StringBuilder();
// 		int sc1 = 0;
// 		int sc2 = 0;

// 		for (int i = 1; i <= N; i++) {
// 			if ((pick & (1 << i)) != 0) {
// 				sc1 += cal(i, 1, pick);
// 			} else {
// 				sc2 += cal(i, 0, pick);
// 			}
// 		}

// 		return Math.abs(sc2 - sc1);
// 	}

// 	private static int cal(int idx, int num, int pick) {
// 		int sum = 0;

// 		for (int i = 1; i <= N; i++) {
// 			if (i == idx)
// 				continue;

// 			if (num == 1) {
// 				if ((pick & (1 << i)) != 0) {
// 					sum += arr[idx][i];
// 				}
// 			} else {
// 				if ((pick & (1 << i)) == 0) {
// 					sum += arr[idx][i];
// 				}
// 			}
// 		}
// 		return sum;
// 	}

// }