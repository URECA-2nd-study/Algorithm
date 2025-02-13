package jinu.week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Week2_2668 {

    public static boolean isUsed[];

    public static List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int number[] = new int[N+1];
        isUsed = new boolean[N+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            number[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=N;i++){
            if(!isUsed[i]){
                group(number,i);
            }
        }

        System.out.println(numbers.size());
        Collections.sort(numbers);
        numbers.forEach(n ->{
            System.out.println(n);
        });





    }

    public static void group(int number[],int start){

        HashSet<Integer> numberSet = new HashSet<>();
        HashSet<Integer> valueSet = new HashSet<>();

        numberSet.add(start);
        int i = start;

        do{
            numberSet.add(i);
            valueSet.add(number[i]);
            i = number[i];


        }while(!numberSet.contains(i));

        if(numberSet.size() == valueSet.size()){
            numberSet.forEach(n->{
                isUsed[n] = true;
                numbers.add(n);
            });
        }


    }
}
