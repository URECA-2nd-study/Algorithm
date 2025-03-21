import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week7_20160 {

    static int V, E;
    static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        int[] yakultPoints = new int[10];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            yakultPoints[i] = Integer.parseInt(st.nextToken());
        }

        int myStart = Integer.parseInt(br.readLine());

        // 야쿠르트 아줌마 도착 시간 계산
        long[] yakultTime = new long[10]; // 누적 시간 오버플로우 대비 long 사용
        Arrays.fill(yakultTime, Long.MAX_VALUE);
        yakultTime[0] = 0;
        int prev = yakultPoints[0];

        for (int i = 1; i < 10; i++) {
            int[] distances = dijkstra(prev);
            if (distances[yakultPoints[i]] != Integer.MAX_VALUE) {
                yakultTime[i] = yakultTime[i - 1] + distances[yakultPoints[i]];
                prev = yakultPoints[i];
            } else {
                for (int j = i; j < 10; j++) {
                    if (distances[yakultPoints[j]] != Integer.MAX_VALUE) {
                        yakultTime[j] = yakultTime[i - 1] + distances[yakultPoints[j]];
                        prev = yakultPoints[j];
                        i = j;
                        break;
                    }
                }
            }
        }

        // 내 도착 시간 계산
        int[] myDistances = dijkstra(myStart);

        // 결과 찾기
        int result = -1;
        for (int i = 0; i < 10; i++) {
            if (yakultTime[i] != Long.MAX_VALUE && myDistances[yakultPoints[i]] != Integer.MAX_VALUE) {
                if (myDistances[yakultPoints[i]] <= yakultTime[i]) {
                    if (result == -1 || yakultPoints[i] < result) {
                        result = yakultPoints[i]; // 가장 작은 지점 번호 갱신
                    }
                }
            }
        }

        System.out.println(result);
    }

    static int[] dijkstra(int start) {
        int[] distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.cost > distance[cur.to]) continue;

            for (Edge next : edges[cur.to]) {
                if (distance[next.to] > cur.cost + next.cost) {
                    distance[next.to] = cur.cost + next.cost;
                    pq.add(new Edge(next.to, distance[next.to]));
                }
            }
        }

        return distance;
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
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}