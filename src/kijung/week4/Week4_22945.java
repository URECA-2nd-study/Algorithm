import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week4_22945 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 포인터를 양끝에 위치시키면서 줄이기
        int left = 0;
        int right = N - 1;
        int answer = 0;

        while (left < right) { // 서로 만나면 종료

            int len = right - left - 1;
            answer = Math.max(answer, Math.min(nums[left], nums[right]) * len);

            if (nums[left] <= nums[right]) left++;
            else right--;

        }

        System.out.println(answer);

    }
}
