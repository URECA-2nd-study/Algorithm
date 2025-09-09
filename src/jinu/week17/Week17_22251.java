package jinu.week17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week17_22251 {

    static int[][] numberArray = new int[][]{{1,1,1,1,1,1,0},
            {0,0,0,0,1,1,0},
            {1,0,1,1,0,1,1},
            {1,0,0,1,1,1,1},
            {0,1,0,0,1,1,1},
            {1,1,0,1,1,0,1},
            {1,1,1,1,1,0,1},
            {1,0,0,0,1,1,0},
            {1,1,1,1,1,1,1},
            {1,1,0,1,1,1,1}};

    public static int calculateNeedDiff(int i,int j){
        int count = 0;
        for(int k=0;k<7;k++){
            if(numberArray[i][k]!=numberArray[j][k]){
                count+=1;
            }
        }

        return count;
    }

    public static int[] intToArray(int number,int k){
        String nString = String.format("%0"+k+"d",number);

        int arr[] = new int[k];
        for(int i=0;i<k;i++){
            arr[i] = nString.charAt(i)-'0';
        }

        return arr;
    }

    static int count = 0;

    public static void BT(int P,int diff[][], int baseNumberArr[], int start, int totalReverseCount, String value,int N){

        if(start == baseNumberArr.length){
            if(totalReverseCount<=P && Integer.valueOf(value)<=N && Integer.valueOf(value)>=1){
                //  System.out.println(Integer.valueOf(value));
                count++;
            }
            return;
        }

        for(int i=0;i<=9;i++){
            BT(P,diff,baseNumberArr, start+1, totalReverseCount + diff[baseNumberArr[start]][i], value+i,N);
        }

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int diff[][] = new int[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                diff[i][j] = calculateNeedDiff(i,j);
            }
        }

        int baseNumberArr[] = intToArray(X,K);

        BT(P,diff, baseNumberArr, 0,0,"",N);

        System.out.println(count-1);

    }
}
