package yeoeun.week16;

import java.io.*;
import java.util.StringTokenizer;

public class Week16_13422 {
    static int N, M, K;
    static int[] house = new int[N];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            house = new int[N];

            int total = 0;
            int answer = 0;

            int MAX = (N == M) ? N : N + M - 1;
            for (int j = 0; j < MAX; j++) {
                if (j < N) house[j] = Integer.parseInt(st.nextToken());

                total = total + ((j < N) ? house[j] : house[j - N]) - ((j >= M) ? house[j - M] : 0);
                if (j >= M - 1 && total < K) answer++;
            }
            bw.write(answer + "\n");
        }
        br.close();
        bw.close();
    }
}
