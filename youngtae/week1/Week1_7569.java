package week1;

import java.io.*;
import java.util.*;

public class Week1_7569 {

	static int N, M, H, day, size;
	static int[] dx = {0, 0, 0, 1, 0, -1};
	static int[] dy = {0, 0, 1, 0, -1, 0};
	static int[] dz = {1, -1, 0, 0, 0, 0};
	static int[][][] boxs;
	static int GREEN_TOMATO;
	static boolean[][][] visited;
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		GREEN_TOMATO = 0;  // 익지 않은 토마토

		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());
		H = Integer.parseInt(str.nextToken());

		boxs = new int[H][M][N];
		visited = new boolean[H][M][N];

		for(int i = 0; i < H; i++) {
			for(int j = 0; j < M; j++) {
				str = new StringTokenizer(br.readLine());
				for(int k = 0; k < N; k++) {
					int status = Integer.parseInt(str.nextToken());  // 토마토 상태
					if(status == 1) { 	// 익은 토마토
						q.add(new int[] {i, j, k});		// 첫 BFS 탐색될 수 있도록 Queue에 먼저 삽입
						visited[i][j][k] = true;	// 다른 토마토에서 방문할 수 없도록 방문 처리
					} else if(status == 0) {	// 익지 않은 토마토
						GREEN_TOMATO += 1;		// 개수 체크
					}

					boxs[i][j][k] = status;
				}
			}
		}

		solution(sb);

		br.close();
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

	private static void solution(StringBuilder sb) {
		if(GREEN_TOMATO == 0) {		// 익지 않은 토마도가 하나도 없다면 BFS() 탐색 없이 바로 종료
			sb.append("0");
			return;
		}

		BFS();
		sb.append(GREEN_TOMATO > 0 ? "-1" : day);		// 익지 않은 토마토가 1개 이상이라면 -1, 그렇지 않다면 소요일을 sb에 담기
	}

	private static void BFS() {
		day = 0;		// 소요일 변수
		size = 0;		// Queue 사이즈 담을 변수
		while(!q.isEmpty()) {		// Queue가 빌 때까지 반복
			size = q.size();		// BFS 탐색을 시작하기 전 현재 Queue size를 저장해 1일 치만 탐색 가능하도록 함.

			while(size-- > 0) {		// 현 Queue size 만큼만 BFS 탐색
				int[] now = q.poll();

				for(int a = 0; a < 6; a++) {
					int nz = now[0] + dz[a];
					int nx = now[1] + dx[a];
					int ny = now[2] + dy[a];

					if(nz < 0 || nx < 0 || ny < 0 || nz >= H || nx >= M || ny >= N || visited[nz][nx][ny]) {
						continue;
					}

					if(boxs[nz][nx][ny] == 0) {		// 익지 않은 토마토 발견
						visited[nz][nx][ny] = true;
						q.add(new int[] {nz, nx, ny});
						GREEN_TOMATO -= 1;		// 익지 않은 토마토 총 개수에서 1개 차감
					}
				}
			}

			day += 1;		// 소요일 증가

			if(GREEN_TOMATO <= 0) {		// 익지 않은 토마토가 다 익어졌다면 큐 종료
				break;
			}
		}
	}

}
