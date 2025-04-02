package yeoeun.week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week9_1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N <= K) { // 바로 들고갈 수 있는 경우
            System.out.println(0);
            return; // 프로그램 종료
        }

        // N보다 작은 Max Water 찾기 (2의 제곱수 중)
        int water = 1;
        while(true) {
            if((water << 1) < N) water <<= 1; // 2배씩
            else break;
        }

        // 최소 찾기
        while(K > 0) {
            if(N >= water) { // 물통에 물 넣기
                N -= water;
                K--;
            } else { // 넣을 물 양 조절
                water /= 2;
            }
        }
        // N = 남은 물 -> 0이면 차를 구할 필요X
        System.out.println(N == 0 ? 0 : water - N);
    }
}
