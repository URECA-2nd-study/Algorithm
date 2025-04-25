package jinu.week12;

import java.util.*;
class Week12_수식복원하기 {
    class Solution {
        public String[] solution(String[] expressions) {
            int answerCount = getCountOfMisteryNumber(expressions);
            String[] answer = new String[answerCount];
            // 가능한 진법의 후보 반환
            List<Integer> huboJinBub = getHuboJinBub(expressions);

            //
            makeAnswer(answer,huboJinBub,expressions);
            return answer;
        }

        // 정답 배열의 크기 구하기 : 만일 X가 포함되어 있으면 정답 배열 크기 1 증가
        public int getCountOfMisteryNumber(String[] expressions){
            int count = 0;
            for(int i=0;i<expressions.length;i++){
                if(expressions[i].contains("X")){
                    count++;
                }
            }
            return count;
        }

        // 표현식으로 부터 가능한 진법의 후보를 List 형태로 가지고 온다.
        public List<Integer> getHuboJinBub(String []expressions){

            //각각의 후보 진법 중 안되는 것은 true로 설정
            boolean cantJinBub[] = new boolean[10];

            for(int i=0;i<expressions.length;i++){

                // 숫자 분리
                List<String> numberList = seperateNumberFromExpression(expressions[i]);
                String firstNumber = numberList.get(0);
                String secondNumber = numberList.get(1);
                String result = numberList.get(2);
                String op = numberList.get(3);

                // 구성된 숫자를 바탕으로 진법이 될 수 없는 것을 축출
                checkCantJinBub(cantJinBub,firstNumber);
                checkCantJinBub(cantJinBub,secondNumber);
                checkCantJinBub(cantJinBub,result);

                if(result.equals("X")){
                    continue;
                }

                // 결괏값이 미지수가 아닐 때
                for(int jinBub=2;jinBub<=9;jinBub++){
                    if(cantJinBub[jinBub]){
                        continue;
                    }

                    // 계산 결과가 맞는지 확인 이후 맞지 않다면 그 진법을 축출한다.
                    if(op.equals("+")){
                        if(switchToDigix(firstNumber,jinBub) + switchToDigix(secondNumber,jinBub)
                                != switchToDigix(result,jinBub))

                            cantJinBub[jinBub] = true;
                    }
                    else if(op.equals("-")){
                        if(switchToDigix(firstNumber,jinBub) - switchToDigix(secondNumber,jinBub)
                                != switchToDigix(result,jinBub))
                            cantJinBub[jinBub] = true;
                    }

                }
            }

            // 가능한 진법의 후보를 반환한다.
            List<Integer> result = new ArrayList<>();

            for(int i=2;i<=9;i++){
                if(!cantJinBub[i]){
                    result.add(i);
                }
            }
            return result;
        }

        // 숫자를 이루고 있는 숫자는 최소한 그 숫자의 이하 진법이 아니라는 것을 알려준다.
        public void checkCantJinBub(boolean []cantJinBub,String number){

            if(number.equals("X")){
                return;
            }

            for(int i=0;i<number.length();i++){
                for(int j=2;j<=number.charAt(i)-'0';j++){
                    cantJinBub[j] = true;
                }
            }
        }

        // X진법의 형태를 10진법으로 전환한다.
        public int switchToDigix(String number,int jinBub){
            String reversed = new StringBuilder(number).reverse().toString();
            int numberDigix = 0;
            for(int i=0;i<reversed.length();i++){
                numberDigix+=(reversed.charAt(i)-'0')*Math.pow(jinBub,i);
            }
            return numberDigix;
        }


        // 정답 배열에 값을 대입한다.

        public void makeAnswer(String []answer,List<Integer> huboJinBub,String []expressions){
            int answerNumber = 0;
            //  System.out.println(huboJinBub);
            for(int i=0;i<expressions.length;i++){

                String expression = expressions[i];
                // 미지수인 것만 답을 채우려고 한다. 따라서 미지수가 포함되어 있지 않다면 넘김.
                if(!expressions[i].contains("X")){
                    continue;
                }

                // 숫자 분리.
                List<String> tmp = seperateNumberFromExpression(expressions[i]);
                String firstNumber = tmp.get(0);
                String secondNumber = tmp.get(1);
                String result = tmp.get(2);
                String op = tmp.get(3);

                // resultNum: 실제 값을 계산하여 10진수의 형태로 반환한다.
                // 이떄 진법 중에 첫 번째 진법을 사용하여 계산한다.
                int resultNum = 0;
                if(op.equals("+")){
                    resultNum = switchToDigix(firstNumber,huboJinBub.get(0)) + switchToDigix(secondNumber,huboJinBub.get(0));
                }
                else{
                    resultNum = switchToDigix(firstNumber,huboJinBub.get(0)) - switchToDigix(secondNumber,huboJinBub.get(0));
                }

                // 10진법의 형태를 다시 변환한다.
                String tmpResult = Integer.toString(resultNum,huboJinBub.get(0));

                // X(미지수)를 대체할 기본 값은 tmpResult
                String replaceString = tmpResult;

                // 나머지 진법의 결과가 위 결과와 똑같은지 확인한다.
                for(int j=1;j<huboJinBub.size();j++){
                    int tmpResultNum = 0;
                    if(op.equals("+")){
                        tmpResultNum = switchToDigix(firstNumber,huboJinBub.get(j)) + switchToDigix(secondNumber,huboJinBub.get(j));
                    }
                    else{
                        tmpResultNum = switchToDigix(firstNumber,huboJinBub.get(j)) - switchToDigix(secondNumber,huboJinBub.get(j));
                    }
                    // 다르다면 X 대신 ? 대입
                    if(!tmpResult.equals(Integer.toString(tmpResultNum,huboJinBub.get(j)))){
                        replaceString = "?";
                    }
                }

                // 첫 번쨰 숫자와 두 번째 숫자가 같고 빼기라면 0 대입
                if(firstNumber.equals(secondNumber) && op.equals("-")){
                    replaceString = "0";
                }

                // 정답 배열에 값을 대입한다.
                answer[answerNumber++] = expressions[i].replace("X",replaceString);

            }
        }


        // 표현식으로 부터 숫자를 분리하여 반환한다.
        // 첫 번째 값에는 처음 숫자
        // 두 번째 값에는 두 번째 숫자
        // 세 번째 값에는 = 이후의 숫자 또는 X
        // 네 번째 값에는 op (+ or -) 반환
        public List<String> seperateNumberFromExpression(String expression){
            String firstNumber = null;
            String secondNumber = null;
            String result = null;
            String op = null;
            if(expression.contains("+")){
                String split[] = expression.split("\\+");
                firstNumber = split[0].trim();
                secondNumber = split[1].split("\\=")[0].trim();
                result = split[1].split("\\=")[1].trim();
                op = "+";
            }
            else{
                String split[] = expression.split("\\-");
                firstNumber = split[0].trim();
                secondNumber = split[1].split("\\=")[0].trim();
                result = split[1].split("\\=")[1].trim();
                op = "-";
            }
            List<String> tmp = new ArrayList<>();
            tmp.add(firstNumber);
            tmp.add(secondNumber);
            tmp.add(result);
            tmp.add(op);
            return tmp;
        }
    }
}
