package src.jaehyeon.week16;

import java.io.*;
import java.util.*;

public class Week16_2987_1 {
    
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

    public static double answerTriangleArea;
    public static int answerAppleTreeCount;

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

            if(isInArea(atp)){
                
                answerAppleTreeCount++;
            }
        }
    }
    
    private static boolean isInArea(Pos pos) {
        
        Pos A = triangleVertexPos.get(0);
        Pos B = triangleVertexPos.get(1);
        Pos C = triangleVertexPos.get(2);
        
        int ccw1 = ccw(A, B, pos);
        int ccw2 = ccw(B, C, pos);
        int ccw3 = ccw(C, A, pos);
        
        // 삼각형 내부 또는 경계에 존재하는 점일 경우 각 변에 대한 CCW 동작 시, 동일한 방향 또는 직선이다
        return (ccw1 >= 0 && ccw2 >= 0 && ccw3 >= 0) || 
            (ccw1 <= 0 && ccw2 <= 0 && ccw3 <= 0);
    }

    private static int ccw(Pos a, Pos b, Pos c){

        long result = (long)(a.x) * (b.y - c.y) 
                    + (long)(b.x) * (c.y - a.y) 
                    + (long)(c.x) * (a.y - b.y);
        
        if (result == 0) return 0;// 일직선
        else if (result > 0) return 1;// 반시계 방향
        else return -1;// 시계 방향
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
            xC * (yA - yB)) 
            / 2.0;

        answerTriangleArea = Double.parseDouble(String.format("%.1f", calcResult));
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
