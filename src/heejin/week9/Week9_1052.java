package heejin.week9;

import java.io.*;
import java.util.*;

public class Week9_1052 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int ans = 0;

        // 2진수 N의 1의 개수 = 최소로 만들 수 있는 물병 수
        while(Integer.bitCount(N) > K){
            // 물병 하나 더 구매
            N++; // 1L 추가
            ans++; // 구매한 물병 개수 추가
        }

        System.out.println(ans);
    }
}
