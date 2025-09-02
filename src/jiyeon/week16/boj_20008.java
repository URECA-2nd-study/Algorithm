package week16;

import java.io.*;
import java.util.*;

public class boj_20008 {

    static class Skill {
        int c, d;
        public Skill(int c, int d) { 
        	this.c = c;
        	this.d = d;
        }
    }
    
    static int[] nextTimes;
    static Skill[] skills;
    static int min = Integer.MAX_VALUE; //최소 시간
    static int N, HP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        HP = Integer.parseInt(st.nextToken());

        skills = new Skill[N];
        nextTimes = new int[N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            skills[i] = new Skill(c, d);
        }
        
        dfs(0,0);
        
        System.out.println(min);


    }//main
    
    //백트래킹 
    public static void dfs (int curTime, int damage) {
    	if (curTime >= min) {
    		return;
    	}
    	
    	//몬스터가 죽으면 끝
    	if(damage>= HP) {
    		min = Math.min(curTime, min);
    		return;
    	}
    	
    	boolean flag = false;
    	
    	for(int i=0; i<N; i++) {
    		
    		//현재 시간에 스킬 사용가능할 경우
    		if(nextTimes[i] <= curTime) {
    			flag = true;
    			int prev = nextTimes[i];
    			nextTimes[i] = curTime + skills[i].c;
    			dfs(curTime+1, damage + skills[i].d);
    			nextTimes[i] = prev;
    		}
    	}
    	
    	//모든 스킬이 사용 불가한 경우 시간 증가 
    	if(!flag) {
    		dfs(curTime+1, damage);
    	}
    }
}
