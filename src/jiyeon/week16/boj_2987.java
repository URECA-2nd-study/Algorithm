package week16;

import java.io.*;
import java.util.*;

public class boj_2987 {
	
	static int[][] dots = new int[3][2]; //삼각형 좌표
	static int count = 0; //백준이의 사과나무 개수
	static double appleArea = 0; //백준이 땅 넓이

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			dots[i][0] = Integer.parseInt(st.nextToken());
			dots[i][1] = Integer.parseInt(st.nextToken());
		}
		
		appleArea = getArea(dots[0][0],dots[0][1],dots[1][0],dots[1][1],dots[2][0],dots[2][1]);
		
		
		int appleNum = Integer.parseInt(br.readLine());
		
		for(int i=0; i<appleNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(isInner(dots,x,y)) {
				count ++;
			}
		}
		
		System.out.println(Math.round(appleArea*10)/10.0);
		System.out.print(count);


	}//main 
	
	//삼각형 넓이 구하는 메서드 
	public static double getArea(int x1, int y1, int x2, int y2, int x3, int y3) {
		return Math.abs(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2))/2.0;
	}
	
	//삼각형 내부에 있는 점인지 파악하는 메서드 
	public static boolean isInner(int [][] dots, int x, int y) {
		double area = getArea(dots[0][0],dots[0][1],dots[1][0],dots[1][1],x,y) +
				getArea(dots[1][0],dots[1][1],dots[2][0],dots[2][1],x,y) +
				getArea(dots[0][0],dots[0][1],dots[2][0],dots[2][1],x,y);
		
		if(area == appleArea) {
			return true;
		}
		
		return false;
	}

}

/*
 * 한 점에 대하여 만약 삼각형의 두 점과 주어진 점을 이어서 만든 삼각형 3개의 넓이의 합이 원래 삼각형과 같다면
 *  그 점은 삼각형 내부에 있는 점이다. 
 * */
