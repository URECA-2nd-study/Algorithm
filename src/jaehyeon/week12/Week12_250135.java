package src.jaehyeon.week12;

public class Week12_250135 {
/*
    초침-시침
    초침-분침
    겹친다는 의미 = 각도가 동일
*/

    class Solution {

        public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

            int start = h1*3600 + m1*60 + s1;
            int end = h2*3600 + m2*60 + s2;

            int answer = 0;

            if(start%360 == 0 || start%360 == 12) answer++;

            while(start < end){

                //초당 각도
                double curHourAnglePerSec = (start/(double)120)%360;
                double curMinAnglePerSec = (start/(double)10)%360;
                double curSecAnglePerSec = (start*6)%360;

                //1초 후 각도
                double nextHourAnglePerSec = ((start+1)/(double)120)%360;
                double nextMinAnglePerSec = ((start+1)/(double)10)%360;
                double nextSecAnglePerSec = ((start+1)*6)%360;

                //모듈러 연산 때문에 360도가 0이 되는 경우 예외처리
                if(nextHourAnglePerSec == 0) nextHourAnglePerSec = 360;
                if(nextMinAnglePerSec == 0) nextMinAnglePerSec = 360;
                if(nextSecAnglePerSec == 0) nextSecAnglePerSec = 360;

                //초침이 시침을 지나갔을 경우
                if(curSecAnglePerSec < curHourAnglePerSec &&
                        nextSecAnglePerSec >= nextHourAnglePerSec) answer++;
                //초침이 분침을 지나갔을 경우
                if(curSecAnglePerSec < curMinAnglePerSec &&
                        nextSecAnglePerSec >= nextMinAnglePerSec) answer++;

                //시침과 분침이 동일한 경우 중복 제거
                if(nextMinAnglePerSec == nextHourAnglePerSec) answer--;

                start++;
            }

            return answer;
        }
    }//solution

}
