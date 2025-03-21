package src.jaehyeon.week7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week7_17940 {

    static public class Node {
        int end;
        long weight;
        int transfer;

        public Node(int end, long weight, int transfer) {
            this.end = end;
            this.weight = weight;
            this.transfer = transfer;
        }
    }

    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int N, M;
    static int[] trainCompany;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trainCompany = new int[N];

        for (int i = 0; i < N; i++) {
            trainCompany[i] = Integer.parseInt(br.readLine());
            adj.add(new ArrayList<>());
        }

        for (int start = 0; start < N; start++) {
            st = new StringTokenizer(br.readLine());
            for (int end = 0; end < N; end++) {
                int weight = Integer.parseInt(st.nextToken());
                if (weight != 0) {//0이면 연결되어 있지 않음
                    adj.get(start).add(new Node(end, weight, 0));
                }
            }
        }

        dijkstra();
        br.close();
    }

    private static void dijkstra() throws IOException {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.transfer == o2.transfer) {
                return (int) (o1.weight - o2.weight);
            } else return o1.transfer - o2.transfer;
        });
        pq.offer(new Node(0, 0, 0));

        boolean[] visited = new boolean[N];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.end == M) {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                bw.write(cur.transfer + " " + cur.weight);
                bw.flush();
                bw.close();
                break;
            }

            if (!visited[cur.end]) {
                visited[cur.end] = true;
                for (Node next : adj.get(cur.end)) {
                    if (!visited[next.end]) {
                        pq.offer(new Node(next.end,
                                cur.weight + next.weight,
                                trainCompany[cur.end] == trainCompany[next.end] ? cur.transfer : cur.transfer + 1));
                    }
                }
            }
        }
    }
}
