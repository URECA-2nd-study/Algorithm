package yeoeun.week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week6_17142 {
    static int N, M;
    static final int INF = Integer.MAX_VALUE;
    static int answer = INF;
    static int totalBlank;

    static Coord[] selectedVirus;
    static boolean[] selected;
    static int[][] map;
    static List<Coord> virus = new ArrayList<>();

    static int[] xs = {0, 0, 1, -1};
    static int[] ys = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        selectedVirus = new Coord[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if(num == 2) virus.add(new Coord(i, j));
                else if(num == 0) totalBlank++;
            }
        }

        // 바이러스 선택 -> bfs돌면서 최소 구하기
        if(totalBlank == 0) {
            answer = 0;
        } else {
            selected = new boolean[virus.size()];
            selectVirus(0, 0);
        }
        System.out.println(answer == INF ? -1 : answer);
    }

    // 백트래킹 : 활성화할 바이러스 선택
    private static void selectVirus(int idx, int depth) {
        if(depth == M) {
            int min = bfs(); // 확산 시간 계산
            answer = Math.min(min, answer);
            return;
        }

        for (int i = idx; i < virus.size(); i++) {
            if(!selected[i]) {
                selected[i] = true;

                selectedVirus[depth] = virus.get(i);
                selectVirus(i+1, depth+1);

                selected[i] = false;
            }
        }
    }

    // 확산 시간 계산
    private static int bfs() {
        boolean[][] visited = new boolean[N][N];
        Queue<Coord> queue = new LinkedList<>();
        for (Coord c : selectedVirus) {
            queue.offer(c);
            visited[c.x][c.y] = true;
        }

        int time = 0;
        int countBlank = 0; // 확산된 "0"개수 확인
        int qCount = M;
        while(!queue.isEmpty()) {
            Coord now = queue.poll();
            qCount--;

            for (int i = 0; i < 4; i++) {
                int curX = now.x + xs[i];
                int curY = now.y + ys[i];

                if(checkArrange(curX, curY) && !visited[curX][curY] && map[curX][curY] != 1) {
                    if(map[curX][curY] == 0) countBlank++;
                    visited[curX][curY] = true;
                    queue.offer(new Coord(curX, curY));
                }
            }

            if(qCount == 0 && !queue.isEmpty()) {
                qCount = queue.size();
                time++;
                if(countBlank == totalBlank) return time; // 전체 확산인 경우
            }
        }
        return INF; // 전체 확산되지 못한 경우
    }

    private static boolean checkArrange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
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
