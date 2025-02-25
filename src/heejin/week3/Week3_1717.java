package heejin.week3;

import java.io.*;
import java.util.*;

class Week3_1717 {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        // 자기 자신을 부모로 초기화
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        // 합집합 또는 같은 집합 포함 확인 연산 수행
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if (command == 0) { // 합집합 연산
                union(first, second);
            } else { // 같은 집합 포함 확인 연산
                int firstP = findParent(first);
                int secondP = findParent(second);

                sb.append(firstP == secondP ? "YES" : "NO").append("\n");
            }
        }

        // 결과 출력
        System.out.print(sb);
    }

    static void union(int first, int second) {
        int firstP = findParent(first);
        int secondP = findParent(second);

        if (firstP < secondP) {
            parent[secondP] = firstP;
        } else {
            parent[firstP] = secondP;
        }
    }

    static int findParent(int idx) {
        if (parent[idx] == idx) {
            return idx;
        }
        return parent[idx] = findParent(parent[idx]);
    }
}