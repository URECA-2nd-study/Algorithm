package jinu.week9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Week9_10728 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            for(int i=1;i<=n;i++){
                BT(new ArrayList<>(),i,1,n);
            }

            bw.write(MAX+"\n");
            for(int i=0;i<answerList.size();i++){
                bw.write(answerList.get(i)+" ");
            }
            bw.write("\n");

            MAX = Integer.MIN_VALUE;
            answerList = new ArrayList<>();


        }


        bw.flush();
    }

    // BT: N개의 숫자 중 size만큼의 숫자를 고르기.
    public static void BT(List<Integer> number,int size,int start,int n){

        // 일정 size만큼 숫자를 골랐다면...
        if(number.size()==size){
            //임의의 3개의 숫자를 고르는 백트래킹 시작
            BT2(new ArrayList<>(),number,0);
            // 임의의 3개의 숫자끼리 XOR 연산 시 모두 0 이 아니라면
            if(isOk){
                // 최대 수열 길이인지 비교
                if(MAX<number.size()){
                    MAX = number.size();
                    answerList.clear();
                    for(int j=0;j<number.size();j++){
                        answerList.add(number.get(j));
                    }

                }

            }
            isOk= true;

            return;
        }

        for(int i=start;i<=n;i++){
            number.add(i);
            BT(number,size,i+1,n);
            number.remove(number.size()-1);
        }


    }

    static boolean isOk = true;
    static int MAX = Integer.MIN_VALUE;
    static List<Integer> answerList = new ArrayList<>();

    // 임의의 숫자 3개를 고르는 백트래킹
    // 3개의 숫자를 고르고 이 3 숫자의 XOR 연산이 0이라면 isOk = false 로 설정
    // isOk: 3개의 XOR 연산의 결과가 0이 아닌지
    public static void BT2(List<Integer> tmp,List<Integer> number,int start){
        if(tmp.size()==3){
            int p = tmp.get(0) ^ tmp.get(1);
            int pp = p ^ tmp.get(2);
            if(pp==0){
                isOk = false;
            }
            return;
        }

        for(int i=start;i<number.size();i++){
            tmp.add(number.get(i));
            BT2(tmp,number,i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}
