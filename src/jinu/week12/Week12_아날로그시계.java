package jinu.week12;

public class Week12_아날로그시계 {

    class Solution {

        // 분침과 초침이 만나는 횟수를 구하려고 한다.
        // 따라서 분침과 초침이 만나는 주기를 구하려고 한다.
        // 분침과 초침이 만나는 주기(S)는 1시간이다. (3시 0분 0초에서 4시 0분 0초)
        // 이 1시간 동안 분침과 초침은 59번 만나게 된다.
        private int SECOND_PER_MINUTE_CYCLE = 60 * 60;
        private int COUNT_PER_MINUTE_CYCLE = 59;

        // 시침과 초침이 만나는 횟수를 구하려고 한다.
        // 따라서 시침과 초침이 만나는 주기를 구하려고 한다.
        // 시침과 초침이 만나는 주기(S)는 12시간이다. (0시 0분 0초에서 12시 0분 0초)
        // 이 12시간 동안 시침과 초침은 759번 만나게 된다. (12 * 60 - 1) : 1시간에 60번 만나지만 12시에는 59번     //만난다.
        private int SECOND_PER_HOUR_CYCLE = SECOND_PER_MINUTE_CYCLE * 12;
        private int COUNT_PER_HOUR_CYCLE = 719;


        public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
            int answer = -1;

            int startSec = parseToSec(h1, m1, s1);
            int endSec = parseToSec(h2, m2, s2);

            // 0시 0분 0 초에서 endSec 까지 울리는 알람의 횟수 - 0시 0분 0초에서 startSec까지 울리는 알람의  횟         //횟수
            answer = countAlrams(endSec) - countAlrams(startSec);

            // 현재 시작점이 알림이 울려야 한다면 1을 더해주어야 한다.
            answer += alramNow(startSec) ? 1 : 0;

            return answer;
        }

        //시간을 초로 분해
        private int parseToSec(int hour, int minute, int second) {
            return hour * 60 * 60 + minute * 60 + second;
        }

        // 0시 0 분 0 초 부터 seconds 동안 울리는 알람의 횟수.
        private int countAlrams(int seconds) {
            // (2)

            // 초침이 분침과 만나 발생하는 알람의 횟수
            int minuteAlaram = (seconds * COUNT_PER_MINUTE_CYCLE) / SECOND_PER_MINUTE_CYCLE;

            // 초침이 시침과 만나 발생하는 알람의 횟수
            int hourAlaram = (seconds * COUNT_PER_HOUR_CYCLE) / SECOND_PER_HOUR_CYCLE;

            // (3) 중복되는 횟수
            // 시간 주기보다 seconds가 작다면 기본 0시에서 발생하는 횟수 제외: 1
            // 시간 주기보다 seconds가 크다면 12시간을 넘겼기에 2번 제외
            int duplicatedAlrams = SECOND_PER_HOUR_CYCLE <= seconds ? 2 : 1;

            return minuteAlaram + hourAlaram - duplicatedAlrams;
        }

        private boolean alramNow(int seconds) {
            return seconds * COUNT_PER_MINUTE_CYCLE / SECOND_PER_MINUTE_CYCLE == 0 ||
                    seconds * COUNT_PER_HOUR_CYCLE % SECOND_PER_HOUR_CYCLE == 0;
        }
    }
}
