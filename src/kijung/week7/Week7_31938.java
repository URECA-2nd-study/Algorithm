package BaekJoon_31938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week7_31938 {
    static int N, M;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        System.out.println(dijkstra());
    }

    static long dijkstra() {
        long[] distance = new long[N + 1];
        long[] cost = new long[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        Arrays.fill(cost, Long.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        distance[1] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge next : edges[cur.to]) {
                long dist = distance[cur.to] + next.cost;
                long newCost = (long) (distance[cur.to] * 0.9) + next.cost;
                if (distance[next.to] > dist) {
                    distance[next.to] = dist;
                    cost[next.to] = newCost;
                    pq.add(new Edge(next.to, distance[next.to]));
                } else if (distance[next.to] == dist && cost[next.to] > newCost) {
                    cost[next.to] = newCost;
                }
            }
        }

        long sum = 0;
        for (int i = 2; i <= N; i++) {
            sum += cost[i];
        }

        return sum;
    }
}

class Edge implements Comparable<Edge> {
    int to;
    long cost;

    public Edge(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return Long.compare(this.cost, e.cost);
    }
}