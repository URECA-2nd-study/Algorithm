import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week5_15903 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        while (M-- > 0) {
            long a = pq.poll();
            long b = pq.poll();
            pq.add(a + b);
            pq.add(a + b);
        }

        long answer = 0;
        for (long a : pq) answer += a;

        System.out.println(answer);

    }
}
