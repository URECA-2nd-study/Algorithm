import java.io.*;
import java.util.*;

class Week12_150367 {
    // numbers 배열 안에 있는 숫자들을 비트형식으로 변환해서
    // 자식노드는 하나씩 비워도 되지만 부모노드가 하나씩 켜져있어야 한다.
    // 자식노드가 있다면 -> 부모노드도 있어야 한다는 말
    // 비트 형식은 이진트리 중위 순회 형식으로 되어 있다.
    // 그러면 중위 순회할 때 각 서브트리의 부모노드가 1이 되어 있어야 한다.
    
    public int[] solution(long[] numbers) {     
        int[] answer = new int[numbers.length];
        
        int idx = 0;
        for(long w : numbers) {
            StringBuilder sb = new StringBuilder();   
          
            while(w > 0) {
                sb.append(w & 1);
                w >>= 1;
            }
            
            int fullLength = (1 << getTreeLevel(sb.length())) - 1;
            
            while(sb.length() < fullLength) {
                sb.append(0);
            }
            
            sb.reverse();
            answer[idx++] = isBinaryTree(sb.toString()) ? 1 : 0;
        }
        
        return answer;
    }
    
    // 자식 노드가 있다면 부모노드도 있어야하고
    // 자식 노드가 없다면 부모노드는 있어도 되고 없어도 된다.
    private static boolean isBinaryTree(String str) {
        if(str.length() == 1) return true;
        
        int mid = str.length() / 2;
        char root = str.charAt(mid);
        String left = str.substring(0, mid);
        String right = str.substring(mid + 1);
        
        if(root == '0' && (left.contains("1") || right.contains("1"))) {
            return false;
        }
        return isBinaryTree(left) && isBinaryTree(right);
    }
    
    private static int getTreeLevel(int nodeCnt) {
        int level = 0;
        
        while( (1 << level) - 1 < nodeCnt) {
            level++;
        }
        return level;
    }
}