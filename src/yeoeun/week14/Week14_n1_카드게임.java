package yeoeun.week14;

public class Week14_n1_카드게임 {
    public int solution(int coin, int[] cards) {
        int cardCnt = cards.length;
        int roundCnt = 0;

        boolean[] canSelect = new boolean[cardCnt + 1]; // 가지고 있는지 여부
        boolean[] isFree = new boolean[cardCnt + 1]; // 냈는지 여부

        // 첫 카드 가져오기
        for (int i = 0; i < cardCnt / 3; i++) {
            canSelect[cards[i]] = true;
            isFree[cards[i]] = true;
        }

        // 최대 게임 횟수 : coin/3회
        for (int i = 0; i < cardCnt / 3; i++) {
            // coin 남아 있다면 카드 뽑기 (없으면 어차피 못 뽑음)
            if (coin > 0) {
                canSelect[cards[cardCnt / 3 + i * 2]] = true;
                canSelect[cards[cardCnt / 3 + i * 2 + 1]] = true;
            }

            boolean passFlag = false;
            int minCost = 10;
            int minPair = 0;

            // 현재 상태 중 최소 비용 하나 선택
            // 카드/3 보다 크면 코인 지불해야 함
            for (int j = 1; j <= cardCnt / 2; j++) {
                if (!canSelect[j] || !canSelect[cardCnt + 1 - j]) continue;

                // 둘 다 있는 경우, cost 계산
                int curCost = 0;
                if (!isFree[j]) curCost++;
                if (!isFree[cardCnt + 1 - j]) curCost++;

                if (curCost <= coin && minCost > curCost) {
                    minCost = curCost;
                    minPair = j;
                    passFlag = true;
                }
            }

            // n+1 못만들면 종료
            if (!passFlag) break;

            // 가능하면 점수 증가
            roundCnt++;

            canSelect[minPair] = false;
            canSelect[cardCnt + 1 - minPair] = false;
            coin -= minCost;
        }

        // 종료 라운드 고려
        return roundCnt + 1;
    }
}
