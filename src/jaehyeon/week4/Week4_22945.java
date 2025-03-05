package src.jaehyeon.week4;

import java.util.*;
import java.io.*;

public class Week4_22945 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int len = arr.length;
        int startPointer = 0;
        int endPointer = len - 1;

        int maxTeamStatus = 0;


        while (startPointer+2 <= endPointer) {
            maxTeamStatus = Math.max(maxTeamStatus, Math.min(arr[startPointer], arr[endPointer]) * (endPointer - startPointer - 1));

            if (arr[startPointer] < arr[endPointer]) {
                startPointer++;
            } else {
                endPointer--;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(maxTeamStatus +"");
        bw.flush();
        bw.close();
        br.close();
    }
}
