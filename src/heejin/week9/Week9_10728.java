package heejin.week9;
import java.io.*;
import java.util.*;

public class Week9_10728 {
    static int T, N;
    static List<Integer> ans, temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            ans = new ArrayList<>();

            // 1~N을 수열에 포함하냐 안하냐를 비트 마스킹으로 표시
            // ex. 1을 포함하면 0번째 비트 1, 2를 포함하면 1번째 비트 1, ...

            int cnt = 1 << N; // 2^N-1까지(N개의 비트가 모두 1인 경우) 탐색 -> 1~N을 가지고 만들 수 있는 수열의 모든 조합
            for (int mask = 1; mask < cnt; mask++) {
                temp = new ArrayList<>();

                // 비트 마스킹에 따라 수열 만들기
                for (int j = 0; j < N; j++) {
                    if ((mask & (1 << j)) != 0) { // mask의 j번째 비트가 1이라면(=j+1을 수열에 포함시키면) j+1을 답에 포함
                        temp.add(j + 1);
                    }

                    // XOR 조건 만족하고, 현재까지 가장 긴 길이라면 답 업데이트
                    if(check(temp) && temp.size() > ans.size()){
                        ans = temp;
                    }
                }
            }

            // 만족하는 가장 긴 길이
            sb.append(ans.size()).append("\n");

            // 수열 출력
            for (int num : ans) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    // list에 있는 임의의 세 원소들이 조건(XOR한 값이 0)을 만족하는 지 반환
    static boolean check(List<Integer> list) {
        for (int i = 0; i < list.size() - 2; i++) {
            for (int j = i + 1; j < list.size() - 1; j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    if ((list.get(i) ^ list.get(j) ^ list.get(k)) == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
