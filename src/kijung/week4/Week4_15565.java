import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week4_15565 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        // 1이 라이언 인형
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        int lionCnt = 0;

        int left = 0;
        int right = 0;

        while (right < N) {

            if (nums[right] == 1) {
                lionCnt++;
            }

            while (lionCnt >= K && left <= right) { // lion개수가 K개가 유지될때까지 leftf 증가시키기
                answer = Math.min(answer, right - left + 1);
                if (nums[left] == 1) lionCnt--;
                left++;
            }

            right++;

        }

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);

    }
}
