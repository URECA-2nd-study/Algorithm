package jiyeon.week1;

import java.util.*;
import java.io.*;

public class 토마토 {

	//pseudo code 
	//1. 초기 조건 확인
	//1-1. 탐색을 했는데도 안익은 토마토가 있다면 -> 절대로 안 익는 조건 -> -1 리턴 
	//2. """"bfs""""로 탐색
	//2-1. 탐색 중에 위,아래,왼,오,앞,뒤에 익은 토마토가 있을 경우 해당 토마토는 익음.
	//3. 결과 출력
	
	static int[][][] box;
	static int n,m,h;
	
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
		Queue<int[]> queue = new LinkedList<>();
		
		
		for (int i = 1; i <= h; i++) { 
		    for (int j = 1; j <= n; j++) { 
		        st = new StringTokenizer(br.readLine());
		        for (int k = 1; k <= m; k++) { 
		            box[i][j][k] = Integer.parseInt(st.nextToken());
		            if(box[i][j][k] == 1)
		            	queue.add(new int[] {k,j,i});
		            	
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

		//2. """"bfs""""로 탐색
		
		int day = bfs(queue);
		
		
		//3. 결과 출력
		if(chkAllRipe())
			System.out.println(day);
		else
			System.out.println(-1);
		
		
	}
	
	
	//1.초기 조건 체크 

	//1-1. 토마토가 절대로 안익는 조건인지 확인
	static boolean chkAllRipe() {
		for(int i=1; i<=h; i++) {
			for(int j=1; j<=n ; j++) {
				for(int k=1; k<=m; k++) {
					if(box[i][j][k]==0)
						return false;
				}
			}
		}
		return true;
	}
	

	//bfs
	static int bfs(Queue<int []> queue) {
		int day = -1;
		
		while(!queue.isEmpty()) {
			int len = queue.size();
			day++;
			
			for(int i=0; i<len; i++) {
				int [] tomato = queue.poll();
				int x = tomato[0];
				int y = tomato[1];
				int z = tomato[2];
				
				for(int j=0; j<6; j++) {
					int curX = x+dx[j];
					int curY = y+dy[j];
					int curZ = z+dz[j];
					
					if(box[curZ][curY][curX]==0) {
						box[curZ][curY][curX] = 1;
						queue.add(new int[] {curX,curY,curZ});
					}
				}
			}
			
		}
		return day;
	}
	
	

}
