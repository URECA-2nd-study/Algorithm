package src.jaehyeon.Week6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Week6_17142 {

    static class Virus {
        int x, y, time, virusBridge;

        public Virus(int y, int x, int time, int virusBridge) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.virusBridge = virusBridge;
        }
    }

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    static final char VIRUS = '*';
    static final char WALL = '-';
    static final char EMPTY = '.';

    static int N, M, answer = Integer.MAX_VALUE;
    static char[][] map;
    static List<Virus> virus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {

                int input = Integer.parseInt(st.nextToken());

                switch (input) {
                    case 0:
                        map[y][x] = EMPTY;
                        break;
                    case 1:
                        map[y][x] = WALL;
                        break;
                    case 2:
                        map[y][x] = VIRUS;
                        virus.add(new Virus(y, x, 0, 0));
                        break;
                }

            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (selectActivatingVirus()) {
            bw.write("-1");
        } else {
            bw.write(answer + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean selectActivatingVirus() {

        ArrayDeque<Virus> stack = new ArrayDeque<>();
        combination(stack, 0);

        return answer == Integer.MAX_VALUE;
    }

    private static void combination(ArrayDeque<Virus> stack, int start) {

        if (stack.size() == M) {
            bfs(stack.clone());
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            stack.push(virus.get(i));
            combination(stack, i + 1);
            stack.pop();
        }
    }

    private static void bfs(ArrayDeque<Virus> queue) {
        boolean[][] visited = new boolean[N][N];

        char[][] clone = new char[N][N];

        for (int i = 0; i < N; i++) {
            clone[i] = map[i].clone();
        }

        for (Virus v : queue) {
            int y = v.y;
            int x = v.x;

            visited[y][x] = true;
        }
//        System.out.println("일 때 최소 시간");
        int virusMoveCount = 0;
        while (!queue.isEmpty()) {
            Virus v = queue.poll();
            int y = v.y;
            int x = v.x;
            int time = v.time;
            int virusBridge = v.virusBridge;
            boolean deactivateVirusIsMovedFlag = false;

            for (int i = 0; i < 4; i++) {
                int py = y + dy[i];
                int px = x + dx[i];

                //연구소 밖, 중복 방문, 벽 예외 처리
                if (isOutOfRange(py, px) || visited[py][px] || map[py][px] == WALL) continue;

                deactivateVirusIsMovedFlag = true;//더이상 나아갈 수 없으면 false로 유지됨
                clone[py][px] = 'X';
                if (map[py][px] == VIRUS) {
                    queue.offer(new Virus(py, px, time + 1, virusBridge + 1));
                } else {
                    queue.offer(new Virus(py, px, time + 1, 0));
                }
                visited[py][px] = true;
            }//for
            if (!deactivateVirusIsMovedFlag && map[y][x] == VIRUS) {
                time -= virusBridge;
                virusMoveCount = Math.max(virusMoveCount, time);
            } else if (!deactivateVirusIsMovedFlag && map[y][x] == EMPTY) {
                virusMoveCount = Math.max(virusMoveCount, time);
            }
        }//while

        virusMoveCount = isSpreadAll(clone, virusMoveCount);

//        System.out.println("최소 시간 : " + virusMoveCount);

        answer = Math.min(virusMoveCount, answer);
    }

    private static int isSpreadAll(char[][] clone, int virusMoveCount) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
//                System.out.print(clone[i][j] + " ");
                if (clone[i][j] == '.') {
//                    System.out.println("다 못채워서 최소시간 연산 X");
                    virusMoveCount = Integer.MAX_VALUE;
                    return virusMoveCount;
                }
            }
        }
        return virusMoveCount;
    }

    private static boolean isOutOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }
}
