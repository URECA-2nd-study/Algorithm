package yeoeun.week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week7_31938 {
    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static long[] distance, answer;
    static List<List<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 정보 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e, c));
            graph.get(e).add(new Node(s, c));
        }

        distance = new long[N+1]; // 실제 거리 정보 관리
        answer = new long[N+1]; // 비용 정보 관리 (최종 답)
        Arrays.fill(distance, INF);

        dijkstra(1);

        long total = 0L;
        for (int i = 1; i <= N; i++) {
            total += answer[i];
        }
        System.out.println(total);
    }

    public static void dijkstra (int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(n -> n.cost));
        boolean[] visited = new boolean[N+1];

        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            // 선택 노드 방문 처리
            if(visited[now.idx]) continue;
            else visited[now.idx] = true;

            for (Node next : graph.get(now.idx)) {
                long realCost = distance[now.idx] + next.cost;

                // 미방문 && 새로운 distance가 최소인 경우(같아도 처리)
                if(visited[next.idx] || distance[next.idx] < realCost) continue;

                // 실제 거리(distance)는 같아도 감소한 거리값은 다를 수도 있음
                // -> 같은 거리인 경우에도 업데이트 (savedCost는 항상 realCost보다 같거나 작으므로)
                answer[next.idx] = (long) (distance[now.idx] * 0.9) + next.cost;

                // 기존 경로(100%) 다익스트라 정보 갱신
                distance[next.idx] = realCost;
                pq.offer(new Node(next.idx, realCost));
            }
        }
    }

    public static class Node {
        int idx;
        long cost;

        public Node (int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
