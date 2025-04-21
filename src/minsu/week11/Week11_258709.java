import java.io.*;
import java.util.*;

class Solution {
    private static int[][] dices;
    private static int N;
    private static double max;
    private static ArrayList<Integer> ans;
    private static ArrayList<Integer> rsA;
    private static ArrayList<Integer> rsB;
    
    public ArrayList<Integer> solution(int[][] dice) {
        N = dice.length;
        dices = dice;
        combi(0, 0, 0);
        
        ArrayList<Integer> answer = new ArrayList<>();
        for(int w : ans) {
            answer.add(w + 1);
        }
        
        return answer;
    }
    
    // 주사위를 먼저 고르고 A가 고를 주사위를 고르고 나머지는 B가 고르는 주사위
    // 각 주사위의 번호 1개씩 모아서 합의 조합을 구한다.
    // 그리고 a가 고른 주사위의 합 b가 고른 주사위의 합을 구한다.
    
    // A가 고를 주사위를 고른다.
    private static void combi(int idx, int depth,int choice) {
        if(depth == (N >> 1)) {
            
            ArrayList<Integer> A = new ArrayList<>();
            ArrayList<Integer> B = new ArrayList<>();
            rsA = new ArrayList<>();
            rsB = new ArrayList<>();
            
            for(int i = 0; i<N; i++) {
                if((choice & (1 << i)) != 0) {
                    A.add(i);
                }else {
                    B.add(i); 
                }
            }
            
            cal(0, A, 0, true);
            cal(0, B, 0, false);
            
            Collections.sort(rsB);
            
            int win = 0;
            for(int i = 0; i<rsA.size(); i++) {
                int curr = rsA.get(i);
                win += upperBound(curr);
            }
            
            double tmp = (double) win / (rsA.size() * rsB.size());
            
            if(max < tmp) {
                max = tmp;
                ans = A;
            }
            
        }else {
            
            for(int i = idx; i<N; i++) {
                choice |= (1 << i);
                combi(i + 1, depth + 1, choice);
                choice ^= (1 << i);
            }
            
        }
    }
    
    // a가 b를 이기는 시점을 이진 탐색으로 구하기
    private static int upperBound(int target) {
        int s = 0;
        int e = rsB.size() - 1;
        
        while(s <= e ) {
            int mid = s + (e - s) / 2;
            
            if(rsB.get(mid) < target) {
                s = mid + 1;
            }else {
                e = mid - 1;
            }
        }
        
        return s;
    }
    
    // 뽑은 주사위에서 한 개씩만 골라서 그것을 더해야돼
    
    private static void cal(int depth, ArrayList<Integer> choice, int sum, boolean isA) {
        if(depth == (N >> 1)) {
            
            if(isA) {
                rsA.add(sum);
            }else {
                rsB.add(sum);
            }
            
        } else {
            int curr = choice.get(depth);
            
            for(int i = 0; i<6; i++) {
                cal(depth + 1, choice, sum + dices[curr][i], isA);
            }
        }
    }
}