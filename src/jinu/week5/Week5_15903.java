package jinu.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week5_15903 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //pq 에서 작은 것 부터 2개 꺼내어 다시 pq 에 저장
        PriorityQueue<Long> pq = new PriorityQueue<>();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            pq.add(Long.parseLong(st.nextToken()));
        }


        for(int i=0;i<m;i++){
            long first = pq.poll();
            long second = pq.poll();

            pq.add(first+second);
            pq.add(first+second);
        }


        long sum = 0 ;
        while(!pq.isEmpty()){
            sum += pq.poll();
        }

        System.out.println(sum);
    }

}
