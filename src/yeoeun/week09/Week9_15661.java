package yeoeun.week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Week9_15661 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] power = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                power[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < (1 << N); i++) {
            ArrayList<Integer> start = new ArrayList<>();
            ArrayList<Integer> link = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if((i & (1 << j)) == 0) start.add(j); // j번째가 0 -> start팀
                else link.add(j); // j번째가 1 -> link팀
            }

            // 각각 점수 구하기
            int startP = 0;
            for (int j = 0; j < start.size() - 1; j++) {
                for (int k = j + 1; k < start.size(); k++) {
                    startP += power[start.get(j)][start.get(k)] + power[start.get(k)][start.get(j)];
                }
            }

            int linkP = 0;
            for (int j = 0; j < link.size() - 1; j++) {
                for (int k = j + 1; k < link.size(); k++) {
                    linkP += power[link.get(j)][link.get(k)] + power[link.get(k)][link.get(j)];
                }
            }
            // 최소 점수 차 구하기
            answer = Math.min(answer, Math.abs(startP - linkP));
        }
        System.out.println(answer);
    }
}
