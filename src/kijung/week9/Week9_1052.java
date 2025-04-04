import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week9_1052 {

    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int bottles;
        int add = 0;

        while (true) {
            bottles = Integer.bitCount(N);
            if (bottles <= K) break;

            int idx = (N & (-N)); // 1이 처음으로 나타나는 위치
            add += idx;
            N += idx;
        }

        System.out.println(add);

    }
}
