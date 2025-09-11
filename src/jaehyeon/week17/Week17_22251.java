package src.jaehyeon.week17;

import java.io.*;
import java.util.*;

public class Week17_22251 {
    
    public static final int[][] DIODE_STATE = {
        {1,1,1,1,1,1,0},
        {0,0,0,0,1,1,0},
        {1,0,1,1,0,1,1},
        {1,0,0,1,1,1,1},
        {0,1,0,0,1,1,1},
        {1,1,0,1,1,0,1},
        {1,1,1,1,1,0,1},
        {1,0,0,0,1,1,0},
        {1,1,1,1,1,1,1},
        {1,1,0,1,1,1,1},
    };

    public static final int DIODE = 7;

    public static int N,K,P;
    public static String X;
    public static int answer;


    public static void main(String[] args) throws IOException{

        init();

        solution();

        printAnswer();
    }

    private static void solution(){

        String[] currentLED = X.split("");

        for(int i=1; i<=N; i++){

            if(isSameFloor(i)) continue;

            String num = String.valueOf(i);

            num = fillZero(num);

            String[] targetLED = num.split("");

            if(isChangable(targetLED, currentLED)) answer++;
        }
        
    }

    private static boolean isChangable(String[] targetLED, String[] currentLED){

        int changeCount = 0;

        for(int i=0; i<K; i++){

            String targetString = targetLED[i];
            String currentString = currentLED[i];

            if (isDifferentNumber(targetString, currentString)) {
                
                for(int j=0; j<DIODE; j++){

                    int targetNum = Integer.parseInt(targetString);
                    int currentNum = Integer.parseInt(currentString);

                    if (isDifferentDiodeState(DIODE_STATE[targetNum][j], DIODE_STATE[currentNum][j])) {

                        changeCount++;
                    }
                }

                if(changeCount > P) return false;
            }
        }

        return true;
    }

    private static boolean isDifferentDiodeState(int target, int current){

        return target != current;
    }

    private static boolean isDifferentNumber(String target, String current){

        return target != current;
    }

    private static boolean isSameFloor(int i){

        return i == Integer.parseInt(X);
    }

    private static String fillZero(String num){

        while(num.length() < K){

            num = "0" + num;
        }

        return num;
    }

    private static void printAnswer() throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        bw.write(answer + "");
        bw.close();
    }


    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());// 빌딩 층 수
        K = Integer.parseInt(st.nextToken());// 디스플레이 자리 수
        P = Integer.parseInt(st.nextToken());// 최대 반전시킬 LED 수
        X = st.nextToken();// 현재 층

        X = fillZero(X);// K보다 자릿수가 적은 만큼 앞에 0으로 채우기
    } 
}
