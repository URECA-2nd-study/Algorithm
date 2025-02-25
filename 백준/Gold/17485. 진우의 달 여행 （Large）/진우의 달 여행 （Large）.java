import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // DP에서 직전 방향을 저장을 할 것인가를 생각...

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][][] dp = new int[4][N][M]; // 1번은 좌측 대각선... 2번은 밑으로.. 3번은 우측 대각선

        // dp 무한대로 초기화할 필요가 있음
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], 12345678);
            }
        }


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                dp[0][i][j] = Integer.parseInt(st.nextToken());
            }
        }



        for (int i = 0; i < M; i++) { // 초기값 설정

            // 좌측 대각선
            if (i - 1 >= 0) dp[1][1][i - 1] = dp[0][1][i - 1] + dp[0][0][i];

            // 밑으로 내려가기
            dp[2][1][i] = dp[0][1][i] + dp[0][0][i];

            // 우측 대각선
            if (i + 1 < M) dp[3][1][i + 1] = dp[0][1][i + 1] + dp[0][0][i];

        }


        for (int i = 1; i < N - 1; i++) { // i번째 행
            for (int j = 0; j < M; j++) { // j번째 열

                // 이전이 좌측 대각선이라면 밑, 우측만 가능
                dp[2][i + 1][j] = Math.min(dp[2][i + 1][j], dp[0][i + 1][j] + dp[1][i][j]);
                if (j + 1 < M) dp[3][i + 1][j + 1] = Math.min(dp[3][i + 1][j + 1], dp[0][i + 1][j + 1] + dp[1][i][j]);

                // 이전이 밑이라면 좌측 대각선, 우측만 가능
                if (j - 1 >= 0) dp[1][i + 1][j - 1] = Math.min(dp[1][i + 1][j - 1], dp[0][i + 1][j - 1] + dp[2][i][j]);
                if (j + 1 < M) dp[3][i + 1][j + 1] = Math.min(dp[3][i + 1][j + 1], dp[0][i + 1][j + 1] + dp[2][i][j]);

                // 이전이 우측 대각선이라면 좌측 대각선, 밑만 가능
                if (j - 1 >= 0) dp[1][i + 1][j - 1] = Math.min(dp[1][i + 1][j - 1], dp[0][i + 1][j - 1] + dp[3][i][j]);
                dp[2][i + 1][j] = Math.min(dp[2][i + 1][j], dp[0][i + 1][j] + dp[3][i][j]);

            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.min(answer, dp[i][N - 1][j]);
            }
        }

        System.out.println(answer);

    }
}
