import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        
        for (String c : completion) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (String p : participant) {
            if (!map.containsKey(p) || map.get(p) <= 0) answer = p;
            else map.put(p, map.get(p) - 1);
        }
        
        return answer;
    }
}