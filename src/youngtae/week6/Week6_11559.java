package youngtae.week6;

import java.io.*;
import java.util.*;

public class Week6_11559 {

	static final int LINE = 12, ROW = 6;
	static int result;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] visited;
	static char[][] map;
	static PriorityQueue<Node> popPyuo;

	static class Node implements Comparable<Node> {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if(this.y == o.y) {
				return this.x - o.x;
			}

			return this.y - o.y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new char[LINE][ROW];
		result = 0;
		popPyuo = new PriorityQueue<>();  // 터뜨린 뿌요 위치 저장

		for(int i = 0; i < LINE; i++) {
			map[i] = br.readLine().toCharArray();
		}

		while(true) {
			// pyuo 찾기
			// pop -> down 반복
			// pop.count == 0 -> exit
			find();
			pop();
			if(popPyuo.size() == 0) {
				break;
			}
			result += 1;
			down();
		}

		bw.write(String.valueOf(result));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void find() {  // visited[뿌요 위치] == tru
		visited = new boolean[LINE][ROW];
		for(int i = 0; i < LINE; i++) {
			Arrays.fill(visited[i], true);
			for(int j = 0; j < ROW; j++) {
				if(map[i][j] != '.') {
					visited[i][j] = false;
				}
			}
		}
	}

	private static void down() {
		// 터뜨린 뿌요의 행 좌표(popPyuo)를 이용해서 down 시키기
		// 맨 바닥쪽에서 터진 곳부터 down시킨다.
		while(!popPyuo.isEmpty()) {
			Node now = popPyuo.poll();
			int x = now.x;
			int y = now.y;

			for(int i = x; i > 0; i--) {
				map[i][y] = map[i-1][y];
			}
			map[0][y] = '.';  // 한 칸 이상 down 했기에 가장 위(map[0][y])에 빈 공간('.') 추가
		}
	}

	private static void pop() {
		for(int i = LINE-1; i >= 0; i--) {
			for(int j = 0; j < ROW; j++) {
				if(!visited[i][j]) {
					BFS(i, j);  // BFS로 터질 뿌요 찾기
				}
			}
		}
	}

	private static void BFS(int i, int j) {
		Deque<int[]> dq = new ArrayDeque<>();
		Deque<int[]> recordDq = new ArrayDeque<>();
		dq.add(new int[] {i, j});
		recordDq.add(new int[] {i, j});
		visited[i][j] = true;
		char puyo = map[i][j];

		while(!dq.isEmpty()) {
			int[] now = dq.poll();

			for(int k = 0; k < 4; k++) {
				int nx = now[0] + dx[k];
				int ny = now[1] + dy[k];

				if(nx < 0 || ny < 0 || nx >= LINE || ny >= ROW || visited[nx][ny]) {
					continue;
				}

				if(map[nx][ny] == puyo) {
					visited[nx][ny] = true;
					dq.add(new int[] {nx, ny});
					recordDq.add(new int[] {nx, ny});
				}
			}
		}

		if(recordDq.size() >= 4) {
			for(int[] arr : recordDq) {
				int x = arr[0];
				int y = arr[1];
				map[x][y] = '.'; // 터뜨리기
				popPyuo.add(new Node(x, y));  // 터뜨린 뿌요 위치 저장
			}
		}
	}

}