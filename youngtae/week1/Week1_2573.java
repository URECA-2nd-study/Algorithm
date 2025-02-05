package week1;

import java.io.*;
import java.util.*;

public class Week1_2573 {

	static int N, M, year, area, size;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] map, tmp;
	static boolean[][] visited;
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());

		map = new int[N][M];		// 빙산 지도 배열
		tmp = new int[N][M];		// 빙산를 한번에 녹이기 위해, 각 빙산이 줄어들 크기 저장 배열
		visited = new boolean[N][M];

		for(int i = 0; i < N; i++) {
			str = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int status = Integer.parseInt(str.nextToken());

				if(status != 0) {		// 바다가 아닌 빙산는 바로 Queue에 삽입
					q.add(new int[] {i, j});
				}
				map[i][j] = status;
			}
		}

		BFS();
		if(area < 2) {		// BFS가 끝났는데 area(빙산 구역)이 2개 미만이라면 다 녹을 떄까지 두 덩이 이상이 아님
			sb.append("0");
		} else {
			sb.append(year);
		}

		br.close();
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

	private static void BFS() {
		year = 0;
		size = 0;

		while(!q.isEmpty()) {
			size = q.size();		// 1년 씩 빙산를 녹이기 위해 Queue size() 저장

			areaCheck();		// 현재 빙산 구역 확인

			if(area != 1) {		// 빙산이 다 녹았거나(area==0) 두 구역(area >= 2)일 때 전체 종료
				break;
			}
			year += 1;		// 빙산이 남았고 한 구역이기 때문에 1년 증가

			while(size-- > 0) {
				int[] now = q.poll();

				for(int k = 0; k < 4; k++) {
					int nx = now[0] + dx[k];
					int ny = now[1] + dy[k];

					if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
						continue;
					}

					if(map[nx][ny] == 0) {		// 주변이 빙산이 아닌 바다라면
						tmp[now[0]][now[1]] += 1;		// 빙산이 줄어들 크기를 1 증가
					}

				}
			}

			// 1년치 빙산이 줄어들 크기를 모두 계산한 후에, tmp를 다 돌면서 빙산 크기를 줄이기
			for(int a = 0; a < N; a++) {
				for(int b = 0; b < M; b++) {
					if(tmp[a][b] != 0) {
						// 빙산이 높이는 음수가 없기에 다 녹으면 0, 남아 있다면 해당 값으로 변경
						map[a][b] = map[a][b] - tmp[a][b] < 0 ? 0 : map[a][b] - tmp[a][b];
						tmp[a][b] = 0;		// 줄어들 빙산 크기 0으로 초기화
					}
					if(map[a][b] >= 1) {		// 녹은 후 빙산이 아직 남아 있다면 q에 다시 넣기
						q.add(new int[] {a, b});
					}
				}
			}


		}
	}

	private static void areaCheck() {
		visited = new boolean[N][M];		// 빙산 방문 초기화
		area = 0;		// 구역 변수 초기화

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] > 0) {		// 방문하지 않은 빙산이라면 또 다른 BFS로 구역 개수 탐색
					BFS2(i, j);
					area += 1;		// 한 번의 BFS가 끝날 때마다 구역 1 증가
				}
			}
		}
	}

	private static void BFS2(int i, int j) {
		Queue<int[]> q2 = new LinkedList<>();		// 빙산 구역을 나누기 위해 새로운 Queue
		q2.add(new int[] {i, j});
		visited[i][j] = true;		// 현재 빙산 방문 처리

		while(!q2.isEmpty()) {
			int[] now = q2.poll();

			for(int k = 0; k < 4; k++) {
				int nx = now[0] + dx[k];
				int ny = now[1] + dy[k];

				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
					continue;
				}

				if(map[nx][ny] > 0) {
					visited[nx][ny] = true;
					q2.add(new int[] {nx, ny});
				}
			}
		}
	}

}