import java.util.*;

class Solution {
    
    static Map<String, ArrayList<String>> map;
    
    public int solution(String[][] clothes) {
        int answer = 1;
        map = new HashMap<>();
        
        for (String[] clothe : clothes) {
            String name = clothe[0];
            String type = clothe[1];
            
            if (!map.containsKey(type)) {
                map.put(type, new ArrayList<String>());
            }
            
            map.get(type).add(name);
        }
        
        for (String key : map.keySet()) {
            answer *= (map.get(key).size() + 1);
        }
        
        
        return answer - 1;
    }
    
    
    static void cal() {
        
    }
}