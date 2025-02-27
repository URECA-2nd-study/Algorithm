import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[] num, numCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 접시수
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
        num = new int[N];
        numCount = new int[d + 1];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        int pt1 = 0;
        int pt2 = k - 1;
        int result;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < k; i++) {
            set.add(num[i]);
            numCount[num[i]]++;
        }
        result = set.size();
        if (!set.contains(c)) result += 1;

        while (pt1 < N) {
            numCount[num[pt1]]--;
            if (numCount[num[pt1]] == 0) {
                set.remove(num[pt1]);
            }
            pt1++;
            if (pt2 < N - 1) {
                pt2++;
            } else if (pt2 == N - 1) {
                pt2 = 0;
            }
            set.add(num[pt2]);
            numCount[num[pt2]]++;
            int size = set.size();
            if (!set.contains(c)) size += 1;
            result = Math.max(size, result);
        }

        System.out.println(result);
    }
}
