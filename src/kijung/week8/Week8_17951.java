import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week8_17951 {

    static int N, K;
    static int[] scores;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        scores = new int[N];

        int left = 0;
        int right = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
            right += scores[i];
        }
        right++;

        while (left < right) {
            int mid = (left + right) / 2;
            if (check(mid) < K) right = mid;
            else left = mid + 1;
        }

        System.out.println(left - 1);

    }

    static int check(int mid) { // 목표 점수 이상으로 나눠지면 그룹 분할

        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (sum + scores[i] >= mid) {
                sum = 0;
                cnt++;
            } else sum += scores[i];
        }

        return cnt;
    }


}
