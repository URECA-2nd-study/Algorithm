import java.io.*;
import java.util.*;

public class 졸려_9515 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		ArrayList<String> info = new ArrayList<>();

		int T = Integer.parseInt(br.readLine());
		String str = br.readLine();
		String comp = str;
		int t = 0;
		info.add(str);

        // 인덱스가 짝수일 경우 바로 앞에 붙이고
        // 인덱스가 훌수일 경우 중간지점에 붙이는데 뒤에서부터 읽어서 붙여야 하는 규칙이 있습니다.

        // 깜빡일 때마다 나오는 문자열을 lnfo에 저장하고
        // 얼마나 자주 깜빡여야 입력값으로 주어지는 문자열과 같은지를 알아내서
        // info에 저장된 갯수와 모듈러 연산을 하여 나온 값이
        // info의 인덱스의 문자열이 정답입니다.

		while (true) {

			sb = new StringBuilder();

			for (int i = 0; i < str.length(); i+=2) {
					sb.append(str.charAt(i));
			}

			for(int i = str.length() - 1; i>=0; i--) {
				if(i % 2 == 1) {
					sb.append(str.charAt(i));
				}
			}
			
			str = sb.toString();
			t++;
			if (str.equals(comp))
				break;
			info.add(str);
		}

		System.out.println(info.get(T % t));

	}
}