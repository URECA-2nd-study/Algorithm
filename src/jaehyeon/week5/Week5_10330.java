package src.jaehyeon.week5;

/**
 * 1110000 의 경우에서 포인터를 사용해서 111이후 0을 가리킬 때 1과 0을 바꾸는 기준을 본다
 * 만약 1110100 이라고 할 경우 0 뒤에 나오는 1과 교체하는 것이 정답이 된다
 * 그렇다면 구현해야할 로직은 현재 포인터 앞의 수를 확인하는 로직을 구현해야한다 (X)  3번 예제는 다시 앞의 숫자를 봐야함
 * 그렇다면 매 회차마다 전체 탐색을 하여 N제곱 만큼 반복한다?
 * 1과 0의 갯수를 받아두고 선택한다?
 */

import java.util.*;
import java.io.*;

public class Week5_10330 {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr= new int[N];
        int[] omr = new int[M];

        int[] zeroStart = new int[N];//omr조건을 만족하면서 0으로 시작하는 배열
        int[] oneStart = new int[N];//omr조건을 만족하면서 1로 시작하는 배열

        st= new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++){
            omr[i] = Integer.parseInt(st.nextToken());
        }

        //zeroStart, oneStart 배열 채우기
        int index = 0;
        for (int i=0; i<M; i++){
            for (int j = 0; j<omr[i]; j++){
                zeroStart[index] = i%2;
                oneStart[index] = 1-i%2;
                index++;
            }
        }

        //zeroStart 배열부터 arrClone 배열과 비교하며 카운트
        //마지막 숫자가 달라서 교체하지 못할 경우 flag가 true가 되며 반복문이 끝나게 되면 MAX_VALUE로 지정하게된다
        int zeroCount = 0;
        boolean zeroFlag = false;
        int[] arrClone = arr.clone();
        for (int i=0; i<N; i++){
            if (arrClone[i]!=zeroStart[i]){
                zeroFlag = true;
                for (int j=i+1; j<N; j++){
                    if (arrClone[j]==zeroStart[i]){
                        zeroCount+=j-i;
                        int temp = arrClone[j];
                        arrClone[j] = arrClone[i];
                        arrClone[i] = temp;
                        zeroFlag = false;
                        break;
                    }
                }
            }
        }
        if (zeroFlag) zeroCount = Integer.MAX_VALUE;

        //상단 zeroStart와 동일하게 동작한다
        int oneCount = 0;
        boolean oneFlag = false;
        arrClone = arr.clone();
        for (int i=0; i<N; i++){
            if (arrClone[i]!=oneStart[i]){
                oneFlag = true;
                for (int j=i+1; j<N; j++){
                    if (arrClone[j]==oneStart[i]){
                        oneCount+=j-i;
                        int temp = arrClone[j];
                        arrClone[j] = arrClone[i];
                        arrClone[i] = temp;
                        oneFlag = false;
                        break;
                    }
                }
            }
        }
        if (oneFlag) oneCount = Integer.MAX_VALUE;

        //문제 조건에서 항상 변경이 가능한 배열만 주어지기 때문에 zeroStart, oneStart 최소 하나는 Integer.MAX_VALUE로 변환되지 않기 때문에
        //작은 값을 출력한다
        bw.write(Math.min(oneCount,zeroCount)+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
