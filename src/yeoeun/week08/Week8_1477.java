package yeoeun.week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week8_1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] rest = new int[N+2]; // 시작, 끝도 함께 저장
        rest[0] = 0;
        rest[1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i < N+2; i++) {
            rest[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(rest);

        // 매개변수 탐색 (최대 거리 기준)
        int ps = 1;
        int pl = L-1;

        while(ps <= pl) {
            int mid = (ps + pl) / 2;
            int count = 0;

            // 최대 거리가 mid일 때, 새로운 휴게소의 개수 count
            for (int i = 1; i < N+2; i++) {
                count += (rest[i] - rest[i-1] - 1) / mid;
            }

            // 거리 갱신
            if(count > M) ps = mid + 1;
            else pl = mid - 1;
        }
        System.out.println(ps);
    }
}
