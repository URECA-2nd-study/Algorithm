package week17;

import java.io.*;
import java.util.*;

public class boj_22251 {

	static int N,K,P,X;
	static int[][] cnt = new int [10][10]; //num[i][j] : i -> j로 바꾸기 위한 반전횟수
	static int ans; 
	static String[] strArr;
	static int [] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		String strX = st.nextToken();
		X = Integer.parseInt(strX);
		
		strArr = strX.split("");
		arr = new int[K];
		int strLen = strArr.length;
		
		for(int i=0; i<strArr.length; i++) {
			arr[i+K-strLen] = Integer.parseInt(strArr[i]);
		}
		
		init();
		
		int [] temp = new int[K];
		dfs(0,temp);
		System.out.println(ans);

	}
	
	//모든 N자리 숫자를 검사 
	public static void dfs(int index, int[] buf) {
		if(index == K) {

			int tempCnt = 0;
			int tempNum = 0;
			
			for(int k=0; k<K; k++) {
				tempNum += buf[k] * Math.pow(10, K-k-1);
			}
			
			
			//빌딩 층수보다 크면 검사 X 
			if(tempNum>N || tempNum == X || tempNum == 0) {
				return;
			}
			
			for(int k=0; k<K; k++) {
				tempCnt += cnt[arr[k]][buf[k]];
			}
			
			if(tempCnt>P) {
				return;
			}
			else {
				ans ++;
			}
			
			return;
		}
		
		for(int i=0; i<10; i++) {
			buf[index] = i; 
			dfs(index+1, buf);
		}
	}
	
	
	//i -> j로 바꾸기 위한 반전횟수를 구하기 위한 초기 메서드
	public static void init() {
		
		int [] num = new int[10];
		
		
		num[0] = 0b1110111;
		num[1] = 0b0010010;
		num[2] = 0b0111101;
		num[3] = 0b0111011;
		num[4] = 0b1011010;
		num[5] = 0b1101011;
		num[6] = 0b1101111;
		num[7] = 0b0110010;
		num[8] = 0b1111111;
		num[9] = 0b1111011;
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				cnt[i][j] = Integer.bitCount(num[i] ^ num[j]);
			}
		}
		
	}

}
