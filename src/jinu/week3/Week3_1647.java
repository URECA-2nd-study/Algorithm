package jinu.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week3_1647 {

    static int parent[];

    static class House implements Comparable<House> {

        int v1;
        int v2;
        int weight;

        public House(int v1,int v2,int weight){
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(House h){
            return this.weight-h.weight;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=0;i<=N;i++){
            parent[i]=i;
        }


        // kruskal 알고리즘 활용 -> 전체 집합을 최소 비용 간선 트리로 묶은 이후
        // 그 중에서 가장 가중치가 큰 값을 뺀 것이 정답이라 생각.
        PriorityQueue<House> pq = new PriorityQueue<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new House(v1,v2,weight));
        }

        int sum = 0;
        int max = Integer.MIN_VALUE;

        while(!pq.isEmpty()){
            House h = pq.poll();

            if(findParent(h.v1)==findParent(h.v2)){
                continue;
            }

            if(max<h.weight){
                max = h.weight;
            }
            sum+=h.weight;
            union(h.v1,h.v2);
        }

        System.out.println(sum-max);


    }

    public static void union(int v1,int v2){

        int parentV1 = findParent(v1);
        int parentV2 = findParent(v2);

        if(parentV1 == parentV2){
            return;
        }

        if(parentV1>parentV2){
            parent[parentV1] = parentV2;
        }
        else{
            parent[parentV2] = parentV1;
        }

    }

    public static int findParent(int number){

        if(number == parent[number]){
            return number;
        }

        return parent[number] = findParent(parent[number]);
    }
}
