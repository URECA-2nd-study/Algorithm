import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (o1, o2) -> {
            return o1[1] - o2[1];
        });
        
        int cur = routes[0][1];
        int cnt = 1;
        for (int i = 1; i < routes.length; i++) {
            if (cur >= routes[i][0] && cur <= routes[i][1]) continue;
            else {
                cnt++;
                cur = routes[i][1];
            }
        }
        
        return cnt;
    }
}