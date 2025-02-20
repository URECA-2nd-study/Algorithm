import java.util.*;

class Solution {
    
    static int[] dp, cnt1, cnt2;
    
    public int[] solution(int target) {
        int[] answer = new int[2];
        
        dp = new int[target + 1];
        Arrays.fill(dp, 12345678); 
        cnt1 = new int[target + 1];
        cnt2 = new int[target + 1];
        dp[0] = 0; // 0점일 때는 던질 필요 없음
    
        for (int i = 1; i <= target; i++) {
            
            for (int j = 1; j <= 20; j++) {
                int add = 0;
                
                //  싱글
                add = j;
                if (i - add >= 0) { 
                    if (dp[i - add] + 1 < dp[i]) {
                        dp[i] = dp[i - add] + 1;
                        cnt1[i] = cnt1[i - add] + 1;
                        cnt2[i] = cnt2[i - add];
                    } else if (dp[i - add] + 1 == dp[i]) {
                        if (cnt1[i - add] + cnt2[i - add] + 1 > cnt1[i] + cnt2[i]) {
                            cnt1[i] = cnt1[i - add] + 1;
                            cnt2[i] = cnt2[i - add];
                        }
                    }
                }
                
                // 불 (50점)
                add = 50;
                if (i - add >= 0) {
                    if (dp[i - add] + 1 < dp[i]) {
                        dp[i] = dp[i - add] + 1;
                        cnt1[i] = cnt1[i - add];
                        cnt2[i] = cnt2[i - add] + 1;
                    } else if (dp[i - add] + 1 == dp[i]) {
                        if (cnt1[i - add] + cnt2[i - add] + 1 > cnt1[i] + cnt2[i]) {
                            cnt1[i] = cnt1[i - add];
                            cnt2[i] = cnt2[i - add] + 1;
                        }
                    }
                }
                
                // 🎯 더블
                add = j * 2;
                if (i - add >= 0) {
                    if (dp[i - add] + 1 < dp[i]) {
                        dp[i] = dp[i - add] + 1;
                        cnt1[i] = cnt1[i - add];
                        cnt2[i] = cnt2[i - add];
                    } else if (dp[i - add] + 1 == dp[i]) {
                        if (cnt1[i - add] + cnt2[i - add] > cnt1[i] + cnt2[i]) {
                            cnt1[i] = cnt1[i - add];
                            cnt2[i] = cnt2[i - add];
                        }
                    }
                }
                
                // 🎯 트리플
                add = j * 3;
                if (i - add >= 0) {
                    if (dp[i - add] + 1 < dp[i]) {
                        dp[i] = dp[i - add] + 1;
                        cnt1[i] = cnt1[i - add];
                        cnt2[i] = cnt2[i - add];
                    } else if (dp[i - add] + 1 == dp[i]) {
                        if (cnt1[i - add] + cnt2[i - add] > cnt1[i] + cnt2[i]) {
                            cnt1[i] = cnt1[i - add];
                            cnt2[i] = cnt2[i - add];
                        }
                    }
                }
            }
        }
        
        answer[0] = dp[target];  
        answer[1] = cnt1[target] + cnt2[target]; 
        
        return answer;
    }
}