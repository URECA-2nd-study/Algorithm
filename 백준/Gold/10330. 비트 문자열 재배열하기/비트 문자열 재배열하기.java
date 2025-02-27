import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {


    // 맨 앞부터 차례대로 바꾸기
    static int N, M;
    static int[] repeated;
    static StringBuilder str1 = new StringBuilder();
    static StringBuilder str2 = new StringBuilder();
    static StringBuilder cur = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cur.append(st.nextToken());
        }

        repeated = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            repeated[i] = Integer.parseInt(st.nextToken());
        }

        String add = "0";
        // 시작이 0인 str1 만들기
        for (int i = 0; i < M; i++) {
            str1.append(add.repeat(repeated[i]));
            add = add.equals("0") ? "1" : "0";
        }

        // 시작이 1인 str2 만들기
        add = "1";
        for (int i = 0; i < M; i++) {
            str2.append(add.repeat(repeated[i]));
            add = add.equals("0") ? "1" : "0";
        }

        int answer = Integer.MAX_VALUE;
        answer = Math.min(answer, cal(str1.toString()));
        answer = Math.min(answer, cal(str2.toString()));

        System.out.println(answer);

    }

    static int cal(String input) {
        StringBuilder temp = new StringBuilder(cur);
        int cnt = 0;

        // 맨 앞에서부터 바꿔주며 횟수 증가시키기
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == input.charAt(i)) continue;

            // 같지 않다면 바꿔주기. 단 바꿀 수 있는 문자가 뒤에 있는지 확인해야 함
            boolean canChange = false;
            int changeIdx = -1; // 발견된 가장 빠른 위치
            for (int j = i + 1; j < temp.length(); j++) {
                if (temp.charAt(j) == input.charAt(i)) {
                    canChange = true;
                    changeIdx = j;
                    break;
                }
            }

            if (canChange) { // 바꿔줄 수 있다면...
                // 발견한 위치부터 앞으로 땡겨오기
                char insertChar = temp.charAt(changeIdx);
                temp.deleteCharAt(changeIdx);
                cnt += changeIdx - i;
                temp.insert(i, insertChar);
            }


        }

        if (temp.toString().equals(input)) return cnt; // 문자열 바꾸기에 성공한 경우
        else return Integer.MAX_VALUE;

    }

}
