package jinu.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week9_15661 {

    static int ability[][];
    static int N;
    static int DIFF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // ability 초기화
        N = Integer.parseInt(st.nextToken());
        ability = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j =1; j <= N;j++){
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // (1<<N)-2 의 의미 : N이 4로 예를 들면 0000 ~ 1111 을 제외한 값, 반절로 나누어서 불필요한 연산을 줄임.
        for(int i=1;i<=((1<<N)-2)/2;i++){

            boolean isUsed[] = new boolean[N+1];

            // 0110 이라면 0일때 false, 1일 떄 true를 구현하기 위함.
            for(int j=N-1;j>=0;j--){
                isUsed[N-j] = (i & (1<<j)) == 0 ? false:true;
            }

            calculateDIFF(isUsed);
        }

        System.out.println(DIFF);



    }

    // 두 값을 계산 후 차이 갱신
    public static void calculateDIFF(boolean []isUsed){
        int star = 0;
        int link = 0;

        for(int i=1;i<=N;i++){
            for(int j=i+1;j<=N;j++){
                if(isUsed[i] && isUsed[j]){
                    star+=ability[i][j]+ability[j][i];
                }
                else if(!isUsed[i] && !isUsed[j]){
                    link+=ability[i][j]+ability[j][i];
                }
            }
        }

        DIFF = Math.min(DIFF,Math.abs(star-link));
    }

}
