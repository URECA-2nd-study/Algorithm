package jaehyeon.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week1_7569 {

    static int M;
    static int N;
    static int H;

    static final int[] dx = {1, -1, 0, 0, 0, 0};//동 > 서 > 남 > 북 > 상 > 하
    static final int[] dy = {0, 0, 1, -1, 0, 0};
    static final int[] dz = {0, 0, 0, 0, 1, -1};

    static int[][][] map;
    static boolean[][][] visited;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Queue<Position> q = new LinkedList<>();

    static int answerDay = 0;

    static class Position {
        int x;
        int y;
        int z;
        int day;
        public Position(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {

        run();
    }//main

    private static void run() throws IOException {
        inputBoxSizeData();
        inputTomatoBoxData();

        bfs();

        if(isExistUninfectedTomato()){
            System.out.println(answerDay);
        }
        else{
            System.out.println(-1);
        }

    }

    private static void inputBoxSizeData() throws IOException {
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());//가로 칸의 수
        N = Integer.parseInt(st.nextToken());//세로 칸의 수
        H = Integer.parseInt(st.nextToken());//상자 층의 수

        map = new int[H][N][M];
        visited = new boolean[H][N][M];
    }

    private static void inputTomatoBoxData() throws IOException {

        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < M; x++) {
                    int tomatoBoxInventory = Integer.parseInt(st.nextToken());
                    map[z][y][x] = tomatoBoxInventory;
                    if(tomatoBoxInventory == 1){
                        q.offer(new Position(x,y,z,0));
                    }
                }
            }
        }
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            Position p = q.poll();

            for (int i = 0; i < 6; i++) {
                int px = p.x + dx[i];
                int py = p.y + dy[i];
                int pz = p.z + dz[i];
                int day = p.day;

                if(isValidatePosition(px,py,pz)){
                    infectTomato(px,py,pz,day);
                }
                else{
                    answerDay = Math.max(answerDay, day);
                }
            }
        }
    }

    private static boolean isValidatePosition(int x,int y,int z){
        return isRange(x,y,z) && !isVisited(x,y,z) && isExistTomato(x,y,z);
    }

    //좌표가 유효 범위인지
    private static boolean isRange(int x, int y, int z) {
        return 0 <= x && 0 <= y && 0 <= z && x < M && y < N && z < H;
    }

    //좌표를 방문했는지
    private static boolean isVisited(int x,int y,int z){
        return visited[z][y][x];
    }

    //좌표에 덜익은 토마토가 있는지
    private static boolean isExistTomato(int x, int y, int z){
        return map[z][y][x] == 0;
    }

    //토마토 감염시키기
    private static void infectTomato(int x, int y, int z,int day){
        map[z][y][x] = 1;
        visited[z][y][x] = true;
        q.offer(new Position(x,y,z,day+1));
    }

    private static boolean isExistUninfectedTomato(){
        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if(map[z][y][x] == 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
