package src.jaehyeon.week14;

import java.util.PriorityQueue;

public class Week14_214288 {

    class Solution {

        public int answer = Integer.MAX_VALUE;

        public int[] comb;
        public int restMento;

        public int solution(int k, int n, int[][] reqs) {

            // 멘토 배정 조합을 담을 배열
            comb = new int[k];

            // 모든 유형에 최소 한명의 멘토 배정
            for (int i = 0; i < k; i++) {
                comb[i]++;
            }

            restMento = n - k;

            combine(k, 0, 0, reqs);

            return answer;
        }

        private void combine(int k, int index, int depth, int[][] reqs) {

            if (depth == restMento) {

                sol(reqs);

            } else {
                for (int i = index; i < k; i++) {
                    comb[i]++;

                    combine(k, i, depth + 1, reqs);

                    comb[i]--;
                }
            }

        }

        private void sol(int[][] reqs) {

            PriorityQueue<Integer>[] thread = new PriorityQueue[comb.length];

            int time = 0;

            for (int i = 0; i < comb.length; i++) {
                int threadCount = comb[i];
                thread[i] = new PriorityQueue<>();
                for (int ii = 0; ii < threadCount; ii++) {

                    thread[i].offer(0);//조합 만큼 스레드 대기

                }

            }

            for (int[] req : reqs) {

                int arrivalTime = req[0];
                int runningTime = req[1];
                int type = req[2] - 1;

                int endTime = thread[type].poll();

                if (endTime <= arrivalTime) {
                    thread[type].offer(arrivalTime + runningTime);
                } else {
                    time += endTime - arrivalTime;
                    thread[type].offer(endTime + runningTime);
                }
            }

            answer = Math.min(answer, time);

        }
    }
}
