package src.jaehyeon.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week7_31938 {

    static public class Node {
        int end;
        long weight;

        public Node(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int N, M;
    static long[] answer, dist;

    public static void main(String[] args) throws IOException {
        init();

        dijkstra();

        long sum = 0L;
        for (long l : answer) {
            sum += l;
        }
        System.out.println(sum);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(o -> o.weight));
        pq.offer(new Node(1, 0));

        boolean[] visited = new boolean[N + 1];

        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (!visited[cur.end]) {
                visited[cur.end] = true;

                for (Node next : adj.get(cur.end)) {

                    if (!visited[next.end] && dist[next.end] >= dist[cur.end] + next.weight) {

                        answer[next.end] = (long) (cur.weight * 0.9) + next.weight;

                        dist[next.end] = dist[cur.end] + next.weight;

                        pq.offer(new Node(next.end, cur.weight + next.weight));

                    }
                }
            }
        }

//        System.out.println(Arrays.toString(answer));
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new long[N + 1];

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }
    }
}


//4 4
//1 2 100
//2 3 1100
//2 4 1100
//3 4 10
//
//2480