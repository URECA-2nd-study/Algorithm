package week5;

import java.util.*;
import java.io.*;

public class boj_10330 {
	
	static int N,M; 
	static int [] bits ; //비트문자열
	static int [] codes; //연속 코드 
	static int [][] candidates = new int[2][]; //바꿔야 하는 문자열의 후보를 저장하는 배열 
	static int min = Integer.MAX_VALUE;
	
	/*
	 * 1. 연속 코드가 같은 문자열 후보 두 가지 먼저 만들어 놓기 o
	 * 2. 각 각의 최솟값 구하고 더 작은 값 출력 
	 * 
	 * */
	

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		bits = new int[N];
		codes = new int[M];
		candidates = new int[2][N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			bits[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			codes[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 

		candidates[0] = makeCandidate(candidates[0], 0);
		candidates[1] = makeCandidate(candidates[1], 1);
		
		int input0Num = get0Num(bits);
		
		for(int a = 0; a<2; a++) {
			int [] candi = candidates[a];
			int count = 0;
			
			//연산을 통해 재배열이 가능한지 여부 파악 
			int candi0Num = get0Num(candi);
			
			if(candi0Num!=input0Num){
				continue;
			}
			
			int [] copyBits = copyArr(bits);
			
			for(int i=0; i<N; i++) {
				if(candi[i] == copyBits[i])
					continue;
				
				int changeIndex = findIndex(i,copyBits);
				count += (changeIndex - i);
				swap(i,changeIndex,copyBits);
			}
			
			min = Math.min(min, count);
		} //for 
		
		System.out.println(min);
		
		
		
	}//main
	
	
	
	//배열 copy 메서드
	static int[] copyArr(int[] bits){
		int [] copy = new int[N];
		for(int i=0; i<N; i++)
			copy[i] = bits[i];
		
		return copy;
	}
	
	//후보 만드는 메서드 (0으로 시작하는 문자열, 1로 시작하는 문자열)
	static int[] makeCandidate(int[] arr, int startNum) {
		int start = 0;
		int end = codes[0];
		int num = startNum;
		
		for(int i=1; i<=M; i++) {
			
			for(int j=start; j<end; j++) {
				arr[j] = num;
			}
			
			if(i==M)
				break;
			
			start += codes[i-1];
			end += codes[i];
			
			if(num == 0)
				num = 1;
			else 
				num = 0;
		}
		
		
		return arr;
		
	}//makeCandidate
	
	
	//자기 자신의 인덱스를 기준으로 바꿔야하는 값을 가진 가장 가까운 인덱스가 어디에 있는지 찾는 메서드
	static int findIndex(int ownIndex, int [] arr ) {
		int num = arr[ownIndex];
		int findNum = 0;
		if (num == 0)
			findNum = 1;
		
		int index = 0;
		for(int i=ownIndex+1; i<N; i++) {
			if(arr[i] == findNum) {
				index = i;
				break;
			}
		}
		
		return index;
		
	}//findIndex
	
	
	//swap 메서드 
	static void swap (int ownIndex, int findIndex,int[] arr ) {
		
		int temp = arr[ownIndex];
		arr[ownIndex] = arr[findIndex];
		arr[findIndex] =temp;
		
	}//swap
	
	//문자열에 있는 0의 개수 카운트
	static int get0Num(int [] arr) {
		int count = 0;
		
		for(int i=0; i<N; i++) {
			if(arr[i]==0)
				count ++;
		}
		
		return count;
	}
	

	

}
