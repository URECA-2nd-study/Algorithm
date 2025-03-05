package yeoeun.week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Week4_22945 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        Person[] arr = new Person[N];
        boolean[] used = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = new Person(i, Integer.parseInt(st.nextToken()));
        }

        // cost를 기준으로 정렬
        Arrays.sort(arr, Comparator.comparing(p -> p.cost));

        int max = 0;
        int pf = 0;
        int pl = N-1;
        // 마지막(cost가 가장 큼)이 min이 되는 경우는 없음
        for (int i = 0; i < N-1; i++) {
            // i번째의 cost가 최소가 되어야 함 -> 이전의 것들은 쌍이 될 수 없음
            used[arr[i].idx] = true;

            // 포인터 재배치
            while(used[pf] || used[pl]) {
                if(used[pf]) pf++;
                if(used[pl]) pl--;
            }

            // 최대 거리 찾기: 양 끝단 중 더 먼 것을 선택
            int distance = Math.max(Math.abs(pf-arr[i].idx), Math.abs(pl-arr[i].idx))-1;

            // 능력치 max 계산
            max = Math.max(max, arr[i].cost * distance);
        }
        System.out.println(max);
    }
    
    public static class Person {
        int idx;
        int cost;
        
        public Person(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
