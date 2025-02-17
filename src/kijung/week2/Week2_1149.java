import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        int[][] dp = new int[size][3];
        int[][] color = new int[size][3];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }

        }


        dp[0][0] = color[0][0];
        dp[0][1] = color[0][1];
        dp[0][2] = color[0][2];

        for (int i = 1; i <= size - 1; i++) {

            // 현재 R은 이전 B, G 중에 하나 선택
            dp[i][0] = Math.min(dp[i - 1][1] + color[i][0], dp[i - 1][2] + color[i][0]);
            // 현재 G은 이전 R, B 중에 하나 선택
            dp[i][1] = Math.min(dp[i - 1][0] + color[i][1], dp[i - 1][2] + color[i][1]);
            // 현재 B은 이전 R, G 중에 하나 선택
            dp[i][2] = Math.min(dp[i - 1][0] + color[i][2], dp[i - 1][1] + color[i][2]);

        }

        Arrays.sort(dp[size - 1]);
        System.out.println(dp[size - 1][0]);
    }
}
