package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week1_2573 {

    static int iceBurg[][];
    static int N;
    static int M;

    static int dy[]={-1,0,1,0};
    static int dx[]={0,1,0,-1};


    static final int DIRECTION_SIZE = 4;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        iceBurg = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                iceBurg[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        boolean allMelt = true;



        while(true){

            int numberOfIsland = countOfIsland();

            if(numberOfIsland>=2){
                allMelt = false;
                break;
            }

            if(numberOfIsland==0){
                break;
            }
            melt();
            time++;
        }

        if(allMelt){
            System.out.println(0);
        }
        else{
            System.out.println(time);
        }


    }

    public static void melt(){

        List<int[]> meltInfos = getMeltInfo();

        meltInfos.forEach(meltInfo->{
            iceBurg[meltInfo[0]][meltInfo[1]]-=meltInfo[2];
            if(iceBurg[meltInfo[0]][meltInfo[1]]<0){
                iceBurg[meltInfo[0]][meltInfo[1]]=0;
            }
        });

    }

    public static List<int[]> getMeltInfo(){

        List<int[]> meltInfo = new ArrayList<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(iceBurg[i][j]!=0){
                    meltInfo.add(new int[]{i,j,countOfSea(i,j)});
                }
            }
        }

        return meltInfo;
    }

    public static int countOfSea(int i,int j){
        int count = 0;
        for(int p=0;p<DIRECTION_SIZE;p++){
            int tmpY = i+dy[p];
            int tmpX = j+dx[p];

            if(tmpY>=0 && tmpY<N && tmpX>=0 && tmpX<M && iceBurg[tmpY][tmpX]==0){
                count++;
            }
        }

        return count;
    }

    public static int countOfIsland(){
        boolean isVisited[][]=new boolean[N][M];

        int countOfLand = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!isVisited[i][j] && iceBurg[i][j]>0){
                    BFS(i,j,isVisited);
                    countOfLand++;
                }
            }
        }

        return countOfLand;
    }

    public static void BFS(int i,int j,boolean [][]isVisited){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});
        isVisited[i][j]=true;

        while(!queue.isEmpty()){
            int position[] = queue.poll();

            for(int p=0;p<DIRECTION_SIZE;p++){
                int newY = position[0]+dy[p];
                int newX = position[1]+dx[p];

                if(newY>=0 && newY<N && newX>=0 && newX < M && !isVisited[newY][newX] && iceBurg[newY][newX]>0){
                    isVisited[newY][newX] =true;
                    queue.add(new int[]{newY,newX});
                }
            }
        }

    }



}
