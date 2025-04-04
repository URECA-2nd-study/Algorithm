import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Week9_10728 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            List<Integer> answer = new ArrayList<>();
            int size = Integer.parseInt(br.readLine());
            int maxSize = 0;

            for (int i = 1; i < (1 << size); i++) {
                List<Integer> candidates = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    if ((i & (1 << j)) != 0) {
                        candidates.add(j + 1); // 조합 만들기
                    }
                }

                if (check(candidates)) { // 모두 xor 연산이 가능한지
                    if (candidates.size() > maxSize) {
                        maxSize = candidates.size();
                        answer.clear();
                        answer.addAll(candidates);
                    }
                }

            }

            bw.write(maxSize + "\n");
            for (int num : answer) {
                bw.write(num + " ");
            }
            bw.write("\n");

        }

        bw.flush();
        bw.close();
        br.close();

    }

    static boolean check(List<Integer> candidates) {
        for (int i = 0; i < candidates.size(); i++) {
            for (int j = i + 1; j < candidates.size(); j++) {
                for (int k = j + 1; k < candidates.size(); k++) {
                    if ((candidates.get(i) ^ candidates.get(j) ^ candidates.get(k)) == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
