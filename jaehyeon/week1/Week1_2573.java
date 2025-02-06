package jaehyeon.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Week1_2573 {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int N;//행
    static int M;//열

    static int[][] map;//빙산맵

    static int answerYear = 0;

    static Deque<Pair> q = new ArrayDeque<>();

    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        init();
        int answerYear = 0;
        while(true){
            meltingProcess();
            answerYear++;
            int separate = checkSeparated();
            if((separate != 1)){
                if(separate==0){
                    answerYear = 0;
                }
                break;
            }
        }
        System.out.println(answerYear);
    }

    private static void init() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());//5
            M = Integer.parseInt(st.nextToken());//7

            map = new int[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void meltingProcess(){
        boolean[][] visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] > 0){
                    q.offer(new Pair(i,j));//배열 내 모든 빙하 위치 큐에 삽입
                    visited[i][j] = true;
                }
            }
        }
        bfs(visited);

    }
    private static void bfs(boolean[][] visited){
        while(!q.isEmpty()){
            Pair p = q.poll();

            for(int i=0; i<4; i++){
                int px = dx[i] + p.x;
                int py = dy[i] + p.y;

                if(isRange(px, py)) continue;//범위 체크
                if(isSea(px,py) && !visited[px][py]){//바다 &&  방문하지 않음 > 빙하 녹이기
                    map[p.x][p.y]--;

                }
                //바다 && 방문함 > 이전에 빙하였음
                //이미 큐에 빙하의 위치가 삽입되어 있기 때문에 빙하를 발견함에 따라 별도의 처리를 할 필요가 없다.
            }
        }
    }

    private static int checkSeparated(){
        int count = 0;
        boolean[][] visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] > 0 && !visited[i][j]){
                    dfs(i,j,visited);
                    count++;
                }
            }
        }
        return count;
    }
    private static void dfs(int x, int y,boolean[][] visited){
        visited[x][y] = true;
        for(int i=0; i<4; i++){
            int px = dx[i] + x;
            int py = dy[i] + y;

            if(isRange(px, py)) continue;

            if(isIceberg(px,py) && !visited[px][py]){
                dfs(px,py,visited);
            }
        }

    }

    private static boolean isRange(int x, int y) {
        return 0 > x || x >= N || 0 > y || y >= M;
    }

    private static boolean isSea(int x, int y){
        return map[x][y] <= 0;
    }

    private static boolean isIceberg(int x,int y){
        return map[x][y] > 0;
    }
}
