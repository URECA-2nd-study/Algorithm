import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for(int i=1; i<s.length()/2+1; i++){ //i는 자른 단위 
            String sub = s.substring(0,i);
            StringBuilder sb = new StringBuilder();
            int count = 1; 
            
            for(int j=i; j<s.length(); j+=i){
                String current ;
                if(j+i <= s.length()){
                    current = s.substring(j,j+i);
                }
                else{
                    current = s.substring(j); //문자열 길이가 i의 배수가 아닐 때 예외 처리 
                }
                
                if(sub.equals(current)){
                    count ++;
                    continue;
                }
                else if(count==1){
                    sb.append(sub);
                }
                else{
                    sb.append(count).append(sub);
                    count = 1; 
                }
                sub = current;
                
            }//for - j
            
            if(count>1){
                sb.append(count);
            }
            
            sb.append(sub);
            
            answer = Math.min(answer, sb.length());
            
        }
        return answer;
    }
}