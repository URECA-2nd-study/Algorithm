package yeoeun.week04;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week4_7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // A 배열 초기화
            int[] arrA = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arrA);

            // B 배열 초기화
            int[] arrB = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arrB);

            int bp = 0; // B의 포인터
            int count = 0; // 결과

            for (int ap = 0; ap < N; ap++) {
                // bp 이전의 것들은 모두 ap의 값보다 작음
                for (int i = bp; i <M ; i++) {
                    // bp가 더 크거나 같으면, bp 이전까지가 ap 보다 작은 것들임
                    if(arrA[ap] <= arrB[bp]) {
                        count += bp;
                        break; // 이후는 필요X
                    } else bp++; // bp가 더 작으면 다음값 확인
                }

                // 배열 B 전체가 ap보다 작은 경우
                if(bp == M) {
                    count += M;
                }
            }
            bw.write(count + "\n");
        }
        br.close();
        bw.close();
    }
}
