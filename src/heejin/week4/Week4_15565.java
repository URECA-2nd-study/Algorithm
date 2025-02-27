package heejin.week4;
import java.io.*;
import java.util.*;

public class Week4_15565 {
    static int N, K;
    static int[] arr;
    static List<Integer> lion = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] == 1) lion.add(i); // 라이언 위치 저장
        }

        if(lion.size() < K){ // 라이언 개수가 K보다 작으면 -1 출력
            System.out.print(-1);
            return;
        }

        int start = 0, end = K-1; // 고정 길이 윈도우
        while(end < lion.size()){
            answer = Math.min(answer, lion.get(end) - lion.get(start) + 1);
            start++;
            end++;
        }

        System.out.println(answer);
    }
}
