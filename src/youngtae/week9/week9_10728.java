package youngtae.week9;

import java.io.*;
import java.util.*;

public class week9_10728 {

	static int T, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer str;

		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			int len = 0;
			List<Integer> result = new ArrayList<>();

			for(int i = 1; i < (1 << N); i++) {
				List<Integer> list = new ArrayList<>();

				// 부분집합 넣기
				for(int j = 0; j < N; j++) {
					if((i & 1 << j) != 0) list.add(j+1);
				}

				boolean flag = true;


				for(int j = 0; j < list.size(); j++) {
					for(int p = j+1; p < list.size(); p++) {
						for(int q = p+1; q < list.size(); q++) {
							if((list.get(j) ^ list.get(p) ^ list.get(q)) == 0) {
								flag = false;
							}
						}
					}
				}

				if(flag) {
					if(list.size() > len) {
						len = list.size();
						result = new ArrayList<>(list);
					}
				}
			}

			bw.write(len + "\n");
			for(int i : result) {
				bw.write(i + " ");
			}
			bw.write("\n");

		}

		bw.flush();
	}

}