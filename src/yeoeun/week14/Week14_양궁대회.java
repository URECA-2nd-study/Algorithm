package yeoeun.week14;

public class Week14_양궁대회 {
    static int N;
    static int mp = -1;
    static int[] apeach;
    static int[] selected = new int[11];
    static int[] answer = new int[11];

    public int[] solution(int n, int[] info) {
        N = n;
        apeach = info;

        // 정답 찾기
        dfs(0, 0);

        return (mp == -1) ? new int[] {-1} : answer;
    }

    public void dfs(int cur, int cnt) {
        if(cur == 11) {
            if(cnt > N) return;
            if(cnt == N) {
                // 어피치, 라이언 점수 계산
                int ap = 0;
                int lp = 0;

                for(int i = 0; i <= 10; i++) {
                    if(apeach[i] == 0 && selected[i] == 0) continue;

                    if(apeach[i] < selected[i]) lp += (10 - i);
                    else ap += (10 - i);
                }

                // 비교해서 라이언이 이기면 점수차 비교 -> 업뎃
                if(lp > ap) {
                    if(mp < lp - ap) {
                        mp = Math.max(mp, lp - ap);
                        answer = selected.clone();
                    } else if(mp == lp - ap) { // 더 낮은 점수 많이 맞춘 사람
                        for(int i = 10; i >= 0; i--) {
                            if(selected[i] > answer[i]) { // 새 답이 더 낮음
                                answer = selected.clone();
                                break;
                            } else if(selected[i] < answer[i]) { // 기존이 더 낮음
                                break;
                            }
                        }
                    }
                } // if
            }
            return;
        }

        // 마지막은 화살 몰아주기
        if (cur == 10) {
            selected[cur] = N - cnt;
            dfs(cur + 1, N);
            selected[cur] = 0;
            return;
        }

        // 이길 수 있으면 이기는 경우 고려
        if(N >= apeach[cur] + cnt + 1) {
            selected[cur] = apeach[cur] + 1;
            dfs(cur + 1, cnt + selected[cur]);
            selected[cur] = 0;
        }

        // 지거나 비기는 경우 (lion이 0인 경우) 고려
        dfs(cur + 1, cnt);
    }
}
