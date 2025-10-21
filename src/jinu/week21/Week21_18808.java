package jinu.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Week21_18808 {

    static int noteBook[][];

    static List<int[][]> stickers;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        noteBook = new int[N][M];

        int K = Integer.parseInt(st.nextToken());

        stickers = new ArrayList<>();

        for(int i = 0;i < K; i++){
            st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int tmp[][] = new int[R][C];

            for(int r = 0; r<R; r++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<C;j++){
                    tmp[r][j] = Integer.parseInt(st.nextToken());
                }
            }

            stickers.add(tmp);
        }

//        print(noteBook);
//        System.out.println("---------------------");



        for(int sticker[][] : stickers){

            int applySticker[][] = sticker;

            boolean next = false;

            for(int k =0 ;k < 4; k++){

                for(int i =0 ;i< N;i++){
                    for(int j =0 ; j<M;j++){

                        if(next){
                            break;
                        }

                        if(canApply(noteBook, applySticker, i, j)){
                            apply(noteBook, applySticker, i, j);
                            next = true;
                        }

                    }
                }

                if(next){
                    break;
                }

                applySticker = turnRight(applySticker);
            }
//            print(noteBook);
//            System.out.println("---------------------------");
        }

        //  print(noteBook);

        System.out.println(countOne(noteBook));




    }

    public static boolean canApply(int [][]noteBook, int applySticker[][], int i, int j){
        for(int y = i; y < i + applySticker.length; y++){
            for(int x = j; x < j + applySticker[0].length; x++){

                if(y >= noteBook.length || x >= noteBook[0].length){
                    return false;
                }

                if(noteBook[y][x]==1 && applySticker[y-i][x-j] == 1){
                    return false;
                }
            }
        }

        return true;
    }

    public static void apply(int [][]noteBook, int applySticker[][], int i, int j){

        for(int y = i; y < i + applySticker.length; y++){
            for(int x = j; x < j + applySticker[0].length; x++){

                if(noteBook[y][x]==0 && applySticker[y-i][x-j]==1){
                    noteBook[y][x] = applySticker[y-i][x-j];
                }
            }
        }
    }

    public static int[][] turnRight(int [][]applySticker){

        int tmpApplySticker[][] = new int[applySticker[0].length][applySticker.length];

        for(int i = 0; i <applySticker.length; i++){
            for(int j =0;j<applySticker[0].length; j++){
                tmpApplySticker[j][applySticker.length-1-i] = applySticker[i][j];
            }
        }

        return tmpApplySticker;
    }

    public static void print(int noteBook[][]){
        for(int i=0;i<noteBook.length; i++){
            for(int j=0;j<noteBook[0].length; j++){
                System.out.print(noteBook[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int countOne(int noteBook[][]){

        int count = 0;

        for(int i=0;i<noteBook.length; i++){
            for(int j=0;j<noteBook[0].length; j++){
                if(noteBook[i][j]==1){
                    count++;
                }
            }
        }

        return count;
    }

}
