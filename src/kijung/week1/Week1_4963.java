package kijung.week1;

import java.io.*;
import java.util.*;

public class Week1_4963 {

    static int row, col;
    static int[][] map, dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}}; // 방항배열
    static boolean[][] visited;
    static List<int[]> posList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());
            if (row == 0 && col == 0) break;

            map = new int[row][col];
            visited = new boolean[row][col];
            posList = new ArrayList<>(); // 섬 좌표 저장

            for (int i = 0; i < row; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < col; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    map[i][j] = value;
                    if (value > 0) posList.add(new int[]{i, j});
                }
            }

            bw.write(find() + "\n");

        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int find() {
        int count = 0;
        for (int[] pos : posList) {
            int curR = pos[0];
            int curC = pos[1];
            if (visited[curR][curC]) continue; // 방문했던 좌표라면 생략
            count++;
            search(curR, curC);
        }

        return count;
    }

    static void search(int curR, int curC) { // bfs로 방문 처리
        Queue<int[]> queue = new LinkedList<>();
        visited[curR][curC] = true;
        queue.add(new int[]{curR, curC});

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            for (int[] d : dir) {
                int nextR = pos[0] + d[0]; // 다음 탐색할 곳
                int nextC = pos[1] + d[1];
                if (nextR >= row || nextR < 0 || nextC >= col || nextC < 0) continue;
                if (visited[nextR][nextC] || map[nextR][nextC] == 0) continue;
                visited[nextR][nextC] = true;
                queue.add(new int[]{nextR, nextC});
            }
        }
    }
}
