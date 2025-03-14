package BaekJoon_11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week6_11559 {

    static final int row = 12, col = 6;
    static char[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static List<int[]> bombList;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[row][col];
        int answer = 0;

        for (int i = 0; i < row; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 다 터지고 중력 작용 시키기
        while (true) {

            visited = new boolean[row][col];
            bombList = new ArrayList<>();

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        checkFour(i, j);
                    }
                }
            }

            if (bombList.isEmpty()) break;

            // 폭파시키기
            bomb();
//            printMap();


            // 중력작용
            gravity();

            answer++;



        }

        System.out.println(answer);

    }

    // 4개 이상 연결되어있는지 확인하면서 폭파 리스트에 추가하기
    static void checkFour(int startRow, int startCol) {
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int curRow = info[0];
            int curCol = info[1];
            cnt++;

            for (int[] d : dir) {
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];
                if (nextRow >= row || nextRow < 0 || nextCol >= col || nextCol < 0) continue;
                if (visited[nextRow][nextCol] || map[nextRow][nextCol] != map[startRow][startCol]) continue;
                queue.add(new int[]{nextRow, nextCol});
                visited[nextRow][nextCol] = true;
            }

        }

        if (cnt >= 4) {
            bombList.add(new int[]{startRow, startCol});
        }

    }

    static void bomb() {
        for (int[] b : bombList) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(b);
            char target = map[b[0]][b[1]];
            while (!queue.isEmpty()) {
                int[] info = queue.poll();
                int curRow = info[0];
                int curCol = info[1];

                for (int[] d : dir) {
                    int nextRow = curRow + d[0];
                    int nextCol = curCol + d[1];

                    if (nextRow >= row || nextRow < 0 || nextCol >= col || nextCol < 0) continue;
                    if (map[nextRow][nextCol] == target){
                        map[nextRow][nextCol] = '.';
                        queue.add(new int[]{nextRow, nextCol});
                    }

                }

            }

        }
    }

    static void gravity() {
        for (int i = 0; i < col; i++) {
            for (int j = row - 1; j >= 0; j--) {
                if (map[j][i] == '.') {
                    int start = j - 1;
                    while (start >= 0 && map[start][i] == '.') start--; // 블록 찾을때까지 현재 열에서 위로 탐색
                    if (start >= 0) {
                        map[j][i] = map[start][i];
                        map[start][i] = '.';
                    }
                }
            }
        }
    }

    static void printMap() {
        for (int i = 0; i < row; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }


}
