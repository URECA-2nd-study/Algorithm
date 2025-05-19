package yeoeun.week13;

public class Week13_비밀_코드_해독 {
    static int N, answer;
    static int[][] Q;
    static int[] answers;
    static int[] selected = new int[5];

    public int solution(int n, int[][] q, int[] ans) {
        // 전역변수화
        N = n;
        Q = q;
        answers = ans;
        dfs(0, 1);
        return answer;
    }

    public void dfs(int depth, int start) {
        if(depth == 5) {
            if(check()) answer++;
            return;
        }

        for(int i = start; i <= N; i++) {
            selected[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

    // 현재 선택된 것들 검증
    public boolean check() {
        for(int i = 0; i < Q.length; i++) {
            int cnt = 0;
            for(int j = 0; j < 5; j++) {
                for(int s : selected) {
                    if(s == Q[i][j]) cnt++;
                }
            }
            if(cnt != answers[i]) return false;
        }
        return true;
    }
}
