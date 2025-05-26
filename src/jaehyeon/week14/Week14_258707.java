package src.jaehyeon.week14;

import java.util.HashSet;

public class Week14_258707 {
/*
    1부터 n사이의 카드 뭉치
    coin : 동전 수
    가장 긴 라운드 만큼 진행하려면 동전을 아껴야 한다.
*/

    class Solution {
        public int solution(int coin, int[] cards) {
            HashSet<Integer> stock = new HashSet<>();
            HashSet<Integer> stockPlus = new HashSet<>();

            int n = cards.length;
            int index = n / 3;
            int answer = 0;

            for (int i = 0; i < index; i++) {
                stock.add(cards[i]);
            }

            int target = n + 1;

            while (true) {

                answer++;

                if (index >= n) break;

                stockPlus.add(cards[index]);
                stockPlus.add(cards[index + 1]);
                index += 2;
                boolean flag = false;

                // 동전 사용 X
                for (int i : stock) {
                    if (stock.contains(target - i)) {
                        stock.remove(i);
                        stock.remove(target - i);
                        flag = true;
                        break;
                    }
                }

                // 동전 1개 사용
                if (!flag && coin > 0) {
                    for (int i : stock) {
                        if (stockPlus.contains(target - i)) {
                            stock.remove(i);
                            stockPlus.remove(target - i);
                            coin--;
                            flag = true;
                            break;
                        }
                    }
                }

                // 동전 2개 사용
                if (!flag && coin > 1) {
                    for (int i : stockPlus) {
                        if (stockPlus.contains(target - i)) {
                            stockPlus.remove(target - i);
                            stockPlus.remove(i);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }

                if (!flag) break;
            }

            return answer;
        }
    }
}
