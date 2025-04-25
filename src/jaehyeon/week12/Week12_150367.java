package src.jaehyeon.week12;

public class Week12_150367 {

    class Solution {
        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];

            for(int i=0; i<numbers.length; i++){
                //입력값을 2진수로 변환
                String bitString = Long.toBinaryString(numbers[i]);

                //포화 이진트리의 노드 갯수로 변환
                bitString = makeFullBinaryTree(bitString);

                //입력값으로 1이 주어졌을 때 예외처리
                if(bitString.length() == 1){
                    answer[i] = 1;
                    continue;
                }

                boolean flag = true;
                while(flag){

                    String newBitString = "";

                    for(int ii=0; ii<bitString.length(); ii = ii+4){
                        //3개의 노드를 분리해서 가능한 이진트리인지 판별
                        String bit = isPossibleBinaryTree(bitString.substring(ii,ii+3));
                        if(bit.equals("-1")){
                            flag = false;
                            break;
                        }
                        //앞에서부터 3+1 개를 자르기 때문에 마지막에 가면 3개만 자르고 +1개를 자를 수가 없기때문에 예외처리
                        if(ii+4 >= bitString.length()){
                            newBitString += bit;
                        }
                        else{
                            newBitString += bit+bitString.charAt(ii+3);
                        }
                    }

                    //반복하다가 마지막 루트 노드와 자식 노드 2개를 판별하면 newBitString에는 1개만 남게 된다.
                    if(newBitString.length() == 1) {
                        answer[i] = 1;
                        break;
                    }
                    bitString = newBitString;
                }

            }


            return answer;
        }


        /**
         * 포화 이진트리로 변경
         */
        public String makeFullBinaryTree(String bitString){
            int len = bitString.length();
            int nodeCount = 1;
            int depth = 2;

            while(len > nodeCount){
                nodeCount += depth;
                depth = depth*2;
            }

            for(int ii=0; ii<nodeCount-len; ii++){
                bitString = "0" + bitString;
            }

            return bitString;
        }

        /**
         * 부모노드 1개와 자식노드 2개 총 3개의 노드가 현재 가능한 조합인지 판별
         */
        public String isPossibleBinaryTree(String bitString){

            char[] bitArray = bitString.toCharArray();

            if(bitArray[1] == '1') return "1";
            else if(bitArray[0] == '1' || bitArray[2] == '1') return "-1";
            return "0";
        }
    }//solution
}

