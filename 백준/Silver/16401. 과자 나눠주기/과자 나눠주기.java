import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] nums;
    static int M, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 조카 수
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = Arrays.stream(nums).max().getAsInt() + 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (cal(mid) < M) right = mid;
            else left = mid + 1;
        }

        System.out.println(left - 1);

    }

    static int cal(int mid) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt += nums[i] / mid;
        }

        return cnt;
    }
}
