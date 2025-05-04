package src.jaehyeon.week13;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Week13_388354 {

    class Solution {

        public ArrayList<Integer>[] adj;
        public int maxNode;
        public boolean[] visited;

        public int[] solution(int[] nodes, int[][] edges) {

            init(nodes, edges);

            int answer[] = dfs(nodes);

            return answer;
        }

        private int[] dfs(int[] nodes){

            visited = new boolean[maxNode+1];

            int nomalTree = 0;
            int reverseTree = 0;

            for(int node : nodes){

                if(visited[node]) continue;

                ArrayDeque<Integer> s = new ArrayDeque<>();
                s.push(node);

                int nodeCount = 0;

                int reverseRootCount = 0;
                int nomalRootCount = 0;

                int nomalChildCount = 0;
                int reverseChildCount = 0;

                while(!s.isEmpty()){

                    int n = s.pop();
                    nodeCount++;
                    visited[n] = true;

                    boolean state = adj[n].size()%2 == n%2;
                /*
                    state == true
                    홀짝 트리의 루트 노드이자
                    역홀짝 트리의 자식 노드가 될 수 있다.

                    홀짝 트리의 자식 노드가 될 수없고
                    역홀짝 트리의 루트 노드가 될 수 없다.

                    state == false
                    홀짝 트리의 자식 노드이자
                    역홀짝 트리의 루트 노드가 될 수 있다.

                    홀짝 트리의 루트 노드가 될 수없고
                    역홀짝 트리의 자식 노드가 될 수 없다.
                */
                    if(state){
                        nomalRootCount++;
                        reverseChildCount++;

                    }else{
                        reverseRootCount++;
                        nomalChildCount++;
                    }

                    // if(nomalRootCount >= 2 && reverseRootCount >= 2){
                    //     fillTrue(n);
                    //     break;
                    // }

                    for(int a : adj[n]){
                        if(visited[a]) continue;
                        s.push(a);
                    }

                }//while

                //System.out.println(Arrays.toString(visited));

                if(reverseChildCount == nodeCount-1 && reverseRootCount == 1){
                    reverseTree++;
                }
                if(nomalChildCount == nodeCount-1 && nomalRootCount == 1){
                    nomalTree++;
                }

            }//for

            return new int[]{nomalTree,reverseTree};
        }

        private void fillTrue(int node){

            ArrayDeque<Integer> s = new ArrayDeque<>();
            s.push(node);

            while(!s.isEmpty()){
                int a = s.pop();

                visited[a] = true;

                for(int b : adj[a]){
                    if(visited[b]) continue;
                    s.push(b);
                }
            }

        }

        private void init(int[] nodes, int[][] edges){

            Arrays.sort(nodes);

            maxNode = nodes[nodes.length-1];

            adj = new ArrayList[maxNode+1];

            for(int node : nodes){

                adj[node] = new ArrayList<>();

            }

            for(int[] edge : edges){

                adj[edge[0]].add(edge[1]);
                adj[edge[1]].add(edge[0]);

            }
        }
    }
}
