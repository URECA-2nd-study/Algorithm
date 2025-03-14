import java.io.*;
import java.util.*;

public class PuyoPuyo_11559 {

	private static char[][] map;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		map = new char[12][6];
		visited = new boolean[12][6];

		for (int r = 0; r < 12; r++) {
			String str = br.readLine();

			for (int c = 0; c < 6; c++) {

				map[r][c] = str.charAt(c);
            }

		}

		int time = 0;

		while (true) {
			boolean isPossible = false;
			ArrayList<int[]> bomb = new ArrayList<>();

			for (int r = 0; r < 12; r++) {

				for (int c = 0; c < 6; c++) {

                    // 뿌요이면서, 이전에 선택되지 않은 뿌요이라면 bfs 탐색을 실시합니다.
					if (map[r][c] >= 'A' && map[r][c] <= 'Z' && !visited[r][c]) {
						ArrayList<int[]> select = bfs(new int[] { r, c, map[r][c] });

                        // 만약 자기와 같은 색이 4개 이상일 겨웅 연쇄적으로 터지게 합니다.
						if (select.size() >= 4) {
							isPossible = true;
							bomb.addAll(select);
						}
					}

				}

			}

            // 더 이상 터질게 없다면 while문을 종료 시킵니다.
			if (!isPossible)
				break;

            // 터지고 난 것을 .으로 바꿉니다. 그 이후 중력을 적용합니다.
			process(bomb);
			time++;
			visited = new boolean[12][6];

		}

		System.out.println(time);

	}

	private static void process(ArrayList<int[]> arr) {

		for (int[] w : arr) {
			map[w[0]][w[1]] = '.';
		}

        // 중력 적용 메서드    
		delete();

	}

    // 뿌요 바로 빝에 빈 공간이 있으면 중력으로 인해 떨어져야함.
	private static void delete() {

		for (int c = 0; c < 6; c++) {

			for (int r = 11; r > 0; r--) {

				if (map[r][c] == '.') {
                    
					for (int i = r - 1; i >= 0; i--) {

						if (map[i][c] != '.') {
							map[r][c] = map[i][c];
							map[i][c] = '.';
							break;
						}

					}

				}

			}

		}


	}

	private static ArrayList<int[]> bfs(int[] start) {
		visited[start[0]][start[1]] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);

		ArrayList<int[]> bomb = new ArrayList<>();

		while (!q.isEmpty()) {

			int[] curr = q.poll();
			bomb.add(curr);

			for (int i = 0; i < 4; i++) {

				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && map[nr][nc] == curr[2] && !visited[nr][nc]) {

					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc, map[nr][nc] });
				}

			}
		}

		return bomb;

	}

}
