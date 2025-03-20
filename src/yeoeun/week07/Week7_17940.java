package yeoeun.week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week7_17940 {
    static final int INF = 1000001; // (N)1000 * (E)1000
    static int N, M;
    static int[] stations, distance, transfer;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 역 정보 입력받기
        stations = new int[N];
        for (int i = 0; i < N; i++) {
            stations[i] = Integer.parseInt(br.readLine());
        }

        // 연결 정보 입력받기
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        distance = new int[N];
        transfer = new int[N];

        Arrays.fill(distance, INF);
        dijkstra(0);

        System.out.println(transfer[M] + " " + distance[M]);
    }

    public static void dijkstra(int start) {
        // 환승 횟수 기준 -> 거리순 정렬
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing((Node n) -> n.transfer).thenComparing(n -> n.cost));
        boolean[] visited = new boolean[N];

        queue.add(new Node(start, 0, 0));
        distance[start] = 0;

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            // 방문여부 체크
            if(visited[now.idx]) continue;
            else visited[now.idx] = true;

            for (int next = 0; next < N; next++) {
                if(graph[now.idx][next] == 0) continue;

                if(!visited[next] && distance[next] > now.cost + graph[now.idx][next]) {
                    // 환승 정보 : 다르면 증가
                    transfer[next] = (stations[now.idx] != stations[next]) ? transfer[now.idx] + 1 : transfer[now.idx];

                    // 거리 정보 갱신
                    distance[next] = now.cost + graph[now.idx][next];

                    // 큐 삽입
                    queue.add(new Node(next, transfer[next], distance[next]));
                }
            }
        }
    }

    public static class Node {
        int idx;
        int transfer;
        int cost;

        public Node (int idx, int transfer, int cost) {
            this.idx = idx;
            this.transfer = transfer;
            this.cost = cost;
        }
    }
}
