package jinu.week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week18_2293 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int dp[]= new int[k+1];
        dp[0] = 1;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());

            for(int j=1;j<=k;j++){
                if(j>=price){
                    dp[j] += dp[j-price];
                }
            }

        }

        System.out.println(dp[k]);
    }

}
