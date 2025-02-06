package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week1_7569 {

    static int tomatoes[][][];

    static int N;
    static int M;

    static int H;

    static int DAY_OF_FINAL_DAY;

    static int dy[]={0,0,0,0,1,-1};
    static int dx[]={0,0,-1,1,0,0};

    static int dz[]={1,-1,0,0,0,0};

    static final int DIRECTION_SIZE = 6;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        DAY_OF_FINAL_DAY = N*M*H;

        tomatoes = new int[N][M][H];

        for(int k=0;k<H;k++){
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++){
                    tomatoes[i][j][k]= Integer.parseInt(st.nextToken());
                }
            }
        }

        BFS();
        System.out.println(getDayOfAllRipe());


        /*
        시간 초과 나는 풀이
         */

//        int day = 0;
//        boolean canAllRipe = true;
//        while(!allRipe()){
//            if(day>DAY_OF_FINAL_DAY){
//                canAllRipe = false;
//                break;
//            }
//            ripe();
//            day++;
//        }
//
//
//        if(!canAllRipe){
//            System.out.println(-1);
//        }
//        else{
//            System.out.println(day);
//        }

    }

    public static void BFS(){

        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                for(int k=0;k<H;k++){
                    if(tomatoes[i][j][k]==1){
                        queue.add(new int[]{i,j,k,1});
                    }
                }
            }
        }

        while(!queue.isEmpty()){
            int []position = queue.poll();

            for(int i=0;i<DIRECTION_SIZE;i++){
                int newY = position[0]+dy[i];
                int newX = position[1]+dx[i];
                int newZ = position[2]+dz[i];
                int day = position[3];

                if(newY>=0 && newY < N && newX>=0 && newX < M && newZ>=0 && newZ <H && tomatoes[newY][newX][newZ]==0){
                    tomatoes[newY][newX][newZ] = day+1;
                    queue.add(new int[]{newY,newX,newZ,day+1});
                }
            }
        }


    }



/*
시간 초과 나는 풀이!
 */

//    public static void ripe(){
//        List<int[]> needToRipe = getNeedToRipe();
//
//        needToRipe.forEach(needToRipeInfo->{
//            tomatoes[needToRipeInfo[0]][needToRipeInfo[1]][needToRipeInfo[2]]=1;
//        });
//    }
//
//    public static List<int[]> getNeedToRipe(){
//
//        List<int[]> needToRipe = new ArrayList<>();
//
//        for(int k = 0;k<H;k++){
//            for(int i=0;i<N;i++){
//                for(int j=0;j<M;j++){
//                    if(tomatoes[i][j][k]==1){
//                        for(int p =0;p<DIRECTION_SIZE;p++){
//                            int newY = i+dy[p];
//                            int newX = j+dx[p];
//                            int newZ = k+dz[p];
//                            if(newY>=0 && newY<N && newX >=0 && newX <M && newZ >=0 && newZ<H && tomatoes[newY][newX][newZ]==0){
//                                needToRipe.add(new int[]{newY,newX,newZ});
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return needToRipe;
//    }
//
//    public static boolean allRipe(){
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                for(int k=0;k<H;k++){
//                    if(tomatoes[i][j][k]==0){
//                        return false;
//                    }
//                }
//            }
//        }
//
//        return true;
//    }

    public static int getDayOfAllRipe(){

        int max = Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                for(int k=0;k<H;k++){
                    if(max<tomatoes[i][j][k]){
                        max = tomatoes[i][j][k];
                    }
                    if(tomatoes[i][j][k]==0){
                        return -1;
                    }
                }
            }
        }

        return max-1;
    }


}
