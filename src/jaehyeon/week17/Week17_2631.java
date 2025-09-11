package src.jaehyeon.week17;

import java.io.*;
import java.util.*;

public class Week17_2631 {

    public static int N, answer;
    public static int[] childArray;
    public static int[] dp;

    public static void main(String[] args) throws IOException{

        init();

        solution();

        printAnswer();
    }

    private static void solution(){

        for(int i=1; i<N; i++){

            for(int j=0; j<i; j++){

                if (childArray[i] > childArray[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        Arrays.sort(dp);

        answer = N - dp[N-1];
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());// 아이들 수

        childArray = new int[N];
        dp = new int[N];

        Arrays.fill(dp, 1);

        for(int i=0; i<N; i++){

            childArray[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void printAnswer() throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(answer + "");
        bw.close();
    }
}
