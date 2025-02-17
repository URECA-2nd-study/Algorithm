package yeoeun.week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week2_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Color[] costs = new Color[N];

        // 값 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            costs[i] = new Color(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < N; i++) {
            if(i == 0) {
                costs[i].Rmin = costs[i].Rcost;
                costs[i].Gmin = costs[i].Gcost;
                costs[i].Bmin = costs[i].Bcost;
            } else {
                costs[i].Rmin = Math.min(costs[i-1].Gmin, costs[i-1].Bmin) + costs[i].Rcost;
                costs[i].Gmin = Math.min(costs[i-1].Rmin, costs[i-1].Bmin) + costs[i].Gcost;
                costs[i].Bmin = Math.min(costs[i-1].Rmin, costs[i-1].Gmin) + costs[i].Bcost;
            }
        }
        System.out.println(Math.min(costs[N-1].Rmin, Math.min(costs[N-1].Gmin, costs[N-1].Bmin)));
    }

    public static class Color {
        int Rcost;
        int Gcost;
        int Bcost;

        // 자신이 각각 R/G/B일 때의 최소 비용
        int Rmin;
        int Gmin;
        int Bmin;

        public Color(int Rcost, int Gcost, int Bcost) {
            this.Rcost = Rcost;
            this.Gcost = Gcost;
            this.Bcost = Bcost;
        }
    }
}
