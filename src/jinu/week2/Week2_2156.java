package jinu.week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week2_2156 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int []juice = new int[n+1];


        for (int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            juice[i] = Integer.parseInt(st.nextToken());
        }

        int dp[] = new int[n+1];
        dp[1] = juice[1];

        if(n>=2){
            dp[2] = juice[1]+juice[2];
        }


        for(int i=3;i<=n;i++){

            dp[i] = Math.max(dp[i-2]+juice[i],Math.max(dp[i-3]+juice[i-1]+juice[i],dp[i-1]));

        }

        System.out.println(dp[n]);





    }
}
