package yeoeun.week09;

import java.io.*;
import java.util.ArrayList;

public class Week9_10728 {
    static int maxLen;
    static ArrayList<Integer> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            maxLen = 0;

            // 2^n번 -> 포함 여부를 나타냄!!
            for (int i = 0; i < (1 << N); i++) {
                ArrayList<Integer> list = new ArrayList<>();
                // j번째 수가 포함인지 확인
                for (int j = 0; j < N; j++) {
                    if((i & (1 << j)) != 0) {
                        list.add(j+1);
                    }
                }

                // 0이 되는 조합이 없으면 정답 갱신
                if(!checkZero(list)) {
                    answer = new ArrayList<>(list);
                    maxLen = list.size();
                }
            }

            // 정답 입력
            bw.write(maxLen + "\n");
            for (Integer i : answer) {
                bw.write(i + " ");
            }
            bw.write("\n");
        }
        br.close();;
        bw.close();
    }

    public static boolean checkZero(ArrayList<Integer> list) {
        // 길이가 더 긴 경우에만 실행 (시간 단축)
        if(maxLen >= list.size()) return true;

        // 조합별 조건 만족 확인
        for (int i = 0; i < list.size()-2; i++) {
            for (int j = i+1; j < list.size()-1; j++) {
                // A ^ B == C 이면, A ^ B ^ C == 0
                if (list.contains(list.get(i) ^ list.get(j))) {
                    return true; // 0 있음
                }
            }
        }
        return false; // 0 없음
    }
}
