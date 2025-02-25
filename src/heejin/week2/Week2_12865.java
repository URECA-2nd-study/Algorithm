package heejin.week2;

import java.io.*;
import java.util.*;

/**
 * [문제]
 * 최대 K 무게를 넣을 수 있는 베낭에 물건들의 가치 합이 최대가 되도록 하기
 *
 * [풀이]
 * i번 물건에 대해 선택지는 두 가지
 * (1) 물건을 가방에 넣는다
 * (2) 물건을 가방에 넣지 않는다
 *
 * 남은 최대 무게가 j일 때 i번 물건까지의 최대 가치 합
 * (1) j < (i번 물건 무게): 가방에 물건을 넣을 수 없으므로 i번 물건을 넣지 않음
 * (2) j >= (i번 물건 무게): (i번 물건을 가방에 안 넣는 경우), (i번 물건을 가방에 넣는 경우) 중 최대 가치를 선택
 */

public class Week2_12865 {
    static int N, K;
    static Integer[][] dp; // 최대 무게가 j일 때, i번째 물건까지의 가치 최대 합
    static int[][] item;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        item = new int[N + 1][2];
        dp = new Integer[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken()); // 무게
            item[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        // 초기값
        for (int i = 0; i <= K; i++) {
            dp[0][i] = 0;
        }

        System.out.println(sol(N, K));
    }


    // 남은 무게가 left일 때, idx번 물건까지의 최대 가치 합 계산
    static int sol(int idx, int left) { // 물건의 인덱스 , 가방에 남은 여유 공간
        // 이미 계산된 값
        if (dp[idx][left] != null) {
            return dp[idx][left];
        }

        // 가방에 남은 여유 공간 < idx번 물건 무게 -> 물건을 가방에 넣을 수 없음
        if (left < item[idx][0]) {
            return dp[idx][left] = sol(idx - 1, left);
        }

        // (idx번 물건을 가방에 안 넣는 경우), (idx번 물건을 가방에 넣는 경우) 중 최대 가치를 선택
        return dp[idx][left] = Math.max(
                sol(idx - 1, left),
                sol(idx - 1, left - item[idx][0]) + item[idx][1]
        );
    }
}
