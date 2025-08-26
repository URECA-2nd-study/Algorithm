import java.io.*;
import java.util.*;

class Week15_60057 {
    public int solution(String s) {
        int answer = s.length();
        
        for(int i = 1; i <= s.length() >> 1; i++) {
            answer = Math.min(answer, compress(s, i)); 
        }
        return answer;
    }
    
    private int compress(String s, int unit) {
        StringBuilder sb = new StringBuilder();
        String prev = s.substring(0, unit);
        int cnt = 1;
        
        for(int i = unit; i < s.length(); i += unit) {
            int end = Math.min(i + unit, s.length());
            String curr = s.substring(i, end);
            
            if(prev.equals(curr)) {
                cnt++;
            } else {
                if(cnt > 1) {
                    sb.append(cnt);
                }
                sb.append(prev);
                prev = curr;
                cnt = 1;
            }
        }
        
        if (cnt > 1) {
            sb.append(cnt);
        }
        sb.append(prev);
        return sb.length();
    }
}