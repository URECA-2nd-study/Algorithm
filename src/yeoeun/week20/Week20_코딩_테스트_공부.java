package yeoeun.week20;

import java.util.Arrays;

public class Week20_코딩_테스트_공부 {
    static final int INF = 987654321;
    public int solution(int alp, int cop, int[][] problems) {
        int ma = 0;
        int mc = 0;
        for(int[] p : problems) {
            ma = Math.max(ma, p[0]);
            mc = Math.max(mc, p[1]);
        }

        // 이미 만족이면 바로 반환
        if(alp >= ma && cop >= mc) return 0;

        // idx 조절
        alp = Math.min(alp, ma);
        cop = Math.min(cop, mc);

        // DP
        int[][] dp = new int[ma + 2][mc + 2];
        for(int[] d : dp) {
            Arrays.fill(d, INF);
        }

        dp[alp][cop] = 0;
        for(int a = alp; a <= ma; a++) {
            for(int c = cop; c <= mc; c++) {
                // 알고 UP
                dp[a+1][c] = Math.min(dp[a][c] + 1, dp[a+1][c]);

                // 코딩 UP
                dp[a][c+1] = Math.min(dp[a][c] + 1, dp[a][c+1]);

                // 문제 해결
                for(int[] p : problems) {
                    // 현재 풀 수 있는 문제에 대해
                    if(a >= p[0] && c >= p[1]) {
                        int na = Math.min(a + p[2], ma);
                        int nc = Math.min(c + p[3], mc);
                        dp[na][nc] = Math.min(dp[na][nc], dp[a][c] + p[4]);
                    }
                }
            }
        }
        return dp[ma][mc];
    }
}
