package heejin.week7;

import java.io.*;
import java.util.*;

public class Week7_17940 {
    final static int INF = Integer.MAX_VALUE;

    static int N, M;
    static int[] station, dist, transfer;
    static List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        station = new int[N];
        transfer = new int[N];
        dist = new int[N];
        adj = new List[N];

        // 지하철역 운영 회사 정보 입력
        for (int i = 0; i < N; i++) {
            station[i] = Integer.parseInt(br.readLine());

            dist[i] = INF;
            transfer[i] = INF;
            adj[i] = new ArrayList<>();
        }

        // 지하철 소요 시간 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int time = Integer.parseInt(st.nextToken());
                if (time != 0) {
                    adj[i].add(new Node(j, time, INF));
                }
            }
        }

        // 0부터 다른 모든 정점까지 적게 환승 + 최소 시간 경로 구하기
        dijkstra(0);

        // 출력
        System.out.print(transfer[M] + " " + dist[M]);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];

        pq.add(new Node(start, 0, 0));
        transfer[start] = 0;
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (!visited[cur.to]) {
                if(cur.to == M) return;

                visited[cur.to] = true;

                for (Node next : adj[cur.to]) {
                    int isSameCom = station[cur.to] == station[next.to] ? 0 : 1; // 환승 여부
                    int newTransfer = cur.transCnt + isSameCom;
                    int newTime = dist[cur.to] + next.time;

                    // 환승 더 적음 or 환승 수는 동일하지만 이동 시간 짧음 -> 업데이트
                    if (transfer[next.to] > newTransfer
                            || (transfer[next.to] == newTransfer && dist[next.to] > newTime)) {

                        transfer[next.to] = newTransfer;
                        dist[next.to] = newTime;

                        pq.add(new Node(next.to, dist[next.to], transfer[next.to]));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        int time;
        int transCnt;

        Node(int to, int time, int transCnt) {
            this.to = to;
            this.time = time;
            this.transCnt = transCnt;
        }

        @Override
        public int compareTo(Node n) {
            if (this.transCnt == n.transCnt) {
                return this.time - n.time;
            }
            return this.transCnt - n.transCnt;
        }
    }
}