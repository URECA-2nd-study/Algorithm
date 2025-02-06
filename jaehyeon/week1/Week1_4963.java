package jaehyeon.week1;

import java.io.*;
import java.util.*;

public class Week1_4963 {

    static int w;
    static int h;

    static int[][] map;
    static boolean[][] visited;

    static final int[] dx = {0,0,1,-1,-1,1,1,-1};
    static final int[] dy = {1,-1,0,0,-1,1,-1,1};

    public static class Pair{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w==0 && h==0) break;

            map = new int[h+1][w+1];
            visited = new boolean[h+1][w+1];

            int island = 0;

            for(int height = 1; height <= h; height++) {
                st = new StringTokenizer(br.readLine());
                for(int width = 1; width <= w; width++) {
                    map[height][width] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=1; i<=h; i++) {
                for(int j=1; j<=w; j++) {
                    if(map[i][j] == 1 && !visited[i][j]) {
                        bfs(j,i);
                        island++;
                    }
                }
            }
            sb.append(island).append("\n");
        }//while
        System.out.print(sb);
    }//main

    private static void bfs(int x,int y) {
        Deque<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(x,y));

        while(!q.isEmpty()) {
            Pair p = q.poll();

            for(int i=0; i<8; i++) {
                int px = p.x + dx[i];
                int py = p.y + dy[i];

                if(!isRange(px, py)) continue;
                if(isVisited(px,py)) continue;
                if(isIsland(px,py)) {
                    q.offer(new Pair(px,py));
                    visited[py][px] = true;
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 < x && x < w+1 && 0 < y && y < h+1;
    }

    private static boolean isVisited(int x, int y) {
        return visited[y][x] == true;
    }

    private static boolean isIsland(int x, int y) {
        return map[y][x] == 1;
    }
}
