package src.jaehyeon.week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Week4_16562 {

    static int[] arr;
    static int[] weight;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        weight = new int[N + 1];
        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v, w);
        }

        long answer = 0;

        for (int i = 1; i <= N; i++) {
            int index = find(i);

            if (!visited[index]) {
                answer += weight[index];
                visited[index] = true;
            }

            visited[i] = true;
        }

        if (answer > k) sb.append("Oh no");
        else sb.append(answer);

        bw.write(sb + "");
        bw.flush();
        bw.close();
        br.close();

    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (weight[pa] > weight[pb]) {
            arr[pa] = pb;
        } else {
            arr[pb] = pa;
        }
    }

    private static int find(int a) {
        if (a == arr[a]) return a;
        else {
            arr[a] = find(arr[a]);
            return arr[a];
        }
    }
}