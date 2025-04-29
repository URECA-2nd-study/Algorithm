package jinu.week12;

import java.util.*;
class Week12_표현가능한이진트리 {
    class Solution {
        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];
            for(int i=0;i<numbers.length;i++){
                long number = numbers[i];
                String fullBinaryNumber = changeToFull(changeToBinary(number));
                List<Integer> position = new ArrayList<>();

                // 1번 3번 5번... 의 포지션을 리스트에 대입한다. 1번 3번 5번은 리프 노드의 번호라고 생각한다.
                for(int j=1;j<=fullBinaryNumber.length();j+=2){
                    position.add(j);
                }

                answer[i] = decideDivide(fullBinaryNumber,position);
            }
            return answer;
        }


        //숫자를 이진수로 변환
        public String changeToBinary(Long number){
            return Long.toBinaryString(number);
        }
        // 포화 개수만큼 0을 앞에 붙힌다.
        public String changeToFull(String binaryNumber){
            int zeroCount = needZeroCount(binaryNumber.length());
            StringBuilder sb = new StringBuilder(binaryNumber);
            for(int i=0;i<zeroCount;i++){
                sb.insert(0,"0");
            }
            return sb.toString();
        }
        // 포화 개수만큼 0 개수 구하기
        public int needZeroCount(int stringLength){
            int k = 0;
            while(true){
                if(Math.pow(2,k)-1<= stringLength &&
                        stringLength <= Math.pow(2,k+1)-1){
                    return (int)Math.pow(2,k+1)-1 - stringLength;
                }
                k++;
            }

        }

        // 알고리즘을 적용하여 표현 가능한지 판단()
        public int decideDivide(String binaryNumber,List<Integer> position){

            // position 의 크기가 1일 때 즉 루트 노드일 때는 하나의 이진트리로 해당 수를 표현
            // 할 수 있다는 의미의 1 반환
            if(position.size()==1){
                return 1;
            }

            List<Integer> newPosition = new ArrayList<>();

            for(int i=0;i<position.size();i+=2){
                // 자식 노드의 합을 2로 나눈 값은 부모 노드의 번호
                int midPosition = (position.get(i)+position.get(i+1))/2;

                // 자식 노드가 1을 가지고 있는데
                if(binaryNumber.charAt(position.get(i)-1) == '1' ||
                        binaryNumber.charAt(position.get(i+1)-1) == '1'){
                    // 부모 노드가 0을 가지고 있다면 이진트리로 해당 수를 표현할 수 없다.
                    if(binaryNumber.charAt(midPosition-1)=='0'){
                        return 0;
                    }
                }
                newPosition.add(midPosition);
            }
            return decideDivide(binaryNumber,newPosition);
        }

    }

}
