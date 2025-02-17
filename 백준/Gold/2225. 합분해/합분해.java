import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 0부터 N까지 범위 숫자중 K개 써서 N 만들기
        // K개 써서 만들 수 있는 숫자...

        int[][] dp = new int[K + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            dp[1][i] = 1;
        }

        // 1장 0 1 2 3 4 5 6 7
        //     1 1 1 1 1 1 1 1
        // 2장 0 1 2 3 4 5 6 7
        //    1 (1+0), (0 + 1)



        for (int i = 2; i <= K; i++) {

            for (int j = 0; j <= N; j++) {
                dp[i][j] = dp[i - 1][j]; // 이전 방법에서 0 더하기
                if (j - 1 >= 0) dp[i][j] = (dp[i][j] +  dp[i][j - 1]) % 1_000_000_000 ; // 이전 방법에서 1 더하기...
            }
        }

        System.out.println(dp[K][N]);
    }
}
