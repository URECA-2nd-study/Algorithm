package yeoeun.week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Week6_11559 {
    static boolean[][] visited;
    static char[][] map = new char[12][6];
    static int[] xs = {0, 0, 1, -1};
    static int[] ys = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정보 입력받기
        for (int i = 0; i < 12; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        boolean flag = true; // 뿌요가 터졌는지 여부확인
        int cascadeCount = 0; // 연쇄 횟수 (정답)

        while(flag) { // 마지막에 터지지 않았다면 더 이상 계산 X
            flag = popPuyo(); // 터트리기 수행

            if(flag) { // 터진 경우
                puyoDown(); // 다음 검사를 위한 준비
                cascadeCount++; // 연쇄 수 증가
            }
        }
        System.out.println(cascadeCount);
    }

    // 터지면 true 반환
    public static boolean popPuyo() {
        visited = new boolean[12][6];
        boolean isPopped = false;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if(map[i][j] != '.' && !visited[i][j]) {
                    // bfs로 터트리기
                    boolean popFlag = bfs(new Coord(i, j));
                    if(popFlag) isPopped = true;
                }
            }
        }
        return isPopped;
    }

    public static boolean bfs(Coord start) {
        Queue<Coord> queue = new LinkedList<>();
        List<Coord> popped = new ArrayList<>(); // 터짐 후보 뿌요들 관리

        queue.offer(start);
        popped.add(start);
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()) {
            Coord now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int curX = now.x + xs[i];
                int curY = now.y + ys[i];

                if(checkArrange(curX, curY) && !visited[curX][curY] && map[now.x][now.y] == map[curX][curY]) {
                    visited[curX][curY] = true;
                    queue.offer(new Coord(curX, curY));
                    popped.add(new Coord(curX, curY));
                }
            }
        }

        if(popped.size() < 4) { // 4개 미만이면 X터짐
            return false;
        } else { // 4 이상이면 pop
            for (Coord c : popped) {
                map[c.x][c.y] = '.';
            }
            return true;
        }
    }

    public static void puyoDown() {
        for (int i = 0; i < 6; i++) { // 세로줄을 기준으로 실행
            for (int j = 11; j >= 0; j--) { // 아래부터 탐색
                boolean hasUpPuyo = true;
                if(map[j][i] == '.') { // 아래에 빈칸이 있으면
                    for (int k = j-1; k >= 0 ; k--) { // 올라가면서 탐색
                        if(map[k][i] != '.') { // 빈칸 아닌 뿌요 있으면 자리 바꾸기
                            map[j][i] = map[k][i];
                            map[k][i] = '.';
                            break;
                        } else if(k == 0) {
                            // 전체 돌고도 뿌요 없으면 윗부분 확인은 불필요
                            hasUpPuyo = false;
                        }
                    } // for
                }
                // 위에 뿌요 없으면 다음 세로줄로 넘어가기
                if(!hasUpPuyo) break;
            }
        }
    }

    public static boolean checkArrange(int x, int y) {
        return 0 <= x && x < 12 && 0 <= y && y < 6;
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
