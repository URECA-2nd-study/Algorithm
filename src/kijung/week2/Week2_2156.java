import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] wine = new int[size + 1];
        int[] dp = new int[size + 1];

        for (int i = 1; i <= size; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = wine[1];

        if (size >= 2) dp[2] = wine[1] + wine[2];

        for (int i = 3; i <= size; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
        }

        System.out.println(dp[size]);
    }
}
