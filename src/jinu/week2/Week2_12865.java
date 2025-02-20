    package jinu.week2;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import javax.sound.midi.SysexMessage;

    public class Week2_12865 {

        public static void main(String[] args) throws Exception {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int w[]= new int[n+1];
            int v[] = new int[n+1];
            for(int i=1;i<=n;i++){
                st = new StringTokenizer(br.readLine());
                w[i] = Integer.parseInt(st.nextToken());
                v[i] = Integer.parseInt(st.nextToken());
            }



            int dp[][]=new int[n+1][k+1];

            //ArrayIndexOutOfBound 처리 위한 if 문
            if(w[1]<=k){
                dp[1][w[1]] = v[1];
                for(int j=1;j<=k;j++){
                    if(dp[1][j]<dp[1][j-1]){
                        dp[1][j]=dp[1][j-1];
                    }
                }
            }


            for(int i=2;i<=n;i++){
                for(int j=1;j<=k;j++){
                    if(j>=w[i]){
                        dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w[i]]+v[i]);
                    }
                    else{
                        dp[i][j]=dp[i-1][j];
                    }

                }
            }

            System.out.println(dp[n][k]);





        }


    }
