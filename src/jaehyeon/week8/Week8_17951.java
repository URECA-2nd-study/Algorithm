package src.jaehyeon.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week8_17951 {

    static int N;//시험지 개수 최대 100,000
    static int K;//그룹 수 최대 100,000
    static int max;
    static int min;

    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(parametricSearch());

    }

    private static int parametricSearch() {

        int start = min;
        int end = max;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (canGrouping(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    private static boolean canGrouping(int param) {
        int temp = 0;
        int index = 0;
        int groupCount = 0;
        while (true) {
            temp += arr[index];
            if (temp >= param) {
                groupCount++;
                temp = 0;
                if (groupCount == K) {
                    return true;
                }
            }
            index++;
            if (index >= arr.length) {
                return false;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            min = Math.min(input, min);
            arr[i] = input;
            max += input;
        }
    }
}
