import java.io.*;
import java.util.*;

public class Week10_3192 {


    static int[][] board = new int[3][3];
    static int totalSum = 0;
    static PriorityQueue<LineInfo> lines = new PriorityQueue<>(Comparator.comparingInt(o -> o.zeroCount));

    public static void main(String[] args) throws IOException {
        initBoard();

        analyzeLines();

        if (lines.peek().zeroCount != 0) {
            fillZeros(totalSum / 2); // 중심값이 없는 경우, 전체 합 / 2로 추정
        } else {
            fillZeros(0); // 중심값이 있는 경우, 해당 라인의 합으로 기준 결정
        }

        printBoard();
    }

    private static void initBoard() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int y = 0; y < 3; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < 3; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
                totalSum += board[y][x];
            }
        }
    }

    private static void analyzeLines() {
        int rightDownZeros = 0;
        int rightDownSum = 0;
        List<int[]> rightDownPos = new ArrayList<>();

        int leftUpZeros = 0;
        int leftUpSum = 0;
        List<int[]> leftUpPos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            int rowZeros = 0;
            int rowSum = 0;
            List<int[]> rowPos = new ArrayList<>();

            int colZeros = 0;
            int colSum = 0;
            List<int[]> colPos = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                // 가로
                if (board[i][j] == 0) {
                    rowZeros++;
                    rowPos.add(new int[]{i, j});
                }
                rowSum += board[i][j];

                // 세로
                if (board[j][i] == 0) {
                    colZeros++;
                    colPos.add(new int[]{j, i});
                }
                colSum += board[j][i];
            }

            lines.offer(new LineInfo(rowPos, rowZeros, rowSum));
            lines.offer(new LineInfo(colPos, colZeros, colSum));

            // 대각선 (오른쪽 아래)
            if (board[i][i] == 0) {
                rightDownZeros++;
                rightDownPos.add(new int[]{i, i});
            }
            rightDownSum += board[i][i];

            // 대각선 (왼쪽 아래)
            if (board[i][2 - i] == 0) {
                leftUpZeros++;
                leftUpPos.add(new int[]{i, 2 - i});
            }
            leftUpSum += board[i][2 - i];
        }

        lines.offer(new LineInfo(rightDownPos, rightDownZeros, rightDownSum));
        lines.offer(new LineInfo(leftUpPos, leftUpZeros, leftUpSum));
    }

    private static void fillZeros(int defaultSum) {
        int targetSum = defaultSum;

        while (!lines.isEmpty()) {
            LineInfo line = lines.poll();

            if (line.zeroCount == 0) {
                if (targetSum == 0) {
                    targetSum = line.sum;
                }
                continue;
            }

            if (line.zeroCount == 1) {
                int[] pos = line.zeroPositions.get(0);
                if (board[pos[0]][pos[1]] == 0) {
                    board[pos[0]][pos[1]] = targetSum - line.sum;
                }
            }

            if (line.zeroCount == 2) {
                int knownSum = line.sum;
                int unknownY = -1;
                int unknownX = -1;

                for (int[] pos : line.zeroPositions) {
                    if (board[pos[0]][pos[1]] != 0) {
                        knownSum += board[pos[0]][pos[1]];
                    } else {
                        unknownY = pos[0];
                        unknownX = pos[1];
                    }
                }

                if (unknownY != -1) {
                    board[unknownY][unknownX] = targetSum - knownSum;
                }
            }
        }
    }

    private static void printBoard() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int[] row : board) {
            for (int value : row) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class LineInfo {
    List<int[]> zeroPositions;
    int zeroCount;
    int sum;

    LineInfo(List<int[]> zeroPositions, int zeroCount, int sum) {
        this.zeroPositions = zeroPositions;
        this.zeroCount = zeroCount;
        this.sum = sum;
    }
}