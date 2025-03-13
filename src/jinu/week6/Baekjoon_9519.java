package jinu.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_9519 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        StringBuilder word = new StringBuilder(st.nextToken());
        int p = n % getSamePeriod(word,word.toString());

        for(int i=0;i<p;i++){
            rollWord(word);
        }

        System.out.println(word);

    }


    // (a,b,c,d,e) -> 가 다시 (a,b,c,d,e) 가 되는 주기를 구함.
    public static int getSamePeriod(final StringBuilder word,String original){

        int p = 1;

        while(true){
            rollWord(word);
            if(word.toString().equals(original)){
                return p;
            }
            p++;
        }

    }

    // word 를 규칙에 맞게 돌리는 메소드.
    private static void rollWord(StringBuilder word) {

        int k = word.length()%2==0? word.length()-1:word.length()-2;

        List<Character> characterList = new ArrayList<>();
        for(int j = k; j>=1; j-=2){
            characterList.add(word.charAt(j));
            word.deleteCharAt(j);
        }

        for(int j=0;j<characterList.size();j++){
            word.append(characterList.get(j));
        }
    }
}
