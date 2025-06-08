/*
완탐?
- 열쇠는 자물쇠의 영역을 벗어나도 된다. 
=> 열쇠를 옮겨가면서 맞는지 확인 
=> 안맞는다면 90도 돌려서 또 확인 (3번반복)

*/

import java.util.*;
import java.math.*;

class Solution {
    
    static int N, M;
    
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        
        for(int i=0; i<4; i++){
            key = turnKey(key);
            if(substitute(key, lock)){
                return true;
            }
        }
        
        return false;
    }//main
    
    //lock 확장하는 메서드
    public int[][] expandLock(int [][] lock){
        int [][] expanded = new int [N+2*(M-1)][N+2*(M-1)];
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                expanded[i+M-1][j+M-1] = lock[i][j];
            }
        }
        
        return expanded;
    }
    
    
    //열쇠를 한 칸씩 옮기면서 대입하는 메서드 
    public boolean substitute(int [][] key, int [][] lock){
        
        for(int x=0; x<N+M-1; x++){
            for(int y=0; y<N+M-1; y++){
                int [][] expanded = expandLock(lock);
                for(int i=0; i<M; i++){
                    for(int j=0; j<M; j++){
                        expanded[x+i][y+j] += key[i][j];
                    }
                }
                
                if(isEnabled(expanded)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // expanded Lock이 모두 1인지 확인하는 메서드 
    public boolean isEnabled (int [][] lock){
        for(int i=M-1; i<N+M-1; i++){
            for(int j=M-1; j<N+M-1; j++){
                if(lock[i][j] != 1){
                    return false;
                }
            }
        }
        return true;
    }
    
    //key를 오른쪽으로 90도 돌리는 메서드
    public int[][] turnKey (int [][] key){
        int [][] turned = new int[M][M];
        
        // [i][j] => [j][Math.abs(i-2)] 
        
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                turned[j][M-i-1] = key[i][j];
            }
        }
        return turned;
    } //turnKey
    
}