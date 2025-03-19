package yeoeun.week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Week6_9519 {
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        String word = br.readLine();

        list.add(word);

        for (int i = 1; i <= X; i++) {
            // 되돌리기 로직
            if(word.equals(unBlank(list.get(list.size()-1)))) { // 원래 String과 같으면
                // i번 돌았을 때 원래대로 돌아옴 : index값 계산 후 바로 break
                X -= i * (X / i);
                break;
            }
        }
        System.out.println(list.get(X));
    }
    
    public static String unBlank(String word) {
        int len = word.length();
        StringBuilder sb = new StringBuilder();
        char[] newStr = new char[len];
        for (int i = 0; i < len; i++) {
            if(i % 2 == 0) newStr[i/2] = word.charAt(i); // 짝수번째는 앞에서부터 채움
            else newStr[len - i/2 - 1] = word.charAt(i); // 홀수번째는 뒤에서부터 채움
        }

        for (char c : newStr) {
            sb.append(c); // 최종 답안 만들기 (배열 활용)
        }

        list.add(sb.toString());
        return sb.toString();
    }
}
