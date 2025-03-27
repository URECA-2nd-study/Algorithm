package jinu.week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week8_17951 {

    static int arr[];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());


        //left 의미 : 현수가 받을 수 있는 최저 점수
        // right 의미 : 현수가 받을 수 있는 최대 점수
        int left = 0;
        int right = 0;
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            right+= arr[i];
        }


        while(left<=right){

            //mid: 그룹의 시험 점수 최솟값
            int mid = (left + right) / 2;

            // mid 를 기준으로 몇개의 그룹이 생기는지 파악한다.
            int countOfGroup = 0;
            int tmp = 0;
            for(int i=0;i<N;i++){
                if(tmp+arr[i]>mid){
                    countOfGroup++;
                    tmp = 0;
                }
                else{
                    tmp+=arr[i];
                }
            }
            countOfGroup++;

            // K 보다 작을 떄 : 그룹의 개수를 늘리기 위해 시험 점수 최솟값을 줄인다.
            // K와 같을 때: 그룹 최솟값을 맞추기 위해 시험 점수 최솟값을 줄인다
            // ex: 예제에서 53이라면 2개의 Group 으로 나뉨 => 50 을 맞추기 위해 점수를 줄임.
            if(countOfGroup<=K){
                right = mid-1;
            }
            // K보다 크다면: 그룹의 개수를 줄이기 위해 시험 점수 최솟값을 늘린다.
            else{
                left = mid+1;
            }
        }

        System.out.println(right+1);






    }
}
