import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] nums;
    static int cityMAX;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        nums = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < size; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        cityMAX = Integer.parseInt(br.readLine());

        int left = 0;
        int right = Arrays.stream(nums).max().getAsInt() + 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (!check(mid)) right = mid;
            else left = mid + 1;
        }

        System.out.println(left - 1);

    }

    // 가능한지 불가능한지 확인하는 메소드
    static boolean check(int max) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += Math.min(nums[i], max);
        }

        return sum <= cityMAX;
    }
}
