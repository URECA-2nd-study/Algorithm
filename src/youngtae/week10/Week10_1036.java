package youngtae.week10;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Week10_1036 {

	static int N, K;
	static Node[] bases;
	static Map<String, Integer> changeBaseMap;

	static class Node implements Comparable<Node> {
		int base;
		BigInteger sub;  // 총 가중치

		Node(int base, BigInteger sub) {
			this.base = base;
			this.sub = sub;
		}

		public int compareTo(Node o) {
			return o.sub.compareTo(this.sub);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer str;

		N = Integer.parseInt(br.readLine());
		List<String> list = new ArrayList<>();
		changeBaseMap = new HashMap<>();  // 바꿀 문자가 저장될 Map

		// 0~Z 가중치를 저장할 배열 초기화
		bases = new Node[36];
		for(int i = 0; i < 36; i++) {
			bases[i] = new Node(i, BigInteger.ZERO);
		}

		String st = "";
		for(int i = 0; i < N; i++) {
			st = br.readLine().trim();  // 공백 제거

			// 문자열을 하나씩 순회
			// 이때, 각 문자를 36진수로 변환
			// 이후 각 위치에 따라 진법을 계산해서 Z부터 얼마나 차이나는지에 대한 가중치 계산.
			for(int j = 0; j < st.length(); j++) {
				BigInteger integer = new BigInteger(String.valueOf(st.charAt(j)), 36);
				int base = Integer.parseInt(String.valueOf(integer));

				int tmp = st.length()-j-1;

				BigInteger sub = BigInteger.ZERO;
				if(tmp == 0) sub = BigInteger.valueOf(35 - base);
				else sub = BigInteger.valueOf(35 - base).multiply(BigInteger.valueOf(36).pow(tmp));

				bases[base] = new Node(base, (bases[base].sub.add(sub)));
			}

			// 입력된 문자열 저장. 이를 갖고 나중에 Z로 변환된 문자열을 만들거임.
			list.add(st);
		}

		// 가중치 내림차순으로 정렬
		Arrays.sort(bases);

		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			Node node = bases[i];

			// Z는 가중치가 무조건 0임.
			// 정렬 기준은 (1) 가중치 내림차순, (2) base 오름차순
			// 즉 Z가 나왔다는 것은 더 이상 치환할 문자가 없다는 것 ==> 종료
			if(node.base == 35) break;

			changeBaseMap.put(Integer.toString(node.base, 36).toUpperCase(), node.base);
		}

		BigInteger sum = BigInteger.ZERO;
		// Z로 변경하며 더하기
		for(String s : list) {
			// Z 변환 시 리소스를 줄이기 위해 SB 사용
			StringBuilder sb2 = new StringBuilder();
			for(int i = 0; i < s.length(); i++) {
				if(changeBaseMap.containsKey(String.valueOf(s.charAt(i)))) {
					sb2.append("Z");
					continue;
				}

				sb2.append(s.charAt(i));
			}

			sum = sum.add(new BigInteger(sb2.toString(), 36));
		}

		sb.append(sum.toString(36));

		bw.write(sb.toString().toUpperCase());
		bw.flush();
	}
}