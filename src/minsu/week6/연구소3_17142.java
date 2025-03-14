import java.io.*;
import java.util.*;

public class 연구소3_17142 {

	private static int minTime = 987654321;
	private static int N, M;
	private static int[][] map;
	private static ArrayList<int[]> virus = new ArrayList<>();
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		int zeroCnt = 0;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

                // 바이러스가 퍼질 수 있는 빈공간의 총 갯수를 저장
				if (map[r][c] == 0)
					zeroCnt++;

                // 바이러스일 경우 virus에 저장하고, 해당 map 값을 -1로 변경하여
                // 바이러스임을 표시
				if (map[r][c] == 2) {
					virus.add(new int[] { r, c });
					map[r][c] = -1;
				}

			}
		}

        // 만약 바이러스가 퍼질 공간이 없다면 0을 출력하고 종료
		if (zeroCnt == 0) {
			System.out.println(0);
			return;
		}

		combi(0, 0, new int[M], zeroCnt);
		System.out.println(minTime == 987654321 ? -1 : minTime);

	}

    // 어떤 바이러스들을 선택해서 퍼트릴껀지 조합으로 구현합니다.
	private static void combi(int idx, int depth, int[] result, int zeroCnt) {

		if (depth == M) {
            
            // 바이러스들을 M개 만큼 선택했다면 bfs 탐색을 실시
			bfs(new int[N][N], result, zeroCnt);

		} else {

			for (int i = idx; i < virus.size(); i++) {
				result[depth] = i;
				combi(i + 1, depth + 1, result, zeroCnt);
			}

		}

	}

	private static void bfs(int[][] copyMap, int[] select, int zeroCnt) {


        // 일단 copyMap을 만듭니다.
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, N);
		}

		Queue<int[]> q = new ArrayDeque<>();

        // 초기 바이러스들을 q에 넣습니다. visit boolean 배열을 생성하지 않으려고
        // 초기 바이러스들의 map의 값들을 -2로 바꾸었습니다.
		for (int s : select) {
			int[] curr = virus.get(s);
			q.offer(new int[] { curr[0], curr[1], 0 });
			copyMap[curr[0]][curr[1]] = -2;
		}

		int time = 0;

		while (!q.isEmpty()) {

			int[] curr = q.poll();

            // 만약 최소 시간보다 오래 걸린다면 그냥 종료 시킵니다.
			if (time >= minTime)
				return;

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

                // map 범위 안에 있으면서, 바이러스가 없는 빈공간이거나, 비활성화된 바이러스일 경우
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && (copyMap[nr][nc] == 0 || copyMap[nr][nc] == -1)) {

                    // 만약 빈공간일 경우
                    // 총 빈 공간의 갯수를 하나씩 감소
					if (copyMap[nr][nc] == 0) {
						zeroCnt--;

                        // time은 빈공간에 바이러스가 들어갈 때마다 업데이트 해줍니다.
                        // 비활성화된 바이러스에도 업데이트를 하면 로직이 잘못됨
						time = Math.max(time, curr[2] + 1);
					}
                    
                    // 비활성화 된 바이러스이든 빈 공간이든 그냥 그 위치까지 걸리는 시간으로 값을 변경
					copyMap[nr][nc] = curr[2] + 1;
					q.offer(new int[] { nr, nc, copyMap[nr][nc] });

				}

			}

		}

        // 만약 모든 공간이 벽 또는 바이러스라면 최소 걸리는 시간을 업데이트 해줍니다.
		if (zeroCnt == 0)
			minTime = Math.min(minTime, time);

	}
}
