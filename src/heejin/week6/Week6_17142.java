package heejin.week6;

import java.io.*;
import java.util.*;

public class Week6_17142 {

    static int N, M, zeroCnt, min = Integer.MAX_VALUE;
    static int[][] map, active;
    static List<int[]> virus;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        virus = new ArrayList<>();
        active = new int[M][2];
        map = new int[N][N];

        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) virus.add(new int[]{i, j});
                else if(map[i][j] == 0) zeroCnt++;
            }
        }

        // 빈 칸이 없는 경우
        if(zeroCnt == 0){
            System.out.print(0);
            return;
        }

        dfs(0,0);

        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }

    // 활성 상태 모든 경우의 수 탐색
    static void dfs(int start, int activeCnt) {
        if (activeCnt == M) {
            min = Math.min(min, bfs());
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            int[] cur = virus.get(i);

            // 활성화된 바이러스 저장
            active[activeCnt] = cur;

            dfs(i + 1, activeCnt + 1);
        }
    }


    static int bfs() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        int cnt = 0, tempTime = 0;

        // 활성화된 바이러스 넣기
        for(int[] start : active){
            queue.add(new int[]{start[0], start[1], 0});
            visited[start[0]][start[1]] = true;
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dir[d][0];
                int ny = cur[1] + dir[d][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visited[nx][ny] || map[nx][ny] == 1) continue;

                // 비활성화 바이러스 or 빈 칸
                queue.add(new int[]{nx, ny, cur[2] + 1});
                visited[nx][ny] = true;

                if(map[nx][ny] == 0){
                    cnt++;
                    tempTime = Math.max(tempTime, cur[2] + 1);
                }
            }
        }

        if(cnt != zeroCnt) return Integer.MAX_VALUE;

        return tempTime;
    }
}
