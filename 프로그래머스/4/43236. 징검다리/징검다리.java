import java.util.*;

class Solution {
        
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);
        
        int left = 1;
        int right = distance + 1;
        
        while (left < right) {
            int mid = (left + right) / 2; // 최소값 중 최대값 
            
            int cnt = 0;
            int prev = 0;
            
            for (int rock : rocks) {
                if (rock - prev < mid) {
                    cnt++;
                    if (cnt > n) break;
                } else prev = rock;
            }
            
            if (distance - prev < mid) cnt++;
            
            if (cnt > n) {
                right = mid;
            } else left = mid + 1;
            
        }
        
        return left - 1;
    }
    
   
}