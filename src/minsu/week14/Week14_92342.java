import java.io.*;
import java.util.*;

class Week14_92342 {
    private static int arrowsCount, max;
    private static int[] apeachArrows;
    private static int[] result = {-1};
    
    public int[] solution(int n, int[] info) {
        arrowsCount = n;
        apeachArrows = info;
        
        dfs(0, 0, new int[11]);
        return result;
    }
    
    private static void dfs(int depth, int currArrowsCount, int[] ryanArrows) {
        if(depth == 10) {
            // 1점까지 다 쐈다면 d
            // 남은 화살 갯수는 0점에 다 쏜다고 가정.
            ryanArrows[depth] = arrowsCount - currArrowsCount;
            // 둘의 게임 차이 계산
            int diff = gameDiff(ryanArrows);
            
            // diff가 양수일 경우만 ryan이 apeach를 이긴 경우
            if(diff > 0) {
                // max보다 클 경우 result를 다시 최신화
                if(max < diff) {
                    max = diff;
                    result = ryanArrows.clone();
                }else if(max == diff) {
                    // max랑 같을 경우 낮은 점수를 더 많이 쏜 결과를 출력해야함.
                    for(int i = 10; i >= 0; i--) {
                        // 둘 다 0개를 쏜 건 continue
                        if(ryanArrows[i] == 0 && result[i] == 0) {
                            continue;
                        }
                        // 기존에 있던 결과와 현재 ryan이 쐈던 기록 중에 가장 낮은 점수를 쐈던 것을
                        // 비교해서 정답 최신화
                        if(ryanArrows[i] > result[i]) {
                            result = ryanArrows.clone();
                            break;
                        }else {
                            break;
                        }
                    }
                }
            }
            return;
        }
        
        // 해당 점수 과녁을 안쐈을 때
        dfs(depth + 1, currArrowsCount, ryanArrows);
        
        // 해당 점수 과녁을 apeach보다 한 발 더 많이 쐈을 경우.
        int targetArrow = apeachArrows[depth] + 1;
        // 지금까지 쐈던 화살 갯수 + 현재 쏴야 할 화살 갯수 < 총 화살 갯수보다 작을 경우.
        if(targetArrow + currArrowsCount <= arrowsCount) {
            // ryan이 해돵 과녁 점수에 몇 발 쐈는지 기록
            ryanArrows[depth] = targetArrow;
            dfs(depth + 1, currArrowsCount + targetArrow, ryanArrows);
            // ryan이 해당 과녁 점수 초기화
            ryanArrows[depth] = 0;   
        } 
    }
    
    // apeach와 ryan 점수 차이 계산하는 메서드
    private static int gameDiff(int[] ryanArrows) {
        int ryanScore = 0;
        int apeachScore = 0;
        
        for(int i = 0; i <= 10; i++) {
            // 둘 다 0점일 때는 continue
            if(ryanArrows[i] == 0 && apeachArrows[i] == 0) {
                continue;
            }
            
            // ryan이 해당 과녁 점수에 더 많이 쐈을 경우 ryan 점수에 해당 과녁 점수를 더함.
            if(ryanArrows[i] > apeachArrows[i]) {
                ryanScore += (10 - i);
            }else {
                // 이외의 경우 apeach 점수에 더함.
                apeachScore += (10 - i);
            }
        }
        // 둘의 차이를 return
        return ryanScore - apeachScore;
    }
}