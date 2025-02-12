package kijung.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week1_7569 {
    static int row, col, height, totalTomato, curTomato = 0;
    static int[][][] map;
    static int[][] dir = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};
    static Queue<int[]> tomatoes = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        map = new int[height][row][col];
        totalTomato = row * col * height; // 총 토마토 개수

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < row; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < col; k++) {
                    int value = Integer.parseInt(st.nextToken());
                    map[i][j][k] = value;
                    if (value == -1) totalTomato--;
                    else if (value == 1) tomatoes.add(new int[]{i, j, k});
                }
            }
        }

        System.out.println(start());

    }

    static int start() {
        int year = 0;

        while (!tomatoes.isEmpty()) {
            int size = tomatoes.size();
            boolean isChanged = false;

            for (int i = 0; i < size; i++) { // 큐에 있던 토마토 개수만큼 반복
                int[] info = tomatoes.poll();
                int curH = info[0];
                int curR = info[1];
                int curC = info[2];
                curTomato++; // 익은 토마토 수 증가
                for (int[] d : dir) {
                    int nextH = curH + d[0];
                    int nextR = curR + d[1];
                    int nextC = curC + d[2];

                    if (check(nextH, nextR, nextC)) {
                        map[nextH][nextR][nextC] = 1;
                        tomatoes.add(new int[]{nextH, nextR, nextC});
                        isChanged = true;
                    }
                }

            }


            if (isChanged) year++;

        }

        if (totalTomato == curTomato) return year; // 총 익은 토마토 수와 전체 토마토 수 같으면 year 반환
        else return -1;
    }

    static boolean check(int h, int r, int c) {
        if (h >= height || h < 0 || r >= row || r < 0 || c >= col || c < 0) return false;
        return map[h][r][c] == 0;
    }
}
