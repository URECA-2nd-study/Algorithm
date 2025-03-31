package heejin.week8;
import java.io.*;
import java.util.*;

public class Week8_1477 {
    static int N, M, L;
    static int[] location;
    static int min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        location = new int[N + 2];

        location[0] = 0;
        location[N + 1] = L;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            location[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(location);

        min = 1; // 휴게소가 없는 최대 거리의 최솟값
        max = L - 1; // 휴게소가 없는 최대 거리의 최댓값

        // lower bound
        while (min < max) {
            int mid = (min + max) / 2;

            // 최대 거리가 mid일 때 설치 가능한 휴게소 개수 구하기
            int cnt = 0;
            for (int i = 1; i < location.length; i++) {
                cnt += (location[i] - location[i - 1] - 1) / mid;
            }

            // 설치 가능한 휴게소 개수가 M개보다 많으면 최대 거리를 늘리기
            if (cnt <= M) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        // 최대 거리의 최솟값
        System.out.println(min);
    }
}
