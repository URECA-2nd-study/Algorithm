import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        
        if (n <= cores.length) return n;
        
        int left = 1;
        int right = 10000 * n + 1;
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (cal(mid, cores) >= n) right = mid;
            else left = mid + 1;
        }
        
        int completed = cal(left - 1, cores);
        System.out.println(left - 1);
        
        for (int i = 0; i < cores.length; i++) {
            if (left % cores[i] == 0) {
                if (++completed == n) {
                    answer = (i + 1);
                }
            }
        }
        
        return answer;
        
    }
    
    static int cal(int time, int[] cores) { // 처리한 작업량 계산
        int cnt = cores.length;
        
        for (int i = 0; i < cores.length; i++) {
            cnt += time / cores[i];
        }
        
        return cnt;
         
    }
}

