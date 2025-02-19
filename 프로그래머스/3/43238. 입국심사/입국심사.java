import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 1;
        long right = (long) Arrays.stream(times).max().getAsInt() * n + 1;
        
        while(left < right) {
            long mid = (left + right) / 2;
            if (check(mid, n, times)) right = mid;
            else left = mid + 1;
        }
        
        return left;
    }
    
    static boolean check(long mid, int n, int[] times) {
        long cnt = 0;
        
        for (int i = 0; i < times.length; i++) {
            cnt += mid / times[i];
        }
        
        return cnt >= n;
    }
    
}