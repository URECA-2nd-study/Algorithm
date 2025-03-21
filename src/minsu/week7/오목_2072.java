import java.io.*;
import java.util.*;

public class 오목_2072 {

	private static int result = 987654321;
	// map에는 흰돌, 검은돌을 기록하고 board에는 몇 번째 수인지 기록한다.
	private static int[][] map = new int[21][21];
	private static int[][] board = new int[21][21];
	// 오목 탐색 방향 현재 돌 기준으로 세로, 우하, 좌하, 오른쪽 가로
	private static int[][] delta = { { 1, 0 }, { 1, 1 }, { 1, -1 }, { 0, 1 } };

	private static ArrayList<int[]> dolls = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// 출력문
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int cnt = 1;
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = cnt++;
			map[r][c] = ((i & 1) == 1) ? 1 : 2;
			dolls.add(new int[] { r, c });
		}

		int[] info = null;

		for (int[] curr : dolls) {
			
			// 현재 돌 기준으로 delta 범위 탐색
			for (int i = 0; i < 4; i++) {
				// 탐색 후 결과 값을 info 배열에 담음
				// info 배열에는 (좌표, 해당 돌에서 현재 delta 범위 탐색을 했을 때, 나오는 돌의 갯수를 담고 있음)
				info = dfs(curr[0], curr[1], i, map[curr[0]][curr[1]]);

				// 만약 돌이 5개가 나왔다면
				// 이전 delta위치에 있던 돌이 자기 돌이 아니여야
				// 6목이 아님.
				if (info[2] == 5 && map[curr[0] - delta[i][0]][curr[1] - delta[i][1]] != map[curr[0]][curr[1]]) {
					// 5목이 한 개 이상 존재할 가능성이 있으므로
					// 5목이 됐을 때, 탐색 범위에서는 가장 크고, 탐색해서 나왔던 가장 큰 값들 중에서는 가장 작아야함.
					search(info, i);
				}

			}

		}

		if (result == 987654321)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	private static void search(int[] info, int state) {

		int nr = info[0];
		int nc = info[1];
		int max = board[nr][nc];
		
		for (int i = 0; i < 4; i++) {
			nr += delta[state][0];
			nc += delta[state][1];
			max = Math.max(max, board[nr][nc]);
			
		}

		result = Math.min(max, result);

	}

	private static int[] dfs(int r, int c, int dir, int color) {

		int nr = r;
		int nc = c;
		int nd = dir;
		int cnt = 1;
		int[] info = null;

		while (true) {

			nr += delta[nd][0];
			nc += delta[nd][1];

			if (!(nr >= 1 && nr < 20 && nc >= 1 && nc < 20)) {
				break;
			}

			if (map[nr][nc] != color) {
				break;
			}

			cnt++;
		}

		info = new int[] { r, c, cnt };

		return info;

	}

}