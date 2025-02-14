package youngtae.week2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Week2_2668 {

	static int N;
	static boolean flag;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static boolean[] visited;
	static int[] tmp;
	static List<Integer> answers = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		/**
		 * BFS탐색을 통해 스왑 가능 + 위아래가 동일한 개수를 찾는다.
		 */

		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		tmp = new int[N+1];

		for(int i = 0; i <= N; i++) {       //인접 리스트
			list.add(new ArrayList<>());
		}

		for(int i = 1; i <= N; i++) {
			int num = Integer.parseInt(br.readLine());
			list.get(i).add(num);
		}

		for(int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			flag = false;
			dfs(i, i);		// index 번째의 카드에 적힌 번호(cost1)의 카드(index2==cost1)에 적힌 번호(cost2==index)와 같아야한다.
		}

		List<Integer> result = answers.stream().distinct().collect(Collectors.toList());  // 조합이 중복일 수도 있기에 중복 제거
		Collections.sort(result);
		sb.append(result.size()).append("\n");
		for(int i : result) {
			sb.append(i).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int index, int cost) {
		visited[index] = true;

		for(int i : list.get(index)) {
			if(i == cost) {
				flag = true;
				answers.add(index);
				return;
			}

			if(!visited[i]) {
				dfs(i, cost);
			}
		}

		if(flag) {
			answers.add(index);
		}
	}
}
