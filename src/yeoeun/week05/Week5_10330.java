package yeoeun.week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week5_10330 {
    static int INF = Integer.MAX_VALUE;
    static int N, M;
    static int[] code; // 초기값
    static int[] contBit; // 변환해야 하는 값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        code = new int[N];
        for (int i = 0; i < N; i++) {
            code[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        contBit = new int[M];
        for (int i = 0; i < M; i++) {
            contBit[i] = Integer.parseInt(st.nextToken());
        }

        int[] temp = code.clone();

        // 0으로 시작 시 최소 연산
        int zeroMin = change(0);

        // 연산으로 인한 영향 제거
        code = temp.clone();

        // 1로 시작 시 최소 연산
        int oneMin = change(1);

        System.out.println(Math.min(zeroMin, oneMin));
    }

    public static int change(int start) {
        int needBit = start; // (현 위치에서) 필요한 값
        int countIdx = 0; // needBit 없데이트를 위한 포인터
        int count = contBit[countIdx];
        int replaceCount = 0;

        for (int i = 0; i < N; i++) {
            // change가 필요한지 여부 계산
            if(code[i] != needBit) {
                int res = replace(i, needBit);
                if(res == INF) return INF; // 불가능한 경우를 상정
                replaceCount += res;
            }

            // needBit 값 업데이트
            if(--count == 0 && i != N-1) {
                count = contBit[++countIdx];
                needBit = needBit == 0 ? 1 : 0;
            }
        }
        return replaceCount;
    }

    // idx 번째에 need 값이 오도록 만들 때 필요한 연산 수를 반환
    public static int replace(int idx, int need) {
        for (int i = idx+1; i < N; i++) {
            if(code[i] == need) {
                code[i] = code[idx];
                code[idx] = need;
                return i - idx;
            }
        }
        return INF;
    }
}
