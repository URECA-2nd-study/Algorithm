import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, D;
    static int[] num;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[N];
        Deque<long[]> deque = new ArrayDeque<>();

        long max = Long.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            while (!deque.isEmpty() && deque.peekFirst()[0] < i - D) deque.pollFirst();

            // 현재 위치에서 시작
            dp[i] = num[i];

            if (!deque.isEmpty()) dp[i] = Math.max(dp[i], deque.peekFirst()[1] + num[i]);
            max = Math.max(max, dp[i]);

            // 현재 위치 i에 도달하려면 D 이하의 거리에서 점프할 수 있어야 하므로 i-D부터 i-1까지 중 최대 dp 값을 찾아야 함.
            // 즉 dp[i]보다 작은 값은 필요없음
            while (!deque.isEmpty() && deque.peekLast()[1] <= dp[i]) deque.pollLast();

            deque.addLast(new long[]{i, dp[i]});
        }

        System.out.println(max);


    }


}
