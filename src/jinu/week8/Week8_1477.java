package jinu.week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week8_1477 {

    static int highway[];
    static int N;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());


        highway = new int[N+2];
        highway[0] = 0;
        highway[N+1]= L;
        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            highway[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(highway);

        // 답의 범위는 1~ L-1 이므로 아래와 같이 범위 설정
        int left = 1;
        int right = L-1;

        int mid = 0;

        while(left<= right){

            // mid 의미: 휴게소가 없는 구간의 최댓값.
            // 각 구간을 휴게소가 없는 구간의 최댓값으로 나누고 모두 합한다면 그 의미는 ...
            // 휴게소가 없는 구간의 최댓값이 mid일 때 추가적으로 만들 수 있는 휴게소의 개수
            mid = (left+right)/2;
            int maxRestCount = getCountOfAdditional(mid);

            // 너무 잘개 쪼갰다 -> mid 값이 너무 작다 -> mid 값을 키운다.
            if(M<maxRestCount){
                left = mid+1;
            }
            // 너무 크게 쪼갰다. -> mid 값이 너무 크다 -> mid 값을 작게 한다.
            // 같을 때도 더 잘게 쪼갬으로써 mid 값을 최대한 줄인다.
            else{
                right = mid - 1;
            }
        }

        System.out.println(left);



    }

    // 주어진 mid(distance) 값으로 최대한 많은 휴게소 갯수
    static int getCountOfAdditional(int distance){

        int cnt = 0;
        for(int i=0;i<=N;i++){
            int betweenDistance = (highway[i+1]-highway[i]);
            cnt += betweenDistance / distance;

            if(betweenDistance%distance == 0){
                cnt-=1;
            }
        }

        return cnt;
    }
}
// 그리디같은 PQ가 안되는 이유: 100 ,400, 500 이고 2개의 휴게소 추가시 유사 그리디 방식 사용시 -> 250