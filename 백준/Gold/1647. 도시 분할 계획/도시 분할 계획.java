import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Edge[] edges;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        parents = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, cost);
        }

        Arrays.sort(edges);
        init();
        System.out.println(kruskal());
    }

    static int kruskal() {
        int selectedEdge = 0;
        int totalCost = 0;
        int max = 0;

        for (int i = 0; i < M; i++) {
            Edge curEdge = edges[i];
            if (find(curEdge.from) != find(curEdge.to)) {
                selectedEdge++;
                totalCost += curEdge.cost;
                union(curEdge.from, curEdge.to);
                max = Math.max(max, curEdge.cost);
            }

            if (selectedEdge == N - 1) return totalCost - max;
        }
        return -1;
    }

    static void init() {
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        parents[bRoot] = aRoot;
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }


    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}