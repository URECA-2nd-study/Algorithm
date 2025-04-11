package BaekJoon_1036;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Week10_1036 {

    static int N, K;
    static String[] numbers;
    static BigInteger[] gain = new BigInteger[36];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new String[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = br.readLine();
        }

        for (int i = 0; i < 36; i++) {
            gain[i] = BigInteger.ZERO;
        }

        K = Integer.parseInt(br.readLine());

        for (String number : numbers) {
            int len = number.length();
            for (int i = 0; i < len; i++) {
                int num = toInt(number.charAt(i));
                BigInteger w = BigInteger.valueOf(36).pow(len - i - 1);
                BigInteger mul = BigInteger.valueOf(35 - num).multiply(w);
                gain[num] = gain[num].add(mul);
            }
        }

        Integer[] order = new Integer[36];
        for (int i = 0; i < 36; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> gain[b].compareTo(gain[a]));
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < K; i++) {
            set.add(toChar(order[i]));
        }

        BigInteger sum = BigInteger.ZERO;
        for (String number : numbers) {
            BigInteger temp = BigInteger.ZERO;
            int len = number.length();
            for (int i = 0; i < len; i++) {
                char cur = number.charAt(i);
                if (set.contains(cur)) cur = 'Z';
                int value = toInt(cur);
                BigInteger w = BigInteger.valueOf(36).pow(len - i - 1);
                temp = temp.add(BigInteger.valueOf(value).multiply(w));
            }
            sum = sum.add(temp);
        }

        System.out.println(sum.toString(36).toUpperCase());

    }

    // 문자 -> 정수 (0-9, A-Z)
    static int toInt(char c) {
        if (Character.isDigit(c)) return c - '0';
        return c - 'A' + 10;
    }

    // 정수 -> 문자 (0-9, A-Z)
    static char toChar(int n) {
        if (n < 10) return (char) ('0' + n);
        return (char) ('A' + (n - 10));
    }
}
