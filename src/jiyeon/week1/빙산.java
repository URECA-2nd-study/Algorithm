package jiyeon.week1;

import java.util.*;
import java.io.*;


public class Main {
	
	//pseudo code
	//0. n, m 입력 받고 -> 빙산 배열 저장 
	//1. 빙산이 몇 덩어리 인지 확인
	//1-1. 처음부터 빙산이 두 덩어리 이상이면 0을 출력
	//1-2. 빙산이 계속 한 덩어리 이다가 한 날 한시에 다 녹아서 갑자기 0 덩어리가 되면 0을 출력
	//2. 빙산이 한 덩어리 인 경우 빙산을 녹이기 

	static int n,m;
	static int [][] icebergs;
	static boolean [][] visited;
	static int [][] melts;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//0. n, m 입력 받고 -> 빙산 배열 저장 
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		icebergs = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				icebergs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int year = 0;
		
		while(true) {
			visited = new boolean[n][m];
			melts = new int[n][m];
			
			//1. 빙산이 몇 덩어리 인지 확인
			int num = getIceBergsNum();

			//1-1. 처음부터 빙산이 두 덩어리 이상이면 0을 출력
			//1-2. 빙산이 계속 한 덩어리 이다가 한 날 한시에 다 녹아서 갑자기 0 덩어리가 되면 0을 출력
			if(num>=2) {
				System.out.println(year);
				break;
			}else if(num==0 && year>0) {
				System.out.println(0);
				break;
			}

			//2. 빙산이 한 덩어리 인 경우 빙산을 녹이기 
			meltIceBerg();
			year++;
		}
		

	}//main
	
	
	//1. 빙산이 몇 덩어리 인지 확인
	static int getIceBergsNum() {
		int count = 0 ;
		for(int i=1; i<n; i++) {
			for(int j=1; j<m; j++) {
				if(!visited[i][j] && icebergs[i][j]>0) {
					dfs(j,i);
					count++;
				}
			}
		}
		return count;
	}//getIceBergsNum
	
	
	//dfs 함수 
	static void dfs(int w, int h) {
		int [] dx = {0,1,0,-1};
		int [] dy = {-1,0,1,0};
		
		visited[h][w] = true;
		
		for(int i=0; i<4; i++) {
			int currentX = w+dx[i];
			int currentY = h+dy[i];
			
			//녹는 빙산의 양을 melts 배열을 통해 count
			if(icebergs[currentY][currentX]==0)
				melts[h][w] += 1;

	        
			if(!visited[currentY][currentX] && icebergs[currentY][currentX]>0) {
				dfs(currentX,currentY);
			}
		}
		
	}//dfs
	
	
	//2. 얼음 녹이는 함수
	static void meltIceBerg() {
		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < m; j++) {
		            icebergs[i][j] = Math.max(icebergs[i][j] - melts[i][j], 0);
		    }
		}

	}
}
