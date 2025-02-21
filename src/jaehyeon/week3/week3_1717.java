package src.jaehyeon.week3;

import java.util.*;
import java.io.*;

public class week3_1717 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = i;
        }


        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int unionOrFind = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(unionOrFind == 1){
                if(find(a)==find(b)){
                    bw.write("YES\n");
                }
                else{
                    bw.write("NO\n");
                }
            }
            else{
                union(a,b);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int a, int b) {
        int n1 = find(a);
        int n2 = find(b);

        if (n1 != n2) arr[n1] = n2;
    }

    private static int find(int n) {
        if (arr[n] == n) return n;
        else {
            arr[n] = find(arr[n]);
            return arr[n];
        }
    }
}
