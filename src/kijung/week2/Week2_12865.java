import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] items = new int[N + 1][2];
        int[][] result = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken());
            items[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int weight = items[i][0];
            int value = items[i][1];

            for (int j = 1; j <= K; j++) {
                result[i][j] = result[i - 1][j];
                if (j - weight >= 0) {
                    result[i][j] = Math.max(result[i][j], result[i - 1][j - weight] + value);
                }
            }
        }

        System.out.println(result[N][K]);

    }
}
