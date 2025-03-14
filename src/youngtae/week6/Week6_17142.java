package youngtae.week6;

import java.io.*;
import java.util.*;

public class Week6_17142 {

	static int N, M, air, tmpAir;
	static List<int[]> virus, mixVirus;
	static boolean[] isUsed;
	static boolean[][] visited;
	static Deque<int[]> dq;
	static int[][] map, tmpMap;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	/**
	 * 0 - 빈칸, 1 - 벽, 2 - 비활성 바이러스, 3 - 활성 바이러스
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		virus = new ArrayList<>();
		mixVirus = new ArrayList<>();

		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());

		map = new int[N][N];

		int tmp = 0;
		for(int i = 0; i < N; i++) {
			str = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				tmp = Integer.parseInt(str.nextToken());
				if(tmp == 2) {  // 모든 바이러스 위치 저장
					virus.add(new int[] {i, j});
				}
				if(tmp == 0) { // 모든 바이러스 복제 공간(빈 공간) 위치 저장
					air += 1;
				}
				map[i][j] = tmp;
			}
		}
		int result = Integer.MAX_VALUE;
		if(air == 0) {  // 모든 곳이 벽 또는 바이러스로 이루어져 있으면 0 출력
			result = 0;
		} else {
			isUsed = new boolean[virus.size()];
			int[] arr = new int[0];
			backTracking(0, arr);  // 백트래킹을 이용해 M개의 활성 바이러스 조합 뽑기

			int st = 0;
			int time = 0;
			while (true) {
				if (st == mixVirus.size())
					break;
				visited = new boolean[N][N];

				tmpAir = air;
				copyMap();
				activVirus(st);

				time = sol();
				if (time != -1) {
					result = Math.min(result, time);
				}

				st++;
			}
		}

		bw.write(result == Integer.MAX_VALUE ? "-1" : String.valueOf(result));
		bw.flush();
		br.close();
		bw.close();
	}

	private static void backTracking(int k, int[] arr) {
		if(k == M) {
			int[] tmp = new int[M];
			for(int i = 0; i < M; i++) {
				tmp[i] = arr[i];
			}
			mixVirus.add(tmp); // 각 조합 저장
			return;
		}

		for(int i = 0; i < virus.size(); i++) {
			if(!isUsed[i]) {
				if(k == 0) arr = new int[M];

				int[] now = virus.get(i);

				// 1차원 배열에 2차원 좌표를 저장하기 위해 추가 계산
				// 연구소 크기가 최대 50*50이기 때문에 안전하게 100을 곱한다.
				arr[k] = (now[0] * 100) + now[1];
				if(k != 0 && arr[k-1] > arr[k]) continue;
				isUsed[i] = true;
				backTracking(k+1, arr);
				isUsed[i] = false;
			}
		}
	}

	private static int sol() {
		int day = 0;

		while(true) {
			int size = dq.size();  // 복제 시작 전 현재 있는 활성 바이러스 수
			if(tmpAir == 0) break;  // 더 이상 복제할 공간 없으면 종류
			while(size-- > 0) {
				int[] now = dq.poll();

				for(int k = 0; k < 4; k++) {
					int nx = now[0] + dx[k];
					int ny = now[1] + dy[k];

					if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

					if(tmpMap[nx][ny] == 0) {
						tmpAir -= 1;
						visited[nx][ny] = true;
						dq.add(new int[] {nx, ny});
					}

					if(tmpMap[nx][ny] == 2) {
						visited[nx][ny] = true;
						dq.add(new int[] {nx, ny});
					}
				}
			}

			if(dq.size() == 0) break;

			day += 1;  // 일 수 증가
		}

		return tmpAir == 0 ? day : -1;
	}

	private static void activVirus(int st) {
		dq = new ArrayDeque<>();
		int[] now = mixVirus.get(st);  // 조합(M개 활성 바이러스 위치)가 포함된 배열 꺼내기
		for(int i = 0; i < M; i++) {    // 1차원 배열에 저장된 2차원 바이러스 위치 해독
			int num = now[i];
			int x = num / 100;
			int y = num % 100;

			dq.add(new int[] {x, y});
		}
	}

	private static void copyMap() {
		tmpMap = new int[N][N];  // 초기 연구소 복사
		for(int i = 0; i < N; i++) {
			tmpMap[i] = map[i];
		}
	}
}