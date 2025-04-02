package heejin.week9;

import java.io.*;
import java.util.*;

public class Week9_15661 {
    static int N, ans = Integer.MAX_VALUE;
    static int[][] ability;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ability = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // N비트에 각 사람이 포함 스타트팀에 포함되는지(1) 안되는지(0) 표시
        // 팀에 한 명 이상이어야 하므로 모든 비트가 1인 경우(1<<N - 1) 제외
        int maskMax = (1 << N) - 1;
        for (int mask = 1; mask < maskMax; mask++) {
            // 두 팀의 능력치 계산
            int startAbility = 0;
            int linkAbility = 0;

            // mask에 따라 각 사람이 어느 팀에 포함되는지 체크
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    int bitI = (mask >> i) & 1;
                    int bitJ = (mask >> j) & 1;

                    if ((bitI ^ bitJ) == 0) { // i, j번쨰 비트가 같으면 같은 팀
                        if (bitI == 1) { // 비트가 1이면 스타트팀에 포함
                            startAbility += ability[i][j] + ability[j][i];
                        } else {
                            linkAbility += ability[i][j] + ability[j][i];
                        }
                    }

                }
            }

            // 정답 업데이트
            ans = Math.min(ans, Math.abs(startAbility - linkAbility));
        }

        System.out.println(ans);
    }
}