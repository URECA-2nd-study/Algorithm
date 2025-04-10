package yeoeun.week10;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week10_3192 {
    static int[][] square = new int[3][3];
    static Queue<Coord> blank = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                square[i][j] = Integer.parseInt(st.nextToken());
                if(square[i][j] == 0 && !(i == 1 && j == 1)) blank.offer(new Coord(i, j));
            }
        }

        // 가운데 수 구하기
        if(square[1][1] == 0) fillMid();

        int sum = 3 * square[1][1];
        while(!blank.isEmpty()) {
            Coord now = blank.poll(); // 현재 좌표
            // 가로 확인
            int tmp = 0;
            for (int i = 0; i < 3; i++) {
                if(square[now.x][i] != 0) {
                    tmp += square[now.x][i];
                } else if(i != now.y) { // 다른 0이 있으면
                    tmp = 0;
                    break;
                }
            }
            if(tmp != 0) {
                square[now.x][now.y] = sum - tmp;
                continue;
            }

            // 세로 확인 (tmp == 0)
            for (int i = 0; i < 3; i++) {
                if(square[i][now.y] != 0) {
                    tmp += square[i][now.y];
                } else if(i != now.x) { // 다른 0이 있으면
                    tmp = 0;
                    break;
                }
            }
            if(tmp != 0) {
                square[now.x][now.y] = sum - tmp;
                continue;
            }

            // 대각선 확인 (tmp == 0)
            for (int i = 0; i < 3; i++) {
                if(now.x == 1 || now.y == 1) break; // 대각선 불가 지역
                if(now.x == now.y) { // 좌측 대각선
                    if (square[i][i] != 0) {
                        tmp += square[i][i];
                    } else if (i != now.x) { // 다른 0이 있으면
                        tmp = 0;
                        break;
                    }
                } else { // 우측 대각선
                    if (square[i][2-i] != 0) {
                        tmp += square[i][i];
                    } else if (i != now.x && 2-i != now.y) { // 다른 0이 있으면
                        tmp = 0;
                        break;
                    }
                }
            }
            if(tmp != 0) {
                square[now.x][now.y] = sum - tmp;
                continue;
            }
            // 불가능하면 나중으로 미루기
            blank.offer(now);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bw.write(square[i][j] + " ");
            }
            bw.write("\n");
        }
        br.close();
        bw.close();
    }

    // 최대 O(4)
    public static void fillMid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(square[i][j] != 0 && square[2-i][2-j] != 0) {
                    square[1][1] = (square[i][j] + square[2-i][2-j]) / 2;
                    return;
                }
            }
        }
    }

    public static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
