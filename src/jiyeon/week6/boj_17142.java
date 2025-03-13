package jiyeon.week6;

import java.util.*;
import java.io.*;

public class boj_17142 {
	
	/*
	 * 바이러스 중 M개를 선택하여 활성화 -> 벽과 비활성 바이러스를 제외하고 모든 칸에 바이러스가 퍼지는데 최소 몇 초가 걸리는지 
	 * 
	 * 1. 바이러스에서 M개를 선택하는 모든 조합 구하기 O
	 * 2. 맵 카피 후 -> 각 조합마다 BFS 탐색을 진행하여 최소 시간 구하기 
	 * 3. 맵 초기화 
	 * 4. BFS를 돌았는데도 빈칸이 남아있으면 -1, 벽은 남아있어도 됨 
	 * */

	//0 = 빈 칸, 1 = 벽, 2 = 바이러스 
	static int[][] map;
	static boolean [][] visited;
	static int N, M, virusNum,emptyNum,emptyCopyNum;
	static ArrayList<int []> virus = new ArrayList<>(); //virus.get(i) = {y,x} i번째 바이러스의 좌표는 y,x
	static Queue<int []> combi = new ArrayDeque<>(); // 바이러스 선택 조합
	static PriorityQueue<Integer> answer = new PriorityQueue<>(); //결과를 담아놓는 큐
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				
				if(n==2) {
					virus.add(new int[] {i,j});
					virusNum++;
				}
				
				if(n==0) emptyNum++;
			}
		}//initialize & input
		
		if(emptyNum == 0) {
			System.out.println(0);  
		}
		else {
			int temp[] = new int[M]; //조합 임시 저장 배열
			getCombi(0, 0, temp);
			
			while(!combi.isEmpty()) {
				
				for(int i=0; i<N; i++) {
					Arrays.fill(visited[i], false);
				} // 방문 배열 초기화
				
				int [][] copyMap = copyMap();
				int [] selectedVirus = combi.poll();
				emptyCopyNum = emptyNum;
				
				int day = bfs(selectedVirus, copyMap);
				
//				System.out.println("===============");
//				System.out.println(Arrays.toString(selectedVirus));
//				System.out.println("day : "+ day);
				
				if(day>0 && emptyCopyNum<=0)
					answer.add(day);
				
			}//바이러스 선택 조합마다 탐색
			
			if(answer.isEmpty())
				System.out.println(-1);
			else
				System.out.println(answer.poll());
			
		}
		
		

	}//main 
	

	
	//BFS
	static int bfs(int [] selectedVirus, int[][] map) {
		
		int day = -1;
		
		Queue<int []> queue = new ArrayDeque<>();
		
		for(int n=0; n<M; n++) {
			int [] virusCoordi = virus.get(selectedVirus[n]);
			int y = virusCoordi[0];
			int x = virusCoordi[1];
			queue.add(virusCoordi);
			visited[y][x] = true;
		}
		
		while(!queue.isEmpty()) {
			
			int len = queue.size();
			
			day++;
			
			if(emptyCopyNum == 0) //더 이상 바이러스를 옮길 곳이 없으면 break
				break;
			
			for(int i=0; i<len; i++) {
				int [] current = queue.poll();
				int curY = current[0];
				int curX = current[1];
				
				for(int j=0; j<4; j++) {
					int nextY = curY+dy[j];
					int nextX = curX+dx[j];
					
					if(nextY>=0 && nextY<N && nextX>=0 && nextX<N && !visited[nextY][nextX] && map[nextY][nextX]!=1 ) {
						if(map[nextY][nextX] == 0)
							emptyCopyNum --;
							
						queue.add(new int[] {nextY,nextX});
						visited[nextY][nextX] = true;
					}
					
					// 비활성 상태가 활성상태바이러스를 만나면 활성상태가 됨 
				}
			}
			
		}
		
		return day;
		
	}//bfs
	
	
	//Case마다 초기 맵을 복사하기 위한 메서드 
	static int[][] copyMap(){
		int [][] copy = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j] = map[i][j];
			}
		}
		
		return copy;
	}//copyMap

	
	//바이러스 선택 조합을 구하는 메서드
	static void getCombi(int index, int start, int[] temp) {
		
		if(index == M) {
			int [] arr = new int[M];
			for(int i=0; i<M; i++) {
				arr[i] = temp[i];
			}
			combi.add(arr);
			return;
		}
		
		for(int i=start; i<virusNum; i++) {
			temp[index] = i;
			getCombi(index+1, i+1, temp);
		}
		
	
	}//getCombi

}
