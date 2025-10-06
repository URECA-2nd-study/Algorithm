package jinu.week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Week19_20165 {

    static int domino[][];
    static char dominoStatus[][];

    static final Map<Character, int[]> map = Map.of(
            'E', new int[]{0, 1},
            'W', new int[]{0, -1},
            'S', new int[]{1,0},
            'N', new int[]{-1,0}
    );

    static int count = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        domino = new int[N+1][M+1];
        dominoStatus = new char[N+1][M+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++){
                domino[i][j]=Integer.parseInt(st.nextToken());
                dominoStatus[i][j]='S';
            }
        }

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            char D = st.nextToken().charAt(0);

            run(X,Y,D,N,M);

            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());

            dominoStatus[X][Y]='S';
        }

        System.out.println(count);
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                System.out.print(dominoStatus[i][j]+" ");
            }
            System.out.println();
        }


    }

    public static void run(int X,int Y,char D,int N,int M){
        if(dominoStatus[X][Y]=='F'){
            return;
        }

        int dx = map.get(D)[0];
        int dy = map.get(D)[1];
        int currentX = X;
        int currentY = Y;
        int tmpCount = 1;
        dominoStatus[currentX][currentY]='F';

        int endX = X + dx*(domino[X][Y]-1);
        int endY = Y + dy*(domino[X][Y]-1);


        while(true){
            if(currentX ==endX && currentY == endY){
                break;
            }
            currentX+=dx;
            currentY+=dy;

            if(currentX>N || currentX<=0 || currentY > M || currentY <=0){
                break;
            }

            if(dominoStatus[currentX][currentY]=='S'){
                dominoStatus[currentX][currentY] = 'F';
                if(dx+dy==1){
                    endX = Math.max(endX,currentX + dx * (domino[currentX][currentY]-1));
                    endY = Math.max(endY,currentY + dy * (domino[currentX][currentY]-1));
                }
                else{
                    endX = Math.min(endX,currentX + dx * (domino[currentX][currentY]-1));
                    endY = Math.min(endY,currentY + dy * (domino[currentX][currentY]-1));
                }

                tmpCount++;
            }
        }

        count+=tmpCount;

    }

}
