package src.jaehyeon.week10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week10_3192 {
    static public class Sudoku {
        List<int[]> zeroPos;
        int zeroCount;
        int sum;

        public Sudoku(List<int[]> zeroPos, int zeroCount, int sum) {
            this.zeroPos = zeroPos;
            this.zeroCount = zeroCount;
            this.sum = sum;
        }
    }

    static int[][] map = new int[3][3];
    static int wholeSum = 0;

    static PriorityQueue<Sudoku> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.zeroCount));

    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        init();

        sol1();

        if (pq.peek().zeroCount != 0) {
            sol2(wholeSum / 2);
        } else {
            sol2(0);
        }

        for (int[] ints : map) {
            for (int anInt : ints) {
                sb.append(anInt).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }

    private static void sol2(int num) {

        int maxSum = num;
        while (!pq.isEmpty()) {
            Sudoku sudoku = pq.poll();

            if (sudoku.zeroCount == 0) {
                if (maxSum != 0) continue;
                maxSum = sudoku.sum;
                continue;
            }

            if (sudoku.zeroCount == 1) {
                int[] pos = sudoku.zeroPos.get(0);

                if (map[pos[0]][pos[1]] == 0) {
                    map[pos[0]][pos[1]] = maxSum - sudoku.sum;
                }
                continue;
            }

            if (sudoku.zeroCount == 2) {
                int sum = sudoku.sum;
                int y = -1;
                int x = -1;
                for (int[] pos : sudoku.zeroPos) {
                    if (map[pos[0]][pos[1]] != 0) {
                        sum += map[pos[0]][pos[1]];
                    } else if (map[pos[0]][pos[1]] == 0) {
                        y = pos[0];
                        x = pos[1];
                    }
                }
                if (y != -1){
                    map[y][x] = maxSum - sum;
                }
            }
        }
    }

    private static void sol1() {
        int rightDownZeroCount = 0;
        int rightDownSum = 0;
        List<int[]> rightDownZeroPos = new ArrayList<>();

        int leftUpZeroCount = 0;
        int leftUpSum = 0;
        List<int[]> leftUpZeroPos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int rowZeroCount = 0;
            int rowSum = 0;
            List<int[]> rowZeroPos = new ArrayList<>();

            int colZeroCount = 0;
            int colSum = 0;
            List<int[]> colZeroPos = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 0) {
                    rowZeroCount++;
                    rowZeroPos.add(new int[]{i, j});
                }
                rowSum += map[i][j];
                if (map[j][i] == 0) {
                    colZeroCount++;
                    colZeroPos.add(new int[]{j, i});
                }
                colSum += map[j][i];
            }
            pq.offer(new Sudoku(rowZeroPos, rowZeroCount, rowSum));
            pq.offer(new Sudoku(colZeroPos, colZeroCount, colSum));

            if (map[i][i] == 0) {
                rightDownZeroCount++;
                rightDownZeroPos.add(new int[]{i, i});
            }
            rightDownSum += map[i][i];
            if (map[i][2 - i] == 0) {
                leftUpZeroCount++;
                leftUpZeroPos.add(new int[]{i, 2 - i});
            }
            leftUpSum += map[i][2 - i];
        }
        pq.offer(new Sudoku(rightDownZeroPos, rightDownZeroCount, rightDownSum));
        pq.offer(new Sudoku(leftUpZeroPos, leftUpZeroCount, leftUpSum));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        for (int y = 0; y < 3; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < 3; x++) {
                int input = Integer.parseInt(st.nextToken());
                map[y][x] = input;
                wholeSum += input;
            }
        }
    }
}
