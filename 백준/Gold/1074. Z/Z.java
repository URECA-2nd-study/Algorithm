import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int findRow;
    static int findCol;
    static int size;

    static int findNum(int length, int curRow, int curCol, int curSum) {
        if (length == 1) {
            return curSum;
        }

        int newLength = length / 2;
        int newSum;

        if (findRow < curRow + newLength && findCol < curCol + newLength) { // Top-Left
            newSum = curSum;
            return findNum(newLength, curRow, curCol, newSum);
        } else if (findRow < curRow + newLength && findCol >= curCol + newLength) { // Top-Right
            newSum = curSum + newLength * newLength;
            return findNum(newLength, curRow, curCol + newLength, newSum);
        } else if (findRow >= curRow + newLength && findCol < curCol + newLength) { // Bottom-Left
            newSum = curSum + 2 * newLength * newLength;
            return findNum(newLength, curRow + newLength, curCol, newSum);
        } else { // Bottom-Right
            newSum = curSum + 3 * newLength * newLength;
            return findNum(newLength, curRow + newLength, curCol + newLength, newSum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = Integer.parseInt(st.nextToken());
        findRow = Integer.parseInt(st.nextToken());
        findCol = Integer.parseInt(st.nextToken());

        int length = (int) Math.pow(2, size);
        System.out.println(findNum(length, 0, 0, 0));
    }
}
