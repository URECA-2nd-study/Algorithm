package src.jaehyeon.week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class week3_24542 {

    static final int MOD = 1000000007;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            union(s1, s2);
        }

        long sum = 1;
        int[] countArr = new int[N+1];
        for (int i = 1; i <=N; i++) {
            countArr[find(i)]++;
        }

        for(int i=1; i<=N; i++){
            if(countArr[i]!=0){
                sum = sum*countArr[i] % MOD;
            }
        }

        bw.write(sum%MOD + "");
        bw.flush();
        bw.close();
        br.close();

    }

    private static void union(int s1, int s2) {
        int node1 = find(s1);
        int node2 = find(s2);

        if (node1 != node2) arr[node1] = node2;
    }

    private static int find(int s) {
        if (arr[s] == s) return s;
        else {
            arr[s] = find(arr[s]);
            return arr[s];
        }
    }
}
