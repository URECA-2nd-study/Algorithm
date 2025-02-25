import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> map = new HashMap<>();
        int answer = 0;

        int N = Integer.parseInt(br.readLine());
        String target = br.readLine();

        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < N - 1; i++) {
            String str = br.readLine();
            int add = 0;
            int remove = 0;
            Map<Character, Integer> tempMap = new HashMap<>(map);

            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if (tempMap.containsKey(c)) {
                    if (tempMap.get(c) > 0) {
                        tempMap.put(c, tempMap.get(c) - 1);
                    } else add++;
                } else add++;
            }

            for (int value : tempMap.values()) {
                remove += value;
            }

            if (add <= 1 && remove <= 1) answer++;

        }

        System.out.println(answer);
    }
}
