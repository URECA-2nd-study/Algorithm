import java.io.*;
import java.util.*;

class Week12_340210 {
    private static int N;
    private static String[] A, B, C, op1, op2;
    // 식은 A op1 B op2 C 이렇게 구성되어 있습니다.
    
    public ArrayList<String> solution(String[] expressions) {
        
        N = expressions.length;
        A = new String[N];
        B = new String[N];
        C = new String[N];
        op1 = new String[N];
        op2 = new String[N];
        
        int idx = 0;
        // 주어진 식들에서 가장 큰 숫자를 찾아 저장하는 변수입니다.
        int minRange = 0;
        for(String str : expressions) {
            minRange = Math.max(minRange, findMinFormation(str));
            String[] tmp = str.split(" ");
            A[idx] = tmp[0];
            op1[idx] = tmp[1];
            B[idx] = tmp[2];
            op2[idx] = tmp[3];
            C[idx++] = tmp[4];
        }
        
        // 최소 x진법부터 최대 y진법까지 찾아 저장하는 배열입니다.
        ArrayList<Integer> possibleBases = findPossibleBase(minRange);

        // X에 조건에 맞춰 값을 넣어주는 메서드입니다.
        ArrayList<String> answer = fillValueX(possibleBases);
        return answer;
    }
    
    // 가능한 진법들을 완탐하여 X에 들어가는 값을 찾습니다.
    // X에 들어가는 값이 하나라면 그 값을 출력하고 여러 개라면 "?"를 출력합니다.
    private static ArrayList<String> fillValueX(ArrayList<Integer> bases) {
        ArrayList<String> answer = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
            if(!C[i].equals("X")) continue;
    
            Set<String> rs = new HashSet<>();
            
            for(int base : bases) {
                int a = toInt(A[i], base);
                int b = toInt(B[i], base);
                int c = op1[i].equals("+") ? (a + b) : (a - b);
                rs.add(fromInt(c, base));
            }
            
            String fill = "?";
            // rs의 크기가 1이라면 X에 들어가는 값이 1개라는 것이기 때문에 X에 rs에 들어간 값을 넣고
            // 1이 아니라면 값이 여러 개가 존재할 수 있기 때문에 ?를 넣어 출력합니다.
             if(rs.size() == 1) {
                for(String s : rs) {
                    fill = s;
                }
            }
        
        answer.add(A[i] + " " + op1[i] + " " + B[i] + " " + op2[i] + " " + fill);
        }
        return answer;
    }
    
    // 가능한 base 진법들을 찾는 메서드
    private static ArrayList<Integer> findPossibleBase(int minRange) {
        
        ArrayList<Integer> possibleBase = new ArrayList<>();
        
        for(int i = minRange; i<=9; i++) {
            boolean isPoss = true;
            
            for(int j = 0; j < N; j++) {
                if(!C[j].equals("X")) {
                    int a = toInt(A[j], i);
                    int b = toInt(B[j], i);
                    
                    int c = op1[j].equals("+") ? (a + b) : (a - b);

                    if(c != toInt(C[j], i)) {
                        isPoss = false;
                        break;
                    }
                }
            }
            
            if(isPoss) {
                possibleBase.add(i);
            }
        }
    
        return possibleBase;
    }

    // 주어진 10진수를 base진법대로 변환하는 메서드
    private static String fromInt(int x, int base) {
        if(x == 0) return "0";
        StringBuilder sb = new StringBuilder();
        
        while(x > 0) {
            sb.append(x % base);
            x /= base;
        }
        return sb.reverse().toString();
    }
    
    // 주어진 base진법 수를 10진수로 변환하는 메서드
    private static int toInt(String num, int base) {
        int rs = 0;

        for(int i = 0; i < num.length(); i++) {
            rs = rs * base + (num.charAt(i) - '0');
        }
        return rs;
    }
    
    // 식의 가장 큰 값을 찾는 메서드입니다.
    private static int findMinFormation(String expression) {
        int max = 0;
        
        for(int i = 0; i< expression.length(); i++) {
            if(expression.charAt(i) >= '1' && expression.charAt(i) <= '9') {
                max = Math.max(max, expression.charAt(i));
            }
        }
        return max - '0' + 1;
    }
}