package yeoeun.week13;

public class Week13_택배_배달과_수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long total = 0;

        int deliverIdx = updateIdx(deliveries, n - 1);
        int pickIdx = updateIdx(pickups, n - 1);

        while (deliverIdx >= 0 || pickIdx >= 0) {
            // 이번 회차 왕복 거리 더하기
            total += (long)(Math.max(deliverIdx, pickIdx) + 1) * 2;

            // 배달 처리
            int devCnt = cap;
            for (int i = deliverIdx; i >= 0 && devCnt > 0; i--) {
                if (deliveries[i] == 0) continue;

                if (deliveries[i] <= devCnt) {
                    devCnt -= deliveries[i];
                    deliveries[i] = 0;
                } else {
                    deliveries[i] -= devCnt;
                    devCnt = 0;
                }
            }
            deliverIdx = updateIdx(deliveries, deliverIdx);

            // 수거 처리
            int pickCnt = cap;
            for (int i = pickIdx; i >= 0 && pickCnt > 0; i--) {
                if (pickups[i] == 0) continue;

                if (pickups[i] <= pickCnt) {
                    pickCnt -= pickups[i];
                    pickups[i] = 0;
                } else {
                    pickups[i] -= pickCnt;
                    pickCnt = 0;
                }
            }
            pickIdx = updateIdx(pickups, pickIdx);
        }
        return total;
    }

    // 가장 멀리 남아 있는 집 인덱스 반환
    private int updateIdx(int[] arr, int idx) {
        while (idx >= 0 && arr[idx] == 0) {
            idx--;
        }
        return idx;
    }
}
