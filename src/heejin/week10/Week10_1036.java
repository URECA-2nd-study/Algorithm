package heejin.week10;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Week10_1036 {
    static int N, K;
    static String[] arr;
    static BigInteger[] gain = new BigInteger[36]; // 각 숫자의 가치 합 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        Arrays.fill(gain, BigInteger.ZERO);

        // 입력
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();

            for (int j = 0; j < arr[i].length(); j++) {
                int num = charToInt(arr[i].charAt(j));

                // 자리수가 높을 수록 가중치 높음
                int exp = arr[i].length() - 1 - j;
                BigInteger weight = BigInteger.valueOf(36).pow(exp);

                // Z와의 크기 차이를 곱하기
                BigInteger result = weight.multiply(BigInteger.valueOf(35 - num));

                gain[num] = gain[num].add(result);
            }
        }

        K = Integer.parseInt(br.readLine());

        // 나온 알파벳 정렬
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < 36; i++) {
            if (gain[i].compareTo(BigInteger.ZERO) > 0) {
                pq.add(new Node(i, gain[i]));
            }
        }

        // K개 고르기
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            if (pq.isEmpty()) {
                break;
            }

            list.add(pq.poll().idx);
        }

        // 고른것들 Z(35)로 바꾸기
        StringBuilder sb;
        for (int i = 0; i < arr.length; i++) {
            sb = new StringBuilder();
            for (char c : arr[i].toCharArray()) {
                int num = charToInt(c);

                if (list.contains(num)) {
                    sb.append('Z');
                } else {
                    sb.append(c);
                }
            }

            arr[i] = sb.toString();
        }

        // 뒤에서부터 덧셈
        String ans = "0";
        for (String s : arr) {
            ans = add(ans, s);
        }

        System.out.print(ans);
    }

    // 36진수 두 수를 더하는 함수
    static String add(String a, String b) {
        Stack<Character> aStack = new Stack<>();
        Stack<Character> bStack = new Stack<>();

        for (char c : a.toCharArray()) {
            aStack.push(c);
        }
        for (char c : b.toCharArray()) {
            bStack.push(c);
        }

        StringBuilder result = new StringBuilder();
        int sum = 0;
        while (!aStack.isEmpty() || !bStack.isEmpty() || sum != 0) {
            if (!aStack.isEmpty()) {
                sum += charToInt(aStack.pop());
            }
            if (!bStack.isEmpty()) {
                sum += charToInt(bStack.pop());
            }

            result.append(intToChar(sum % 36));
            sum /= 36;
        }

        return result.reverse().toString();
    }

    // 0~9, A~Z -> 0~35
    static int charToInt(char c) {
        if (Character.isAlphabetic(c)) {
            return c - 'A' + 10;
        } else {
            return c - '0';
        }
    }


    // 0~35 -> 0~9, A~Z
    static char intToChar(int i) {
        if (i < 10) {
            return (char) ('0' + i);
        } else {
            return (char) (i - 10 + 'A');
        }
    }

    static class Node implements Comparable<Node> {
        int idx;
        BigInteger value;

        Node(int idx, BigInteger value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return o.value.compareTo(this.value);
        }
    }
}