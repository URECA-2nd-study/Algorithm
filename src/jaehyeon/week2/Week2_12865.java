package src.jaehyeon.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week2_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][K+1];//N,K 좌표로 갈 수록 넣는 물품의 수와 넣을 수 있는 무게가 증가한다.
        int[] wrr = new int[N+1];
        int[] vrr = new int[N+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            wrr[i] = Integer.parseInt(st.nextToken());
            vrr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=N; i++){//현재 넣으려는 물건의 인덱스 1부터 N까지
            for(int j=1; j<=K; j++){//현재 넣을 수 있는 무게 제한, 각 물건은 1개씩만 들어감
                if(j-wrr[i] < 0){//무게가 0 미만은 존재하지 않음
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-wrr[i]]+vrr[i]); //안넣은게 큰지 , 넣은게 큰지
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
