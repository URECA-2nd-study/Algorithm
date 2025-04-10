package yeoeun.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

public class Week10_1036 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        // [] -> {본인(int), 점수}
        BigInteger[][] map = new BigInteger[36][2];
        for (int i = 0; i < 36; i++) {
            map[i][0] = map[i][1] = BigInteger.valueOf(0);
        }

        StringBuilder word = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            word.setLength(0);

            int len = tmp.length();
            for (int j = 0; j < len; j++) {
                char c = tmp.charAt(j);

                // 앞의 0 제거
                if(word.length() == 0 && c == '0') continue;
                else word.append(c);

                int ci = toInt(c);
                BigInteger score = BigInteger.valueOf(35-ci);
                for (int k = 0; k < len-j-1; k++) {
                    score = score.multiply(BigInteger.valueOf(36));
                }
                map[ci][0] = BigInteger.valueOf(ci); // 문자값
                map[ci][1] = map[ci][1].add(score); // 점수 증가
            }

            // 값이 "0"인 경우
            if(word.length() == 0) {
                word.append(0);
                map[0][1] = map[0][1].add(BigInteger.valueOf(1));
            }
            words[i] = word.toString();
        }
        Arrays.sort(map, Comparator.comparing((BigInteger[] c) -> c[1]).reversed());

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            if(map[i][1].equals(BigInteger.valueOf(0))) break; // 모든 문자 확인됨
            char toZ = toChar(map[i][0].intValue());
            for (int j = 0; j < N; j++) {
                words[j] = words[j].replace(toZ, 'Z');
            }
        }

        // 값 더하기
        String result = words[0];
        for (int i = 1; i < N; i++) {
            result = add(result, words[i]);
        }
        System.out.println(result);
    }

    public static String add(String n1, String n2) {
        StringBuilder result = new StringBuilder();

        boolean upper = false; // 올림수 계산
        int maxLen = Math.max(n1.length(), n2.length());
        for (int i = 0; i < maxLen; i++) {
            int a = (i < n1.length()) ? toInt(n1.charAt(n1.length() - i - 1)) : 0;
            int b = (i < n2.length()) ? toInt(n2.charAt(n2.length() - i - 1)) : 0;
            int res = upper ? a+b+1 : a+b;

            upper = res > 35; // 올림 계산
            res %= 36; // 남은 값
            result.append(toChar(res));
        }
        if(upper) result.append(1); // 남은 올림
        return result.reverse().toString();
    }

    public static int toInt (char c) {
        if(c <= '9') return c - '0';
        else return c - 'A' + 10;
    }

    public static char toChar (int n) {
        if(n <= 9) return (char) (n + '0');
        else return (char) (n + 'A' - 10);
    }
}
