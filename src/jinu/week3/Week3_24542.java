package jinu.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Week3_24542 {

    static int parent[];

    static int N;

    static int DIV_SIZE = 1000000007;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        //부모 배열 초기화
        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i]=i;
        }



        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int student1 = Integer.parseInt(st.nextToken());
            int student2 = Integer.parseInt(st.nextToken());

            makeUnion(student1,student2);
        }



        // Map -> key: 집합을 대표하는 부모 노드 , Value -> 집합의 개수
        Map<Integer,Integer> groupSize = new HashMap<>();
        for(int i=1;i<=N;i++){
            int number = findParent(i);

            if(!groupSize.containsKey(number)){
                groupSize.put(number,1);
            }
            else{
                groupSize.put(number,groupSize.get(number)+1);
            }
        }


        // long 타입에 주의

        long result = 1;
        for(int key: groupSize.keySet()){
            result = (result * groupSize.get(key))%DIV_SIZE;
        }

        System.out.println(result%DIV_SIZE);

    }

    public static void makeUnion(int student1,int student2){


        int parent1 = findParent(student1);
        int parent2 = findParent(student2);


        if(parent1 == parent2){
            return;
        }

        if(parent1>parent2){
            parent[parent1] = parent2;
        }
        else {
            parent[parent2] = parent1;
        }


    }

    public static int findParent(int number){
        if(number == parent[number]){
            return number;
        }

        return parent[number] = findParent(parent[number]);
    }
}
