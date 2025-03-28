import java.io.*;
import java.util.*;

public class Week8_1477 {
    static int N, M, L;
    static int[] locations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        locations = new int[N + 2];
        locations[0] = 0;
        locations[N + 1] = L;

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                locations[i] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(locations);

        int left = 1;  // 최소 간격 (0이면 나눌 수 없음)
        int right = L; // 최대 간격 (고속도로 길이)

        while (left < right) {
            int mid = (left + right) / 2;
            if (canUse(mid)) right = mid;
            else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    static boolean canUse(int mid) {
        int cnt = 0;

        for (int i = 1; i < locations.length; i++) {
            int gap = locations[i] - locations[i - 1];
            cnt += (gap / mid);
            if (gap % mid == 0) cnt--; // 나눠 떨어지면 중복 설치 방지
        }

        return cnt <= M;

    }
}