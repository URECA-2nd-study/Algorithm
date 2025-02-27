package src.jaehyeon.week4;

import java.io.*;
import java.util.*;

public class Week4_7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int aSize = Integer.parseInt(st.nextToken());
            int bSize = Integer.parseInt(st.nextToken());

            int[] arr = new int[aSize];
            int[] brr = new int[bSize];

            st = new StringTokenizer(br.readLine());
            for (int a = 0; a < aSize; a++) {
                arr[a] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int b = 0; b < bSize; b++) {
                brr[b] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(brr);

            sb.append(binarySearch(arr, brr)).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(sb+"");
        bw.flush();
        bw.close();
        br.close();
    }//main

    private static int binarySearch(int[] arr, int[] brr){
        int startIndexOfA = 0;
        int endIndexOfA = arr.length;

        int answer = 0;

        for(int i=startIndexOfA; i<endIndexOfA; i++){

            int startIndexOfB = 0;
            int endIndexOfB = brr.length-1;
            int count = 0;

            while(startIndexOfB <= endIndexOfB){
                int midIndexOfB = (startIndexOfB + endIndexOfB)/2;

                if (arr[i] > brr[midIndexOfB]){
                    startIndexOfB = midIndexOfB+1;
                    count = startIndexOfB;
                } else {
                    endIndexOfB = midIndexOfB-1;
                }
            }//while

            answer += count;
        }//for

        return answer;
    }
}
