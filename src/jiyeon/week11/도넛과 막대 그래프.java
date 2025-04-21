package jiyeon.week11;

import java.util.*;

class Solution {
    
    //HashMap의 key : 정점의 번호 
    public HashMap<Integer,Integer> in = new HashMap<>(); //key로 들어오는 간선의 개수 
    public HashMap<Integer,Integer> out = new HashMap<>(); //key로부터 나가는 간선의 개수 
    
    
    public int[] solution(int[][] edges) {
        
        int donut = 0 ; //도넛 그래프 개수 
        int stick = 0; //막대 그래프 개수 
        int inf = 0 ; //8자 그래프 개수 
        int generated = 0; // 생성된 정점 번호
        int genEdgeNum = 0 ; //생성된 정점에서 나가는 간선의 개수 
        
        for(int i=0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            
            in.put(b, in.getOrDefault(b,0)+1);
            out.put(a,out.getOrDefault(a,0)+1);
            
        }
        
        //생성된 정점 번호 찾기
        for(Map.Entry<Integer,Integer> entry : out.entrySet()){
            int vertex = entry.getKey(); 
            int outEdgeNum = entry.getValue();
            
            if(outEdgeNum >=2 && !in.containsKey(vertex)){
                generated = vertex;
                genEdgeNum = outEdgeNum;
            }
        }
        
        
        for(Map.Entry<Integer,Integer> entry : in.entrySet()){
            
            int vertex = entry.getKey();
            int inEdgeNum = entry.getValue();
            
            if(inEdgeNum>=1 && !out.containsKey(vertex)){
                stick ++;
            }
            
            if(inEdgeNum>=2 && out.containsKey(vertex) && out.get(vertex) == 2){
                inf ++;
            }
            
        }
        
        donut = genEdgeNum - stick - inf;
        
        int[] answer = {generated, donut, stick, inf};
        
        return answer;
    }
    
}