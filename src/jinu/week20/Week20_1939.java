package jinu.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week20_1939 {

    static class Node implements Comparable<Node>{

        int weight;
        int number;

        public Node(int number, int weight){
            this.weight = weight;
            this.number = number;
        }

        @Override
        public int compareTo(Node n){
            return n.weight - this.weight;
        }
    }

    static List<Node> graph[];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for(int i = 1; i<=N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i =1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to,weight));
            graph[to].add(new Node(from,weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, end, N));


    }

    static int dijkstra(int start, int end,int N){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        boolean isVisited[] = new boolean[N+1];
        int maxWeight[] = new int[N+1];
        Arrays.fill(maxWeight, Integer.MIN_VALUE);
        maxWeight[start] = Integer.MAX_VALUE;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(isVisited[cur.number]) continue;

            isVisited[cur.number] = true;

            for(Node next : graph[cur.number]){
                if(maxWeight[next.number] < Math.min(maxWeight[cur.number], next.weight)){
                    maxWeight[next.number] = Math.min(maxWeight[cur.number], next.weight);
                    pq.add(new Node(next.number, next.weight));
                }
            }
        }

        return maxWeight[end];
    }

}
