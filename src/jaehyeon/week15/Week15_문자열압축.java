package src.jaehyeon.week15;

public class Week15_문자열압축 {

    /**
     * compressionWeight : 문자열을 어느 길이 만큼 자를건지 지정 compressionCount : 자른 문자열의 반복 횟수 1부터 시작
     */
    class Solution {

        public int answer = Integer.MAX_VALUE;

        public int solution(String s) {

            char[] arr = s.toCharArray();

            if (s.length() < 2) {
                return 1;
            }

            for (int i = 1; i <= s.length() / 2; i++) {

                compress(i, arr);

            }

            return answer;
        }

        public void compress(int compressionWeight, char[] arr) {

            String previous = "";
            int sum = 0;
            int compressionCount = 1;

            for (int i = 0; i < arr.length; i = i + compressionWeight) {

                StringBuilder sb = new StringBuilder();

                int index = i;

                for (int ii = 0; ii < compressionWeight; ii++) {

                    sb.append(arr[index]);
                    index++;
                    if (index >= arr.length) {
                        break;
                    }
                }

                if (previous.isEmpty()) {

                    previous = sb.toString();
                    sum += previous.length();
                    continue;
                }

                if (previous.equals(sb.toString())) {
                    compressionCount++;
                    continue;
                }

                if (compressionCount != 1) {

                    if (compressionCount >= 1000) {
                        sum += 4;
                    } else if (compressionCount >= 100) {
                        sum += 3;
                    } else if (compressionCount >= 10) {
                        sum += 2;
                    } else {
                        sum += 1;
                    }
                    compressionCount = 1;
                }

                previous = sb.toString();
                sum += previous.length();
            }//for

            //aabbaccc 처럼 ccc로 반복하다 끝날 경우
            //sum에 반영이 안되고 탈출하기 때문에 예외처리
            if (compressionCount != 1) {

                if (compressionCount >= 1000) {
                    sum += 4;
                } else if (compressionCount >= 100) {
                    sum += 3;
                } else if (compressionCount >= 10) {
                    sum += 2;
                } else {
                    sum += 1;
                }
            }
            answer = Math.min(sum, answer);
        }
    }
}
