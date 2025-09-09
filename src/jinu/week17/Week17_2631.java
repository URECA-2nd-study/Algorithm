package jinu.week17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week17_2631 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int arr[] = new int[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(N-LIS(arr));

    }

    public static int LIS(int []arr){
        int dp[] = new int[arr.length];
        dp[0] = 1;

        for(int i=1;i<arr.length;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(arr[j]<arr[i]){
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                }
            }
        }
        int max = -1;
        for(int i=0;i<arr.length;i++){
            max = Math.max(dp[i],max);
        }

        return max;
    }

}
