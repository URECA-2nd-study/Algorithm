package youngtae.week10;

import java.io.*;
import java.util.*;

public class Week10_3192 {

	static int[][] map;
	static Queue<int[]> zeros;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		map = new int[3][3];
		zeros = new ArrayDeque<>();

		int num = 0;
		for(int i = 0; i < 3; i++) {
			str = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				num = Integer.parseInt(str.nextToken());
				if(num == 0) zeros.add(new int[] {i, j}); // 0 좌표 확인

				map[i][j] = num;
			}
		}

		int total = 0;

		// 한 가운데가 0인 경우 '각 대각선 / 2' == 한 가운데 값
		if(map[1][1] == 0) {
			if(map[0][0] != 0 && map[2][2] != 0) {
				total += map[0][0] + map[2][2];
			} else if(map[0][2] != 0 && map[2][0] != 0) {
				total += map[0][2] + map[2][0];
			} else if(map[0][1] != 0 && map[2][1] != 0) {
				total += map[0][1] + map[2][1];
			} else if(map[1][0] != 0 && map[1][2] != 0) {
				total += map[1][0] + map[1][2];
			}
		} else {
			// 대칭되는 두 곳의 합 == 한 가운데 * 2
			total = map[1][1] * 2;
		}

		int count = 0;
		// 최대 세 개의 숫자가 지워지기 떄문에 3곳을 확인함
		//하나의 행, 열 또는 대각선에 지워진 숫자가 1개인 경우 -> 총 합 - 나머지 두 개 수의 합 == 지워진 숫자
		// 하나의 행, 열 또는 대각선에 지워진 숫자가 2개 이상일 경우 -> 각 숫자 판별 불가 -> 다시 queue에 삽입
		while(count++ < 3) {
			if(zeros.isEmpty()) break;

			int[] now = zeros.poll();
			int x = now[0];
			int y = now[1];

			if(x == 1 && y == 1) {
				map[x][y] = total / 2;
				continue;
			}

			if(x == 0 && y == 0) {
				if(map[2][2] == 0) {
					zeros.add(now);
					continue;
				}

				map[x][y] = Math.abs(total-map[2][2]);
			} else if(x == 2 && y == 2) {
				if(map[0][0] == 0) {
					zeros.add(now);
					continue;
				}

				map[x][y] = Math.abs(total-map[0][0]);
			} else if(x == 0 && y == 2) {
				if(map[2][0] == 0) {
					zeros.add(now);
					continue;
				}

				map[x][y] = Math.abs(total-map[2][0]);
			} else if(x == 2 && y == 0) {
				if(map[0][2] == 0) {
					zeros.add(now);
					continue;
				}

				map[x][y] = Math.abs(total-map[0][2]);
			} else if(x == 1 && y == 0) {
				if(map[1][2] == 0) {
					zeros.add(now);
					continue;
				}

				map[x][y] = Math.abs(total-map[1][2]);
			} else if(x == 1 && y == 2) {
				if(map[1][0] == 0) {
					zeros.add(now);
					continue;
				}

				map[x][y] = Math.abs(total-map[1][0]);
			} else if(x == 0 && y == 1) {
				if(map[2][1] == 0) {
					zeros.add(now);
					continue;
				}

				map[x][y] = Math.abs(total-map[2][1]);
			} else if(x == 2 && y == 1) {
				if(map[0][1] == 0) {
					zeros.add(now);
					continue;
				}

				map[x][y] = Math.abs(total-map[0][1]);
			}
		}

		while(!zeros.isEmpty()) {
			int sum = 0;
			int[] now = zeros.poll();
			int x = now[0];
			int y = now[1];

			boolean flag = true;
			for(int i = 0; i < 3; i++) {
				if(i == y) continue;
				// 같은 행에 또 다른 지워진 숫자가 있을 경우
				if(map[x][i] == 0) flag = false;
			}

			// 같은 행의 지워진 숫자가 혼자인 경우
			if(flag) {
				sum = map[x][0] + map[x][1] + map[x][2];
				map[x][y] = Math.abs((total + (total/2)) - sum);
				continue;
			}

			flag = true;
			for(int i = 0; i < 3; i++) {
				if(i == x) continue;
				// 같은 열에 또 다른 지워진 숫자가 있을 경우
				if(map[i][y] == 0) flag = false;
			}

			// 같은 열에 지워진 숫자가 혼자인 경우
			if(flag) {
				sum = map[0][y] + map[1][y] + map[2][y];
				map[x][y] = Math.abs((total + (total/2)) - sum);
				continue;
			}

			// 다시 삽입
			zeros.add(new int[] {x, y});

			// 반복적으로 돌리면 대각선인 경우는 굳이 검증하지 않아도 결국 다 채워지기에 대각선을 이용하지 않는다.
		}

		for(int[] arr : map) {
			for(int i : arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
	}



}