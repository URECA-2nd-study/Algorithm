import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        int day = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][N];
            boolean isMoved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    if (bfs(i, j)) isMoved = true;
                }
            }

            if (!isMoved) break;
            day++;
        }

        System.out.println(day);
    }

    static boolean bfs(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<int[]> area = new ArrayList<>();
        queue.add(new int[]{row, col});
        int sum = 0;
        boolean check = false;
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int curRow = queue.peek()[0];
            int curCol = queue.peek()[1];
            queue.poll();
            area.add(new int[]{curRow, curCol});
            sum += map[curRow][curCol];

            for (int i = 0; i < 4; i++) {
                int nextRow = curRow + dir[i][0];
                int nextCol = curCol + dir[i][1];
                if (nextRow >= N || nextRow < 0 || nextCol >= N || nextCol < 0) continue;
                if (visited[nextRow][nextCol]) continue;
                if (Math.abs(map[nextRow][nextCol] - map[curRow][curCol]) >= L && Math.abs(map[nextRow][nextCol] - map[curRow][curCol]) <= R) {
                    queue.add(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                    check = true;
                }
            }
        }

        int changeNum = sum / area.size();
        for (int[] pos : area) {
            map[pos[0]][pos[1]] = changeNum;
        }

        return check;
    }
}
