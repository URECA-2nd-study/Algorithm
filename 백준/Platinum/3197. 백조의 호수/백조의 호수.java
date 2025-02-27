import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int row, col;
    static char[][] map;
    static int[][] swans = new int[2][2];
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Queue<int[]> swanQ = new LinkedList<>();
    static Queue<int[]> meltQ = new LinkedList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        visited = new boolean[row][col];
        int idx = 0;

        for (int i = 0; i < row; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 'L') {
                    swans[idx++] = new int[]{i, j};
                    map[i][j] = '.';
                }
                if (map[i][j] == '.') meltQ.add(new int[]{i, j});
            }
        }

        int day = 0;
        visited[swans[0][0]][swans[0][1]] = true;
        swanQ.add(new int[]{swans[0][0], swans[0][1]});

        while (!canMeet()) {
            melt();
            day++;
        }

//        printMap();
        System.out.println(day);

    }

    static void printMap() {
        for (int i = 0; i < row; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
    static void melt() {

        int len = meltQ.size();

        for (int i = 0; i < len; i++) {
            int[] melt = meltQ.poll();
            int curRow = melt[0];
            int curCol = melt[1];

            for (int[] d : dir) {
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];

                if (nextRow >= row || nextRow < 0 || nextCol >= col || nextCol < 0) continue;
                if (map[nextRow][nextCol] == 'X') {
                    map[nextRow][nextCol] = '.';
                    meltQ.add(new int[]{nextRow, nextCol});
                }

            }

        }

    }

    static boolean canMeet() {
        Queue<int[]> temp = new LinkedList<>();

        while (!swanQ.isEmpty()) {
            int[] swan = swanQ.poll();
            int curRow = swan[0];
            int curCol = swan[1];
            if (curRow == swans[1][0] && curCol == swans[1][1]) return true;

            for (int[] d : dir) {
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];

                if (nextRow >= row || nextRow < 0 || nextCol >= col || nextCol < 0) continue;
                if (visited[nextRow][nextCol]) continue;
                visited[nextRow][nextCol] = true;

                if (map[nextRow][nextCol] == 'X') temp.add(new int[]{nextRow, nextCol});
                else swanQ.add(new int[]{nextRow, nextCol});

            }

        }

        swanQ = temp;

        return false;

    }


}
