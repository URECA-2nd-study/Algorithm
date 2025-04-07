package jiyeon.week9;

import java.io.*;
import java.util.*;

public class boj_15661 {
	
	/*
	 * 조합을 구해서 -> 그 조합에 해당되는 사람들의 능력치 합과 아닌 사람의 능력치 합을 비교 -> 최소 차이를 출력
	 * 비트마스킹으로 모든 조합을 구하자 
	 * - 0이면 start 팀 
	 * - 1이면 link 팀 
	 * 
	 * */
	
	static int N, startExp, linkExp, min; 
	static int[][] exp;
	static ArrayList<Integer> start = new ArrayList<>(); //start 팀에 속한 사람의 num
	static ArrayList<Integer> link = new ArrayList<>(); //link 팀에 속한 사람의 num
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		exp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				exp[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력
		
		min = Integer.MAX_VALUE;
		
		// 두 팀으로 나눠야하기 때문에 0000, 1111과 같은 경우는 제외한다.
		for(int i=1; i<(1<<N)-1; i++) {
			
			startExp = 0;
			linkExp = 0;
			start.clear();
			link.clear();
			
			for(int j=0; j<N; j++) {
				if((i & (1<<j)) == 0) {
					start.add(j);
				}
				else {
					link.add(j);
				}
			}
			
			//각 팀의 점수 계산
			startExp = getScore(start);
			linkExp = getScore(link);
			
			int diff = Math.abs(startExp - linkExp);
			min = Math.min(min, diff);
			
		}
		
		System.out.println(min);
		
		
	}//main
	
	
	//점수 계산하는 메소드 
	static int getScore(ArrayList<Integer> list) {
		int score = 0;
		
		for(int i=0; i<list.size(); i++) {
			for(int j=i;j<list.size(); j++ ) {
				score += exp[list.get(i)][list.get(j)];
				score += exp[list.get(j)][list.get(i)];
			}
		}
		
		return score;
	}//getScore

}
