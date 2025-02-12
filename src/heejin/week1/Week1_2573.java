package heejin.week1;

import java.util.*;
import java.io.*;

public class Week1_2573 {
    static int[][] board;
    static int[][] zeroCnt;
    static boolean[][] visited;
    static int cnt= 1;
    static int[] dirx = {1,0,-1,0};
    static int[] diry = {0,1,0,-1};
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        int year = 0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(cnt>0 && cnt<2){
            // 주변 0 개수 카운트
            zeroCnt = new int[n][m];
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(board[i][j]>0 && zeroCnt[i][j]==0){
                        int tempCnt = 0;
                        for(int k=0; k<dirx.length; k++){
                            int nx = i+dirx[k];
                            int ny = j+diry[k];
                            if(nx>=0 && nx<n && ny>=0 && ny<m && board[nx][ny]==0) tempCnt++;
                        }
                        zeroCnt[i][j] = tempCnt;
                    }
                }
            }

            // 빙산 녹이기
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(board[i][j]>0 && zeroCnt[i][j]>0){
                        board[i][j]=Math.max(0,board[i][j]-zeroCnt[i][j]);
                    }
                }
            }
            year++;

            // 빙산 개수 카운트
            cnt=0;
            visited = new boolean[n][m];
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(board[i][j]>0 && !visited[i][j]){
                        cnt++;
                        dfs(i,j);
                    }
                }
            }
        }
        System.out.print(cnt==0 ? 0:year);
    }

    static void dfs(int curx, int cury){
        visited[curx][cury]=true;
        for(int i=0; i<dirx.length; i++){
            int nx = curx+dirx[i];
            int ny = cury+diry[i];
            if(nx<0||ny<0||nx>=n||ny>=m) continue;
            if(visited[nx][ny] || board[nx][ny]==0) continue;
            dfs(nx,ny);
        }
    }
}