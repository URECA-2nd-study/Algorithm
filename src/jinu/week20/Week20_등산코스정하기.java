package jinu.week20;
import java.util.*;

public class Week20_등산코스정하기 {

    class Node implements Comparable<Node>{
        int weight;
        int dest;

        public Node(int dest, int weight){
            this.weight = weight;
            this.dest = dest;
        }

        @Override
        public int compareTo(Node n){
            if(this.weight == n.weight){
                return this.dest - n.dest;
            }

            return this.weight - n.weight;
        }
    }
    class Solution {
        Set<Integer> end = new HashSet<>();
        Set<Integer> start = new HashSet<>();
        List<Node> graph[];

        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

            graph = new List[n+1];

            for(int gate: gates){
                start.add(gate);
            }
            for(int summit: summits){
                end.add(summit);
            }
            for(int i = 0;i <= n ;i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 0; i< paths.length; i++){
                int from = paths[i][0];
                int to = paths[i][1];
                int w = paths[i][2];

                if(!start.contains(from) && !end.contains(to)){
                    graph[from].add(new Node(to,w));
                    graph[to].add(new Node(from,w));
                }
                if(start.contains(from) || end.contains(to)){
                    graph[from].add(new Node(to,w));
                }
                else if(start.contains(to) || end.contains(from)){
                    graph[to].add(new Node(from,w));
                }
            }

            int inten = Integer.MAX_VALUE;
            int thatDest = Integer.MAX_VALUE;

            for(int gate: gates){
                int []result = djkstra(gate, n);
                if(inten > result[1]){
                    inten = result[1];
                    thatDest = result[0];
                }
                else if(inten == result[1] && thatDest > result[0]){
                    thatDest = result[0];
                }
            }

            return new int[]{thatDest, inten};


        }

        public int[] djkstra(int start, int n){
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(start,0));

            boolean isVisit[] = new boolean[n+1];
            int intensity[] = new int[n+1];
            Arrays.fill(intensity, Integer.MAX_VALUE);
            intensity[start] = 0;

            while(!pq.isEmpty()){
                Node cur = pq.poll();

                if(isVisit[cur.dest]){
                    continue;
                }

                // if(cur.weight > intensity[cur.dest]) {
                //     System.out.println(cur.dest);
                //     continue;
                // }

                if(end.contains(cur.dest)){
                    return new int[]{cur.dest,intensity[cur.dest]};
                }

                isVisit[cur.dest] = true;

                for(Node next: graph[cur.dest]){
                    if(intensity[next.dest] > Math.max(intensity[cur.dest], next.weight)){
                        intensity[next.dest] = Math.max(intensity[cur.dest], next.weight);
                        pq.add(new Node(next.dest, intensity[next.dest]));
                    }
                }
            }
            System.out.println("hi");
            return new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
        }
    }

}
