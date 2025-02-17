import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Character> stack = new Stack<>();
        
        for (char digit : number.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && digit > stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        
        }
        
        while (k > 0) {
            stack.pop();
            k--;
        }
            
        

        for (char digit : stack) {
            answer += digit;
        }

        return answer;
    }
        
    
}

