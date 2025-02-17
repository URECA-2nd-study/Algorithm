import java.util.*;

class Solution {
    
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int weight = costs[i][2];
            pq.add(new Edge(from, to, weight));
            pq.add(new Edge(to, from, weight));
        }
        
        
        return MST(n);
    }
    
    static int MST(int n) {
        int selectedEdge = 0;
        int sum = 0;
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int from = cur.from;
            int to = cur.to;
            
            if (find(from) != find(to)) {
                sum += cur.weight;
                selectedEdge++;
                union(from, to);
            }
            
            if (selectedEdge == n - 1) return sum;
        }
        
        return -1;
    }
    
    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    
    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        parent[aRoot] = bRoot;
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;
    
    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
    
}