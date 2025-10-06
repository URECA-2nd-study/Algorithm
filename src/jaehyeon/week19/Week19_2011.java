package src.jaehyeon.week19;

import java.io.*;

public class Week19_2011 {

    public static final int MOD = 1000000;

    public static int answer, incode;
    public static String[] ary;
    public static int[] dp;

    public static void main(String[] args) throws IOException{

        init();

        sol();

        print();
    }

    private static void sol(){

        int len = ary.length;

        int num = Integer.parseInt(ary[0]);

        if(num == 0){

            return;
        }

        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<=len; i++){

            int currentNum = Integer.parseInt(ary[i-1]);
            int previousNum = Integer.parseInt(ary[i-2]);

            if(currentNum == 0){
                
                if(previousNum <= 0 || previousNum >= 3){

                    return;
                }

                dp[i] = dp[i-2];

                continue;
            }

            if(currentNum >= 7){

                if(previousNum >= 2 || previousNum == 0){

                    dp[i] = dp[i-1];

                    continue;
                }

                if(previousNum == 1){

                    dp[i] = (dp[i-2] + dp[i-1]) % MOD;

                    continue;
                }
            }

            if(previousNum == 1 || previousNum == 2){

                dp[i] = (dp[i-2] + dp[i-1]) % MOD;

                continue;
            }

            if(previousNum == 0 || previousNum >= 3){

                dp[i] = dp[i-1];

                continue;
            }
        }

        answer = dp[len];
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        ary = input.split("");
        dp = new int[ary.length+1];
    }

    private static void print() throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(answer));

        bw.close();
    }
}
