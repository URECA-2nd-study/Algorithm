import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] map = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = 0; j < 9; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        solve(0, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static boolean solve(int row, int col) {
        // 목적지인가
        if (row == 9) return true;

        int nextR = row, nextC = col;
        if (col + 1 == 9) {
            nextR++;
            nextC = 0;
        } else {
            nextC++;
        }

        if (map[row][col] != 0) return solve(nextR, nextC);

        for (int i = 1; i <= 9; i++) {
            if (find(row, col, i)) {
                map[row][col] = i;
                if (solve(nextR, nextC)) return true;
                map[row][col] = 0;
            }
        }

        return false;

    }



    static boolean find (int row, int col, int num) {

        for (int i = 0; i < 9; i++) {
            if (map[row][i] == num || map[i][col] == num) return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (map[i][j] == num) return false;
            }
        }

        return true;
    }
}
