package src.jaehyeon.week16;

import java.io.*;
import java.util.*;

public class Week16_13422 {

    public static class Var{

        private int n,m,k;
        private int[] houseValue;

        public Var(int n, int m, int k, int[] houseValue){

            this.n = n;
            this.m = m;
            this.k = k;
            this.houseValue = houseValue;
        }
    }

    public static Var[] test;

    public static void main(String[] args) throws IOException{

        // 데이터 입력
        init();

        // 돈을 훔치는 방법의 가짓수 계산
        String answer = calcCase();

        // 정답 출력
        print(answer);
    }

    private static String calcCase(){

        StringBuilder sb = new StringBuilder();

        for(Var var : test){
            int answer = 0;
            int n = var.n;
            int m = var.m;
            int k = var.k;
            int[] houseValue = var.houseValue;

            long initCase = calcInitCase(m, houseValue);

            if(initCase < k) answer++;
            
            if (n != m){

                for(int i = 0, endIndex = m; i<n-1; i++, endIndex++){

                    initCase -= houseValue[i];
                    if (endIndex >= n) {
                        endIndex %= n;
                    }
                    initCase += houseValue[endIndex];
    
                    if (initCase < k) answer++;
                }
            }

            sb.append(answer).append("\n");
        }

        return sb.toString();
    }

    private static long calcInitCase(int m, int[] houseValue){

        long initCase = 0l;

        for(int i=0; i<m; i++){

            initCase += houseValue[i];
        }

        return initCase;
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testCase = Integer.parseInt(br.readLine());

        test = new Var[testCase];

        StringTokenizer st;

        for(int i=0; i<testCase; i++){

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            int[] houseValue = new int[n];

            st = new StringTokenizer(br.readLine());

            for(int ii = 0; ii<n; ii++){

                houseValue[ii] = Integer.parseInt(st.nextToken());
            }

            test[i] = new Var(n,m,k,houseValue);
        }
    }

    private static void print(String answer) throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(answer);
        bw.close();
    }
}
