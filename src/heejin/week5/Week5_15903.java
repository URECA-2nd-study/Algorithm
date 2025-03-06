package heejin.week5;

import java.io.*;
import java.util.*;

public class Week5_15903 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        // 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer(Long.parseLong(st.nextToken()));
        }

        // 가장 작은 값 두 개를 매번 더하기
        for (int i = 0; i < M; i++) {
            long sum = pq.poll() + pq.poll();
            pq.offer(sum);
            pq.offer(sum);
        }

        // 점수 계산
        long score = 0;
        while (!pq.isEmpty()) {
            score += pq.poll();
        }
        System.out.print(score);
    }
}
