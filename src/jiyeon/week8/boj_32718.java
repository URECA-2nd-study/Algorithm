package jiyeon.week8;

import java.util.*;
import java.io.*;

public class boj_32718 {
	
	static int N,K,T, delta;
	static int[] pq ; 
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		pq = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			pq[i] = Integer.parseInt(st.nextToken())%K;
		}
		
		
		st = new StringTokenizer(br.readLine());
		
		Arrays.sort(pq);
		
		for(int t=0; t<T; t++) {
			
			int a = Integer.parseInt(st.nextToken());
			delta = (delta+a)%K;
			int target = K - delta;
			int idx = binarySearch(target);
			
			int candi1 = -1, candi2 = -1;
			
			//idx가 음수가 아닌 경우 ( == target 이하의 값이 존재하는 경우)
			if(idx>=0)
				candi1 = pq[idx] + delta;
			
			
			/*idx< N-1 인 경우 (== target 값 이상의 원소가 있는 경우)
			 * 즉, 모듈러 순환이 발생을 한 경우를 의미한다.
			 * 
			 * 예를 들어 N = 4, K = 10, delta = 4, target = 10-4 = 6이고 
			 * pq = [0,2,4,7] 이면 
			 * 
			 * 이분 탐색의 값은 target 미만의 값 중 가장 오른쪽을 반환하기 때문에 
			 * 이분탐색의 결과 idx = 2가 나오게 된다. 
			 * 
			 * 이 말의 즉슨 idx 값 이후에 target보다 더 큰 값이 존재한다는 의미이다. 
			 * 모듈러 순환 성질에 의하면 나머지는 0이상 k-1 이하의 값으로 나오기 때문에 
			 * idx 값 이후에 있는 요소들은 모듈러 범위 안으로 wrapping 시켜줘야한다. 
			 * 따라서 아래와 같은 조건을 사용해줘야하는 것이다. (안하면 틀린다.)
			 * 
			 * */
			if(idx < N-1) {
				candi2 = pq[N-1] + delta - K;
			}
			
			sb.append(Math.max(candi1, candi2)).append(" ");


		}
		
		System.out.println(sb);
		
	}//main
	
	//이분탐색 
	static int binarySearch(int target) {
		
		int left = 0;
		int right = N-1;
		
		while(left<=right) {
			int mid = (left+right)/2;
			
			if(pq[mid]<target) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		
		return right;
	}


}
