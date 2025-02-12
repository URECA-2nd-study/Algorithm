package yeoeun.week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week1_2573 {
    static int[][] iceSea;
    static boolean[][] isMelted;
    static boolean[][] visited;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        iceSea = new int[N][M];
        isMelted = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(st.nextToken());

                iceSea[i][j] = temp;
                if(temp == 0) { // 이미 물인 경우
                    isMelted[i][j] = true;
                }
            }
        }

        int count = 0;
        while(true) {
            // 현재 상태 확인 (빙산 개수 확인)
            int iceCount = countIce();
            if(iceCount == 0) { // 마지막까지 나눠지지 X
                count = 0;
                break;
            } else if (iceCount == 1) { // 아직 나눠지지 X
                melt(); // 다시 녹이기
                updateMelted(); // 현 상태 업데이트
                count++; // 1년 증가
            } else { // iceCount == 2 : 2개 이상인 경우
                break; // 나누어졌으므로 break;
            }
        }
        System.out.println(count);
    }

    // 빙산 개수 확인 (2개 이상은 모두 2로 반환)
    private static int countIce() {
        visited = new boolean[N][M];  // 검사마다 새로운 visited 사용 (초기화)
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(iceSea[i][j] != 0 && !visited[i][j]) {
                    checkConnected(i, j);
                    // ice가 2개 이상이면 메소드 종료
                    if(count == 1) return 2;
                    else count++;
                }
            }
        }
        return count;
    }

    // dfs : 연결된 모든 칸을 조회 & visited 갱신
    private static void checkConnected(int x, int y) {
        int[] xs = {-1, 1, 0, 0};
        int[] ys = {0, 0, -1, 1};

        if(iceSea[x][y] != 0 && !visited[x][y]) { // 얼음 존재 & 미방문
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) { // 동서남북에 대해
                if(x + xs[i] >= 0 && x + xs[i] < N && y + ys[i] >= 0 && y + ys[i] < M) { // 인덱스 확인
                    checkConnected(x + xs[i], y + ys[i]);
                }
            }
        }
    }

    // 빙하 녹이기 (+1년)
    public static void melt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(iceSea[i][j] != 0) { // 얼음들에 대해서만
                    int amount = checkAround(i, j);
                    iceSea[i][j] = Math.max(iceSea[i][j] - amount, 0);
                }
            }
        }
    }

    // 주변의 바다 수를 count
    public static int checkAround(int x, int y) {
        int[] xs = {-1, 1, 0, 0};
        int[] ys = {0, 0, -1, 1};

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if(x + xs[i] >= 0 && x + xs[i] < N && y + ys[i] >= 0 && y + ys[i] < M) {
                if(isMelted[x+xs[i]][y+ys[i]]) { // 주변이 녹았는지 확인
                    count++;
                }
            }
        }
        return count;
    }

    // isMelted값을 업데이트 (iceSea의 값업데이트로 인한 오류 방지)
    public static void updateMelted() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(iceSea[i][j] == 0 && !isMelted[i][j]) {
                    isMelted[i][j] = true;
                }
            }
        }
    }
}