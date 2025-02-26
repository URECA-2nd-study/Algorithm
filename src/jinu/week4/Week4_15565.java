package jinu.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Week4_15565 {


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 라이온만 담는 List
        List<Integer> lionSequence = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int number = Integer.parseInt(st.nextToken());

            if(number == 1){
                lionSequence.add(i);
            }
        }


        System.out.println(makeResult(K,lionSequence));
    }


    private static int makeResult(int K, List<Integer> lionSequence) {
        int result = Integer.MAX_VALUE;

        int lionIndex = 0;

        // 배열이 K 보다 작으면 -1 return
        if(lionIndex+ K -1>= lionSequence.size()){
            return -1;
        }

        // 가장 최소의 집합 길이 구하기
        while(lionIndex+ K -1< lionSequence.size()){
            result = Math.min(result, lionSequence.get(lionIndex+ K -1)- lionSequence.get(lionIndex)+1);
            lionIndex++;
        }

        return result;
    }

}
