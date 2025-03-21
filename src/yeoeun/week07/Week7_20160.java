package yeoeun.week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week7_20160 {
    static int V, E;
    static final int INF = 1000000001; // (V)10000 * (w)100000 기준
    static List<List<Node>> graph = new ArrayList<>();
    static int[][] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        distance = new int[V+1][V+1];
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 양방향
            graph.get(s).add(new Node(e, w));
            graph.get(e).add(new Node(s, w));
        }

        // 시작점 -> distance[] 정보 관리
        HashMap<Integer, int[]> map = new HashMap<>();

        // 아주머니 경로 저장
        int[] route = new int[10];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            int rNum = Integer.parseInt(st.nextToken());
            route[i] = rNum;
        }

        // 나 -> 다른 노드 최소 경로 계산
        int mStart = Integer.parseInt(br.readLine());
        map.put(mStart, dijkstra(mStart));

        // 최종 계산
        long yoTime = 0;
        int min = INF;
        if(route[0] != mStart) {
            // 이전 -> 현재 이동 시간 계산
            for (int i = 1; i < 10; i++) {
                int src = route[i-1]; // 시작점

                // 다익스트라 정보 넣기
                if(!map.containsKey(src)) map.put(src, dijkstra(src));

                // 가능한 다음 도착지 탐색
                int distance = map.get(src)[route[i]];
                while(distance == INF && ++i < 10) {
                    distance = map.get(src)[route[i]];
                }

                // dst가 10 이상이면 src가 마지막 지점
                if(i == 10) break;

                yoTime += distance;
                int myTime = map.get(mStart)[route[i]];

                // 가능하면 최소 노드 번호 구하기
                if(yoTime != INF && myTime != INF && yoTime >= myTime) {
                    min = Math.min(min, route[i]);
                }
            }
        } else { // 동일 시작이면 무조건 바로 가능
            min = route[0];
        }
        System.out.println(min == INF ? -1 : min);
    }

    public static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(n -> n.cost));
        boolean[] visited = new boolean[V+1];
        int[] distance = new int[V+1];

        Arrays.fill(distance, INF);
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(visited[now.idx]) continue;
            else visited[now.idx] = true;

            for (Node next : graph.get(now.idx)) {
                if(!visited[next.idx] && distance[next.idx] > now.cost + next.cost) {
                    distance[next.idx] = now.cost + next.cost;
                    pq.add(new Node(next.idx, distance[next.idx]));
                }
            }
        }
        return distance;
    }

    public static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
