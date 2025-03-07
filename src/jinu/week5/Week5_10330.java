package jinu.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week5_10330 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        //초기 배열
        int inital[] = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            inital[i] = Integer.parseInt(st.nextToken());
        }


        //처음에 0의 원소로 시작한다고 가정하고 출발한 배열 brr
        int brr[] = new int[N];

        //처음에 1의 원소로 시작한다고 가정하고 출발한 배열 crr
        int crr[] = new int[N];


        // 배열 초기화
        int p = 0;
        boolean zero = true;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            int k = Integer.parseInt(st.nextToken());

            for(int j=0;j<k;j++){
                if(zero){
                    brr[p] = 0;
                    crr[p] = 1;
                }
                else{
                    brr[p] = 1;
                    crr[p] = 0;
                }
                p++;
            }
            zero=!zero;
        }


        // 배열 brr 에서 i=0 부터 순서대로 원소의 종류를 initial 과 비교하며
        // 다른 경우 그 원소를 가지고 있는 i보다 크고 가까운 index 를 찾아 움직이고 비용 계산

        int sum1 = 0;
        for(int i=0;i<N;i++){
            if(brr[i] == inital[i]){
                continue;
            }

            int nearIndex = getNearIndex(i,brr,inital);

            //-1 인 경우 원소의 종류가 같고 가까운 index 를 못찾음.
            if(nearIndex == -1){
                sum1 = -1;
                break;
            }

            sum1+=(nearIndex-i);
            move(brr,nearIndex,i);
        }

        // 배열 crr 에서 i=0 부터 순서대로 원소의 종류를 initial 과 비교하며
        // 다른 경우 그 원소를 가지고 있는 i보다 크고 가까운 index 를 찾아 움직이고 비용 계산
        int sum2 = 0;
        for(int i=0;i<N;i++){
            if(crr[i] == inital[i]){
                continue;
            }

            int nearIndex = getNearIndex(i,crr,inital);

            if(nearIndex == -1){
                sum2 = -1;
                break;
            }

            sum2+=(nearIndex-i);
            move(crr,nearIndex,i);
        }

        System.out.println(getAnswer(sum1,sum2));



    }

    public static int getNearIndex(int i,int brr[],int initial[]){
        for(int k=i+1;k<brr.length;k++){
            if(initial[i]==brr[k]){
                return k;
            }
        }

        return -1;
    }

    public static void move(int brr[],int findIndex,int i){
        for(int k=findIndex;k>i;k--){
            swap(brr,k,k-1);
        }
    }

    public static void swap(int brr[],int i,int j){
        int tmp = brr[i];
        brr[i] = brr[j];
        brr[j]= tmp;
    }

    public static void print(int crr[]){
        for(int i=0;i<crr.length;i++){
            System.out.print(crr[i]+" ");
        }
        System.out.println();
    }

    //정답 출력
    public static int getAnswer(int sum1,int sum2){
        if(sum1 ==-1 || sum2 ==-1){
            return sum1==-1?sum2:sum1;
        }

        return Math.min(sum1,sum2);
    }
}
