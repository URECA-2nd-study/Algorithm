import java.io.*;
import java.util.*;

class Week13_388352 {

    public int solution(int n, int[][] q, int[] ans) {
        int m = q.length;
        int[] bitmasks = new int[m];
        
        for(int i = 0; i < m; i++) {
            for(int num : q[i]) {
                bitmasks[i] |= 1 << (num - 1);
            }
        }
        
        return GospersHack(n, m, bitmasks, ans);
    }
    
    private static int GospersHack(int n, int m, int[] bitmasks, int[] ans) {
        int comb = (1 << 5) - 1;
        int limit = 1 << n;
        int answer = 0;
        
        
        while (comb < limit) {
            boolean isPoss = true;
            
            for(int i = 0; i < m; i++) {
                if(Integer.bitCount(comb & bitmasks[i]) != ans[i]) {
                    isPoss = false;
                    break;
                }
            }
            
            if(isPoss) answer++;
            
            int x = comb & -comb;
            int y = comb + x;
            comb = (((comb & ~y) / x) >> 1) | y;
        }
        return answer;
    }
}