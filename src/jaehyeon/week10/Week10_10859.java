package src.jaehyeon.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Week10_10859 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        System.out.println(sol(input));
    }

    private static String sol(String input) {
        long number = Long.parseLong(input);

        if (number != 1 && isPrime(number)) {
            char[] reversedArr = isReversible(input.toCharArray());

            if (reversedArr.length != 0) {
                long reverseNumber = Long.parseLong(new String(reversedArr));

                if (isPrime(reverseNumber)) return "yes";
            }
        }
        return "no";
    }

    private static char[] isReversible(char[] arr) {
        int end = arr.length - 1;
        char[] reversedArr = new char[arr.length];

        for (char num : arr) {
            if (num == '3' || num == '4' || num == '7') {
                return new char[0];
            }

            switch (num) {
                case '1', '2', '5', '8', '0':
                    reversedArr[end] = num;
                    break;
                case '6':
                    reversedArr[end] = '9';
                    break;
                case '9':
                    reversedArr[end] = '6';
            }
            end--;
        }

        return reversedArr;
    }

    private static boolean isPrime(long number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
