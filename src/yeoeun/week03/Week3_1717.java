package yeoeun.week03;

import java.io.*;
import java.util.StringTokenizer;

public class Week3_1717 {
    static int[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(command == 0) { // 합집합
                union(a, b);
            } else if (command == 1) { // 연결 여부 확인
                a = find(a);
                b = find(b);

                // 루트가 같다면 연결된 상태
                if(a == b) bw.write("YES\n");
                else bw.write("NO\n");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) root[b] = a;
    }

    private static int find(int node) {
        if(root[node] == node) return node;

        root[node] = find(root[node]);
        return root[node];
    }
}
