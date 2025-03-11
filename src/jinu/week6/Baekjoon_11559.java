package jinu.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_11559 {

    static char map[][];
    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};

    static int HEIGHT = 12;
    static int WIDTH = 6;

    static int count = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new char[HEIGHT][WIDTH];

        for(int i=0; i<HEIGHT; i++){
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            for(int j=0;j<WIDTH;j++){
                map[i][j] = tmp.charAt(j);
            }
        }


        while(true){
            boolean isBomBed = deleteBombAndDecide();
            if(!isBomBed){
                break;
            }
            count+=1;
            drop();
           // print();
        }

        System.out.println(count);
    }


    // 폭탄을 터트리고, 폭탄이 터지는 경우가 있다면 true 반환, 긇지 않으면 False 반환하는 메서드
    public static boolean deleteBombAndDecide(){

        boolean bomb = false;

        for(int i=0;i<HEIGHT;i++){
            for(int j=0;j<WIDTH;j++){

                // 색이 있는 경우에만 밑 로직 수행
                if(map[i][j]!='.'){

                    // 색깔이 있는 경우 초기화
                    List<int[]> connectionList = new ArrayList<>(List.of(new int[]{i, j}));
                    boolean isUsed[][] = new boolean[12][6];
                    isUsed[i][j] = true;

                    //DFS 적용하여 4개 이상인지 판단하기 위해 ConnectionList에 값을 넣어주는 작업
                    DFS(i,j,connectionList,isUsed);


                    //터지는 경우가 4개 이상인 경우
                    if(connectionList.size()>=4){
                        for(int []position: connectionList){
                            map[position[0]][position[1]]='.';
                        }
                        bomb = true;
                    }
                }
            }
        }

        return bomb;
    }

    // DFS 방식으로 터질 수 있는지 판단
    public static void DFS(int i, int j, List<int[]> list, boolean [][]isUsed){

        for(int k =0 ;k<4;k++){
            int newY = i + dy[k];
            int newX = j + dx[k];

            if(newY>=0 && newY<12 && newX>=0 && newX< 6 && map[newY][newX]==map[i][j] && !isUsed[newY][newX]){
                list.add(new int[]{newY,newX});
                isUsed[newY][newX] = true;
                DFS(newY,newX,list,isUsed);
            }
        }
    }

    // 폭탄이 터졌을 때의 공백을 매우기 위해서 Queue 에 안터진 것을 넣어 놓고,
    // 배열을 초기화해준다.
    public static void drop(){
        for(int j=0;j<WIDTH;j++){
            Queue<Character> tmp = new LinkedList<>();
            for(int i=HEIGHT-1;i>=0;i--){
                if(map[i][j]!='.'){
                    tmp.add(map[i][j]);
                }
            }

            for(int i=HEIGHT-1;i>=0;i--){
                if(!tmp.isEmpty()){
                    map[i][j]=tmp.poll();
                }
                else{
                    map[i][j]='.';
                }
            }
        }
    }

    public static void print(){
        for(int i=0;i<HEIGHT;i++){
            for(int j=0;j<WIDTH;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
