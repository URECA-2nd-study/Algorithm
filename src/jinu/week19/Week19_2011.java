package jinu.week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week19_2011 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = st.nextToken();

        char []cArr = s.toCharArray();

        long []dp = new long[s.length()];
        dp[0] = 1;

        boolean success = true;

        if(cArr[0]=='0'){
            success = false;
        }

        for(int i=1;i<s.length();i++){
            boolean tmpSuccess = false;
            if(cArr[i-1]-'0'==1 || cArr[i-1]-'0'==2 && cArr[i]-'0'<=6){
                if(i>=2) {
                    dp[i]+=dp[i-2];
                }
                else {
                    dp[i] += 1;
                }
                dp[i]%=1000000;
                tmpSuccess = true;
            }
            if(cArr[i]-'0'!=0){
                dp[i]+=dp[i-1];
                dp[i]%=1000000;
                tmpSuccess = true;
            }
            if(!tmpSuccess){
                success = false;
                break;
            }
        }

        if(success){
            System.out.println((dp[s.length()-1])%1000000);
        }
        else{
            System.out.println(0);
        }
    }

}
