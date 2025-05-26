package jinu.week14;
import java.util.*;
public class Week14_상담원인원 {

    class Solution {

        int mentorNumber[];
        int minTime = Integer.MAX_VALUE;
        public int solution(int k, int n, int[][] reqs) {
            int answer = 0;
            //상담 유형에 따른 멘토의 수
            mentorNumber = new int[k];

            DFS(0,n,k,0,reqs);
            return minTime;
        }

        /// 상담 유형에 따른 멘토의 수를 백트래킹으로 채운다.
        public void DFS(int j,int n,int k,int sum,int [][]requests){
            //유형별로 인원을 모두 설정하였다면...
            if(j==k){

                //사용자가 최소로 기다리는 시간을 갱신할 수 있으면 갱신
                calculate(requests,k);
                return;
            }

            //마지막 원소인 경우
            if(j==k-1){
                // 총 합 n을 만족시키기 위해 마지막 원소에 넣는다.
                mentorNumber[j] = (n-sum);
                // 1이상이 아닌 경우 return 시킨다.
                if(mentorNumber[j]<=0){
                    return;
                }
                DFS(j+1,n,k,n,requests);
            }
            else{
                // 모든 유형이 1이상이므로..
                for(int i=1;i<=n-k+1;i++){
                    mentorNumber[j]=i;
                    DFS(j+1,n,k,sum+i,requests);
                }

            }

        }

        // 어떤 자료 구조를 써야할지 고민했다. => pq의 배열
        public void calculate(int [][]requests,int k){

            PriorityQueue<Integer> []pqArr = new PriorityQueue[k];
            for(int i=0;i<k;i++){
                pqArr[i] = new PriorityQueue<>();
            }

            int totalWaitingTime = 0;

            for(int i=0;i<requests.length;i++){
                int type = requests[i][2]-1;
                // 시작 시간
                int start = requests[i][0];
                // 상담 시간
                int time = requests[i][1];

                // 멘토의 숫자보다 현재 멘토와 상담하고 있는 학생의 수가 같거나 큰 경우
                if(mentorNumber[type]<=pqArr[type].size()){
                    // 다음 학생의 상담 시작 시간이 가장 빨리 끝나는 상담 시간보다 빨리 시작하는 경우는
                    // 학생이 기다려야 한다.
                    if(start<pqArr[type].peek()){
                        // 기다린 시간 추가
                        totalWaitingTime += pqArr[type].peek()-start;
                        int lastTime = pqArr[type].poll();
                        pqArr[type].add(lastTime+time);

                    }
                    // 다음 학생의 상담 시작 시간이 가장 빨리 끝나는 상담 시간보다 더 느린 경우는
                    // 기다릴 필요가 없다.
                    else{
                        pqArr[type].poll();
                        pqArr[type].add(start+time);
                    }

                }
                // 멘토의 숫자가 현재 남아 돌경우
                else{
                    pqArr[type].add(start+time);
                }
            }

            // 최소 기다린 시간인지 갱신
            minTime = Math.min(minTime,totalWaitingTime);


        }
    }
}
