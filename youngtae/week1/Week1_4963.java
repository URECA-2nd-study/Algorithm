package week1;

import java.io.*;
import java.util.*;

public class Week1_4963 {

	static int W, H;
	static int[] dx = {0, 1, 0, -1, 1, -1, -1, 1};  // 상하좌우 + 대각선
	static int[] dy = {1, 0, -1, 0, 1, -1, 1, -1};
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		/**
		 * 섬의 경로를 찾은 것이 아닌, 모든 경로를 탐색해 총 섬의 개수를 파악하는 문제.
		 * 하나의 섬에서 주변 섬을 탐색하는 방법으로 BFS로 풀이 선택.
		 */
		while(true) {
			str = new StringTokenizer(br.readLine());
			W = Integer.parseInt(str.nextToken());
			H = Integer.parseInt(str.nextToken());

			if(W == 0 && H == 0) {  // 입력의 마지막 줄일 때 종료
				break;
			}

			map = new int[H][W];  // 지도 초기화
			visited = new boolean[H][W];  // 섬 방문 여부 체크를 위한 visited 초기화

			for(int i = 0; i < H; i++) {
				str = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(str.nextToken());  // 입력에 따른 지도 그리기
				}
			}

			int result = 0;  // 총 섬의 개수
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {  // 해당 위치가 섬일 때, 방문하지 않았다면 BFS 탐색
						BFS(i, j);
						result += 1;  // 섬의 개수 1 증가
					}
				}
			}

			sb.append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void BFS(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i, j});
		visited[i][j] = true;

		while(!q.isEmpty()) {
			int[] now = q.poll();

			for(int k = 0; k < 8; k++) {  // 상하좌우 + 대각선을 다 확인하며 주변에 섬이 있는지 찾기
				int nx = now[0] + dx[k];
				int ny = now[1] + dy[k];

				if(nx < 0 || nx >= H || ny < 0 || ny >= W || visited[nx][ny]) {  // Array Index Out 체크 + 방문한 섬인지
					continue;
				}

				if(map[nx][ny] == 1) {  // 해당 좌표가 섬인지 확인
					visited[nx][ny] = true;  // 미리 방문 체크
					q.add(new int[] {nx, ny});  // 방문하지 않은 곳이라면 방문을 위해 queue 삽입
				}
			}
		}
	}

}
