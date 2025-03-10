package heejin.week5;

import java.io.*;
import java.util.*;

public class Week5_10330 {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        // 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] ans0 = new int[N]; // 0으로 시작하는 정답 비트 문자열
        int[] ans1 = new int[N]; // 1로 시작하는 정답 비트 문자열
        st = new StringTokenizer(br.readLine());

        // 정답 비트 문자열 만들기
        int idx = 0;
        for (int i = 0; i < M; i++) {
            int cnt = Integer.parseInt(st.nextToken());

            int value0 = i % 2;
            int value1 = (i + 1) % 2;

            for (int j = 0; j < cnt; j++) {
                ans0[idx] = value0;
                ans1[idx++] = value1;
            }
        }

        // 최소 연산 수 계산
        int minCnt0 = calMinCnt(arr.clone(), ans0);
        int minCnt1 = calMinCnt(arr.clone(), ans1);

        System.out.print(Math.min(minCnt0, minCnt1));
    }

    static int calMinCnt(int[] arr, int[] ans) { // arr: 초기 문자열, ans: 정답 문자열
        int cnt = 0;

        for (int start = 0; start < ans.length; start++) {
            if (arr[start] == ans[start]) { // 정답과 일치하면 다음 비트 문자 비교
                continue;
            }

            int end = start;
            while (end < N && arr[end] != ans[start]) { // 원하는 비트를 찾을 때까지 인접한 두 비트 스왑
                cnt++; // 스왑 연산 카운트
                end++;
            }

            if (end != N) {
                arr[end] = (ans[start] + 1) % 2; // 최종 스왑 완료된 시점의 비트 값 바꾸기
            } else {
                return Integer.MAX_VALUE; // 정답 비트 문자열로 만들 수 없는 경우
            }
        }
        return cnt;
    }
}
