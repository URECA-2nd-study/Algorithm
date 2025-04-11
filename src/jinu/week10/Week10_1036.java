package jinu.week10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Week10_1036 {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<Integer, BigInteger> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] word = new String[n];



        for (int i = 0; i < n; i++) {
            word[i] = br.readLine();

            for (int j = 0; j < word[i].length(); j++) {

                BigInteger bInt = new BigInteger("0");
                // 최댓값을 구하기 위한 개수 구하기
                int power = word[i].length() - j;
                int key = changeToNumber(word[i].charAt(j));

                BigInteger value = map.getOrDefault(key, bInt);
                bInt = BigInteger.valueOf(35);

                //35 의 power 승  * (35 - key) + value (기존값, 키가 없으면 0 )
                bInt = bInt.pow(power).multiply(BigInteger.valueOf(35 - key)).add(value);


                map.put(key, bInt);
            }
        }

        // 변화 값이 가장 큰 수 대로 숫자를 오름차 정렬
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> map.get(o2).compareTo(map.get(o1)));


        int k = Integer.parseInt(br.readLine());

        // 배열 0 ~ 9 , A ~ Z 까지 0부터 35
        // arr는 어떤 값이 Z로 바뀌는지 판단하는 배열
        int[] arr = new int[36];
        if (k != 0) {
            for (int i = 0; i < k; i++) {
                if (list.size() <= i) break;
                arr[list.get(i)] = 35;
            }
        }

        //여유 있게 100 잡음
        // nums는 자리 수에 따른 int 더하기 결괏값
        int[] nums = new int[100];
        calculateNum(word, arr, nums);

        // numbs를 36진수의 숫자 바꾸기 위한 숫자로 바꾼다. 또한 그 시작 index를 같이 가져온다.
        int idx = change36(nums);

        // 36진수로 바꿔서 출력
        for (int i = idx - 1; i >= 0; i--) {
            sb.append(changeToCharacter(nums[i]));
        }

        bw.write(sb.toString().isEmpty() ? "0" : sb.toString());
        bw.flush();
        bw.close();
    }

    private static void calculateNum(String[] word, int[] arr, int[] nums) {
        // 각 자리수의 값 계산
        for (int i = 0; i < word.length; i++) {

            for (int j = 0; j < word[i].length(); j++) {

                // 끝부터
                char c = word[i].charAt(word[i].length() - j - 1);

                // arr 값이 35 즉 이번에 새로 바뀐 Z값이라면 그 위치에다가 35 더해준다.
                if (arr[changeToNumber(c)] == 35) nums[j] += 35;

                // 그렇지 않으면 그 위치에다가 number를 더함.
                else nums[j] += changeToNumber(c);

            }
        }
    }

    // num 을 36진수로 바꾸기 위해서 후처리 작업을 해준다.
    private static int change36(int[] nums) {
        int idx = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > 0) {
                if (nums[i] / 36 > 0) {
                    nums[i+1] += nums[i] / 36;
                    nums[i] %= 36;
                }
                idx = Math.max(idx, i+1); // 끝에 있는 수의 자릿수를 세기
            }
        }
        return idx;
    }

    private static int changeToNumber(char c) {
        if(c>='A' && c<='Z'){
            return c-'A'+10;
        }
        return c-'0';

    }

    private static char changeToCharacter(int number){
        if(number>=10){
            return (char)(number-10 +'A');
        }

        return (char)('0'+number);
    }
}
