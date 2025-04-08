package jinu.week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week10_10859 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();

        // 3, 4, 7을 포함하는지 여부를 파악하기 위한 boolean 값
        boolean isOkNumber = true;

        // 숫자 뒤집기
        StringBuilder reversedN = new StringBuilder();
        for(int i=N.length()-1;i>=0;i--){
            if(N.charAt(i)=='6'){
                reversedN.append("9");
            }
            else if(N.charAt(i)=='9'){
                reversedN.append("6");
            }
            else if(N.charAt(i)=='3' || N.charAt(i)=='4' || N.charAt(i)=='7'){
                isOkNumber = false;
                break;
            }
            else{
                reversedN.append(N.charAt(i));
            }
        }

        //3,4,7을 포함한다면
        if(!isOkNumber){
            System.out.println("no");
        }else{
            long reverseLongN = Long.valueOf(reversedN.toString());
            if(isPrime(reverseLongN) && isPrime(Long.valueOf(N))){
                System.out.println("yes");
            }
            else{
                System.out.println("no");
            }
        }



    }


    // 소수 판별 알고리즘
    public static boolean isPrime(long number){

        if(number == 1){
            return false;
        }

        for(int i=2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return false;
            }
        }

        return true;
    }
}
