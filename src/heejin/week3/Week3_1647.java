package heejin.week3;

import java.io.*;
import java.util.*;

class Week3_1647 {
    static int N, M;
    static int[] parent;
    static int[][] cost;
    static int minCost = 0; // 유지비 합의 최솟값
    static int villageCnt; // 마을 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        cost = new int[M][3];

        // 자기 자신을 부모로 초기화(마을 개수 N개)
        villageCnt = N;
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken()); // from 집
            cost[i][1] = Integer.parseInt(st.nextToken()); // to 집
            cost[i][2] = Integer.parseInt(st.nextToken()); // 유지비
        }

        // 유지비 기준 오름차순으로 정렬
        Arrays.sort(cost, (c1, c2) -> c1[2] - c2[2]);

        // 마을 개수 2개 될 때까지 마을 합치기
        for (int i = 0; i < M; i++) {
            if (villageCnt == 2) break;
            union(cost[i][0], cost[i][1], cost[i][2]);
        }

        // 출력
        System.out.println(minCost);
    }

    static void union(int first, int second, int cost) {
        int firstP = findParent(first);
        int secondP = findParent(second);

        // 서로 다른 마을에 속하면 합치기 가능
        if (firstP != secondP) {
            minCost += cost; // 마을 안의 집들은 서로 연결되므로 유지비 추가
            villageCnt--; // 마을 개수 하나 줄어듦

            // 더 작은 값 쪽으로 편입
            if (firstP < secondP) {
                parent[secondP] = firstP;
            } else {
                parent[firstP] = secondP;
            }
        }
    }

    static int findParent(int idx) {
        if (idx == parent[idx]) {
            return idx;
        }
        return parent[idx] = findParent(parent[idx]);
    }
}