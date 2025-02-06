import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Week1_7569 {
    static int[][][] box;
    static int M, N, H;
    static Queue<Tomato> matured = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];

        // 미숙성(0)이 있는지 여부를 확인하기 위한 flag
        boolean isAllMatured = true;
        // box 초기값 설정
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    int tomato = Integer.parseInt(st.nextToken());
                    box[i][j][k] = tomato;

                    if(tomato == 0) { // 덜익은 토마토가 있으면 flag=false
                        isAllMatured = false;
                    }
                    // 익은 토마토들을 따로 관리
                    if(tomato == 1) matured.add(new Tomato(k, j, i));
                }
            }
        }
        if(!isAllMatured) { // 덜익음
            System.out.println(countDays());
        } else { // 처음부터 모두 익어 있음
            System.out.println(0);
        }
    }

    // 토마토 결과 확인
    private static int countDays () {
        int count = 0;

        int[] ms = {0, 0, 0, 0, 1, -1};
        int[] ns = {0, 0, 1, -1, 0, 0};
        int[] hs = {1, -1, 0, 0, 0, 0};

        while(true) {
            if(!hasUnmatured()) { // 다 익었으면 탈출
                break;
            } else {
                if(matured.isEmpty()) { // 영향 줄 수 있는 토마토X -> 숙성 불가
                    return -1;
                }
                // 새로 익은 토마토를 저장하는 다른 임시 Queue를 사용
                Queue<Tomato> tempQ = new LinkedList<>();
                // 주변 확인 & 갱신
                while(!matured.isEmpty()) { // count마다 한바퀴
                    Tomato tmt = matured.poll();
                    for (int i = 0; i < 6; i++) {
                        int curM = tmt.m + ms[i];
                        int curN = tmt.n + ns[i];
                        int curH = tmt.h + hs[i];

                        // 이웃 중 미숙성(0)이 있다면 1로 변경 + tempQ에 추가
                        if (curM >= 0 && curM < M && curN >= 0 && curN < N && curH >= 0 && curH < H) {
                            if (box[curH][curN][curM] == 0) {
                                box[curH][curN][curM] = 1;
                                tempQ.add(new Tomato(curM, curN, curH));
                            }
                        }
                    } // for
                } // while
                matured = new LinkedList<>(tempQ);
                count++;
            }
        } // while
        return count;
    }

    // box에 0이 있는지 여부를 반환
    private static boolean hasUnmatured() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(box[i][j][k] == 0) return true;
                }
            }
        }
        return false;
    }

    // 좌표를 다루기 위한 객체
    public static class Tomato {
        int m;
        int n;
        int h;

        Tomato(int m, int n, int h) {
            this.m = m;
            this.n = n;
            this.h = h;
        }
    }
}

