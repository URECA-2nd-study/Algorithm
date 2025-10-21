package src.jaehyeon.week20;

import java.io.*;
import java.util.*;

public class Week20_5569 {

    private static final int MOD = 100_000;
    private static int w,h,answer;
    private static int[][][][] dp;

    public static void main(String[] args) throws IOException{

        init();

        sol();

        print();
    }

    private static void sol(){

        for(int y=2; y<=h; y++){

            for(int x=2; x<=w; x++){

                // [0 : 위쪽 1: 아래쪽] [0 : 안 꺾음 1: 꺾음]
                dp[y][x][0][0] = (dp[y-1][x][0][0] + dp[y-1][x][0][1]) % MOD;
                dp[y][x][0][1] = dp[y-1][x][1][0] % MOD;
                dp[y][x][1][0] = (dp[y][x-1][1][0] + dp[y][x-1][1][1]) % MOD;
                dp[y][x][1][1] = dp[y][x-1][0][0] % MOD;
            }
        }

        answer = (dp[h][w][0][0] + dp[h][w][0][1] + dp[h][w][1][0] + dp[h][w][1][1]) % MOD;
    }

    private static void init()throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        // 1,1에서 w,h 까지 가는 경우의 수 이므로 h,w가 바뀌어도 값은 똑같다
        dp = new int[h+1][w+1][2][2];

        // 1,w 까지 가는 경우의 수 -> y값이 1이기 때문에 경우의 수는 한가지 뿐이다.
        for(int i=1; i<=h; i++){

            dp[i][1][0][0] = 1;
        }

        for(int i=1; i<=w; i++){

            dp[1][i][1][0] = 1;
        }

    }

    private static void print()throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(answer));
        bw.close();
    }
}
