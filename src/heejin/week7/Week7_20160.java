package heejin.week7;

import java.io.*;
import java.util.*;

public class Week7_20160 {
    final static int INF = Integer.MAX_VALUE;
    static int V, E;
    static int[] move;
    static int[] myDist, yakultDist;
    static List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        move = new int[10];
        myDist = new int[V + 1];
        yakultDist = new int[V + 1];
        adj = new List[V + 1];

        // 초기화
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
            myDist[i] = INF;
        }

        // 가중치 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, w));
            adj[v].add(new Node(u, w));
        }

        // 야쿠르트 아줌마가 이동하는 정점 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            move[i] = Integer.parseInt(st.nextToken());
        }

        // 나의 출발 정점 입력
        int start = Integer.parseInt(br.readLine());

        // 나의 출발 정점으로부터 모든 정점까지의 최단 거리 구하기
        dijkstra(start, myDist);

        long yakultTime = 0;
        int ans = Integer.MAX_VALUE;

        // 둘의 출발 시점이 같은 경우 ans 초기화
        if (start == move[0]) {
            ans = start;
        }

        // 나와 야쿠르트 아줌마가 만나는 시점 구하기
        for (int i = 0; i < 9; i++) {
            Arrays.fill(yakultDist, INF);

            // 야쿠르트 아줌마 지금 정점으로부터 다른 정점까지의 최단 거리 구하기
            dijkstra(move[i], yakultDist);

            // 다음 정점 갈 수 있을 때까지 패스
            int nextIdx = i + 1;
            while (nextIdx < 10 && yakultDist[move[nextIdx]] == Integer.MAX_VALUE) {
                nextIdx++;
            }
            if (nextIdx == 10) break;

            int nextVertex = move[nextIdx];

            // 야쿠르트 아줌마 시간 증가
            yakultTime += yakultDist[nextVertex];

            // 내가 야쿠르트 아줌마보다 같거나 일찍 도착하면 살 수 있음
            if (myDist[nextVertex] <= yakultTime) {
                ans = Math.min(ans, nextVertex);
            }

            i = nextIdx - 1;
        }

        System.out.print(ans == INF ? -1 : ans);
    }

    static void dijkstra(int start, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(!visited[cur.to]){
                visited[cur.to] = true;
                for (Node next : adj[cur.to]) {
                    if (dist[next.to] > dist[cur.to] + next.weight) {
                        dist[next.to] = dist[cur.to] + next.weight;
                        pq.add(new Node(next.to, dist[next.to]));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }
}


