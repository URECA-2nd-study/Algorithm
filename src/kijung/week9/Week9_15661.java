import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Week9_15661 {

    static int N;
    static int[][] S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < (1 << N); i++) { // 조합별로 확인
            List<Integer> start = new ArrayList<>();
            List<Integer> link = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if ( (i & (1 << j)) == 0) {
                    start.add(j);
                } else link.add(j);
            }

            if (start.isEmpty() || link.isEmpty()) continue;

            min = Math.min(min, Math.abs(getScore(start) - getScore(link)));


        }

        System.out.println(min);

    }

    static int getScore(List<Integer> team) { // 점수 계산
        int score = 0;

        for (int i = 0; i < team.size(); i++) {
            for (int j = i + 1; j < team.size(); j++) {
                int p1 = team.get(i);
                int p2 = team.get(j);
                score += S[p1][p2] + S[p2][p1];
            }
        }

        return score;
    }
}
