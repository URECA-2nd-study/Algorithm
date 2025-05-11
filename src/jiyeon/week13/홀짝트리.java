import java.util.*;

/*
기본개념 : 
어떤 노드가 루트일 때 홀수 노드이면, 그 노드가 루트가 아닐 시 역홀수 노드 (반대도 성립)
어떤 노드가 루트일 때 짝수 노드이면, 그 노드가 루트가 아닐 시 역짝수 노드 (반대도 성립)

1. 엣지 배열을 통해 양방향 그래프 생성 
2. 노드 배열을 순회하며 각 노드가 포함된 트리를 구성 (visited이면 continue)
3. 모든 노드는 루트가 아닌 노드라고 가정하고 각 노드의 번호와 자식수를 계산하여 노드의 타입을 판별 
4. 해당 노드 중 하나만 홀/짝 노드이고 나머지는 역홀/역짝 노드이면 reverseTreeNum++
4-1. 해당 노드 중 하나만 역홀/역짝 노드이고 나머지는 홀짝 노드이면 treeNum ++

*/

class Solution {
    
    static int treeNum, reverseTreeNum; 
    static boolean [] visited = new boolean[1000001]; //노드 방문 여부 
    static HashMap<Integer,ArrayList<Integer>> graph = new HashMap<>();
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        for(int node : nodes){
            graph.put(node, new ArrayList<Integer>());
        }
        
        for(int [] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        } //1.양방향 그래프 연결
        
        //2. dfs로 트리 만들기 
        for(int node : nodes){
            if(!visited[node]){
                ArrayList<Integer> curTree = new ArrayList<>(); //현재 트리
                dfs(node, curTree);
                
                //3. node를 루트 노드라고 가정 => 노드 타입 계산
                int yellow = 0, red = 0;
                
                //현재 트리 순회
                for(int cur : curTree){
                    
                    int childNum = graph.get(cur).size() - 1; //트리의 자식 개수
                    boolean isYellow = (cur%2) == (childNum%2);
                    if(isYellow){
                        yellow ++;
                    }
                    else{
                        red ++;
                    }
                    
                }//for - curTree
                
                //4. 트리의 유형을 판별
                
                if (red == 1) {
                    treeNum++;
                }
                
                if(yellow == 1){
                    reverseTreeNum ++ ; 
                }
            }
            
        }//node
        
        answer = new int [] {treeNum, reverseTreeNum};
        
        return answer;
    }//main 
    
    
    public void dfs(int curNode, ArrayList<Integer> tree ){
        visited[curNode] = true;
        tree.add(curNode);
        
        for(int next : graph.get(curNode)){
            if(!visited[next]){
                dfs(next, tree);
            }
        }
    }//dfs
}