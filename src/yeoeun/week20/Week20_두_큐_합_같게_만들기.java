package yeoeun.week20;

import java.util.*;

class Week20_두_큐_합_같게_만들기 {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long total = 0;
        long sum1 = 0;
        for(int i = 0; i < queue1.length; i++) {
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
            sum1 += queue1[i];
            total += queue1[i] + queue2[i];
        }

        // 홀수면 불가능
        if(total % 2 != 0) return -1;

        // 짝수면 탐색
        int standard = q1.size() * 3; // 전부 q1로 이동 -> 전부 q2로 이동 (최악 고려)
        long target = total / 2;
        int answer = 0;

        while(sum1 != target) {
            if(answer++ > standard) return -1;

            if(sum1 < target) {
                int n = q2.poll();
                q1.offer(n);
                sum1 += n;
            } else {
                int n = q1.poll();
                q2.offer(n);
                sum1 -= n;
            }
        }
        return answer;
    }
}
