package yeoeun.week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week5_15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Long total = 0L;
        List<Long> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            total += num;
            list.add(num);
        }
        // 정렬 시간 단축을 위해 list에 추가한 후 한번에 PQ에 넣어줌
        PriorityQueue<Long> pq = new PriorityQueue<>(list);

        for (int i = 0; i < M; i++) {
            long a = pq.poll();
            long b = pq.poll();

            pq.add(a+b);
            pq.add(a+b);
            // a는 b만큼, b는 a만큼 증가함
            total += a+b;
        }
        System.out.println(total);
    }
}
