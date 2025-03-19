import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Week6_9519 {

    static int N;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String first = br.readLine();
        sb = new StringBuilder(first);

        int cnt = 0;
        while (true) {
            change();
            cnt++;
            if (first.equals(sb.toString())) {
                break;
            }
        }

        N %= cnt;
        while (N-- > 0) {
            change();
        }

        System.out.println(sb.toString());

    }

    static void change() {
        int i;
        if (sb.length() % 2 == 0) i = sb.length() - 3;
        else i = sb.length() - 2;
        for (; i >= 0; i -= 2) {
            char c = sb.charAt(i);
            sb.deleteCharAt(i);
            sb.append(c);
        }
    }
}
