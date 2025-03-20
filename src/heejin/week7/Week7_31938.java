package heejin.week7;

import java.io.*;
import java.util.*;

public class Week7_31938 {
    final static long INF = Long.MAX_VALUE;

    static int N, M;
    static List<Edge>[] adj;
    static long[] dist;
    static int[] parent; // 부모 배열
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 초기화
        dist = new long[N + 1];
        parent = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = INF;
        }

        // 도로 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            adj[first].add(new Edge(sec, cost));
            adj[sec].add(new Edge(first, cost));
        }

        dijkstra(1);

        // 결과 계산
        for (int i = 2; i <= N; i++) {
            ans += dist[i] - (dist[parent[i]] / 10); // 부모 노드까지는 9/10 비용으로 옴
        }

        System.out.println(ans);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (!visited[cur.idx]) {
                visited[cur.idx] = true;

                for (Edge next : adj[cur.idx]) {
                    long newDist = cur.distance + next.cost;

                    // 최단 거리 업데이트
                    if (dist[next.to] > newDist) {
                        dist[next.to] = newDist;
                        parent[next.to] = cur.idx; // 최단 거리일 때 이전에 방문하는 부모 노드 초기화

                        pq.add(new Node(next.to, newDist));
                    } else if (dist[next.to] == newDist // 거리가 같을 경우
                            && dist[parent[next.to]] < cur.distance) { // 기존 부모 vs 새로운 부모 중 더 거리가 먼 부모 선택
                        // 트럭 개수가 많은 경로의 cost가 높아야, 다음 트럭 개수가 줄 때 cost가 더 낮음
                        parent[next.to] = cur.idx;
                    }
                }
            }
        }
    }

    static class Edge {
        int to;
        long cost;

        Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    // pq에 들어갈 노드
    static class Node implements Comparable<Node> {
        int idx; // 노드 번호
        long distance;

        Node(int idx, long distance) {
            this.distance = distance;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.distance, o.distance);
        }
    }
}
