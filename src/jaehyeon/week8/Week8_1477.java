package src.jaehyeon.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week8_1477 {

    static int N, M, L;
    static int[] arr;
    static int[] gap;

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(binarySearch());

    }

    private static int binarySearch() {
        int start = 1;
        int end = L - 1;

        while (start <= end) {

            int mid = (start + end) / 2;

            int possibleBuildCount = 0;
            for (int i = 0; i < gap.length; i++) {
                possibleBuildCount += (gap[i] - 1) / mid;
            }

            if (possibleBuildCount > M) {
                //너무 많이 자르는 간격이기 때문에 간격을 키워야 함
                start = mid + 1;
            } else if (possibleBuildCount <= M) {
                //M개 보다 같거나 적게 설치할 수 있다
                //즉, 설치 간격이 너무 커서 최소값을 출력해야하는 정답과 거리가 멀다
                end = mid - 1;
            }
        }
        return start;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;


        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N + 2];
        gap = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        arr[0] = 0;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N + 1] = L;

        Arrays.sort(arr);

        for (int i = 0; i < N + 1; i++) {
            gap[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(gap);

//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
//        System.out.println("Arrays.toString(gap) = " + Arrays.toString(gap));
    }
}
