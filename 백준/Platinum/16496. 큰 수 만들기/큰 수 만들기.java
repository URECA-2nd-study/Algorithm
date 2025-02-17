import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        List<Num> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < size; i++) {
            list.add(new Num(st.nextToken()));
        }
        Collections.sort(list);

        String answer = "";
        for (Num num : list) {
            answer += num.value;
        }

        if (answer.charAt(0) == '0') System.out.println("0");
        else System.out.println(answer);
    }
}


class Num implements Comparable<Num> {
    String value;

    public Num(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Num num) {
        return (num.value + this.value).compareTo(this.value + num.value);
    }
}