/*
참가자들이 기다린 시간을 최소화 하는 멘토의 인원을 정하고, 그 때 기다린 시간 정하기 

일단 유형 별로 최소 1명씩은 배정해야함
-> 그 때의 기다린 시간을 구하기 
-> 그리고 제일 많이 기다린 유형에게 한 명 더 배정한다
-> 그리고 또 기다린 시간을 구하기 
...
-> 멘토 배정 다 할 때 까지 반복 

(위 로직은 실패)
-> 이유 : 가장 많이 기다린 유형에게 멘토를 배정한다고 해서 전체 시간이 줄어든다는 보장이 없다 . . . 

<<개선 로직(아래)>>

각 wait time에 싹 다 한 명씩 추가 배정 
=> 직전 waitTime과 비교하여 제일 많이 시간이 줄어든 유형에다가 한 명 추가 배정한다. 
-> 멘토 배정 다 할 때 까지 반복 

*/
import java.util.*;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        
        int rest = n-k; //유형 별로 1명씩 배정하고 남은 인원
        int [] setNum = new int[k]; //각 유형 별 배정 인원
        Arrays.fill(setNum, 1); // 초기 배정 인원은 각 1명
        
        int [] waitTime = new int[k];
        int [] pastWaitTime = getWait(k, setNum, reqs);

        while(rest>0){
            
            int maxDiff = Integer.MIN_VALUE; 
            int type = -1; //제일 maxDiff인 유형
            int [] bestWaitTime = new int[k];
            
            //각 유형에 한 명씩 추가 배정을 해보기 
            for(int i=0; i<k; i++){
                int [] candi = setNum.clone();
                candi[i] ++;
                waitTime = getWait (k, candi, reqs);
                
                if((pastWaitTime[i] - waitTime[i]) > maxDiff ){
                    maxDiff = pastWaitTime[i] - waitTime[i];
                    type = i;
                    bestWaitTime = waitTime;
                }
                
            }

            setNum[type] ++;
            rest --;
            pastWaitTime = bestWaitTime;
        }
        
        for(int wait : pastWaitTime){
            answer += wait;
        }
        
        return answer;
    }//main 
    
    //유형 별 기다린 시간 반환
    static int[] getWait (int k, int [] setNum, int[][] reqs){
        
        ArrayList<ArrayList<Integer>> lastTime = new ArrayList<>(); //각 유형 별 제일 빠르게 끝나는 상담 시간 
        
        for(int i=0; i<k; i++){
            lastTime.add(new ArrayList<Integer>());
            
            for(int j=0; j<setNum[i] ; j++){
                lastTime.get(i).add(0);
            }
        }
        
        int [] waitTime = new int[k] ; //기다린 시간
        
        for(int [] req : reqs){
            int start = req[0];
            int cTime = req[1]; //상담 시간
            int num = req[2] - 1; //유형
            int minLastTime = Integer.MAX_VALUE; //제일 빨리 끝나는 상담 시간
            int minIndex = 0;
            
            //minLastTime 구하기
            for(int i=0; i<lastTime.get(num).size(); i++){
                
                int time = lastTime.get(num).get(i);
                
                if(time < minLastTime){
                    minIndex = i;
                    minLastTime = time;
                }
            }
            
            if(minLastTime>start){
                waitTime[num] += (Math.max(start,minLastTime)-start);
            }
            
            lastTime.get(num).set(minIndex, Math.max(start,minLastTime) + cTime);
            
        }
        
        return waitTime;
    }
}