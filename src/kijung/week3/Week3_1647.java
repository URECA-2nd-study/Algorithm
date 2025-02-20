package BaekJoon_1647;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight));
        }

        Collections.sort(edges); // value 순으로 정렬

        System.out.println(mst(N));

    }

    static int mst(int N) {
        int sum = 0;
        int max = 0;
        int selectedEdge = 0;

        for (Edge edge : edges) {

            if (find(edge.from) != find(edge.to)) {
                max = Math.max(max, edge.weight);
                sum += edge.weight;
                selectedEdge++;
                union(edge.from, edge.to);
            }

            if (selectedEdge == N - 1) return sum - max; // 최소 신장트리에서 가장 큰 값을 제거

        }

        return -1;

    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        parent[aRoot] = bRoot;
    }

    static void init() {
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
}
