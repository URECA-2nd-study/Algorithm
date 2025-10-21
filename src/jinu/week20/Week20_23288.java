package jinu.week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week20_23288 {

    static class Dice{
        int top;
        int bottom;
        int right;
        int left;
        int front;
        int back;

        public Dice(int top,int front,int right){
            this.top = top;
            this.bottom = 7 - top;
            this.front = front;
            this.back = 7 - front;
            this.right = right;
            this.left = 7 - right;
        }

        public void rightRoll(){
            int tmpTop = top;
            int tmpLeft = left;
            int tmpRight = right;
            int tmpBottom = bottom;

            this.right = tmpTop;
            this.bottom = tmpRight;
            this.top = tmpLeft;
            this.left = tmpBottom;
        }

        public void leftRoll(){
            int tmpTop = top;
            int tmpLeft = left;
            int tmpRight = right;
            int tmpBottom = bottom;

            this.right = tmpBottom;
            this.bottom = tmpLeft;
            this.top = tmpRight;
            this.left = tmpTop;
        }
        public void frontRoll(){
            int tmpTop = top;
            int tmpFront = front;
            int tmpBack = back;
            int tmpBottom = bottom;

            this.front = tmpTop;
            this.bottom = tmpFront;
            this.top = tmpBack;
            this.back = tmpBottom;
        }

        public void backRoll(){
            int tmpTop = top;
            int tmpFront = front;
            int tmpBack = back;
            int tmpBottom = bottom;

            this.front = tmpBottom;
            this.bottom = tmpBack;
            this.top = tmpFront;
            this.back = tmpTop;
        }
    }

    static class Game{
        int totalScore;

        int currentY;
        int currentX;
        int repeatCount;

        Dice dice;
        int map[][];

        int dy[]={0,1,0,-1};
        int dx[]={1,0,-1,0};

        int direction;

        int N;
        int M;

        int countMap[][];



        public Game(int repeatCount, int N, int M, int map[][]) {
            this.totalScore = 0;
            this.currentY = 1;
            this.currentX = 1;
            this.direction = 0;
            this.repeatCount = repeatCount;
            this.dice = new Dice(1,5,3);
            this.map = map;
            this.N = N;
            this.M = M;
            this.countMap = new int[N+1][M+1];
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= M; j++){
                    if(countMap[i][j]==0){
                        countMap[i][j] = BFS(i,j);
                    }
                }
            }
        }

        public void play(){
            for(int i = 0; i < repeatCount; i++){
                int tmpY = currentY + dy[direction];
                int tmpX = currentX + dx[direction];

                if(tmpY<1 || tmpY > N || tmpX < 1 || tmpX > M){
                    direction = (direction + 2) % 4;
                }

                diceRoll();

                currentY += dy[direction];
                currentX += dx[direction];


                int B = map[currentY][currentX];
                this.totalScore += B * countMap[currentY][currentX];

                int A = dice.bottom;

                if(A > B){
                    direction = (direction + 1) % 4;
                }
                else if(A < B){
                    direction -= 1;
                    if(direction == -1){
                        direction = 3;
                    }
                }

            }
        }

        public void diceRoll(){
            if(dy[direction]==0 && dx[direction] == 1){
                this.dice.rightRoll();
            }
            else if(dy[direction] == 0 && dx[direction]==-1){
                this.dice.leftRoll();
            }
            else if(dy[direction] == 1 && dx[direction] == 0){
                this.dice.frontRoll();
            }
            else{
                this.dice.backRoll();
            }
        }

        public int BFS(int currentY, int currentX){
            int count = 0;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{currentY, currentX});
            boolean isVisited[][] = new boolean[N+1][M+1];
            isVisited[currentY][currentX] = true;

            while(!queue.isEmpty()){

                int []current = queue.poll();
                count++;

                for(int k = 0; k <4; k++){
                    int nextY = current[0] + dy[k];
                    int nextX = current[1] + dx[k];

                    if(nextY>=1 && nextY<=N && nextX >=1 && nextX <= M && !isVisited[nextY][nextX] && map[nextY][nextX] == map[currentY][currentX]){
                        isVisited[nextY][nextX] = true;
                        queue.add(new int[]{nextY,nextX});
                    }
                }

            }

            return count;
        }

    }



    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int map[][] = new int[N+1][M+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<= M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Game game = new Game(K, N, M, map);

        game.play();

        System.out.println(game.totalScore);


    }

}
