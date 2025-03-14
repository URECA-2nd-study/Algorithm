import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Week6_17142 {

    // -1은 벽으로 표기, 2는 바이러스, 0은 빈 칸
    static int[][] map, dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int N, M, emptyCount = 0;
    static List<int[]> virusList = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
                if (value == 2) {
                    virusList.add(new int[]{i, j}); // 바이러스 후보 리스트에 추가
                } else if (value == 0) {
                    emptyCount++; // 빈 칸 개수 세기
                }
            }
        }

        // 빈 칸이 없는 경우, 이미 모든 칸이 바이러스거나 벽이므로 0 출력
        if (emptyCount == 0) {
            System.out.println(0);
            return;
        }

        // M개의 바이러스 선택하기 (조합)
        int[] selected = new int[M];
        combination(0, 0, selected);

        // 불가능한 경우
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    // 바이러스 M개 조합 생성
    static void combination(int start, int depth, int[] selected) {
        if (depth == M) {
            // 선택된 바이러스로 BFS 실행
            bfs(selected);
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            selected[depth] = i;
            combination(i + 1, depth + 1, selected);
        }
    }

    // BFS로 바이러스 퍼뜨리기
    static void bfs(int[] selected) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[N][N];

        // 방문 배열 초기화 (-1: 미방문)
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }

        // 선택된 활성 바이러스 큐에 넣기
        for (int i = 0; i < M; i++) {
            int[] virus = virusList.get(selected[i]);
            queue.add(new int[]{virus[0], virus[1]});
            visited[virus[0]][virus[1]] = 0; // 시작점은 0초
        }

        int spreadCount = 0; // 퍼진 빈 칸 수
        int maxTime = 0; // 최대 시간

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];

                // 범위 체크
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                // 벽이거나 이미 방문한 곳은 제외
                if (map[nr][nc] == 1 || visited[nr][nc] != -1) continue;

                // 빈 칸인 경우
                if (map[nr][nc] == 0) {
                    spreadCount++;
                    maxTime = Math.max(maxTime, visited[r][c] + 1);
                }

                // 다음 위치 방문 처리
                visited[nr][nc] = visited[r][c] + 1;
                queue.add(new int[]{nr, nc});
            }
        }

        // 모든 빈 칸이 바이러스에 감염되었는지 확인
        if (spreadCount == emptyCount) {
            answer = Math.min(answer, maxTime);
        }
    }
}