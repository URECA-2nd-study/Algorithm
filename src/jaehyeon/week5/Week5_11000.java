package src.jaehyeon.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week5_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            pq.offer(new int[]{S, T});
        }

        PriorityQueue<Integer> answer = new PriorityQueue<>();

        answer.offer(0);

        for (int i=0; i<N; i++){
            int arr[] = pq.poll();
            if (answer.peek() <= arr[0]){
                answer.poll();
            }
            answer.offer(arr[1]);
        }

        System.out.println(answer.size());
    }
}
