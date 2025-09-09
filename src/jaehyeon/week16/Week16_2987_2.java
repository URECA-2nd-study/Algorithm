package src.jaehyeon.week16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Week16_2987_2 {

    public static class Pos{

        private int x;
        private int y;
        
        public Pos(int x, int y){

            this.x = x;
            this.y = y;
        }
    }

    public static List<Pos> triangleVertexPos = new ArrayList<>();
    public static List<Pos> appleTreePos = new ArrayList<>();
    public static int appleTreeCount;

    public static int answerAppleTreeCount;

    public static BigDecimal answerTriangleArea;

    public static void main(String[] args) throws IOException{
        
        // 데이터 입력
        init();
        
        // 계산
        calc();

        // 출력
        printAnswer();
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st;

        for(int i=0; i<3; i++){

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            triangleVertexPos.add(new Pos(x, y));
        }

        appleTreeCount = Integer.parseInt(br.readLine());

        for(int i=0; i<appleTreeCount; i++){

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            appleTreePos.add(new Pos(x, y));
        }
    }

    private static void calc(){

        calcTriangleArea();
        
        calcAppleTreeCountInArea();
    }

    private static void calcAppleTreeCountInArea(){

        for(Pos atp : appleTreePos){

            if(isOutOfRange(atp)){

                continue;
            }

            answerAppleTreeCount++;
        }
    }

    private static boolean isOutOfRange(Pos atp){

        int xA = triangleVertexPos.get(0).x;
        int yA = triangleVertexPos.get(0).y;
        int xB = triangleVertexPos.get(1).x;
        int yB = triangleVertexPos.get(1).y;
        int xC = triangleVertexPos.get(2).x;
        int yC = triangleVertexPos.get(2).y;

        int xATP = atp.x;
        int yATP = atp.y;

        double calcResult = 
            Math.abs(xATP * (yB - yC) + xB * (yC - yATP) + xC * (yATP - yB)) +
            Math.abs(xA * (yATP - yC) + xATP * (yC - yA) + xC * (yA - yATP)) +
            Math.abs(xA * (yB - yATP) + xB * (yATP - yA) + xATP * (yA - yB));

        BigDecimal calcDecimal = new BigDecimal(calcResult);

        calcDecimal = calcDecimal.divide(BigDecimal.valueOf(2));

        return answerTriangleArea.compareTo(calcDecimal) != 0;
    }

    private static void calcTriangleArea(){

        int xA = triangleVertexPos.get(0).x;
        int yA = triangleVertexPos.get(0).y;
        int xB = triangleVertexPos.get(1).x;
        int yB = triangleVertexPos.get(1).y;
        int xC = triangleVertexPos.get(2).x;
        int yC = triangleVertexPos.get(2).y;

        
        double calcResult = Math.abs(
            xA * (yB - yC) +
            xB * (yC - yA) +
            xC * (yA - yB));
            
        answerTriangleArea = new BigDecimal(calcResult).setScale(1, RoundingMode.HALF_EVEN);

        answerTriangleArea = answerTriangleArea.divide(BigDecimal.valueOf(2));
    }

    private static void printAnswer() throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        sb
            .append(answerTriangleArea)
            .append("\n")
            .append(answerAppleTreeCount);

        bw.write(sb.toString());
        bw.close();
    }
}
