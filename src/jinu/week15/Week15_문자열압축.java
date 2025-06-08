package jinu.week15;
import java.util.*;
public class Week15_문자열압축 {

    class Solution {
        public int solution(String s) {

            int min = Integer.MAX_VALUE;

            //단위를 올려가며 문자열 중 가장 짧은 길이 파악.
            // 최장 길이는 길이의 반을 넘을 수 없다.
            for(int unit = 1;unit <= s.length()/2+1; unit++){
                min = Math.min(min,getCompressedStringLength(s,unit));
            }

            return min;

        }

        public int getCompressedStringLength(String s,int unit){
            int len = 0;
            //      System.out.println("unit: "+unit);

            for(int i=0;i<s.length();i+=unit){

                // 현재 위치 + unit이 길이보다 크다면
                if(i+unit>s.length()){
                    //len 에 마지막 글자의 개수만큼 더해준다.
                    len+=s.length()-i;
                    continue;
                }
                //unit 별로 자른다.
                String target = s.substring(i,i+unit);
                int index = i+unit;
                int repeatedCount = 0;

                // 잘른 이후 반복되는 개수가 몇 개인지 파악.
                while(index+unit<=s.length() && target.equals(s.substring(index,index+unit))){
                    repeatedCount++;
                    index+=unit;
                }

                // 반복되는 개수가 0이면
                if(repeatedCount==0){
                    // 그냥 단위만 더해준다.
                    len+=unit;
                    continue;
                }
                // 반복되는 개수가 여러 개면
                i = index-unit;
                len+= unit+(String.valueOf(repeatedCount+1).length());

            }


            return len;
        }
    }
// 실수한 부분 : 반복되는 숫자가 당연히 일의 자리 숫자인줄 알고 1을 더해주다가 틀림
// 아 10이상이 될 수 있구나? => 그래서 나누기 10 연산으로 아무 생각없이 함.
// 근데 생각해보니 100이면 3개가 아니라 10개가 되서 망한다. ㅋ.ㅋ
// 따라서 문자의 길이를 가져오기 위해 String으로 변환후 length로 반환
// 문자열의 길이가 1인 경우도 고려안했다.
// 결국 예외 케이스에 대한 생각을 안한게 문제이다.



}
