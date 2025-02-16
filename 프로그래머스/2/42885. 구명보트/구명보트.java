import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        
        // 남는 공간이 제일 적게 해야할 것 같은데..
        // 제일 무거운사람 + 가벼운 사람 조합으로
        int answer = 1;
        Arrays.sort(people);
        
        int used = 0; // 보트에 탄 사람
        int left = 0; // 작은거 인덱스
        int right = people.length - 1; // 큰거 인덱스
        int leftW = limit;
        int boatCnt = 0;
        
        while (used < people.length) {
            
            if (boatCnt == 2) {
                answer++;
                leftW = limit;
                boatCnt = 0;
                continue;
            }
            
            if (leftW >= people[right]) {
                used++;
                leftW -= people[right--];
                boatCnt++;
            } else if (leftW >= people[left]) {
                used++;
                leftW -= people[left++];
                boatCnt++;
            } else {
                answer++;
                leftW = limit;
                boatCnt = 0;
            }
        }
        
        return answer;

    }
}