import java.util.*;
import java.io.*;

public class Main {
    static boolean[][] visited;
    static int[][] board;
    static int cnt;
    static int[] dirX = {1,0,-1,0,1,-1,-1,1};
    static int[] dirY = {0,1,0,-1,1,1,-1,-1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            cnt = 0;
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) return;
            visited = new boolean[h][w];
            board = new int[h][w];

            for(int i=0; i<h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<w; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(!visited[i][j] && board[i][j]==1) {
                        dfs(i,j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    static void dfs(int curx, int cury){
        visited[curx][cury] = true;
        for(int i=0; i<dirX.length; i++){
            int nx = curx+dirX[i];
            int ny = cury+dirY[i];

            if(nx<0 || ny<0 || nx>=board.length || ny>=board[0].length) continue;
            if(visited[nx][ny] || board[nx][ny]==0) continue;

            visited[nx][ny] = true;
            dfs(nx,ny);
        }
    }
}