package jinu.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week4_22945 {

    static int MAX = Integer.MIN_VALUE;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int developer[] = new int[n+1];


        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            developer[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMaxAbility(developer,n));
    }




    public static int getMaxAbility(int developer[],int n){

        int start = 1;
        int end = n;

        while(start<=end){

            MAX = Math.max(MAX,(end-start-1)*Math.min(developer[start],developer[end]));

            if(developer[start]<developer[end]){
                start++;
            }
            else{
                end--;
            }
        }

        return MAX;

    }
}
