package heejin.week8;
import java.io.*;
import java.util.*;

public class Week8_32718 {
    static int N, K, T;
    static long[] Q;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        Q = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Q[i] = Integer.parseInt(st.nextToken()) % K;
        }

        Arrays.sort(Q);

        long sumA = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(st.nextToken());
            sumA = (sumA + num) % K;

            // (원소 + sumA) % K 가 최대
            // = (원소 + sumA) 가 K 미만이면서 최대인 값
            // = 원소가 K-sumA 미만이면서 최대

            // lower bound를 통해 K-sumA 이하의 최대값 찾기
            int find = lowerBound(K - sumA);

            // 이하 -> 미만으로 만들기
            // 이때 인덱스가 0이면 인덱스 범위 초과니까 N-1로
            if (find == 0) find = N - 1;
            else find--;

            // 다시 (원소 + sumA) % K 로 만들어주기
            sb.append((Q[find] + sumA) % K).append(" ");
        }

        System.out.print(sb);
    }

    static int lowerBound(long num) {
        int left = 0;
        int right = N;

        while (left < right) {
            int mid = (left + right) / 2;

            if (num > Q[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}