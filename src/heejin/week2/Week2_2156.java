import java.util.*;
import java.io.*;

/**
 * [문제]
 * N개의 포도주가 주어졌을 때, 아래 조건 만족시키면서 최대로 마실 수 있는 포도주의 양 구하기
 *
 * [조건]
 * (1) 잔에 들어있는 포도주는 모두 마셔야 하고, 원래 위치에 다시 놓아야 함
 * (2) 연속으로 놓여 있는 3잔을 모두 마실 수 X
 *
 * [풀이]
 * N개의 포도주에 대해 최대 포도주 양은, 아래 3가지 케이스 중 하나
 * (1) (N-3개 중 최대 포도주 양) + (N-1번 포도주 양) + (N번 포도주 양)
 * (2) (N-2개 중 최대 포도주 양) + (N번 포도주 양)
 * (3) (N-1개 중 최대 포도주 양)
 */

public class Main {

    static int N;
    static Integer[] dp;
    static int[] amount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        amount = new int[N + 1];
        dp = new Integer[N + 1];

        for (int i = 1; i <= N; i++) {
            amount[i] = Integer.parseInt(br.readLine());
        }

        // 초기값
        dp[0] = 0;
        dp[1] = amount[1];
        if (N > 1) {
            dp[2] = amount[1] + amount[2];
        }

        System.out.println(sol(N));
    }

    // N개 중 최대 포도주 양
    static int sol(int num) {
        // 이미 계산한 경우 or 초기값
        if (dp[num] != null) {
            return dp[num];
        }

        return dp[num] = Math.max(
                sol(num - 3) + amount[num - 1] + amount[num], // (N-3개 중 최대 포도주 양 + N-1번 포도주 양 + N번 포도주 양)
                Math.max(
                        sol(num - 2) + amount[num] // or (N-2개 중 최대 포도주 양 + N번 포도주 양)
                        , sol(num - 1) // or (N-1개 중 최대 포도주 양)
                ));
    }
}
