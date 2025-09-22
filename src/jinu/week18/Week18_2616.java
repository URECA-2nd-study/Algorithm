package jinu.week18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week18_2616 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int arr[] = new int[n];
        int sum[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());

            if(i==0){
                sum[i]=arr[i];
            }
            else{
                sum[i] = sum[i-1]+arr[i];
            }
        }

        st = new StringTokenizer(br.readLine());
        int maxCount = Integer.parseInt(st.nextToken());

        int dp[][] = new int[4][n];
        for(int i=0;i<4;i++){
            Arrays.fill(dp[i],0);
        }


        for(int i=1;i<=3;i++){
            for(int j=maxCount*i-1;j<n;j++){
                if(j<maxCount){
                    dp[i][j] = sum[j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j-maxCount]+sum[j]-sum[j-maxCount]);
            }
        }


        System.out.println(dp[3][n-1]);

    }

}
