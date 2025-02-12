package heejin.week1;

import java.io.*;
import java.util.*;

public class Main {
    static int m, n, h, day = 0, zeroCnt;
    static int[][][] board;
    static int[] dx = {0, 0, 0, 0, -1, 1};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static Queue<int[]> queue = new LinkedList<>(); // 익은 토마토의 위치, 지난 시간(일) 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        board = new int[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    board[i][j][k] = Integer.parseInt(st.nextToken());
                    if (board[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k, 0});
                    }
                    if (board[i][j][k] == 0) {
                        zeroCnt++; // 안 익은 토마토 수 카운트
                    }
                }
            }
        }
        if (zeroCnt == 0) { // 모든 토마토가 이미 익은 상태
            System.out.println(0);
            return;
        }

        // 새로 익을 수 있는 토마토 전부 처리하기
        bfs();

        if (zeroCnt > 0) { // 모든 토마토가 익을 수 없는 상태
            System.out.println(-1);
            return;
        }
        System.out.println(day);
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curx = cur[0];
            int cury = cur[1];
            int curz = cur[2];

            for (int i = 0; i < dx.length; i++) {
                int nx = curx + dx[i];
                int ny = cury + dy[i];
                int nz = curz + dz[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= n || nz < 0 || nz >= m) continue;

                if (board[nx][ny][nz] == 0) { // 익은 토마토 근처의 안 익은 토마토는 익음
                    board[nx][ny][nz] = 1;
                    zeroCnt--;
                    day = Math.max(day, cur[3] + 1); // 익기까지 가장 오래 걸리는 시간(일)을 저장
                    queue.add(new int[]{nx, ny, nz, cur[3] + 1});
                }
            }
        }
    }
}