package jinu.week13;

import java.util.HashSet;
import java.util.Set;

public class Week13_비밀코드해독 {

    class Solution {
        int count = 0;
        public int solution(int n, int[][] q, int[] ans) {
            BT(new HashSet<>(),n,1,q,ans);
            return count;
        }

        // 백트래킹으로 가능한 정답 조합 판단.
        public void BT(HashSet<Integer> set, int n,int start,int [][]q,int []ans){
            // 정답 개수는 항상 5이므로..
            if(set.size()==5){
                if(allMatch(set,q,ans,n)){
                    count++;
                }
                return;
            }

            for(int i=start;i<=n;i++){
                set.add(i);
                BT(set,n,i+1,q,ans);
                set.remove(Integer.valueOf(i));
            }
        }

        public boolean allMatch(Set<Integer> set, int [][]q, int []ans, int n){
            for(int i=0;i<ans.length;i++){
                int tmpCount=0;
                for(int j=0;j<5;j++){
                    if(set.contains(q[i][j])){
                        tmpCount++;
                    }
                }
                // 일치하는 개수가 동일하지 않으면 false 반환
                if(tmpCount!=ans[i]){
                    return false;
                }
            }

            // 일치하는 개수가 모두 동일하다면 , 즉 정답 후보

            return true;
        }
    }
}
