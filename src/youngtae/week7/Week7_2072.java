import java.io.*;
import java.util.*;

public class Week7_2072 {

	static int N, result, x, y;
	static int[][] map;
	static boolean flag, rb;

	/**
	 * 1 - 흑, 2- 백
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer str;

		N = Integer.parseInt(br.readLine());
		map = new int[20][20];
		result = 0;

		flag = true;
		while(N-- > 0) {
			str = new StringTokenizer(br.readLine());
			x = Integer.parseInt(str.nextToken());
			y = Integer.parseInt(str.nextToken());

			map[x][y] = flag == false ? 1 : 2;  // 흑백 순서에 따른 값 넣기
			result += 1;

			if(check(flag, x, y)) {
				rb = true;
				break;
			}

			flag = !flag;
		}

		bw.write(String.valueOf(rb == false ? -1 : result));
		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean check(boolean flag, int x, int y) {
		if(leftAndRight(flag, x, y)) return true;
		if(updown(flag, x, y)) return true;
		if(tmp1(flag, x, y)) return true; // 좌측상단 -> 우측하단
		if(tmp2(flag, x, y)) return true; // 우측상단 -> 좌측하단

		return false;
	}

	private static boolean tmp1(boolean flag, int x, int y) {
		int find = flag == false ? 1 : 2;
		int count = 1;
		int nx = x;
		int ny = y;
		boolean flag2 = false;

		while(true) {
			if(!flag2 && nx > 1 && ny > 1 && map[nx-1][ny-1] == find) {  // 좌측 상단
				count ++;
				nx -= 1;
				ny -= 1;
				continue;
			}

			if(nx < x) nx = x;
			if(ny < y) ny = x;
			flag2 = true;

			if(nx < 19 && ny < 19 && map[nx+1][ny+1] == find) {  // 우측 하단
				count++;
				nx += 1;
				ny += 1;
				continue;
			}

			break;
		}

		return count == 5 ? true : false;
	}


	private static boolean tmp2(boolean flag, int x, int y) {
		int find = flag == false ? 1 : 2;
		int count = 1;
		int nx = x;
		int ny = y;
		boolean flag2 = false;

		while(true) {
			if(!flag2 && nx > 1 && ny < 19 && map[nx-1][ny+1] == find) { // 우측 상단
				count ++;
				nx -= 1;
				ny += 1;
				continue;
			}

			if(nx < x) nx = x;
			if(ny > y) ny = x;
			flag2 = true;


			if(nx < 19 && ny > 1 && map[nx+1][ny-1] == find) {  // 좌측 하단
				count++;
				nx += 1;
				ny -= 1;
				continue;
			}

			break;
		}

		return count == 5 ? true : false;
	}

	private static boolean updown(boolean flag, int x, int y) {
		int find = flag == false ? 1 : 2;
		int count = 1;
		int nx = x;
		int ny = y;
		boolean flag2 = false;

		while(true) {
			if(!flag2 && nx > 1 && map[nx-1][ny] == find) {
				count ++;
				nx -= 1;
				continue;
			}

			if(nx < x) nx = x;
			flag2 = true;

			if(nx < 19 && map[nx+1][ny] == find) {
				count++;
				nx += 1;
				continue;
			}

			break;
		}

		return count == 5 ? true : false;
	}

	private static boolean leftAndRight(boolean flag, int x, int y) {
		int find = flag == false ? 1 : 2;
		int count = 1;
		int nx = x;
		int ny = y;
		boolean flag2 = false;
		while(true) {
			if(!flag2 && ny > 1 && map[nx][ny-1] == find) {
				count ++;
				ny -= 1;
				continue;
			}

			if(ny < y) ny = y;
			flag2 = true;

			if(ny < 19 && map[nx][ny+1] == find) {
				count++;
				ny += 1;
				continue;
			}

			break;
		}

		return count == 5 ? true : false;
	}

}