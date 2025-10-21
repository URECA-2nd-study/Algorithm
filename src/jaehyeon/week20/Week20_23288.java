package src.jaehyeon.week20;

import java.io.*;
import java.util.*;

public class Week20_23288 {

    private static final int EAST = 0;
    private static final int SOUTH = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;
    private static final int[] dy = {0,1,0,-1};
    private static final int[] dx = {1,0,-1,0};

    private static int N,M,K,answer;
    private static int[][] map;
    private static int[] pos = new int[]{1,1};
    private static int[][] dice = {{0,2,0},{4,1,3},{0,5,0},{0,6,0}};
    private static int dir = EAST;
    
    public static void main(String[] args) throws IOException{

        init();

        sol();

        print();
    }

    private static void sol(){

        for(int i=0; i<K; i++){
            
            movePosition();

            rollDise();

            getScore();

            decideDirection();
        }
    }

    private static void getScore(){

        ArrayDeque<int[]> q = new ArrayDeque<>();

        int y = pos[0];
        int x = pos[1];

        q.offer(new int[]{y, x});

        boolean[][] visited = new boolean[N+1][M+1];

        visited[y][x] = true;

        int target = map[y][x];
        int count = 1;

        while(!q.isEmpty()){

            int[] p = q.poll();

            for(int i=0; i<4; i++){

                int py = p[0] + dy[i];
                int px = p[1] + dx[i];

                if(isOutOfRange(py, px)) continue;
                if(visited[py][px]) continue;
                if(map[py][px] != target) continue;

                visited[py][px] = true;
                q.offer(new int[]{py,px});
                count++;
            }
        }
        
        answer += count * target;

    }

    private static void decideDirection(){

        int y = pos[0];
        int x = pos[1];

        int diseValue = dice[3][1];
        int mapValue = map[y][x];

        if(diseValue > mapValue){
            
            dir = dir+1 >= 4 ? 0 : dir+1;

        }else if(diseValue < mapValue){
            
            dir = dir-1 < 0 ? 3 : dir-1;
        }
    }

    private static void movePosition(){

        int y = pos[0];
        int x = pos[1];

        int nextY = y+dy[dir];
        int nextX = x+dx[dir];

        if(isOutOfRange(nextY,nextX)){

            dir = (dir+2) % 4;

            pos[0] += dy[dir];
            pos[1] += dx[dir];

            return;
        }

        pos[0] = nextY;
        pos[1] = nextX;
    }

    // 주사위 굴리기
    private static void rollDise(){

        int temp;

        switch(dir){

            case EAST:

                temp = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = temp;

                break;

            case SOUTH:

                temp = dice[3][1];
                
                for(int i=3; i>=1; i--){

                    dice[i][1] = dice[i-1][1];
                }

                dice[0][1] = temp;

                break;

            case WEST:

                temp = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = temp;

                break;

            case NORTH:

                temp = dice[0][1];

                for(int i=0; i<3; i++){

                    dice[i][1] = dice[i+1][1];
                }

                dice[3][1] = temp;
        }
    }

    private static boolean isOutOfRange(int y, int x){

        return y <= 0 || x <=0 || y>N || x>M;
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
        
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];

        for(int y=1; y<=N; y++){

            st = new StringTokenizer(br.readLine());

            for(int x=1; x<=M; x++){

                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void print() throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(answer));
        bw.close();
    }
}
