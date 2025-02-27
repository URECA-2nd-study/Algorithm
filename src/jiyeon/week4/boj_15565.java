package week4;

import java.io.*;
import java.util.*;

/*
 * pseudo code
 * 
 * 1. 배열을 순회하며 첫번째 라이언 인덱스를 찾는다. (start pointer)
 * 2. end 포인터를 한 칸 씩 옮기며 라이언 인형을 찾으면 count ++ 및 라이언 인형의 인덱스를 큐에 삽입한다.
 * 3. count가 K개가 되면 해당 배열의 크기와 최솟값을 비교한다.
 * 4. 이후 queue의 첫번째 요소를 start 포인터로 업데이트 하고 count를 1 감소 시킨다. (start 포인터가 업데이트 되는 순간 부분 배열에는 라이언인형이 하나 없어지기 때문)
 * 5. 반복 후 최소 배열 크기 return 
 * */

public class boj_15565 {
	
	static Queue<Integer> pointers = new ArrayDeque<>(); //라이언 인형의 인덱스(포인터) 
	static int[] dolls; 
	static int N,K;
	static int min = Integer.MAX_VALUE;
	static int start = -1;
	
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dolls = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int doll = Integer.parseInt(st.nextToken());
			dolls[i] = doll;

		}//입력
		
		int count = 0; //집합에 들어있는 라이언 인형 개수 
		
		for(int end = 0 ; end<N; end++) {
			
			if(start == -1 && dolls[end] == 1) { // 첫 번째로 나오는 라이언 인형의 인덱스는 queue에 넣지 않도록 처리 
				start = end;
				count++;
			}
			
			
			else if(dolls[end] == 1) {
				count ++;
				pointers.add(end);
			}
			
			
			if(count == K) {
				min = Math.min(end-start+1, min);
				start = pointers.poll();
				count -= 1;
			}
			

			
		}

		
		if(min == Integer.MAX_VALUE)
			System.out.println(-1);
		else 
			System.out.println(min);
		

		
		
	}

}
