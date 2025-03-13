package jinu.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_17142 {

    static int map[][];

    static int dy[]= {-1,1,0,0};
    static int dx[] = {0,0,-1,1};

    static boolean isVisited[][];

    static int N;

    static int ANSWER = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        isVisited = new boolean[N][N];

        List<int[]> activateVirusCandidate = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2){
                    activateVirusCandidate.add(new int[]{i,j});
                }
            }
        }

        BT(activateVirusCandidate,new ArrayList<>(), M,0);

        System.out.println(ANSWER==Integer.MAX_VALUE?-1:ANSWER);




    }

    public static void BT(List<int[]> activateVirusCandidate, List<int[]> activateVirus , int m ,int start){
        if(activateVirus.size()==m){

            BFS(activateVirus);
            return;
        }

        for(int i=start;i<activateVirusCandidate.size();i++){
            activateVirus.add(new int[]{activateVirusCandidate.get(i)[0],activateVirusCandidate.get(i)[1]});
            BT(activateVirusCandidate,activateVirus,m,i+1);
            activateVirus.remove(activateVirus.size()-1);
        }
    }

    public static void BFS(List<int[]> activateVirus){

        intializeIsVisited();
        Queue<int[]> queue = new LinkedList<>();
        int time = 0;

        activateVirus.forEach(position ->{
            queue.add(new int[]{position[0],position[1],time});
            isVisited[position[0]][position[1]] = true;
        });

        int timeMax = 0;

        while(!queue.isEmpty()){

            int []virusInfo = queue.poll();

            for(int k=0;k<4;k++){

                int newY = virusInfo[0] + dy[k];
                int newX = virusInfo[1] + dx[k];

                if(newY>=0 && newY < N && newX>=0 && newX< N && !isVisited[newY][newX] && (map[newY][newX] == 0 || map[newY][newX] ==2)){
                    if(map[newY][newX]==0){
                        timeMax = Math.max(timeMax,virusInfo[2]+1);
                    }

                    queue.add(new int[]{newY,newX,virusInfo[2]+1});
                    isVisited[newY][newX] = true;

                }
            }
        }


        if(isAllSpread()){
            ANSWER = Math.min(ANSWER,timeMax);
        }
    }

    public static boolean isAllSpread(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] == 0 && !isVisited[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    public static void intializeIsVisited(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                isVisited[i][j] = false;
            }
        }
    }
}
