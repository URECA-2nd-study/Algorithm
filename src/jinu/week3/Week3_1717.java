package jinu.week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week3_1717 {

    static int parent[];
    static int n;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 부모 배열 초기화
        parent = new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i]=i;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(st.nextToken());
            int number1 = Integer.parseInt(st.nextToken());
            int number2 = Integer.parseInt(st.nextToken());

            if(order==0){
                union(number1,number2);
            }
            else if(order == 1){
                if(checkUnion(number1,number2)){
                    sb.append("YES"+"\n");
                }
                else{
                    sb.append("NO"+"\n");
                }
            }

        }

        System.out.println(sb);





    }


    // parent 값이 작은 것이 상위 노드로 가게 설정
    public static void union(int number1,int number2){

        int parent1 = findParent(number1);
        int parent2 = findParent(number2);

        if(parent1==parent2){
            return;
        }

        if(parent1<parent2){
            parent[parent2]=parent1;
        }
        else{
            parent[parent1]=parent2;
        }
    }

    // 부모가 같은지 체크
    public static boolean checkUnion(int number1,int number2){

        int parent1 = findParent(number1);
        int parent2 = findParent(number2);

        if(parent1==parent2){
            return true;
        }

        return false;
    }

    // 재귀를 통한 부모 검색
    public static int findParent(int number){

        if(parent[number]==number){
            return number;
        }

        return parent[number] = findParent(parent[number]);

    }


}

