package week5;

import java.util.*;
import java.io.*;


public class boj_11000 {
	
	static int N,count;
	static int[][] input;
	static PriorityQueue<Integer> pq = new PriorityQueue<>(); //강의실 별 수업이 끝나는 시간 저장 
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		} //입력 
		
		Arrays.sort(input, new Comparator<int []>() {
			
			@Override
			public int compare (int[] o1, int[] o2) {
				
				if(o1[0]==o2[0])
					return o1[1]-o2[1];
				
				return o1[0]-o2[0];
			}
		}); //sort
		
		pq.add(input[0][1]);
		
		for(int i=1; i<N; i++) {
			
			int curEnd = pq.peek(); //제일 먼저 끝나는 시간 확인 
			
			if(curEnd<=input[i][0]) { //제일 먼저 끝나는 시간이 현재 수업의 시작하는 시간보다 빠르면 큐에서 빼내기 
				pq.poll(); 
			}

			pq.add(input[i][1]);

			
		}
		
		System.out.println(pq.size()); 

	}

}
