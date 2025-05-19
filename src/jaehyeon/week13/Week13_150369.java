package src.jaehyeon.week13;

public class Week13_150369 {
    class Solution {

        public int lastDeliveryIndex;
        public int lastPickupIndex;
        public int[] delivery;
        public int[] pickup;

        public long solution(int cap, int n, int[] deliveries, int[] pickups) {

            long answer = 0;

            int sum1 = 0;
            int sum2 = 0;

            for(int i=n-1; i>=0; i--){

                //배달이나 수거 둘중 하나라도 남아있다면 해당 거리만큼 왕복 해야한다.
                sum1 += deliveries[i];
                sum2 += pickups[i];

                while( sum1 > 0 || sum2 > 0){

                    sum1 -= cap;
                    sum2 -= cap;
                    answer += (i+1) * 2;

                }

            }

            return answer;
        }
    }
}
