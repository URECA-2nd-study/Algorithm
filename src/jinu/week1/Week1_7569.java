package jinu.week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week1_7569 {
    static int[][][] tomatoes;
    static int N;
    static int M;
    static int H;
    static int DAY_OF_FINAL_DAY;
    static int[] dy = new int[]{0, 0, 0, 0, 1, -1};
    static int[] dx = new int[]{0, 0, -1, 1, 0, 0};
    static int[] dz = new int[]{1, -1, 0, 0, 0, 0};
    static final int DIRECTION_SIZE = 6;

    public Week1_7569() {
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        DAY_OF_FINAL_DAY = N * M * H;
        tomatoes = new int[N][M][H];

        for(int k = 0; k < H; ++k) {
            for(int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < M; ++j) {
                    tomatoes[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        BFS();
        System.out.println(getDayOfAllRipe());
    }

    public static void BFS() {
        Queue<int[]> queue = new LinkedList();

        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                for(int k = 0; k < H; ++k) {
                    if (tomatoes[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k, 1});
                    }
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] position = (int[])queue.poll();

            for(int i = 0; i < 6; ++i) {
                int newY = position[0] + dy[i];
                int newX = position[1] + dx[i];
                int newZ = position[2] + dz[i];
                int day = position[3];
                if (newY >= 0 && newY < N && newX >= 0 && newX < M && newZ >= 0 && newZ < H && tomatoes[newY][newX][newZ] == 0) {
                    tomatoes[newY][newX][newZ] = day + 1;
                    queue.add(new int[]{newY, newX, newZ, day + 1});
                }
            }
        }

    }

    public static int getDayOfAllRipe() {
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                for(int k = 0; k < H; ++k) {
                    if (max < tomatoes[i][j][k]) {
                        max = tomatoes[i][j][k];
                    }

                    if (tomatoes[i][j][k] == 0) {
                        return -1;
                    }
                }
            }
        }

        return max - 1;
    }
}
