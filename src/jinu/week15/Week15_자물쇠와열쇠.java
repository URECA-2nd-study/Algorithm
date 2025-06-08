package jinu.week15;
import java.util.*;
public class Week15_자물쇠와열쇠 {

    class Solution {
        public boolean solution(int[][] key, int[][] lock) {

            int len = 2*key.length + lock.length - 2;

            //map의 형태: 중앙에 Lock 배열 존재하고 Key배열들이 Lock배열을 감쌀수 있게 설계된 Map.
            int map[][] = new int[len][len];


            // lock 배열을 map 의 중앙에 옮김.
            for(int i=key.length-1;i<key.length-1+lock.length;i++){
                for(int j=key.length-1;j<key.length-1+lock.length;j++){
                    map[i][j] = lock[i-(key.length-1)][j-(key.length-1)];
                }
            }

            for(int i=0;i<map.length-key.length+1;i++){
                for(int j=0;j<map.length-key.length+1;j++){
                    //회전
                    for(int r=0;r<4;r++){

                        //key배열을 map에 삽입
                        for(int x=0;x<key.length;x++){
                            for(int y=0;y<key.length;y++){
                                map[i+x][j+y] += key[x][y];
                            }
                        }

                        if(isFullFill(map,key.length,lock.length)){
                            return true;
                        }

                        // 원상 복구
                        for(int x=0;x<key.length;x++){
                            for(int y=0;y<key.length;y++){
                                map[i+x][j+y] -= key[x][y];
                            }
                        }

                        rotate(key);


                    }
                }
            }



            return false;
        }

        // 주어진 조건을 만족하는지 여부
        private boolean isFullFill(int map[][],int keyLength,int lockLength){
            for(int i=keyLength-1;i<keyLength-1+lockLength;i++){
                for(int j=keyLength-1;j<keyLength-1+lockLength;j++){
                    if(map[i][j]!=1){
                        return false;
                    }
                }
            }

            return true;
        }


        // 시계방향으로 key를 회전
        private void rotate(int[][] key){
            int keyLen = key.length;
            int[][] temp = new int[keyLen][keyLen];

            for(int i=0; i<keyLen; i++){
                for(int j=0; j<keyLen; j++){
                    temp[i][j] = key[j][keyLen-i-1];
                }
            }

            for(int i=0; i<keyLen; i++){
                for(int j=0; j<keyLen; j++){
                    key[i][j] = temp[i][j];
                }
            }

        }

    }

}
