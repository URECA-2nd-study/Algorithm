import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week8_32718 {

    static int N, K;
    static int[] nums;
    static int[] adds;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N];

        int T = Integer.parseInt(st.nextToken());
        adds = new int[T];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken()) % K;
        }
        Arrays.sort(nums);

        st = new StringTokenizer(br.readLine());
        adds[0] = Integer.parseInt(st.nextToken()) % K;
        for (int i = 1; i < T; i++) {
            adds[i] = (adds[i - 1] + Integer.parseInt(st.nextToken())) % K;
        }

        for (int i = 0; i < T; i++) {
            int offset = adds[i];

            int maxMod;
            if (offset == 0) {
                maxMod = nums[N - 1]; // offset이 0이면 최대 나머지 그대로
            } else {
                // 이분 탐색으로 K - offset 이상의 첫 번째 위치 찾기
                int target = K - offset;
                int left = 0;
                int right = N;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if (nums[mid] >= target) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }

                int boundary = left;
                // 경계 근처에서 최대값 계산
                if (boundary == 0 || boundary == N) {
                    maxMod = (nums[N - 1] + offset) % K;
                } else {
                    int before = (nums[boundary - 1] + offset) % K;
                    int after = (nums[boundary] + offset) % K;
                    maxMod = Math.max(before, after);
                }
            }

            bw.write(maxMod + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}