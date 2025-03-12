package heejin.week6;

import java.util.*;
import java.io.*;

public class Week6_11559 {

    static int N = 12, M = 6;
    static int cnt;
    static char[][] map;
    static List<int[]> removed;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<int[]> queue = new LinkedList<>();
    static boolean[][] visited = new boolean[N][M];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        while (startRound()) {
            // 한 줄씩 뿌요 아래로 떨어짐
            for (int j = 0; j < M; j++) {
                down(j);
            }

            cnt++;
            visited = new boolean[N][M];
        }

        System.out.print(cnt);
    }

    static boolean startRound() {
        boolean flag = false; // 이번 라운드에서 뿌요가 터졌는지

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != '.' && !visited[i][j]) {

                    removed = new ArrayList<>();
                    int tempCnt = bfs(i, j);

                    if (tempCnt >= 4) {
                        flag = true;

                        // 제거된 뿌요 다 빈 칸으로 만들기
                        for(int[] cur : removed){
                            map[cur[0]][cur[1]] = '.';
                        }
                    }
                }
            }
        }
        return flag;
    }

    static int bfs(int sx, int sy) {
        int cnt = 1;
        queue.add(new int[]{sx, sy});
        visited[sx][sy] = true;
        removed.add(new int[]{sx, sy});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dir[d][0];
                int ny = cur[1] + dir[d][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny] || map[nx][ny] != map[cur[0]][cur[1]]) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
                removed.add(new int[]{nx, ny});
                cnt++;
            }
        }
        return cnt;
    }

    static void down(int col) {
        for (int toRow = N - 1; toRow > 0; toRow--) {
            if (map[toRow][col] == '.') {
                int fromRow = toRow - 1;

                // 빈 칸 아닌 시점까지 위로 이동
                while(fromRow > 0 && map[fromRow][col] == '.'){
                    fromRow--;
                }

                map[toRow][col] = map[fromRow][col];
                map[fromRow][col] = '.';
            }
        }
    }
}
