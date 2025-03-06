package heejin.week5;

import java.io.*;
import java.util.*;

public class Week5_11000 {

    static int N;
    static int[][] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time, (t1, t2) -> {
            if (t1[0] == t2[0]) {
                return t1[1] - t2[1]; // 시작 시간 같으면 종료 시간 빠른 게 먼저
            }
            return t1[0] - t2[0]; // 시작 시간 빠른 게 먼저
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 종료 시간 빠른게 앞에 오게
        for (int i = 0; i < N; i++) {
            if (!pq.isEmpty() && pq.peek() <= time[i][0]) { // 종료 시간 가장 빠른 것과 같은 강의실 가능
                pq.poll();
            }
            pq.add(time[i][1]);
        }

        System.out.println(pq.size());
    }
}

