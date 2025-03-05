package yeoeun.week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Week4_15565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Integer> deque = new LinkedList<>();

        int minSize = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num == 1) { // 1인 경우만 고려
                deque.add(i);
                if(deque.size() >= K) { // K개를 넘어가면 값 확인 및 poll()해주기
                    minSize = Math.min(minSize, (deque.peekLast() - deque.peekFirst() + 1));
                    deque.poll();
                }
            }
        }
        System.out.println(minSize == Integer.MAX_VALUE ? -1 : minSize);
    }
}
