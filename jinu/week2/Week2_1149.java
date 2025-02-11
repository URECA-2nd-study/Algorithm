package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week2_1149 {

    static int RED[];
    static int BLUE[];
    static int GREEN[];

    static int DP[];

    static int RED_COLOR = 0;
    static int GREEN_COLOR = 1;
    static int BLUE_COLOR = 2;

    static int color[][];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        color=new int[N+1][3];
        DP = new int[N+1];
        RED = new int [N+1];
        GREEN = new int[N+1];
        BLUE = new int[N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<3;j++){
                color[i][j]=Integer.parseInt(st.nextToken());
            }

        }

        RED[1] = color[1][RED_COLOR];
        GREEN[1] = color[1][GREEN_COLOR];
        BLUE[1] = color[1][BLUE_COLOR];

        DP[1]=findMin(RED[1],GREEN[1],BLUE[1]);


        for(int i=2;i<=N;i++){
            RED[i]= Math.min(GREEN[i-1],BLUE[i-1]) + color[i][RED_COLOR];
            BLUE[i] = Math.min(RED[i-1],GREEN[i-1]) + color[i][BLUE_COLOR];
            GREEN[i] = Math.min(RED[i-1],BLUE[i-1]) + color[i][GREEN_COLOR];

            DP[i] = findMin(RED[i],BLUE[i],GREEN[i]);
        }

        System.out.println(DP[N]);


    }

    public static int findMin(int x,int y,int z){
        return Math.min(x,Math.min(y,z));
    }
}
