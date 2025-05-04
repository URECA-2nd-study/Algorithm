package src.jaehyeon.week13;

import java.util.ArrayList;

public class Week13_388352 {
    /*
        1부터 n까지의 조합을 구한다.
        매 조합마다 비밀 코드에 대한 응답이 일치하는 지 체크한다.
    */
    class Solution {

        public ArrayList<Integer> secretCode = new ArrayList<>();
        public int answer = 0;

        public int solution(int n, int[][] q, int[] ans) {

            dfs(1, 0, n, q, ans);

            return answer;
        }

        private void validateCode(int[][] q, int[] ans) {

            for (int i = 0; i < q.length; i++) {

                int response = 0;

                for (int j = 0; j < 5; j++) {

                    if (secretCode.contains(q[i][j])) response++;

                }

                if (response != ans[i]) return;
            }

            // System.out.println("secret : " + secretCode);

            answer++;

        }

        private void dfs(int index, int depth, int n, int[][] q, int[] ans) {

            if (depth == 5) {

                validateCode(q, ans);

                return;

            }

            for (int i = index; i <= n; i++) {

                secretCode.add(i);

                dfs(i + 1, depth + 1, n, q, ans);

                secretCode.remove(Integer.valueOf(i));

            }

        }
    }
}
