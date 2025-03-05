import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week4_7795 {

    static int[] a, b;
    static int sizeA, sizeB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            sizeA = Integer.parseInt(st.nextToken());
            sizeB = Integer.parseInt(st.nextToken());

            a = new int[sizeA];
            b = new int[sizeB];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < sizeA; j++) {
                a[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < sizeB; j++) {
                b[j] = Integer.parseInt(st.nextToken());
            }

            // 이분 탐색을 위한 정렬
            Arrays.sort(a);
            Arrays.sort(b);

            int answer = 0;
            for (int j = 0; j < a.length; j++) { // 이분 탐색으로 target보다 작은 것의 개수 구하기
                int target = a[j];
                answer += find(target);
            }

            bw.write(answer + "\n");

        }

        bw.flush();
        bw.close();
        br.close();

    }

    static int find(int target) { // 이분탐색
        int left = 0;
        int right = sizeB;

        while (left < right) {
            int mid = (left + right) / 2;
            if (b[mid] >= target) right = mid;
            else left = mid + 1;
        }

        return left;

    }
}
