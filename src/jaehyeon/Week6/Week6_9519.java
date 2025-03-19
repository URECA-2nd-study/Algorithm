package src.jaehyeon.Week6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Week6_9519 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long X = Long.parseLong(br.readLine());

        char[] originalString = br.readLine().toCharArray();
        int len = originalString.length;
        char[] currentString = originalString;
        if (len < 3) {//문자열 길이가 2 이하면 바뀌지 않음
            bw.write(new String(originalString));
        } else {
            long count = 0;
            for (long blinking = 0; blinking < X; blinking++) {

                int fromStart = 1;
                int toStart = 1;
                int fromEnd = 2;
                int toEnd = 1;
                char[] previousString = new char[len];
                previousString[0] = currentString[0];
                while (true) {

                    previousString[len - toStart] = currentString[fromStart];
                    previousString[toEnd] = currentString[fromEnd];
//                    System.out.println("previousString 2개 이동 : " + Arrays.toString(previousString));
                    fromStart += 2;
                    fromEnd += 2;
                    toStart++;
                    toEnd++;

                    if (fromEnd >= len) {
                        if (fromStart >= len) {
                            break;
                        } else {
                            previousString[toStart] = currentString[fromStart];
//                            System.out.println("previousString 마지막 하나 이동 : " + Arrays.toString(previousString));
                            break;
                        }
                    }
                }
                count++;
//                System.out.println("@@previousString 깜빡임 완료 : " + Arrays.toString(previousString));
//                System.out.println("$$currentString에 덮어씌우기 : " + Arrays.toString(currentString));
                currentString = previousString;

                if (Arrays.equals(currentString, originalString)) {
//                    System.out.println(count+"번 만에 같아짐");
                    //만약 count 번 만에 오리지널 문자열로 돌아왔을 경우
                    blinking = X - 1 - X % count;
                }
            }
            bw.write(new String(currentString));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}