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

    // 빈 칸중 바이러스에 감염된 것을 판단하기 위한 배열
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


        // 비활성 바이러스의 리스트, 활성 바이러스의 후보군을 List 에 담아 놓음.
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

    // 백트래킹 방법으로 비활성 바이러스 중 m 개의 활성 바이러스 선태
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

    // BFS 방식으로 활성 상태의 바이러스로부터 시작해서 전파.
    public static void BFS(List<int[]> activateVirus){

        // 초기화
        intializeIsVisited();
        Queue<int[]> queue = new LinkedList<>();
        int time = 0;

        // 활성 바이러스를 Queue 에 담음.
        activateVirus.forEach(position ->{
            queue.add(new int[]{position[0],position[1],time});
            isVisited[position[0]][position[1]] = true;
        });

        // 모든 빈칸에 전파하는 시간
        int timeMax = 0;

        while(!queue.isEmpty()){

            int []virusInfo = queue.poll();

            for(int k=0;k<4;k++){

                int newY = virusInfo[0] + dy[k];
                int newX = virusInfo[1] + dx[k];

                if(newY>=0 && newY < N && newX>=0 && newX< N && !isVisited[newY][newX] && (map[newY][newX] == 0 || map[newY][newX] ==2)){

                    // 빈 칸이라면 시간을 1 증가한 것을 최대 TIME 으로 갱신
                    if(map[newY][newX]==0){
                        timeMax = Math.max(timeMax,virusInfo[2]+1);
                    }

                    queue.add(new int[]{newY,newX,virusInfo[2]+1});
                    isVisited[newY][newX] = true;

                }
            }
        }


        // 모든 빈칸에 바이러스 가 퍼졌는지 판단.
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
