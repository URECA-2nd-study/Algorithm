import java.io.*;
import java.util.*;

class Week14_258707 {
	private static int N, start;
	private static Set<Integer> inHand, additional;

	public int solution(int coin, int[] cards) {
		N = cards.length;
		inHand = new HashSet();
		additional = new HashSet();

		for (int i = 0; i < N / 3; i++) {
			inHand.add(cards[i]);
		}

		int answer = simulate(coin, cards);
		return answer;
	}

	private static int simulate(int coin, int[] cards) {
		int target = N + 1;
		int idx = N / 3;
		int round = 0;

		while (true) {
            // 라운드 시작.
			round++;

			if (idx >= N) {
				break;
			}
            
            // 카드를 뽑아 set에 넣음.
			additional.add(cards[idx++]);
			additional.add(cards[idx++]);

			boolean isPoss = false;
            
            // 1. 현재 손에 가지고 있는 카드들 중 N + 1을 만들 수 있는 카드가 있다면
            // 뽑아서 사용해버림.
			for (int num : inHand) {
				if (inHand.contains(target - num)) {
					inHand.remove(num);
					inHand.remove(target - num);
					isPoss = true;
					break;
				}
			}
            
            // 1이 만족하면 다음 라운드로 진행.
			if (isPoss)
				continue;
            
            // 1이 만족하지 않았을 경우
            // 2. 덱에서 뽑은 카드 두 개 중 하나와 손에 있던 카드들 중 N + 1이 되는 것이 있으면
            // 뽑아서 사용해버림.
			if (coin > 0) {
				for (int num : inHand) {
					if (additional.contains(target - num)) {
						inHand.remove(num);
						additional.remove(target - num);
						coin--;
						isPoss = true;
						break;
					}
				}
			}
            
            // 2 만족하면 다음 라운드로 진행.
			if (isPoss)
				continue;
            
            // 3. 2개를 뽑아야만 N + 1을 만들 경우
            // 코인이 적어도 2개 이상 있어야함
            // 될 경우 다음 라운드로 넘어가고
            // 안되면 종료.
			if (coin > 1) {
				for (int num : additional) {
					if (additional.contains(target - num)) {
						additional.remove(num);
						additional.remove(target - num);
						coin -= 2;
						isPoss = true;
						break;
					}
				}
			}

			if (!isPoss)
				break;
		}
		return round;
	}
}