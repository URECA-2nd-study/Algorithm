package yeoeun.week10;

import java.util.Scanner;

public class Week10_10859 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();

        if(N == 1 || !checkPrime(N)) {
            System.out.println("no");
            return; // 프로그램 종료
        }

        long mul = 1;
        long newNum = 0;
        boolean flag = true;
        char[] arr = String.valueOf(N).toCharArray();

        for (char c : arr) {
            int num = c - '0'; // 각 자리수
            if (num == 3 || num == 4 || num == 7) {
                flag = false;
                break;
            } else if (num == 6) {
                num = 9;
            } else if (num == 9) {
                num = 6;
            }
            newNum += num * mul;
            mul *= 10;
        }
        flag = flag ? checkPrime(newNum) : flag;
        System.out.println(flag ? "yes" : "no");
    }

    public static boolean checkPrime(long num) {
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
