package jinu.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week21_2515 {

    static class Painting implements Comparable<Painting>{

        int height;
        int cost;

        public Painting(int height, int cost){
            this.height = height;
            this.cost = cost;
        }

        @Override
        public int compareTo(Painting p){
            return this.height - p.height;
        }
    }

    static Painting[] paintings;
    static int []limit, dp;
    static int N,S;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        paintings = new Painting[N];
        dp = new int[N];
        limit = new int[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            paintings[i] = new Painting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(paintings);

        dp[0] = paintings[0].cost;

        for(int i=1;i<N;i++){
            int findMaxHighIndex = binarySearch(i);
            if(findMaxHighIndex > -1){
                dp[i] = Math.max(dp[findMaxHighIndex]+paintings[i].cost, dp[i-1]);
            }
            else{
                dp[i] = Math.max(dp[i-1], paintings[i].cost);
            }
        }

        System.out.println(dp[N-1]);





    }

    static int binarySearch(int index){
        int mid = 0;
        int left = 0;
        int right = index;

        while(left<=right){
            mid = (left+right)/2;

            int diff = paintings[index].height - paintings[mid].height;

            if(diff>=S){
                left = mid+1;
            }
            else{
                right = mid - 1;
            }
        }

        return left - 1;
    }

}
