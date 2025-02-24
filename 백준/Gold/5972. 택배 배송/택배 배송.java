import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Edge>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));
        }

        System.out.println(dijkstra(1));

    }

    static int dijkstra(int start) {
        int[] distance = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));


        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge next : edges[cur.to]) {
                if (distance[next.to] > cur.weight + next.weight) {
                    distance[next.to] = cur.weight + next.weight;
                    pq.add(new Edge(next.to, distance[next.to]));
                }
            }
        }

        return distance[N];
    }
}

class Edge implements Comparable<Edge> {
    int to;
    int weight;

    public Edge(int from, int weight) {
        this.to = from;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
}
