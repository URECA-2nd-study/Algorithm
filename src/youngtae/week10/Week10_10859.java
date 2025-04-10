package youngtae.week10;

import java.io.*;

public class Week10_10859 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String st = br.readLine();

		// 1 또는 거울로 바꿀 수 없는 숫자가 포함되어 있는지 검증
		if(!st.equals("1") && isMirrorNum(st)) {
			StringBuilder mirror = new StringBuilder();
			for(long i = st.length()-1; i >= 0; i--) {
				long num = Long.parseLong(String.valueOf(st.charAt((int)i)));

				// 6과 9는 각 9와 6으로 변경
				if(num == 6) num = 9;
				else if(num == 9) num = 6;

				mirror.append(num);
			}

			// 뒤집어진 수
			long mirrorNum = Long.parseLong(mirror.toString());

			// 기존 수와 뒤집어진 수 모두 소수인지 검증
			if(isPrime(Long.parseLong(st)) && isPrime(mirrorNum)) sb.append("yes");
			else sb.append("no");
		} else {
			sb.append("no");
		}

		bw.write(sb.toString());
		bw.flush();
	}

	private static boolean isPrime(long mirrorNum) {

		// N이 소수인지 확인하기 위해서는 루트 N까지 나눴을 때 나머지가 0인게 없으면 소수
		// 루트 N을 넘는 수로는 N을 나누어도 몫이 루트 N이하이므로, 이미 검사한 수랑 중복  => 최적화
		for(long i = 2; i * i <= mirrorNum; i++) {
			if(mirrorNum % i == 0) {
				return false;
			}
		}
		return true;
	}

	private static boolean isMirrorNum(String st) {
		if(st.contains("3") || st.contains("4") || st.contains("7")) return false;

		return true;
	}
}