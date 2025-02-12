package a0205;

import java.util.*;
import java.io.*;
import java.math.*;


/*
 * **아래는 제가 점화식을 짜기 위해 거친 과정입니다
 * 
 * dp[i][j] : 최대 무게가 i이고, j번째의 물건까지 제시되었을 때 가능한 최대 가치의 합 
 * bag[j] = {w,v} : j번째 물건의 무게 (w)와 가치(v)
 * bag[j][0] = w , bag[j][1] = v
 * 
 * 백준 예시 
 * bag[1] = {6,13}, bag[2]={4,8}, bag[3]={3,6}, bag[4]={5,12}
 * 
 * dp[1][1] = 0, dp[1][2] = 0, dp[1][3]=0, dp[1][4]=0
 * dp[2][1] = 0, dp[2][2] = 0, dp[2][3]=0, dp[2][4]=0
 * dp[3][1] = 0, dp[3][2] = 0, dp[3][3]=6, dp[3][4]=6
 * dp[4][1] = 0, dp[4][2] = 8, dp[4][3]=8, dp[4][4]=8
 * dp[5][1] = 0, dp[5][2] = 8, dp[5][3]=8, dp[5][4]=12
 * dp[6][1] = 13, dp[6][2] = 13, dp[6][3]=13, dp[6][4]=13
 * dp[7][1] = 13, dp[7][2] = 13, dp[7][3]=14, dp[7][4]=14
 * 
 * 점화식을 세워보자...
 * 
 * dp[i][j]가 변하는 경우의 수
 * 1. i=w 이고 bag[j][1] > dp[i][j-1] 일 때
 * 2. i>w 이고 dp[i-w][j-1]+bag[j][1] > dp[i][j-1] 일 때
 * ----
 * 3. i<w 이라면? 어차피 물건을 못넣기 때문에 dp[i][j] = dp[i][j-1] 이다. 
 * 
 * */


public class boj_12865 {
	
	static long[][]dp;
	static int[][]bag;
	static int n,k;
	

	public static void main(String[] args) throws IOException{

		//입력 받기 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 물품의 수 
		k = Integer.parseInt(st.nextToken()); //준서가 버틸 수 있는 무게
		
		//index 1부터 시작하기 위해서 범위 +1
		dp = new long[k+1][n+1];
		bag = new int[n+1][2];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			bag[i][0] = Integer.parseInt(st.nextToken());
			bag[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//dp 점화식 적용
		
		for(int i=1; i<=k; i++) {
			for(int j=1; j<=n; j++) {
				
				if(i==bag[j][0])
					dp[i][j] = Math.max(bag[j][1], dp[i][j-1]);
				else if(i>bag[j][0])
					dp[i][j] = Math.max(dp[i-bag[j][0]][j-1]+bag[j][1],dp[i][j-1]);
				else 
					dp[i][j] = dp[i][j-1];
				
				
			}
		}//for 
		
		System.out.println(dp[k][n]);
		
	}

}
