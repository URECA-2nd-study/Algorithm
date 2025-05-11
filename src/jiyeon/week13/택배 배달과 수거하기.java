import java.util.*;
import java.math.*;

class Solution {
    
    /*
    pick up을 위해 가장 먼 거리를 먼저 가는게 좋음
    역순으로 각 배열을 탐색하며 cap이 다 찰 때 까지 각 거리를 계산 (deliveries, pickups)
    두 거리 중 먼 거리를 채택하여 왕복거리 계산 
    */
    
    static int cap;
    public long solution(int CAP, int N, int[] deliveries, int[] pickups) {
        long answer = 0;
        cap = CAP;
        int start = N-1;
        int delIdx = -1;
        int pickIdx = -1;
        
        while(true){
            delIdx = findIndex(start, deliveries);
            pickIdx = findIndex(start, pickups);
            
            if(delIdx<0 && pickIdx<0)
                break;
            
            start = Math.max(delIdx, pickIdx);
            answer += (long)(start+1)*2;
            visit(delIdx, deliveries);
            visit(pickIdx, pickups);
        }
        
        return answer;
    }//main 
    
    //역순으로 index 만큼 방문하는 메서드 
    public void visit(int index, int [] arr){
        int r = cap;
        
        for(int i = index; i>=0 && r>0; i--){
            if(arr[i] == 0)
                continue;
            
            int min = Math.min(r, arr[i]); //더 작은 값을 빼준다. 
            r -= min;
            arr[i] -=min;
        }
    }//visit 
    
    //역순으로 배열 탐색하는 메서드, return : 제일 큰 i 
    public int findIndex(int start, int [] arr){        
        for(int i = start; i>=0; i--){
            if(arr[i] > 0)
                return i;
        }
        return -1; 
    }//findIndex
    
}