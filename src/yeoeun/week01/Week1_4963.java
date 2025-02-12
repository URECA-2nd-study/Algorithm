package yeoeun.week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week1_4963 {

    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static boolean[][] visited;
    static int w, h;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        while(w != 0 && h != 0) { // 0 0 이 입력되면 종료
            // 지도 초기화
            map = new int[w][h];
            visited = new boolean[w][h];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[j][i] = Integer.parseInt(st.nextToken());
                }
            }

            // 섬 개수 세기
            int count = 0;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if(map[i][j] == 1 && !visited[i][j]) {
                        count++;
                        checkAround(i, j);
                    }
                }
            }

            // 정답 입력
            sb.append(count).append("\n");

            // w, h 값 재설정
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1)); // 마지막 공백 제거
    }

    // 현재 좌표 기준으로 주변 확인
    public static void checkAround(int x, int y) {
        // 방문 처리
        visited[x][y] = true;
        int[] xs = {x-1, x-1, x-1, x, x, x+1, x+1, x+1};
        int[] ys = {y-1, y, y+1, y-1, y+1, y-1, y, y+1};

        for (int i = 0; i < 8; i++) { // 주변 8개 칸 확인
            int currentX = xs[i];
            int currentY = ys[i];
            if(currentX >= 0 && currentX < w && currentY >= 0 && currentY < h) { // 인덱스 확인
                if (map[currentX][currentY] == 1 && !visited[currentX][currentY]) { // 미방문 육지 확인
                    checkAround(currentX, currentY); // 미방문에 대해 재귀 호출
                }
            }
        }
    }
}