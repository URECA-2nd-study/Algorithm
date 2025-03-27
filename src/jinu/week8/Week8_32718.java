package jinu.week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week8_32718 {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken()) % K;
        }
        Arrays.sort(A);

        int s = 0;
        st = new StringTokenizer(br.readLine());
        while (T-- > 0) {
            int a = Integer.parseInt(st.nextToken());

            // 최종적으로 배열 각각에   (element + p ) % K 의 최댓값을 구하기 위해서는
            // 원본 배열이 K-p-1 이면 최댓값인 K-1 이 되므로
            // 이러한 값을 구하거나 가장 가까운 것을 구한다.
            // s는 계속 갱신되는 위에서 p의 역할
            s = (s + a) % K;

            // K - s -1 의 값을 이진탐색으로 검색
            // 만일 값이 존재한다면 idx 의 값은 그 index 를 반환
            // 만일 값이 존재하지 않는다면 음수 반환
            // 음수의 의미: 원소가 삽입되어야 할 위치의 음수값 -1
            // ex: 1,3,5,7,9 이고 4를 넣는다면 => 1 3 (4) 5 7, 9 이므로(idx:2) => -2 -1 = -3 반환
            int idx = Arrays.binarySearch(A, K - s - 1);

            if (idx < 0) {
                idx = -idx - 1 ;

                // 들어가야 할 위치 - 1 을 해줌으로써 답 찾기 가능 .
                if (idx == 0) {
                    idx = A.length - 1;
                } else {
                    idx--;
                }
            }


            System.out.print((A[idx] + s) % K + " ");
        }

    }
}
