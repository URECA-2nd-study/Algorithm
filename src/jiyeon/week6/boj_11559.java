package jiyeon.week6;
import java.util.*;
import java.io.*;

/*
 * 같은 색의 뿌요가 4개 이상 모여있는지 확인하면서 좌표 임시저장 -> 4개 이상 모여있으면 연쇄 -> .으로바꾸기
 * 맵을 한 바퀴 돌고 난 후 마지막으로 뿌요 내리기 
 * 
 * */


public class boj_11559 {
	
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	
	static char [][] map = new char [12][6];
	static boolean [][] visited = new boolean [12][6];
	static Queue<int[]> puyo = new ArrayDeque<>(); //터뜨려야하는 뿌요 좌표
	static Queue<int[]> temp = new ArrayDeque<>(); //현재 탐색 임시 저장 
	static PriorityQueue<int[]> down = new PriorityQueue<>(new Comparator<int[]>() {

		@Override
		public int compare(int[] o1, int[] o2) {
			if(o1[0] == o2[0]) return o1[1] - o2[1];
			return o1[0] - o2[0];
		}
	}); //내려가야하는 뿌요 좌표, y 좌표가 작은 순으로 정렬하여 뿌요가 정확하게 내려가도록 함
	
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<12; i++) {
			String str = br.readLine();
			for(int j=0; j<6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int count = 0;
		
		while(true) {
			boolean isCrashed = false;
			
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					
					if(map[i][j]!='.' && !visited[i][j]) { 
						bfs(i,j);
						
						if(puyo.size()>=4)
							isCrashed = true;
						
						puyoCrash();
					}
					
				}
			}//map 순회 
			
			
			if(isCrashed)
				answer += 1;
			else break; //더 이상 연쇄작용이 일어나지 않으면 break
			
			puyoDown();
			
			for(int i=0; i<12; i++) {
				Arrays.fill(visited[i], false);
			}
			
			count ++;
			
		}
		
		System.out.println(answer);
		
		
	}//main 
	
	
	//같은 색의 뿌요가 4개 이상 모여있는지 확인
	static void bfs(int y, int x) {
		
		char color = map[y][x];
		int puyoNum = 1; 
		
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {y,x});
		visited[y][x] = true; 
		puyo.add(new int[] {y,x});
		
		while(!queue.isEmpty()) {
			
			int [] current = queue.poll();
			int curY = current[0];
			int curX = current[1];
			
			for(int i=0; i<4; i++) {
				int nextY = curY + dy[i];
				int nextX = curX + dx[i];
				if(nextY>=0 && nextY<12 && nextX>=0 && nextX<6) {
					char nextColor = map[nextY][nextX];
					
					if(!visited[nextY][nextX] && color==nextColor) {
						puyoNum += 1;
						queue.add(new int[] {nextY, nextX});
						puyo.add(new int[] {nextY,nextX});
						visited[nextY][nextX] =  true;
					}
				}
			}
			
		}
	}//bfs
	
	
	//뿌요 터뜨리기
	//뿌요가 4개 이상 연속되어 있으면 터뜨리고, 아니면 큐 비우기 
	static void puyoCrash() {
		
		int size = puyo.size();
		if(size>=4) {
			
			while(!puyo.isEmpty()) {
				int [] coordi = puyo.poll();
				int y = coordi[0];
				int x = coordi[1]; 
				map[y][x] = '.';
				down.add(new int[] {y,x});
			}

			
		}
		else {
			puyo.clear();
		}
		
	}

	
	//뿌요 내리기 
	static void puyoDown() {

		while(!down.isEmpty()) {
			int [] coordi = down.poll();
			int y = coordi[0];
			int x = coordi[1]; 
			
			for(int i=y; i>0; i--) {
				map[i][x] = map[i-1][x];
			}
			map[0][x] = '.'; 
		}
		
		
	}//puyoDown
	
	

}
