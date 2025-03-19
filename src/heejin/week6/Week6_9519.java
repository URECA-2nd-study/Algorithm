package heejin.week6;

import java.io.*;
import java.util.*;

public class Week6_9519 {

    static int X;
    static String data;
    static StringBuilder sb = new StringBuilder();
    static List<String> rule = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        X = Integer.parseInt(br.readLine());
        data = br.readLine();

        // 반복되는 규칙 찾기
        String first = data;
        sb.append(first);

        do {
            data = sb.toString();
            rule.add(data);

            sb = new StringBuilder();

            // 홀수번째 글자가 앞으로
            for (int i = 0; i < data.length(); i += 2) {
                sb.append(data.charAt(i));
            }

            // 짝수번째 글자 거꾸로 뒤로
            int last = data.length() % 2 == 0 ? data.length() - 1 : data.length() - 2;
            for (int j = last; j >= 0; j -= 2) {
                sb.append(data.charAt(j));
            }

        } while (!sb.toString().equals(first));

        System.out.print(rule.get(X % rule.size()));
    }
}
