package jinu.week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Week4_7795 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int tc = Integer.parseInt(st.nextToken());

        for(int t=0;t<tc;t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            //A 배열 초기화
            st = new StringTokenizer(br.readLine());
            int A[] = new int[N];
            for(int i=0;i<N;i++){
                A[i] = Integer.parseInt(st.nextToken());
            }

            //B 배열 초기화
            st = new StringTokenizer(br.readLine());
            int B[] = new int[M];
            for(int i=0;i<M;i++){
                B[i] = Integer.parseInt(st.nextToken());
            }

            //모두 정렬
            Arrays.sort(A);
            Arrays.sort(B);

            int aIndex = N-1;
            int bIndex = M-1;
            int count = 0;

            while(aIndex>=0 && bIndex>=0){
                //현재 A 배열 원소 값이 B 배열 원소값보다 클때 : bIndex+1 을 더해주고 a배열 인덱스 감소
                if(A[aIndex]>B[bIndex]){
                    count+=bIndex+1;
                    aIndex--;
                }
                //B 배열 원소값이 더 크면 bIndex 감소
                else if(A[aIndex]<=B[bIndex]){
                    bIndex--;
                }
            }

            bw.write(count+"\n");
        }

        bw.flush();




    }
}
