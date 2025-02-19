package yeoeun.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week3_1647 {
    static int N, M;
    static int[] root;
    static HashMap<Integer, Integer> map = new HashMap<>(); // 그래프(루트) 별 총 노드 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        root = new int[N+1];
        for (int i = 1; i <= N; i++) {
            root[i] = i;
            map.put(i, 1); // 모두 연결X 상태
        }

        // 모든 edge에 대해 cost가 낮은 순으로 정렬
        PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparing(r -> r.cost));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Road(src, dst, cost));
        }

        int minTotalCost = 0;
        while(!pq.isEmpty()) {
            Road road = pq.poll();

            int a = find(road.src);
            int b = find(road.dst);

            // 서로 다른 그래프에 연결된 경우
            if(a != b) {
                // 마을이 2개이고, 두 그래프의 노드 수의 합이 N과 같다면 분리 완료
                if(map.size() == 2 && map.get(a) + map.get(b) == N) {
                    break;
                }

                // 이외에는 병합 수행
                union(a, b);
                minTotalCost += road.cost;
            }
        }
        System.out.println(minTotalCost);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            root[b] = a;

            // 두 그래프를 합친 크기로 update, 나머지 하나는 삭제
            map.replace(a, map.get(a) + map.get(b));
            map.remove(b);
        }
    }

    // root 정보를 찾아주는 메소드
    private static int find(int node) {
        if(root[node] == node) return node;
        root[node] = find(root[node]);
        return root[node];
    }

    public static class Road {
        int src;
        int dst;
        int cost;

        public Road(int src, int dst, int cost) {
            this.src =src;
            this.dst = dst;
            this.cost = cost;
        }
    }
}
