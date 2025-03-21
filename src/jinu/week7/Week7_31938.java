package jinu.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Week7_31938 {


    // start 부터 n 까지의 최단 거리
    static long dist[];

    static class Node implements Comparable<Node>{
        private int from;
        private int to;
        private long cost;

        public Node(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {

            // 최종 비용이 같은 경우 시간비용이 더 작은 것을 고름.
            if(this.cost == o.cost){
                Edge e1 = new Edge(Math.min(this.from,this.to),Math.max(this.from,this.to));
                Edge e2 = new Edge(Math.min(o.from,o.to),Math.max(o.from,o.to));
                long e1Cost = pathCost.get(e1);
                long e2Cost = pathCost.get(e2);

                if(((dist[o.from]*9)/10 + e2Cost) > ((dist[this.from]*9)/10 + e1Cost) ){
                    return -1;
                }
                return 1;
            }
            if(this.cost > o.cost){
                return 1;
            }
            return -1;
        }
    }


    // 예제 입력 간선 저장용 그래프
    static List<Node> graph[];

    // timeGraph: timeGraph[i]: i 를 이전 노드로 가지는 인덱스 (ex: 1-> 2 로 간다면 timeGraph[1] 의 List 안에는 2가 존재 )
    static List<Integer> timeGraph[];

    //path[i] 는 i의 이전 노드의 인덱스 (1-> ... i -> path[i])
    static int path[];

    static int N;

    // 비용 기록용 리스트 : costRecord[i] 는 1부터 i까지 비용 ( 단 각각의 간선마다 9/10 이 이미 적용된 상태)
    static long costRecord[];

    static class Edge{
        private int from;
        private int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            Edge e = (Edge) obj;
            if(this.from==e.from && this.to == e.to){
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from,to);
        }
    }

    // 특정 간선의 저장을 위한 Map
    static Map<Edge,Long> pathCost = new HashMap<>();

    static long answer = 0;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dist = new long[N+1];
        graph = new List[N+1];
        timeGraph = new List[N+1];
        path = new int[N+1];
        pathCost = new HashMap<>();
        costRecord = new long[N+1];
        Arrays.fill(costRecord,Long.MAX_VALUE);

        for(int i=1;i<=N;i++){
            graph[i] = new ArrayList<>();
            timeGraph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph[first].add(new Node(first,second,cost));
            graph[second].add(new Node(second,first,cost));

            // 추후 동일한 간선을 EqualsAndHashCode 로 찾기 위해 순서를 지정 .
            pathCost.put(new Edge(Math.min(first,second),Math.max(first,second)),cost);
        }

        //1에서부터 N 번의 노드까지 최단 거리를 구함.
        // 단 이 과정에서 최단 거리가 동일할 시
        dijkstra(1);


        costRecord[1] = 0;

        // tiemGraph 초기화 : timeGraph[i] 의 리스트안에는 i 를 이전 노드로 하는 값이 들어있다.
        for(int i=2;i<=N;i++){
            timeGraph[path[i]].add(i);
        }

        BFS(1);

        System.out.println(answer);


    }


    //다익스트라
    public static void dijkstra(int start){
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[start] = 0;
        boolean isVisited[] = new boolean[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,start,0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(isVisited[now.to]) continue;

            isVisited[now.to] = true;

            // path[i]에는 i의 이전 노드값이 들어가 있다. (1->... i-> i의 이전 노드값)
            path[now.to] = now.from;

            for(Node next : graph[now.to]){
                // 거리가 같은 것도 PQ 에 넣음으로써 시간 비용이 작은 것을 먼저 꺼내도록 한다.
                if(dist[next.to] >= dist[now.to] + next.cost){
                    dist[next.to] = dist[now.to] + next.cost;
                    pq.add(new Node(now.to,next.to,dist[next.to]));
                }
            }
        }
    }

    // 1부터 BFS 시작
    public static void BFS(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean isVisited[] = new boolean[N+1];

        while(!queue.isEmpty()){
            int nextIndex = queue.poll();

            // 1을 이전 노드로 하는 인덱스를 꺼낸다.
            for(int next : timeGraph[nextIndex]){

                if(isVisited[next]){
                    continue;
                }
                isVisited[next] = true;


                long tmpSum = 0;
                long tmp = 0;
                int tmpStart = next;
                while(true){
                    if(tmpStart == 1){
                        break;
                    }
                    if(costRecord[tmpStart] != Long.MAX_VALUE){
                        tmpSum+=costRecord[tmpStart];
                        tmp = costRecord[tmpStart];
                        break;
                    }

                    int prev = path[tmpStart];
                    Edge edge = new Edge(Math.min(prev,tmpStart),Math.max(prev,tmpStart));
                    long cost = pathCost.get(edge);
                    tmpSum += cost;
                    tmpStart = prev;
                }

                // tmpSum의 의미: 특정 노드까지 가는 시간의 최솟값.
                // tmp의 의미: 9/10 가 적용된 거리까지 시간의 최솟값.
                // before 의 의미: 사실상 간선 비용 .
                long before = tmpSum - tmp;

                // costRecord 에는 각 시간 비용에 9/10가 모두 적용되어 있다. 따라서 나중에 메모이제이션 기법으로
                // 이 값을 찾는다면 그냥 더해주기만 하면 된다.
                costRecord[next] = tmp + (9*before)/10;
                answer += tmpSum;
                queue.add(next);

            }
        }
    }
}
