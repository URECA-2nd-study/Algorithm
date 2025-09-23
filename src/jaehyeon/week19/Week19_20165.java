package src.jaehyeon.week19;

import java.io.*;
import java.util.*;

public class Week19_20165 {

    // 서동북남
    // W E N S
    public static final int[] dy = {0,0,-1,1};
    public static final int[] dx = {-1,1,0,0};

    public static int N,M,R;
    public static int[][] map, cmd;
    public static boolean[][] isFalled;

    public static int attackerPoint;

    public static void main(String[] args) throws IOException{

        init();

        sol();

        print();
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());// 행
        M = Integer.parseInt(st.nextToken());// 열
        R = Integer.parseInt(st.nextToken());// 라운드 횟수
        
        map = new int[N][M];
        isFalled = new boolean[N][M];
        
        for(int i=0; i<N; i++){

            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){

                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        cmd = new int[R][];

        for(int i=0; i<R; i++){

            st = new StringTokenizer(br.readLine());

            int atkY = Integer.parseInt(st.nextToken());
            int atkX = Integer.parseInt(st.nextToken());
            String tmp = st.nextToken();

            int atkDir = 0;

            switch (tmp) {

                case "W":
                    atkDir = 0;
                    break;
                
                case "E":
                    atkDir = 1; 
                    break;

                case "N":
                    atkDir = 2;
                    break;

                case "S":
                    atkDir = 3;
                    break;
            }

            st = new StringTokenizer(br.readLine());

            int dfeY = Integer.parseInt(st.nextToken());
            int dfeX = Integer.parseInt(st.nextToken());

            cmd[i] = new int[]{atkY,atkX,atkDir,dfeY,dfeX};
        }
    }
    
    private static void sol(){

        for(int[] s : cmd){

            int atkY = s[0]-1;
            int atkX = s[1]-1;
            int atkDir = s[2];

            int dfeY = s[3]-1;
            int dfeX = s[4]-1;

            attack(atkY, atkX, atkDir);
            defence(dfeY, dfeX);
        }
    }

    private static void defence(int y, int x){

        isFalled[y][x] = false;
    }

    private static void attack(int a, int b, int dir){

        if(isAlreadyFalled(a,b)) return;

        ArrayDeque<int[]> s = new ArrayDeque<>();

        s.push(new int[]{a,b});

        while(!s.isEmpty()){

            int[] ary = s.pop();

            int y = ary[0];
            int x = ary[1];

            isFalled[y][x] = true;
            attackerPoint++;

            int dominoLength = map[y][x];

            for(int i=0; i<dominoLength-1; i++){               

                y += dy[dir];
                x += dx[dir]; 
                
                if(isOutOfRange(y,x)) break;
                if(isAlreadyFalled(y,x)) continue;
                if(map[y][x] == 1){

                    attackerPoint++;
                    isFalled[y][x] = true;
                    continue;
                }

                s.push(new int[]{y,x});
            }
        }
    }

    private static boolean isAlreadyFalled(int y, int x){

        return isFalled[y][x];
    }

    private static boolean isOutOfRange(int y, int x){

        return y < 0 || x < 0 || y>= N || x>=M;
    }

    private static void print()throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        sb.append(String.valueOf(attackerPoint)).append("\n");

        for(int i=0; i<N; i++){

            for(int j=0; j<M; j++){

                if(isFalled[i][j]){
                    
                    sb.append("F");
                }else{

                    sb.append("S");
                }
                
                sb.append(" ");
            }
            
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}
