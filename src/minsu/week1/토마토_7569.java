package minsu.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7569 {

    private static class tomato {
        int y, x, z, t;

        public tomato(int y, int x, int z, int t) {
            super();
            this.y = y;
            this.x = x;
            this.z = z;
            this.t = t;
        }

    }

    private static int M, N, H;
    private static int[][][] board;
    private static int[] dy = { -1, 1, 0, 0, 0, 0 };
    private static int[] dx = { 0, 0, -1, 1, 0, 0 };
    private static int[] dz = { 0, 0, 0, 0, -1, 1 };
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H][N][M];
        Queue<tomato> q = new ArrayDeque<>();

        for (int z = 0; z < H; z++) {

            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());

                for (int x = 0; x < M; x++) {
                    board[z][y][x] = Integer.parseInt(st.nextToken());

                    if (board[z][y][x] == 1) {
                        q.add(new tomato(y, x, z, 0));
                    }

                }

            }

        }

        bfs(q);

        if (isClear()) {
            System.out.println(time);
        } else {
            System.out.println(-1);
        }

    }

    private static void bfs(Queue<tomato> q) {

        while (!q.isEmpty()) {

            tomato curr = q.poll();

            time = curr.t;

            for (int i = 0; i < 6; i++) {

                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];
                int nz = curr.z + dz[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M && nz >= 0 && nz < H && board[nz][ny][nx] == 0) {
                    board[nz][ny][nx] = 1;
                    q.add(new tomato(ny, nx, nz, curr.t + 1));
                }
            }

        }

    }

    private static boolean isClear() {
        for (int z = 0; z < H; z++) {

            for (int y = 0; y < N; y++) {

                for (int x = 0; x < M; x++) {

                    if (board[z][y][x] == 0) {
                        return false;
                    }

                }

            }
        }

        return true;
    }

}