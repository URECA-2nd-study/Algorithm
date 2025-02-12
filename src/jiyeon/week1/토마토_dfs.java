package jiyeon.week1;

import java.util.*;
import java.io.*;

public class 토마토_dfs {

	//pseudo code 
	//1. 초기 조건을 확인하기
	//1-1. 익지 않은 토마토들이 토마토가 없는 칸에 둘러 싸여있지 않은가? -> 이 경우 -1 리턴
	//1-2. 토마토가 모두 익지는 않았는가? -> 이 경우 0 리턴 
	//2. dfs로 탐색
	//2-1. 탐색 중에 위,아래,왼,오,앞,뒤에 익은 토마토가 있을 경우 해당 토마토는 익는다는 정보를 따로 저장
	//2-2. 모든 토마토 탐색 완료 후 저장해놓은 정보를 기반으로 토마토를 익힌다. 
	//3. 모든 토마토가 익을 때 까지 반복 후 year 리턴 
	
	static int[][][] box;
	static int n,m,h;
	static boolean [][][] visited;
	static boolean [][][] ripes; 
	
	//위,아래,왼,오,앞,뒤
	static int [] dx = {0,0,-1,1,0,0};
	static int [] dy = {0,0,0,0,1,-1};
	static int [] dz = {-1,1,0,0,0,0};
	
	public static void main(String[] args) throws IOException{
		
		//입력받기 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		box = new int[h+2][n+2][m+2];
		visited = new boolean[h+2][n+2][m+2];
		ripes = new boolean[h+2][n+2][m+2];
		
		
		for (int i = 1; i <= h; i++) { 
		    for (int j = 1; j <= n; j++) { 
		        st = new StringTokenizer(br.readLine());
		        for (int k = 1; k <= m; k++) { 
		            box[i][j][k] = Integer.parseInt(st.nextToken());
		        }
		    }
		}

		// 테두리 부분을 -1로 초기화
		for (int i = 0; i <= h + 1; i++) {
		    for (int j = 0; j <= n + 1; j++) {
		        for (int k = 0; k <= m + 1; k++) {
		            if (i == 0 || i == h + 1 || j == 0 || j == n + 1 || k == 0 || k == m + 1) {
		                box[i][j][k] = -1; 
		            }
		        }
		    }
		}

		
		int day=0;
		
		while(true) {
			
			chkNoRipe = false;
			chkAllRipe = true;
			visited = new boolean[h+2][n+2][m+2];
			ripes = new boolean[h+2][n+2][m+2];
			
			
			dfs(1,1,1);
			
			//초기 조건 체크 
			if(chkNoRipe) {
				System.out.println(-1);
				break;
			}else if(chkAllRipe && day==0) {
				System.out.println(0);
				break;
			}else if(chkAllRipe && day>0){
				System.out.println(day);
				break;
			}
			
			//토마토 익히기 
			ripeTomato();
			day++;
		}

	}
	
	
	//1.초기 조건 체크 

	//1-1. 토마토가 절대로 안익는 조건인지 확인
	static boolean chkNoRipe = false;
	
	//1-2. 토마토가 모두 익은 상태인지 확인
	static boolean chkAllRipe = false;
	
	
	//dfs 함수 
	static void dfs(int x, int y, int z) {
		if(box[z][y][x] == 0 ) 
			chkAllRipe = false;

			
		visited[z][y][x] = true;
		
		for(int i=0; i<6;i++) {
			int curX = x+dx[i];
			int curY = y+dy[i];
			int curZ = z+dz[i];
			
			if(box[z][y][x]==0 && box[curZ][curY][curX]==1) {
				ripes[z][y][x] = true;
				chkNoRipe = false;
			}
			
			
			if(!visited[curZ][curY][curX] && box[curZ][curY][curX]!=-1) {
				visited[curZ][curY][curX] = true;
				dfs(curX,curY,curZ);
			}
			
		}
		
	}
	
	
	//2-2. 토마토를 익히는 함수 
	
	static void ripeTomato() {
		for(int i=1; i<=h; i++) {
			for(int j=1; j<=n ; j++) {
				for(int k=1; k<=m; k++) {
					if(ripes[i][j][k])
						box[i][j][k] = 1;
				}
			}
		}
		
	}
	

}
