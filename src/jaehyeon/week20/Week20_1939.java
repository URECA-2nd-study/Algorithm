package src.jaehyeon.week20;

import java.io.*;
import java.util.*;

public class Week20_1939 {

    public static class Node{

        private int end, weight;

        public Node(int end, int weight){

            this.end = end;
            this.weight = weight;
        }
    }

    public static int N,M,start,goal,answer;
    public static ArrayList<ArrayList<Node>> adj = new ArrayList<>();

    public static void main(String[] args) throws IOException{

        init();

        sol();

        print();
    }

    private static void sol(){

        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(o->-o.weight));

        int[] maxWeight = new int[N+1];

        Arrays.fill(maxWeight, 0);

        maxWeight[start] = Integer.MAX_VALUE;

        q.offer(new Node(start, Integer.MAX_VALUE));

        while(!q.isEmpty()){

            Node currentNode = q.poll();

            if(maxWeight[currentNode.end] > currentNode.weight) continue;

            if(isArrived(currentNode.end)){

                answer = currentNode.weight;
                return;
            }

            for(int i=0; i<adj.get(currentNode.end).size(); i++){
                
                Node nextNode = adj.get(currentNode.end).get(i);
                
                int minWeight = Math.min(currentNode.weight, nextNode.weight);

                if(minWeight > maxWeight[nextNode.end]){

                    maxWeight[nextNode.end] = minWeight;
                    
                    q.offer(new Node(nextNode.end, minWeight));
                }
            }
        }

        answer = maxWeight[goal];
    }

    private static boolean isArrived(int end){

        return end == goal;
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++){

            adj.add(new ArrayList<>());
        }

        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i<M; i++){

            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            
            int min = Math.min(A,B);
            int max = Math.max(A,B);

            String key = min+","+max;

            map.put(key, Math.max(map.getOrDefault(key, 0), C));
        }

        for(Map.Entry<String,Integer> entry : map.entrySet()){

            String[] key = entry.getKey().split(",");

            int a = Integer.parseInt(key[0]);
            int b = Integer.parseInt(key[1]);
            int c = entry.getValue();

            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());
    }

    private static void print() throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(answer));
        bw.close();        
    }
}
