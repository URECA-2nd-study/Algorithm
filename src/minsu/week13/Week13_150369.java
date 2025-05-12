import java.util.ArrayDeque;
import java.util.Deque;

public class Week13_150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 1) 남은 배달·수거가 있는 집의 인덱스를 스택에 모아둡니다.
        Deque<Integer> deliverStack = new ArrayDeque<>();
        Deque<Integer> pickupStack  = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) deliverStack.push(i);
            if (pickups[i]   > 0) pickupStack.push(i);
        }

        long answer = 0;
        // 2) 처리할 집이 남아 있는 동안 반복
        while (!deliverStack.isEmpty() || !pickupStack.isEmpty()) {
            // 가장 먼 집 번호(0-based)
            int furthest = 0;
            if (!deliverStack.isEmpty()) furthest = Math.max(furthest, deliverStack.peek());
            if (!pickupStack.isEmpty())  furthest = Math.max(furthest, pickupStack.peek());

            // 왕복 거리 더하기
            answer += (long)(furthest + 1) * 2;

            // 이번 왕복에서 실을 수 있는 배달·수거 용량
            int deliverCap = cap;
            int pickupCap  = cap;

            // 3) 배달 가능한 만큼 처리
            while (deliverCap > 0 && !deliverStack.isEmpty()) {
                int idx = deliverStack.peek();
                if (deliveries[idx] <= deliverCap) {
                    deliverCap   -= deliveries[idx];
                    deliveries[idx] = 0;
                    deliverStack.pop();
                } else {
                    deliveries[idx] -= deliverCap;
                    deliverCap = 0;
                }
            }

            // 4) 수거 가능한 만큼 처리
            while (pickupCap > 0 && !pickupStack.isEmpty()) {
                int idx = pickupStack.peek();
                if (pickups[idx] <= pickupCap) {
                    pickupCap   -= pickups[idx];
                    pickups[idx] = 0;
                    pickupStack.pop();
                } else {
                    pickups[idx] -= pickupCap;
                    pickupCap = 0;
                }
            }
        }

        return answer;
    }

}