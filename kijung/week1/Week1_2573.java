import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week1_2573 {

    static int[][] map, dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int row, col;
    static Set<Pos> posSet = new HashSet<>();
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                int height = Integer.parseInt(st.nextToken());
                map[i][j] = height;
                // 1.pos 리스트를 만들기
                if (height != 0) {
                    posSet.add(new Pos(i, j, height));
                }
            }
        }

        int year = 0;
        while (true) {
            if (posSet.isEmpty()) { // pos set이 비어있을 경우 0 출력
                year = 0;
                break;
            }

            // 2.pos 리스트에서 시작점을 통해 DFS를 통해 탐색한 좌표수와 pos 리스트 길이 비교. (다를 경우 두덩이 이상인 경우임)
            Pos first = posSet.iterator().next();
            if (dfs(first) != posSet.size()) {
                break;
            }

            // 3.pos 리스트 탐색하면서 높이 바꿔줌(다 탐색후 0으로 바꿔줘야 함).
            for (Pos pos : posSet) {
                pos.height -= findZero(pos);
            }

            // 높이가 0이하가 되면 제거 및 맵에 반영
            remove();

            year++;
        }

        System.out.println(year);
    }



    // 상하좌우 0이 저장된 개수 반환.
    static int findZero(Pos pos) {
        int curRow = pos.row;
        int curCol = pos.col;
        int count = 0;

        for (int[] d : dir) {
            int nextRow = curRow + d[0];
            int nextCol = curCol + d[1];
            if (nextRow >= row || nextRow < 0 || nextCol >= col || nextCol < 0) continue;
            if (map[nextRow][nextCol] == 0) count++;
        }

        return count;
    }

    // DFS
    static int dfs(Pos pos) {
        int count = 0;
        visited = new boolean[row][col];
        Stack<int[]> stack = new Stack<>();
        visited[pos.row][pos.col] = true;
        stack.push(new int[]{pos.row, pos.col});

        while (!stack.isEmpty()) {
            count++;
            int curRow = stack.peek()[0];
            int curCol = stack.peek()[1];
            stack.pop();

            for (int[] d : dir) {
                int nextRow = curRow + d[0];
                int nextCol = curCol + d[1];
                if (nextRow >= row || nextRow < 0 || nextCol >= col || nextCol < 0) continue;
                if (visited[nextRow][nextCol] || map[nextRow][nextCol] == 0) continue;
                visited[nextRow][nextCol] = true;
                stack.push(new int[]{nextRow, nextCol});
            }
        }

        return count;
    }

    // 높이가 0이하가 되면 제거 및 맵에 반영
    static void remove() {
        Iterator<Pos> iterator = posSet.iterator();
        while (iterator.hasNext()) {
            Pos pos = iterator.next();
            if (pos.height <= 0) {
                map[pos.row][pos.col] = 0;
                iterator.remove(); // 제거
            }
        }
    }
}


class Pos {
    int row;
    int col;
    int height;

    public Pos(int row, int col, int height) {
        this.row = row;
        this.col = col;
        this.height = height;
    }
}