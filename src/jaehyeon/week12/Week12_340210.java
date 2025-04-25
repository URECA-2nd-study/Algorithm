package src.jaehyeon.week12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 클래스
 * ParsedInput: init()메서드에서 2개의 값을 리턴 받기위한 Wrapper 클래스
 * Code: 수식을 토큰화 하여 저장
 *
 * 메서드
 * solution: main역할
 * init: 수식 토큰화 및 지워진 수식 카운트
 * findMinBase: 가능한 최소 진법 찾기
    * findMaxDigit: 가장 큰 한자리 수 찾기
 *
 * guessBase: 특정 진법을 찾거나 지워진 수식 채우기
    * compareEqual: A op B == C 판별
    * findSpecificBase: 특정 진법 찾기
    * fillWithSpecificBase: 지워진 수식 특정 진법으로 채우기
    * fillWithBaseRange: 지워진 수식 범위로 찾기
 */

public class Week12_340210 {

    class Solution {

        public class ParsedInput{
            ArrayList<Code> codeList;
            int xCount;

            public ParsedInput(ArrayList<Code> codeList, int xCount){
                this.codeList = codeList;
                this.xCount = xCount;
            }
        }

        public class Code{
            String A;
            String op;
            String B;
            String C;

            public Code(String A, String op, String B, String C){
                this.A = A;
                this.B = B;
                this.C = C;
                this.op = op;
            }
        }

        public String[] solution(String[] expressions) {

            // 수식 토큰화, 지워진 수식 카운트
            ParsedInput parsed = init(expressions);

            ArrayList<Code> codeList = parsed.codeList;
            int xCount = parsed.xCount;

            //가능한 최소 진법
            int minBase = findMinBase(codeList);

            //진법 추론
            String[] answer = guessBase(codeList, minBase, xCount);

            return answer;
        }

        private String[] guessBase(ArrayList<Code> codeList, int minBase, int xCount){

            String[] answer = new String[xCount];

            //지워진 수식은 후순위 정렬
            Collections.sort(codeList, Comparator.comparingInt(
                    o -> o.C.equals("X") ? Integer.MAX_VALUE : Integer.parseInt(o.C)
            ));

            int specificBase = 0;

            int answerIndex = -1;

            for(Code code : codeList){

                String a = code.A;
                String b = code.B;
                String c = code.C;
                String op = code.op;

                if(code.C.equals("X")){

                    StringBuilder sb = new StringBuilder();
                    sb.append(a).append(" ").append(op).append(" ").append(b).append(" = ");

                    String X = "";

                    answerIndex++;


                    if(specificBase != 0){
                        // 진법이 정해진 경우
                        X = fillWithSpecificBase(a,b,op,specificBase);

                    }else{
                        // 진법이 정해지지 않은 경우
                        X = fillWithBaseRange(a,b,op,minBase);
                    }

                    sb.append(X);

                    answer[answerIndex] = sb.toString();

                    continue;

                }

                // 특정 진법이 정해진 경우 예외
                if(specificBase != 0) continue;

            /*
                a op b == c 인 경우 maxDigit가 minBase
                maxDigit는 이미 구했기 때문에 예외
            */
                if(compareEqual(a,b,c,op)) continue;

                specificBase = findSpecificBase(a,b,c,op,minBase);

            }

            return answer;

        }

        /*
            최소 진법에서 9진법 까지 탐색하며 값이 달라질 경우 ? 반환
        */
        private String fillWithBaseRange(String A, String B, String op, int minBase){

            String previousValue = "0";
            String X = "";

            for(int base = minBase; base <=9; base++){

                int a = Integer.parseInt(A,base);
                int b = Integer.parseInt(B, base);

                int ab = op.equals("+") ? a+b : a-b;

                String currentValue = Integer.toString(ab, base);

                if(previousValue.equals("0")){
                    previousValue = currentValue;
                    X = previousValue;
                    continue;
                }

                if(!previousValue.equals(currentValue)){
                    X = "?";
                    break;
                }
            }

            return X;

        }

        /*
            특정 진법이 정해지면 해당 진법으로 수식 복원
        */
        private String fillWithSpecificBase(String A, String B, String op, int specificBase){

            int a = Integer.parseInt(A,specificBase);
            int b = Integer.parseInt(B,specificBase);

            int ab = op.equals("+") ? a+b : a-b;

            String C = Integer.toString(ab,specificBase);

            return C;

        }

        /*
            특정 진법 찾기
        */
        private int findSpecificBase(String A, String B, String C, String op, int minBase){

            int specificBase = 0;

            for(int base = minBase; base<=9; base++){

                int a = Integer.parseInt(A,base);
                int b = Integer.parseInt(B,base);
                int c = Integer.parseInt(C,base);

                int ab = op.equals("+") ? a+b : a-b;

                if(ab == c){

                    specificBase = base;
                    break;

                }

            }

            return specificBase;

        }

        /*
            A+B == C or A-B == C
            >> True
        */
        private boolean compareEqual(String A, String B, String C, String op){

            int a = Integer.parseInt(A);
            int b = Integer.parseInt(B);
            int c = Integer.parseInt(C);

            int ab = op.equals("+") ? a+b : a-b;

            return ab == c;

        }

        /*
            수식에서 최소 진수 찾기
        */
        private int findMinBase(ArrayList<Code> codeList){

            int minBase = 2;

            for(Code code : codeList){

                String A = code.A;
                String B = code.B;
                String C = code.C;

                ArrayList<String> numbers = new ArrayList<>();

                numbers.add(A);
                numbers.add(B);

                if(!C.equals("X")){

                    numbers.add(C);

                }

                int maxDigit = findMaxDigit(numbers);

                // maxDigit가 8 이라면 최소 9진법 (8+1)
                minBase = Math.max(minBase, maxDigit+1);

            }

            return minBase;

        }

        /*
            주어진 수식에서 Max Digit 반환
        */
        private int findMaxDigit(ArrayList<String> numbers){

            int maxDigit = 0;

            for(String number : numbers){

                char[] seperatedNumber = number.toCharArray();

                for(char num : seperatedNumber){

                    int n = Character.getNumericValue(num);

                    maxDigit = Math.max(maxDigit, n);

                }

            }

            return maxDigit;

        }

        /*
            주어진 수식을 토큰으로 잘라 list로 반환한다.
        */
        private ParsedInput init(String[] expressions){

            ArrayList<Code> codeList = new ArrayList<>();
            int xCount = 0;

            for(String expression : expressions){

                StringTokenizer st = new StringTokenizer(expression);

                String A = st.nextToken();
                String op = st.nextToken();
                String B = st.nextToken();
                st.nextToken();
                String C = st.nextToken();

                if(C.equals("X")) xCount++;

                codeList.add(new Code(A,op,B,C));

            }

            ParsedInput parsed = new ParsedInput(codeList, xCount);

            return parsed;
        }
    }//solution

}
