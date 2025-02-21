package heejin.week2;

import java.util.*;
import java.io.*;

/**
 * [문제]
 * 아래 조건 만족시키면서 모든 집을 칠하는 비용의 최솟값 구하기
 *
 * [조건]
 * (1) 1번 집의 색 != 2번 집의 색
 * (2) N번 집의 색 != N-1번 집의 색
 * (3) i(2 ≤ i ≤ N-1)번 집의 색 != i-1번, i+1번 집의 색
 *
 * [풀이]
 * N번 집의 색에 따른 최소 비용을 구하고, 이 중 가장 작은 비용을 선택
 *
 * N번 집의 색에 따른 최소 비용 = N-1번 집의 색이 N번 집과 다를 때의 최소 비용 + N번집 색의 비용
 * (1) N번 집의 색이 빨강이면, 이전 집은 초록 or 파랑
 * (2) N번 집의 색이 초록이면, 이전 집은 빨강 or 파랑
 * (3) N번 집의 색이 파랑이면, 이전 집은 초록 or 빨강
 */

public class Week2_1149 {

    static int N;
    static int[][] dp;
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cost = new int[N+1][3];
        dp = new int[N+1][3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기값
        dp[1][0] = cost[1][0];
        dp[1][1] = cost[1][1];
        dp[1][2] = cost[1][2];

        // N번 집의 색에 따라 각각 최소 비용을 구하고, 이 중 가장 작은 비용을 선택
        System.out.println(Math.min(sol(N, 0), Math.min(sol(N, 1), sol(N, 2))));
    }

    static int sol(int num, int color) {
        // 이미 계산한 경우
        if (dp[num][color] > 0) {
            return dp[num][color];
        }

        // num번 집의 색이 빨강(0)이면, num-1번 집의 색은 초록(1) or 파랑(2)
        if (color == 0) {
            dp[num][0] = Math.min(sol(num - 1, 1), sol(num - 1, 2)) + cost[num][0];
        }

        // num번 집의 색이 초록(1)이면, num-1번 집의 색은 빨강(0) or 파랑(2)
        if (color == 1) {
            dp[num][1] = Math.min(sol(num - 1, 0), sol(num - 1, 2)) + cost[num][1];
        }

        // num번 집의 색이 파랑(2)이면, num-1번 집의 색은 빨강(0) or 초록(1)
        if (color == 2) {
            dp[num][2] = Math.min(sol(num - 1, 0), sol(num - 1, 1)) + cost[num][2];
        }

        return dp[num][color];
    }
}
