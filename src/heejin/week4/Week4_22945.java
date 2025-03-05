package heejin.week4;

import java.io.*;
import java.util.*;

public class Week4_22945 {
    static int N;
    static int[] ability;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ability = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ability[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = N - 1;
        while (start < end) {
            int teamAbility = (end - start - 1) * Math.min(ability[start], ability[end]);
            answer = Math.max(answer, teamAbility);

            // 더 큰 값을 남기기
            if(ability[start] < ability[end]){
                start++;
            }else{
                end--;
            }
        }

        System.out.println(answer);
    }
}
