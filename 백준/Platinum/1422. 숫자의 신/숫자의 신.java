import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<String> list = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < K; i++) {
            String input = br.readLine();
            list.add(input);
            max = Math.max(max, Integer.parseInt(input));
        }

        list.sort((a, b) -> (b + a).compareTo(a + b));

        StringBuilder sb = new StringBuilder();
        boolean isUsed = false;

        for (String str : list) {

            if (str.equals(Integer.toString(max)) && !isUsed) {
                for (int i = 0; i < N - K; i++) {
                    sb.append(str);
                }
                isUsed = true;
            }
            sb.append(str);
        }



        System.out.println(sb.toString());

    }
}
