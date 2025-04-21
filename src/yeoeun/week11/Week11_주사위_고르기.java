package yeoeun.week11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Week11_주사위_고르기 {
    static int N, max; // 주사위수, 최대 승리수
    static int[] A, B; // 선택된 주사위 idx
    static int[] answer; // 최종 정답
    static boolean[] selected; // 선택 상태 저장용
    static int[][] dice; // 주사위 정보 전역변수화

    public int[] solution(int[][] diceInfo) {
        // 초기화
        N = diceInfo.length;
        answer = new int[N/2];
        A = new int[N/2];
        B = new int[N/2];
        selected = new boolean[N];
        dice = diceInfo;

        // 주사위 고르기
        pickDice(0, 0);

        // idx 맞춰주기
        for(int i = 0; i < answer.length; i++) {
            answer[i]++;
        }
        return answer;
    }

    // 주사위 N/2개 고르기: 백트래킹
    public void pickDice(int idx, int depth) {
        if(depth == N / 2) {
            // selected[] 기반으로 주사위 정보 넣어주기
            int adx = 0, bdx = 0;
            for(int i = 0; i < N; i++) {
                if(selected[i]) A[adx++] = i;
                else B[bdx++] = i;
            }

            // 전부 선택하면 A 승리 count
            int winCnt = getWin();

            // 최댓값을 정답으로 업데이트
            if(max < winCnt) {
                max = winCnt;
                answer = A.clone();
            }
            return;
        }

        for(int i = idx; i < N; i++) {
            selected[i] = true;
            pickDice(i+1, depth+1);
            selected[i] = false;
        }
    }

    public int getWin() {
        // 얕은 복사-> 주소 넘겨서 결과 저장
        List<Integer> Asums = new ArrayList<>();
        getSums(A, 0, 0, Asums);
        List<Integer> Bsums = new ArrayList<>();
        getSums(B, 0, 0, Bsums);

        Collections.sort(Bsums);

        int wins = 0;
        for(int i : Asums) {
            wins += getMinCount(Bsums, i); // i보다 작은 Bsum의 개수
        }
        return wins;
    }

    // sums들의 전체 경우의수를 return
    public void getSums(int[] idxs, int depth, int sum, List<Integer> result) {
        if(depth == N/2) {
            result.add(sum);
            return;
        }

        int idx = idxs[depth];
        for (int num : dice[idx]) {
            getSums(idxs, depth+1, sum+num, result);
        }
    }

    // 이분탐색
    public int getMinCount(List<Integer> list, int target) {
        int ps = 0;
        int pl = list.size()-1;

        while(ps <= pl) {
            int mid = (ps + pl) / 2;
            if(target <= list.get(mid)) pl = mid-1;
            else ps = mid + 1;
        }
        return pl+1;
    }
}
