
import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static final int LOG = 17;
    static ArrayList<int[]>[] adjList;
    static boolean[] visited;
    static int[] depth;
    static int[][] parent;
    static int[][] shortest, longest;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        depth = new int[N + 1];
        parent = new int[LOG + 1][N + 1];
        shortest = new int[LOG + 1][N + 1];
        longest = new int[LOG + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            adjList[a].add(new int[] {b, len});
            adjList[b].add(new int[] {a, len});

        }

        bfs(1);

        findAncestor();

        int test = Integer.parseInt(br.readLine());
        for (int i = 0; i < test; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lca(a, b);
        }

        System.out.println(sb.toString());
    }

    static void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        visited[root] = true;
        depth[root] = 0;

        while(!queue.isEmpty()) {
            int curNode = queue.poll();

            for (int[] next : adjList[curNode]) {
                int nextTo = next[0];
                int len = next[1];
                if (!visited[nextTo]) {
                    visited[nextTo] = true;
                    parent[0][nextTo] = curNode;
                    shortest[0][nextTo] = len;
                    longest[0][nextTo] = len;
                    depth[nextTo] = depth[curNode] + 1;
                    queue.add(nextTo);
                }
            }
        }
    }

    static void findAncestor() {

        for (int k = 1; k <= LOG; k++) {
            for (int v = 1; v <= N; v++) {
                parent[k][v] = parent[k - 1][parent[k - 1][v]];
                shortest[k][v] = Math.min(shortest[k - 1][v], shortest[k - 1][parent[k - 1][v]]); // 현재 조상과 조상의 조상
                longest[k][v] = Math.max(longest[k - 1][v], longest[k - 1][parent[k - 1][v]]);
            }
        }
    }

    static void lca(int a, int b) {

        int minLen = Integer.MAX_VALUE;
        int maxLen = Integer.MIN_VALUE;

        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int k = LOG; k >= 0; k--) {
            if (depth[b] - depth[a] >= (1 << k)) {
                minLen = Math.min(minLen, shortest[k][b]);
                maxLen = Math.max(maxLen, longest[k][b]);
                b = parent[k][b]; // b 끌어올리기
            }
        }

        if (a == b) {
            sb.append(minLen).append(" ").append(maxLen).append("\n");
            return;
        }

        for (int k = LOG; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                minLen = Math.min(minLen, Math.min(shortest[k][a], shortest[k][b]));
                maxLen = Math.max(maxLen, Math.max(longest[k][b], longest[k][a]));
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        minLen = Math.min(minLen, Math.min(shortest[0][a], shortest[0][b]));
        maxLen = Math.max(maxLen, Math.max(longest[0][a], longest[0][b]));
        sb.append(minLen).append(" ").append(maxLen).append("\n");

    }
}
