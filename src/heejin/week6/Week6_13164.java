package heejin.week6;

import java.io.*;
import java.util.*;

public class Week6_13164 {

    static int N, K;
    static long ans;
    static int[] diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        diff = new int[N - 1];

        st = new StringTokenizer(br.readLine());

        int pre = Integer.parseInt(st.nextToken());
        int cur;

        // 간격 저장
        for (int i = 0; i < N - 1; i++) {
            cur = Integer.parseInt(st.nextToken());
            diff[i] = cur - pre;
            pre = cur;
        }

        // 간격 오름차순 정렬
        Arrays.sort(diff);

        // K개의 그룹으로 만드려면 N-K번 합쳐야 함
        for (int i = 0; i < N - K; i++) {
            ans += diff[i];
        }

        System.out.print(ans);
    }
}
