package yeoeun.week02;

import java.io.*;

public class Week2_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Glass[] glasses = new Glass[n];
        for (int i = 0; i < n; i++) {
            glasses[i] = new Glass(Integer.parseInt(br.readLine()));
        }

        for (int i = 0; i < n; i++) {
            if(i == 0) {
                glasses[i].oox = glasses[i].oxx = 0; // 본인 안마심
                glasses[i].oxo = glasses[i].xoo = glasses[i].xxo = glasses[i].point; // 본인 마심
            }
            else if(i == 1) {
                glasses[i].oox = glasses[0].point; // 0만 마심
                glasses[i].oxo = glasses[i].xxo = glasses[1].point; // 1만 마심
                glasses[i].xoo = glasses[0].point + glasses[1].point; // 둘 다 마심
                glasses[i].oxx = 0;
            }
            else if(i == 2) {
                glasses[i].oox = glasses[0].point + glasses[1].point;
                glasses[i].oxo = glasses[0].point + glasses[2].point;
                glasses[i].xoo = glasses[1].point + glasses[2].point;
                glasses[i].xxo = glasses[2].point;
                glasses[i].oxx = glasses[0].point;
            }
            else {
                glasses[i].oox = glasses[i-1].xoo;
                glasses[i].oxo = Math.max(glasses[i-2].xxo, Math.max(glasses[i-2].oxo, glasses[i-2].xoo)) + glasses[i].point;
                glasses[i].xoo = Math.max(glasses[i-1].oxo, glasses[i-1].xxo) + glasses[i].point;
                glasses[i].xxo = Math.max(glasses[i-2].oxx, glasses[i-2].oox) + glasses[i].point;
                glasses[i].oxx = Math.max(glasses[i-2].oxo, Math.max(glasses[i-2].xxo, glasses[i-2].xoo));
            }
        }
        // 마지막 잔의 케이스들 중 최댓값을 반환
        System.out.println(glasses[n-1].getMax());
    }

    public static class Glass {
        // 포도주 양
        int point;
        // 전의 2개를 마심
        int oox = 0;
        // 전전거 마시고, 전거 안마심
        int oxo = 0;
        // 전전거 안마시고, 전거 마심
        int xoo = 0;
        // 연속 2잔 안마시는 경우도 고려
        int oxx = 0;
        int xxo = 0;

        public Glass(int point) {
            this.point = point;
        }

        // 모든 경우 중 최대 값을 구하기
        public int getMax() {
            int max = Math.max(this.oox, this.oxo);
            max = Math.max(max, this.xoo);
            max = Math.max(max, this.oxx);
            max = Math.max(max, this.xxo);
            return max;
        }
    }
}
