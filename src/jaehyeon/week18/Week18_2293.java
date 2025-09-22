package src.jaehyeon.week18;

import java.io.*;
import java.util.*;

public class Week18_2293 {
    
    public static int n,k;

    public static int[] dp;
    public static int[] arr;

    public static void main(String[] args) throws IOException{

        init();

        solution();

        print();
    }

    private static void solution(){
       
        for(int i=1; i<=n; i++){

            int coin = arr[i];

            for(int j=1; j<=k; j++){
                
                if(coin <= j){

                    dp[j] += dp[j - coin];
                }
            }
        }
    }

    private static void print() throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(dp[k]+"");
        bw.close();
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        dp = new int[k+1];

        for(int i=1; i<=n; i++){

            int coin = Integer.parseInt(br.readLine());

            arr[i] = coin;
        }

        dp[0] = 1;
    }
}
