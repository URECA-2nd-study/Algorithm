package src.jaehyeon.week18;

import java.io.*;
import java.util.*;

public class Week18_2294 {

    private static int n,k;
    private static int[] dp, arr;
    private static String answer;

    public static void main(String[] args) throws IOException{

        init();

        solution();

        print();
    }

    private static void solution(){

        for(int i=1; i<=n; i++){

            int coin = arr[i];
            
            if(coin > k) continue;

            for(int j=1; j<=k; j++){

                if(coin <= j){

                    dp[j] = Math.min(dp[j], dp[j-coin]+1);
                }
            }
        }

        answer = String.valueOf(dp[k] == Integer.MAX_VALUE-1 ? -1 : dp[k]);
    }

    private static void print() throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(answer);
        bw.close();
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        dp = new int[k+1];

        Arrays.fill(dp, Integer.MAX_VALUE-1);

        for(int i=1; i<=n; i++){

            int coin = Integer.parseInt(br.readLine());

            arr[i] = coin;

            if(coin > k) continue;

            dp[coin] = 1;// 5원 동전이 있으면 5원의 최소 갯수는 1이다.
        }

        br.close();
    }
}
