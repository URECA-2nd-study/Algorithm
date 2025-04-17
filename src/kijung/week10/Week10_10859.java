package BaekJoon_10859;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Week10_10859 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();

        boolean isNumber = true;
        for (int i = input.length() - 1; i >= 0; i--) {
            int num = input.charAt(i) - '0';
            int add;
            if (num == 0 || num == 2 || num == 5 || num == 8 || num == 1) add = num;
            else if (num == 3 || num == 4 || num == 7) {
                isNumber = false;
                break;
            } else if (num == 6) add = 9;
            else add = 6;

            sb.append(add);
        }

        if (!isNumber) System.out.println("no");
        else {
            if (isPrime(Long.parseLong(input)) && isPrime(Long.parseLong(sb.toString())) && sb.charAt(0) != '0' ) System.out.println("yes");
            else System.out.println("no");
        }

    }

    static boolean isPrime(Long n) {
        if (n == 1) return false;
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

}
