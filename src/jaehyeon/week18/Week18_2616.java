package src.jaehyeon.week18;

import java.io.*;
import java.util.*;

public class Week18_2616 {

    private static final int MINIMUM_TRAIN = 3;

    private static int passengerCarCount;
    private static int maxDrag;
    private static int[][] dp;
    private static int[] prefixSum;

    public static void main(String[] args) throws IOException{

        init();

        int answer = solution();

        print(answer);
    }

    private static int solution(){

        for(int i=1; i<=MINIMUM_TRAIN; i++){

            for(int j= i*maxDrag; j<=passengerCarCount; j++){

                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-maxDrag] + prefixSum[j] - prefixSum[j-maxDrag]);
            }
        }

        return dp[MINIMUM_TRAIN][passengerCarCount];
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        passengerCarCount = Integer.parseInt(br.readLine());

        prefixSum = new int[passengerCarCount+1];
        dp = new int[MINIMUM_TRAIN+1][passengerCarCount+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1; i<=passengerCarCount; i++){

            prefixSum[i] = prefixSum[i-1] + Integer.parseInt(st.nextToken());
        }

        maxDrag = Integer.parseInt(br.readLine());
    }

    private static void print(int answer) throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(answer+"");
        bw.close();
    }
}
