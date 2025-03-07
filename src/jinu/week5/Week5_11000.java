package jinu.week5;

import com.sun.security.jgss.GSSUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Week5_11000 {

    static class TimeTable implements Comparable<TimeTable>{

        int start;
        int end;

        public TimeTable(int start,int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(TimeTable o){
            return end - o.end;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<TimeTable> timeTables = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            timeTables.add(new TimeTable(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        Collections.sort(timeTables,(o1,o2)-> {
            if(o1.start==o2.start){
                return o2.end-o1.end;
            }

            return o1.start-o2.start;
        });


        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(timeTables.get(0).end);

        for(int i=1;i<N;i++){

            if(pq.peek()<=timeTables.get(i).start){
                pq.poll();
            }
            pq.add(timeTables.get(i).end);
        }

        System.out.println(pq.size());










    }

}
