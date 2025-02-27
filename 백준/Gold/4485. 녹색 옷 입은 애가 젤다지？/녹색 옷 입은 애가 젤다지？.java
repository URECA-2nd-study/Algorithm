import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] distance, map;
    static ArrayList<Edge>[] edges;
    static int N;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (size != 0){
            ++count;
            N = size;
            distance = new int[N * N];
            map = new int[N * N];
            edges = new ArrayList[N * N];

            for (int j = 0; j < N * N; j++) {
                edges[j] = new ArrayList<>();
            }
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    map[j * N + k] = Integer.parseInt(st.nextToken());
                }
            }

            // +N는 밑... -N는 위 (벗어나는지 확인)
            // 맨 왼쪽인 경우 즉 위치 % N == 0 인 경우 -1 불가
            // 맨 오른쪽인 경우 즉 위치 % N == N - 1인 경우 +1 불가
            for (int j = 0; j < N * N; j++) {
                if (j + N < N * N) {
                    edges[j].add(new Edge(j + N, map[j + N]));
                }
                if (j - N >= 0) {
                    edges[j].add(new Edge(j - N, map[j - N]));
                }
                if (j % N != 0) {
                    edges[j].add(new Edge(j - 1, map[j - 1]));
                }
                if (j % N != N - 1) {
                    edges[j].add(new Edge(j + 1, map[j + 1]));
                }
            }
            dijkstra(0);
            bw.write("Problem " + count + ": " + distance[N * N - 1] + "\n");
            size = Integer.parseInt(br.readLine());
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N * N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        pq.add(new Edge(start, map[0]));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge next : edges[cur.to]) {
                if (distance[next.to] > cur.cost + next.cost) {
                    distance[next.to] = cur.cost + next.cost;
                    pq.add(new Edge(next.to, distance[next.to]));
                }
            }
        }
    }

}


class Edge implements Comparable<Edge> {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}