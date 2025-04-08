package heejin.week10;

import java.io.*;
import java.util.*;

public class Week10_10859 {
    static long N, reverseN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Long.parseLong(br.readLine());

        if (isPrime(N)) {

            // N 뒤집기
            long num;
            while (N > 0) {
                num = N;
                num %= 10;
                N /= 10;

                // 3, 4, 7이 있으면 더 이상 숫자가 아님
                if (num == 3 || num == 4 || num == 7) {
                    System.out.print("no");
                    return;
                }

                // 6,9는 거꾸로, 다른 건 그대로
                if(num == 6){
                    sb.append(9);
                } else if(num == 9){
                    sb.append(6);
                } else{
                    sb.append(num);
                }
            }

            reverseN = Long.parseLong(sb.toString());
            if(isPrime(reverseN)){
                System.out.print("yes");
            } else{
                System.out.print("no");
            }

        } else {
            System.out.print("no");
        }
    }


    static boolean isPrime(long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        // 홀수만 검사
        for (long i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
