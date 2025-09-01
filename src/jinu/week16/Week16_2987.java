package jinu.week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week16_2987 {

    static class Pos {
        int x, y;

        public Pos(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static double getCCW(Pos a, Pos b, Pos c) {
        return a.x * b.y + b.x * c.y + c.x * a.y
                - (b.x * a.y + c.x * b.y + a.x * c.y);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Pos A = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Pos B = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        Pos C = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());


        int ans = 0;
        double area = getCCW(A, B, C);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Pos tmp = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            double tmpCCW;

            // A->B->C C의 방향은 AB 직선의 시계방향
            if (area < 0) {
                tmpCCW = getCCW(A, tmp, B);
                if (Math.abs(tmpCCW) > -area) continue;
                // A-> tmp ->B B의 방향은 Atmp 직선의 시계방향
                // 삼각형의 외부에 있다는 의미
                if (tmpCCW < 0) continue;

                tmpCCW = getCCW(B, tmp, C);
                if (Math.abs(tmpCCW) > -area) continue;
                if (tmpCCW < 0) continue;

                tmpCCW = getCCW(C, tmp, A);
                if (Math.abs(tmpCCW) > -area) continue;
                if (tmpCCW < 0) continue;

                ans++;
            } else {
                tmpCCW = getCCW(A, tmp, B);
                if (Math.abs(tmpCCW) > area) continue;
                if (tmpCCW > 0) continue;

                tmpCCW = getCCW(B, tmp, C);
                if (Math.abs(tmpCCW) > area) continue;
                if (tmpCCW > 0) continue;

                tmpCCW = getCCW(C, tmp, A);
                if (Math.abs(tmpCCW) > area) continue;
                if (tmpCCW > 0) continue;

                ans++;
            }
        }

        System.out.printf("%.1f%n%d", Math.abs(area) / 2.0, ans);
    }

}
