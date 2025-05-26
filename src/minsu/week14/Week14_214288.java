import java.io.*;
import java.util.*;

class Week14_214288 {
    /**
     * select 배열은 1 ~ K개 상담 유형에 멘토를 몇 명 배치할 지 저장하는 배열
     * pq는 각 유형 별 상담오는 사람들을 도착 순서대로 오름차순 정렬하기 위해 활용하였습니다.
     * 
     * 각 유형 별로 멘토는 1명씩 있어야 하기 때문에 select 배열에 1씩 먼저 넣어두고
     * 남은 인원들은 완탐으로 select 배열에 배치하였습니다.
     */

    private static int N, K, min;
    private static PriorityQueue<int[]>[] pq;
    public int solution(int k, int n, int[][] reqs) {
        int[] select = new int[k];
        Arrays.fill(select, 1);
        N = n - k;
        K = k;
        min = Integer.MAX_VALUE;
        
		pq = new PriorityQueue[K];
        
		for (int i = 0; i < K; i++) {
			pq[i] = new PriorityQueue<>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[0] == o2[0]) {
						return o1[1] - o2[1];
					}

					return o1[0] - o2[0];
				}
			});
		}
        
        for(int[] w : reqs) {
            pq[w[2] - 1].add(w);
        }
        
        initMento(0, 0, select);
        
        return min;
    }
    
    private static void initMento(int depth, int sum,int[] select) {
        if(depth == K) {
            // 각 유형 별로 멘토를 지정했다.
            // 이제 시뮬레이션 돌리면된다.
            // 최소 값 갱신
            min = Math.min(min, simulate(select));
        } else {
            for(int i = 0; i <= N - sum; i++) {
                select[depth] += i;
                initMento(depth + 1, sum + i, select);
                select[depth] -= i;
            }
        }
         
    }
     
    private static int simulate(int[] select) {
        // 기존에 pq 만들어놨던걸 wait에 깊은 복사함.
        PriorityQueue<int[]>[] wait = new PriorityQueue[K];
        for (int i = 0; i < K; i++) {
            wait[i] = new PriorityQueue<>(pq[i]);
        }
        
        int result = 0;
        
        // 각 유형별로 총 걸리는 상담 시간을 구하는 for문
        for(int i = 0; i < K; i++) {
            int[] mentors = new int[select[i]];
            
            while(!wait[i].isEmpty()) {
                int[] curr = wait[i].poll();
                
                int minIdx = 0;
                for(int j = 1; j < select[i]; j++) {
                    if(mentors[j] < mentors[minIdx]) {
                        minIdx = j;
                    }
                }
                
                if(mentors[minIdx] <= curr[0]) {
                    mentors[minIdx] = curr[0] + curr[1];
                } else {
                    result += mentors[minIdx] - curr[0];
                    mentors[minIdx] = mentors[minIdx] + curr[1];
                }
            }
        }
        return result;
    }
    
}