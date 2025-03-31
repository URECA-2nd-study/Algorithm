package yeoeun.week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week8_32718 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken()) % K;
        }
        Arrays.sort(nums);

        int A = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            A = (A + Integer.parseInt(st.nextToken())) % K;

            int answer = 0;
            int criterion = K - A - 1; // 기준보다 작거나 같은 수 중 가장 큰 값 찾기

            // 가장 작은 수가 기준을 넘음 -> 모두가 A보다 작으므로 가장 큰 값이 정답
            if(nums[0] > criterion) {
                answer = (nums[N-1] + A) % K;
            } else { // nums 중 criterion보다 작거나 같은 것은 무조건 존재
                // 이분 탐색으로 기준보다 작거나 같은 것 중 가장 큰 값 찾기
                int ps = 0;
                int pl = N-1;

                while(ps <= pl) {
                    int mid = (ps + pl) / 2;

                    if(nums[mid] <= criterion) {
                        answer = nums[mid] + A;
                        ps = mid + 1;
                    } else {
                        pl = mid - 1;
                    }
                }
            }
            // 정답 입력
            sb.append(answer).append(" ");
        }
        System.out.println(sb);
    }
}
