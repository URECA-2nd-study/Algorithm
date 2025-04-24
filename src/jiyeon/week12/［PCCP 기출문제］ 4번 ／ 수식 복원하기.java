import java.util.*;
/*
1. N진수의 범위 줄이기 
1-1. 숫자의 범위가 N미만이여야함 
1-2. 수식의 계산 결과가 같아야함
*/

class Solution {
    
    static boolean [] bases = new boolean [8]; //bases[i] : i+2진수의 가능여부 
    static ArrayList<String> eq = new ArrayList<>(); //방정식 보관
    static ArrayList<String> exp = new ArrayList<>(); //완전한 수식 보관
    
    public String[] solution(String[] expressions) {
        int max  = -1;
        int baseCnt = 8; //가능한 진수의 개수 
        Arrays.fill(bases,true);
        
        for(String ex : expressions){
            //방정식인 경우 
            if(ex.charAt(ex.length()-1) == 'X'){
                eq.add(ex);
            }
            else{
                exp.add(ex);
            }
            
            max = Math.max(max, getMax(ex));
        }//for
        
        String[] answer = new String[eq.size()];
        
        //초기 범위 설정 (max 이하는 불가능)
        for(int i=0; i<=max-2; i++){
            bases[i] = false;
            baseCnt --;
        }
        
        //완벽한 수식을 진수 후보에 따라 계산 => 범위 확정하기 
        if(baseCnt != 1){ //만약 baseCnt가 1이라면 무조건 N진수가 확정되기 때문에 skip
            for(String ex : exp){
                for(int i=0; i<8; i++){
                    if(bases[i] && !isEnable(ex,i+2)){
                        bases[i] = false;
                        baseCnt --;
                    }
                }
            }
        }

        //방정식 돌면서 X 확정하기
        for(int k=0; k<eq.size(); k++){
            HashSet<String> results = new HashSet<>(); //가능한 결과 담기
            String ex = eq.get(k);
            String onlyOneResult = ""; 
            for(int i=0; i<8; i++){
                if(bases[i]){
                    onlyOneResult = Integer.toString(cal(ex,i+2), i+2); 
                    results.add(onlyOneResult);
                }
            }
            String ans = "";

            if(results.size()>1){
                ans = getResult(ex,"?");
            }
            else{
                ans = getResult(ex,String.valueOf(onlyOneResult));
            }
            answer[k] = ans; 
        }//for
        
        return answer;
    }//main 
    
    //결과 String 만들기
    public String getResult(String eq, String res){
        StringTokenizer st = new StringTokenizer(eq);
        StringBuilder sb = new StringBuilder();
        sb.append(st.nextToken()).append(" ");
        sb.append(st.nextToken()).append(" ");
        sb.append(st.nextToken()).append(" ");
        sb.append(st.nextToken()).append(" ");
        sb.append(res);
        
        return sb.toString();
    }
    
    // N진수일 때 계산결과가 맞는지 확인하는 메서드 
    public boolean isEnable(String ex, int N){
        int res = cal(ex,N);
        StringTokenizer st = new StringTokenizer(ex);
        st.nextToken(); st.nextToken(); st.nextToken(); st.nextToken();
        int n3= Integer.parseInt(st.nextToken(), N);
        return res == n3;
    }

    //N진수로 계산하기 
    public int cal(String ex, int N){
        
        StringTokenizer st = new StringTokenizer(ex);
        //계산을 위해 N진수라고 가정하고 10진수로 변환 
        int n1 = Integer.parseInt(st.nextToken(), N);
        char op = st.nextToken().charAt(0);
        int n2 = Integer.parseInt(st.nextToken(), N);

        int res = 0;

        if(op=='+'){
            res = n1 + n2;
        }
        else{
            res = n1 - n2;
        }
        //10진수로 반환
        return res;
    }
    
    //최대 숫자 찾기 
    public int getMax(String ex){
        int max = 0;
        
        for(char ch : ex.toCharArray()){
            if(Character.isDigit(ch)){
                int temp = ch - '0';
                max = Math.max(temp,max);
            }
        }
        return max;
    }//getMax 
    
}