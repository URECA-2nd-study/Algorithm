package jinu.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week9_1052 {

    public static void main(String[] args) throws Exception {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = 0;
        while(true){
            // N이 9라고 가정
            // (1 1 1 1 1 1 1 1) 1 => 2로 나눈 나머지 1
            // (2 2 2 2) => 2로 나눈 나머지 0
            // (4 4) => 2로 나눈 나머지 0
            // 8 => 한개의 원소이므로 2로 나눈 나머지 1
            // 9 를 이진수로 바꾸면 1001
            // 숫자를 이진수로 바꾸어 그것의 1의 갯수의 의미는 물이 차있는 물병 갯수
            // 조건에 만족 못하면 N과 함께 answer 을 하나씩 증가
            if(Integer.bitCount(N)>K){
                N++;
                answer++;
                continue;
            }
            break;

        }

        System.out.println(answer);


    }
}
