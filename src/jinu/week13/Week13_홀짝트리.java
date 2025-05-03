package jinu.week13;
import java.util.*;
public class Week13_홀짝트리 {


    class TreeNodeDecisor{
        int numberOfOddNode;
        int numberOfEvenNode;
        int numberOfReverseOddNode;
        int numberOfReverseEvenNode;

        // 홀짝 트리가 되는 조건은?
        // 홀수 노드가 하나이며 나머지 노드가 루트라고 가정하였을 때 모두 역홀수 노드, 역짝수 노드가 되어야 한다.
        // 그리고 짝수노드가 0개 여야 한다.
        // 또는
        // 짝수 노드가 하나이며 나머지 노드가 루트라고 가정하였을 때 모두 역홀수 노드, 역짝수 노드가 되어야 한다.
        // 그리고 홀수 노드가 0개 여야 한다.
        public boolean isOddEvenTree(){
            if((numberOfOddNode==1 && numberOfEvenNode ==0)||
                    (numberOfEvenNode==1 && numberOfOddNode ==0)){
                return true;
            }

            return false;
        }

        // 같은 기준 적용

        public boolean isReverseOddEvenTree(){
            if((numberOfReverseOddNode==1 && numberOfReverseEvenNode ==0)||
                    (numberOfReverseEvenNode==1 && numberOfReverseOddNode ==0)){
                return true;
            }

            return false;
        }




    }



    class Solution {

        // 부모 , 자식 간의 연결
        Map<Integer,List<Integer>> treeMap = new HashMap<>();

        // 방문 여부 판단
        Set<Integer> used = new HashSet<>();

        public int[] solution(int[] nodes, int[][] edges) {
            int[] answer = new int[2];

            initTreeMap(nodes,edges);



            for(int nodeIndex: nodes){
                if(used.contains(nodeIndex)) continue;

                // 방문하지 않은 노드라면
                TreeNodeDecisor treeNodeDecisor = new TreeNodeDecisor();
                fillTreeNodeDecisor(treeNodeDecisor,nodeIndex);

                if(treeNodeDecisor.isOddEvenTree()){
                    answer[0]++;
                }
                if(treeNodeDecisor.isReverseOddEvenTree()){
                    answer[1]++;
                }

            }
            return answer;
        }

        // 부모 자식간의 연결 초기화
        public void initTreeMap(int []nodes,int [][]edges){
            for(int i=0;i<nodes.length;i++){
                treeMap.put(nodes[i],new ArrayList<>());
            }

            for(int i=0;i<edges.length;i++){
                treeMap.get(edges[i][0]).add(edges[i][1]);
                treeMap.get(edges[i][1]).add(edges[i][0]);
            }

        }

        // 루트 부터 재귀적으로 홀수 노드, 짝수 노드, 역홀수노드, 역짝수 노드인지 재귀적으로 탐색
        public void fillTreeNodeDecisor(TreeNodeDecisor treeNodeDecisor,int currentIndex){

            used.add(currentIndex);
            List<Integer> childs = treeMap.get(currentIndex);

            if(currentIndex % 2==0 && childs.size()%2==0){
                treeNodeDecisor.numberOfEvenNode++;
            }
            else if(currentIndex % 2 ==1 && childs.size()%2==0){
                treeNodeDecisor.numberOfReverseOddNode++;
            }
            else if(currentIndex %2 == 0 && childs.size()%2 ==1){
                treeNodeDecisor.numberOfReverseEvenNode++;
            }
            else if(currentIndex % 2==1 && childs.size()%2==1){
                treeNodeDecisor.numberOfOddNode++;
            }

            for(int nextIndex : childs){
                if(used.contains(nextIndex)) continue;
                fillTreeNodeDecisor(treeNodeDecisor,nextIndex);
            }

        }
    }
}
