import java.util.*;

class Solution {
    
    /*
    1. 무조건 가능하지 않은 숫자를 판별하기 (ans = 0인 경우)
    2. 만약 ans=5가 있다면 무조건 답은 1
    3. 가능한 숫자 중에서 조합 만들기.
    4. 조건에 맞으면 정답 ++;
    
    */
    
    static ArrayList<Integer> impossible = new ArrayList<>(); //불가능한 숫자
    static ArrayList<Integer> candi = new ArrayList<>(); //숫자 후보 
    static int answer, m;
    static int [][] q;
    static int [] ans;
    
    public int solution(int n, int[][] sq, int[] sans) {
        q = sq;
        ans = sans;
        m = sans.length;
        
        for(int i=0; i<m; i++){
            if(ans[i] == 5)
                return 1;
            else if(ans[i] == 0){
                for(int num : q[i]){
                    impossible.add(num);
                }
            }
        }//불가능한 숫자 찾기 
        
        //가능한 숫자후보 찾기
        for(int i = 1; i<=n; i++){
            if(!impossible.contains(i)){
                candi.add(i);
            }
        }
        
        //조합 만들면서 조건 확인 
        combi(0, new ArrayList<Integer>());
        
        return answer;
    }//main
    
    
    //조합만들기 
    public void combi (int start, ArrayList<Integer> temp){
        if(temp.size() == 5){
            if(isEnabled(temp)){
                answer++;
            }
            return; 
        }
        
        for(int i=start; i<candi.size(); i++){
            temp.add(candi.get(i));
            combi(i+1, temp);
            temp.remove(temp.size() - 1);
        }
    }//combi
    
    //조건 판별하기 
    
    public boolean isEnabled(ArrayList<Integer> temp){
        
        for(int i=0; i<m; i++){
            int count = 0;
            for(int n : q[i]){
                if(temp.contains(n)){
                    count ++;
                }
            }
            if(ans[i] != count)
                return false;
        }
        
        return true;
    }//isEnabled
    
}