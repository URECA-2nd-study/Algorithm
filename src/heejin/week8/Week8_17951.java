package heejin.week8;

import java.io.*;
import java.util.*;

public class Week8_17951 {
    static int N, K;
    static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        score = new int[N];

        int min = 0; // 시험 점수 최소
        int max = 0; // 시험 점수 최대

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
            max += score[i];
        }

        // 최소값의 최대 구하기 = upper bound
        while (min <= max) {
            int mid = (min + max) / 2;

            // 시험 점수가 mid일 때 나눌 수 있는 그룹의 수 구하기
            int sum = 0, cnt = 0;
            for (int i = 0; i < N; i++) {
                sum += score[i];

                if (mid <= sum) {
                    sum = 0;
                    cnt++;
                }
            }

            // 그룹 수가 더 많거나 같으면 시험 점수 더 높일 수 있음
            if (cnt >= K) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        // 그룹 중 최소 시험 점수의 최댓값
        System.out.print(max);
    }
}