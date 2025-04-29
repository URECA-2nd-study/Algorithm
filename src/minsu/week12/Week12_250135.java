import java.io.*;
import java.util.*;

class Week12_250135 {
    private static double ha = 360.0 / (12 * 3600.0);
    private static double ma = 360.0 / (3600.0);
    private static double sa = 360.0 / 60.0;
    
    public int solution(int h1,int m1,int s1,int h2,int m2,int s2) {
        int ans = 0;
        double start = h1 * 3600.0 + m1 * 60 + s1;
        double end = h2 * 3600.0 + m2 * 60 + s2;
        
        boolean preOverlap = false;
        
        // 시간을 0.001 기준으로 완탐을 진행하였습니다.
        for (double t = start; t<=end; t += 0.001) {
            double ha = ((t % 43200) / 43200) * 360;
            double ma = ((t % 3600) / 3600) * 360;
            double sa = ((t % 60) / 60) * 360;
            
            /**
             * •	초침이 시침과 거의 정확히 겹쳤거나
             * •	초침이 분침과 거의 정확히 겹쳤거나
             * •	둘 중 하나라도 만족하는 경우
             */
            boolean overlap = Math.abs(sa - ha ) < 0.01 || Math.abs(sa - ma) < 0.01;
            
            if(overlap && !preOverlap) {
                ans++;
            }
            preOverlap = overlap;
        }
        return ans;
    }
    
   
}