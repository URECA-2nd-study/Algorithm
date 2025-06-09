import java.io.*;
import java.util.*;

class Week15_60059 {
    private static int N, M, cnt;
    private static int[][] board;
    public boolean solution(int[][] key, int[][] lock) {
        N = key.length;
        M = lock.length;
        cnt = 0;
        
        int w = 2 * N + M - 2;
        board = new int[w][w];
        
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                board[N - 1 + i][N - 1 + j] = lock[i][j];
                
                if(board[N - 1 + i][N - 1 + j] == 0) {
                    board[N - 1 + i][N - 1 + j] = -1;
                    cnt++;
                }
            }
        }      
        boolean answer = simulate(key);
        return answer;
    }
    
    private static boolean simulate(int[][] key) {
        for(int i = 0; i < 4; i++) {
            key = rotate(key);
            
            for(int j = 0; j < N + M - 1; j++) {
                for(int k = 0; k < N + M - 1; k++) {
                    int count = 0;
                
                    out: for(int r = j; r < j + N; r++) {
                            for(int c = k; c < k + N; c++) {
                                if(board[r][c] == 1 && key[r - j][c - k] == 1) {
                                   break out;
                                }
                                if(board[r][c] == -1 && key[r - j][c - k] == 1) {
                                    count++;
                                }
                            }
                    }
                    if(count == cnt) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private static int[][] rotate(int[][] key) {
        int n = key.length;
        int[][] result = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                result[i][j] = key[n - 1 - j][i];
            }
        }
        return result;
    }
}