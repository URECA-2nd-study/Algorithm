package src.jaehyeon.Week6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Week6_11559 {

    static final int HEIGHT = 12;
    static final int WIDTH = 6;

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[HEIGHT][WIDTH];

        for (int y = 0; y < HEIGHT; y++) {
            String s = br.readLine();
            map[y] = s.toCharArray();
        }

        int answer = 0;

        while (true) {
            visited = new boolean[HEIGHT][WIDTH];
            if (isAniPang()) {
//                System.out.println("애니팡");
//                for (char[] chars : map) {
//                    for (char aChar : chars) {
//                        System.out.print(aChar + " ");
//                    }
//                    System.out.println();
//                }

                answer++;

                gravity();
//                System.out.println("중력");
//                for (char[] chars : map) {
//                    for (char aChar : chars) {
//                        System.out.print(aChar + " ");
//                    }
//                    System.out.println();
//                }

            } else break;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();

    }

    private static boolean isAniPang() {
        boolean combo = false;

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (map[y][x] != '.' && !visited[y][x]) {
                    if (dfs(y, x)) combo = true;
                }
            }
        }

        return combo;
    }

    private static boolean dfs(int y, int x) {
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        List<int[]> sameBlockList = new ArrayList<>();

        stack.push(new int[]{y, x});//dfs를 위한 Stack
        sameBlockList.add(new int[]{y, x});//동일 색깔 좌표를 담는 List
        visited[y][x] = true;//중복 탐색 방지를 위한 Array
        char selfColor = map[y][x];//현재 탐색중인 색깔을 담는 변수

        while (!stack.isEmpty()) {
            int[] arr = stack.pop();

            for (int i = 0; i < 4; i++) {

                int py = arr[0] + dy[i];
                int px = arr[1] + dx[i];

                if (isOutOfRange(py, px) || visited[py][px]) continue;
                if (map[py][px] == selfColor) {

                    sameBlockList.add(new int[]{py, px});
                    visited[py][px] = true;
                    stack.push(new int[]{py, px});
                }
            }
        }

        if (sameBlockList.size() < 4) {
            //자기 자신과 인접한 동일 색상의 블럭의 갯수가 4개가 안될 경우
            //애니팡 할 수 없다
            return false;
        }

        doAniPang(sameBlockList);//동일한 블럭 애니팡 시키기

        return true;
    }

    private static void doAniPang(List<int[]> aniPangList) {
        for (int[] arr : aniPangList) {
            int y = arr[0];
            int x = arr[1];

            map[y][x] = '.';
        }
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= HEIGHT || x >= WIDTH;
    }

    private static void gravity() {
        //첫 열의 최하단부터 탐색을 시작
        //'.'일 경우 떨어질 수 있는 높이를 카운트
        //블럭이 나온 경우 현재 떨어질 수 있는 높이 만큼 떨어진다
        //떨어질 수 있는 높이가 0인 경우 해당 블럭을 떨어지지 않는다

        for (int x = 0; x < WIDTH; x++) {
            int fallableHeight = 0;
            for (int y = HEIGHT - 1; y >= 0; y--) {
                if (map[y][x] == '.') fallableHeight++;
                else if (map[y][x] != '.' && fallableHeight > 0) {
                    map[y + fallableHeight][x] = map[y][x];
                    map[y][x] = '.';
                }
            }
        }
    }
}
