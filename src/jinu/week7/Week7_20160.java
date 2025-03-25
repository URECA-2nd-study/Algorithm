package jinu.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week7_20160 {

    static class Node implements Comparable<Node>{
        private int index;
        private long cost;

        public Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }

        public int compareTo(Node n){
            if(this.cost <= n.cost){
                return -1;
            }
            return 1;
        }

    }

    static class YogurtPosition{
        int index;
        long time;

        public YogurtPosition(int index, long time) {
            this.index = index;
            this.time = time;
        }
    }

    static List<Node> graph[];

    static int V;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());


        // 간선 저장.
        graph = new List[V+1];
        for(int i=1;i<=V;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v,w));
            graph[v].add(new Node(u,w));
        }



        // 요쿠르트 아줌마의 판매 경로 배열 저장
        int []sellDestination = new int[10];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<10;i++){
            sellDestination[i] = Integer.parseInt(st.nextToken());
        }


        // 각 판매 위치마다의 시간을 저장.
        List<YogurtPosition> yogurtPositions = new ArrayList<>();
        int startPoint = sellDestination[0];
        long time = 0;
        yogurtPositions.add(new YogurtPosition(startPoint,0));
        for(int i=1;i<10;i++){

            long distance = dijkstra(startPoint,sellDestination[i]);//다익스트라

            if(distance == Long.MAX_VALUE){
                continue;
            }

            yogurtPositions.add(new YogurtPosition(sellDestination[i],time+distance));
            time += distance;
            startPoint = sellDestination[i];

        }




        // 나의 위치에서 비교
        int answer = -1;

        st = new StringTokenizer(br.readLine());
        int myStart = Integer.parseInt(st.nextToken());

        for(YogurtPosition yogurtPosition : yogurtPositions){

            long myTime = dijkstra(myStart,yogurtPosition.index);
            if(myTime<=yogurtPosition.time){
                if(answer == -1){
                    answer = yogurtPosition.index;
                }
                else{
                    answer = Math.min(answer,yogurtPosition.index);
                }
            }
        }

        System.out.println(answer);



    }

    public static long dijkstra(int start,int dest){

        long dist[] = new long[V+1];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[start] = 0;
        boolean isVisited[] = new boolean[V+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(isVisited[now.index]){
                continue;
            }

            isVisited[now.index] = true;

            for(Node next : graph[now.index]){
                if(dist[next.index] > dist[now.index] + next.cost){
                    dist[next.index] = dist[now.index] + next.cost;
                    pq.add(new Node(next.index,dist[next.index]));
                }
            }
        }

        return dist[dest];

    }
}