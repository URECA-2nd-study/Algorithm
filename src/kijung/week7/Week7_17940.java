import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week7_17940 {

    static int N, M;
    static int[] companies;
    static List<Edge>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        companies = new int[N];
        edges = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            companies[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (cost == 0) continue;
                edges[i].add(new Edge(j, cost, 0));
            }
        }

        int[] answer = dijkstra();

        System.out.println(answer[0] + " " + answer[1]);

    }

    static int[] dijkstra() { // 출발지는 항상 0
        int[] distance = new int[N];
        int[] cnt = new int[N]; ; //환승횟수 저장

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(cnt, Integer.MAX_VALUE);
        cnt[0] = 0;
        distance[0] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            for (Edge next : edges[cur.to]) {
                int c = cur.transfer;
                if (companies[next.to] != companies[cur.to]) c++;

                if (cnt[next.to] > c || cnt[next.to] == c && distance[next.to] > cur.cost + next.cost) {
                    cnt[next.to] = c;
                    distance[next.to] = cur.cost + next.cost;
                    pq.add(new Edge(next.to, distance[next.to], c));
                }
            }

        }

        return new int[]{cnt[M], distance[M]};

    }
}

class Edge implements Comparable<Edge> {
    int to;
    int cost;
    int transfer;

    public Edge(int to, int cost, int transfer) {
        this.to = to;
        this.cost = cost;
        this.transfer = transfer;
    }

    @Override
    public int compareTo(Edge e) {
        if (this.transfer == e.transfer) return this.cost - e.cost;
        return this.transfer - e.transfer;
    }
}