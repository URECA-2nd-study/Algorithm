package jinu.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week1_4963 {
    static int[] dy = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    static final int SIZE_OF_DIRECTION = 8;

    public Week1_4963() {
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                bw.flush();
                return;
            }

            int[][] map = new int[h][w];
            boolean[][] check = new boolean[h][w];

            for(int i = 0; i < h; ++i) {
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < w; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int var10001 = countOfLand(map, check, h, w);
            bw.write(var10001 + "\n");
        }
    }

    public static int countOfLand(int[][] map, boolean[][] check, int h, int w) {
        int countOfLand = 0;

        for(int i = 0; i < h; ++i) {
            for(int j = 0; j < w; ++j) {
                if (map[i][j] == 1 && !check[i][j]) {
                    BFS(map, check, i, j, h, w);
                    ++countOfLand;
                }
            }
        }

        return countOfLand;
    }

    public static void DFS(int[][] map, boolean[][] check, int i, int j, int h, int w) {
        check[i][j] = true;

        for(int p = 0; p < 8; ++p) {
            int newY = i + dy[p];
            int newX = j + dx[p];
            if (newY >= 0 && newY < h && newX >= 0 && newX < w && map[newY][newX] == 1 && !check[newY][newX]) {
                DFS(map, check, newY, newX, h, w);
            }
        }

    }

    public static void BFS(int[][] map, boolean[][] check, int i, int j, int h, int w) {
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{i, j});
        check[i][j] = true;

        while(!queue.isEmpty()) {
            int[] landPosition = (int[])queue.poll();

            for(int p = 0; p < 8; ++p) {
                int newY = landPosition[0] + dy[p];
                int newX = landPosition[1] + dx[p];
                if (newY >= 0 && newY < h && newX >= 0 && newX < w && map[newY][newX] == 1 && !check[newY][newX]) {
                    queue.add(new int[]{newY, newX});
                    check[newY][newX] = true;
                }
            }
        }

    }
}

