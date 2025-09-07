package jinu.week16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Week16_13422 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(st.nextToken());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int []money = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                money[i] = Integer.parseInt(st.nextToken());
            }

            int count = 0;
            int sum = 0;
            for(int i=0;i<M;i++){
                sum+=money[i];
            }

            if(sum<K){
                count+=1;
            }

            // N과 M이 같은 경우는 1번 , 이게 없다면 1,2,3  2,3,1  3,2,1 이 생긴다.
            if(N==M){
                bw.write(count+"\n");
                continue;
            }

            for(int start=0;start<N-1;start++){
                int remove = start;
                int next = (start+M)%N;
                sum=sum-money[remove]+money[next];
                if(sum<K){
                    count+=1;
                }
                //  System.out.println(sum);
            }

            bw.write(count+"\n");

        }

        bw.flush();
    }

}
