package src.jaehyeon.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week2_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][N+1];

        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N+1][4];

        dp[1][1] = arr[1][1];
        dp[1][2] = arr[1][2];
        dp[1][3] = arr[1][3];

        for(int i=2; i<=N; i++){
            dp[i][1] = Math.min(dp[i-1][2],dp[i-1][3]) + arr[i][1];
            dp[i][2] = Math.min(dp[i-1][1],dp[i-1][3]) + arr[i][2];
            dp[i][3] = Math.min(dp[i-1][1],dp[i-1][2]) + arr[i][3];
        }

        System.out.println(Math.min(dp[N][1],Math.min(dp[N][2],dp[N][3])));

    }
}
