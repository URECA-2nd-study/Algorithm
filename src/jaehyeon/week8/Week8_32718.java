package src.jaehyeon.week8;

import java.io.*;
import java.util.*;

/**
 * K만큼 반복해야 한다 > K의 최대값 10의 9승 > 매 반복마다 PQ에 넣는다면 시간초과 > 이분 탐색으로 최대 값을 찾는다.
 */

public class Week8_32718 {

    static int N;//큐에 들은 원소 수
    static int K;//모듈러 연산
    static int T;//쿼리 개수
    //N * T 할 시 시간초과 발생
    static int[] pq;//입력 받으면서 모듈러 연산
    static int[] plusQuery;//쿼리 정보

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        init();

        int query = 0;
        for (int i = 0; i < T; i++) {

            query = (query + plusQuery[i])%K;

            int param = K - 1 - query;

            int maxIndex = binarySearch(param);

            if(maxIndex == -1) sb.append((pq[N-1]+query)%K).append(" ");
            else sb.append((pq[maxIndex]+query)%K).append(" ");

        }
        System.out.println(sb);

    }

    private static int binarySearch(int param) {
        int start = 0;
        int end = N - 1;

        int answer = -1;

        while(start<=end) {
            int mid = (start+end)/2;

            if(pq[mid] <= param) {
                start = mid+1;
                answer = mid;
            }else {
                end = mid-1;
            }
        }

        return answer;
    }


    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());//우선순위 큐에 들어있는 원소 개수
        K = Integer.parseInt(st.nextToken());//모든 원소에 대해 정수 K로 나머지 연산
        T = Integer.parseInt(st.nextToken());//쿼리의 개수

        pq = new int[N];
        plusQuery = new int[T];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq[i] = Integer.parseInt(st.nextToken()) % K;
        }

        Arrays.sort(pq);

//        System.out.println(Arrays.toString(pq));
//        result 가 0이면 틀리는 이유
//        5 10 5
//        7 3 9 8 4
//        6 8 6 7 8
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            plusQuery[i] = Integer.parseInt(st.nextToken());
        }
    }
}

