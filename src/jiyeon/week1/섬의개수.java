package jiyeon.week1;

import java.util.*;
import java.io.*;

public class 섬의개수 {
	
	//pseudo code 
	//1. w, h 입력 받기 
	//1-1. w=0, h=0일 경우 종료
	//2. 배열을 통해 그래프로 만들기 
	//3. dfs이용해서 탐색 , visited 배열을 이용해서 모두 방문했을 때 까지 dfs 반복 
	//3-1. dfs가 완료될때 마다 count +1
	//4. 테스트 케이스가 끝날때 마다섬의 수 출력

	static int [][] graph; 
	static boolean [][] visited; 
	static int w,h;
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<Integer> results = new ArrayList<>();
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

		while(true) {
			
			//1. w, h 입력 받기 
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			//1-1. w=0, h=0일 경우 종료
			if(w==0 && h==0)
				break;
			
			//2. 배열을 통해 그래프로 만들기 
			graph = new int[h+2][w+2]; //영역을 넘어가는걸 미리 방지하기 위해서 map을 확장
			visited = new boolean[h+2][w+2];
			
			for(int i=1; i<=h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=w; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//3. dfs이용해서 탐색 , visited 배열을 이용해서 모두 방문했을 때 까지 dfs 반복 
			int count = 0 ;
			for(int i=1; i<=h;i++) {
				for(int j=1;j<=w; j++) {
					if(!visited[i][j] && graph[i][j]==1) {
						dfs(j,i);
						count++;
					}
				}
			}

			results.add(count);
			
		}//while
		
		for(int result : results) {
			System.out.println(result);
		}
		
	}//main
	
	static void dfs(int w, int h) {
		int [] dx = {-1,0,1,1,1,0,-1,-1};
		int [] dy = {-1,-1,-1,0,1,1,1,0};

		visited[h][w] = true;
		
		for(int i=0;i<8;i++) {
			int currentX = w+dx[i];
			int currentY = h+dy[i];
			
			if(!visited[currentY][currentX] && graph[currentY][currentX]==1) {
				visited[currentY][currentX] = true;
				dfs(currentX, currentY);
			}
		}
		
	}//dfs

}
