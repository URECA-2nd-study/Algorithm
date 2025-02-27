package heejin.week4;
import java.io.*;
import java.util.*;

public class Week4_7795 {
    static int N, M;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            A = new int[N];
            B = new int[M];
            int cnt = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(B);

            // 이분 탐색을 이용하여 A[i]보다 커지는 시점 찾기
            for (int i = 0; i < N; i++) {
                int left = 0;
                int right = B.length;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (B[mid] < A[i]) { // 잡아 먹을 수 있으면 더 큰 범위 탐색
                        left = mid + 1;
                    } else {
                        right = mid; // 잡아 먹을 수 없으면 더 작은 범위 탐색
                    }
                }
                cnt += left;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}

