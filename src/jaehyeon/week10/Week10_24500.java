package src.jaehyeon.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Week10_24500 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long N = Long.parseLong(br.readLine());

        long n = Long.highestOneBit(N);

        if ((n ^ (n - 1L)) == N) {
            System.out.println(1);
            System.out.println(N);
        } else {
            System.out.println(2);

            long bitLen = Long.toBinaryString(N).length();
//            System.out.println("bitLen = " + bitLen);
            long mask = (1L << bitLen) - 1;
//            System.out.println("mask = " + mask);

            System.out.println(~N & mask);
            System.out.println(N);
        }
    }
}
