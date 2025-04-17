package jiyeon.week10;

import java.util.*;
import java.io.*;

public class boj_10859 {
	
	static long N; 
	static StringBuilder sb = new StringBuilder(); //숫자 뒤집기 용

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		N = Long.parseLong(str);
		
		//분기 0 : 1이면 바로 No
		if(N==1L) {
			System.out.println("no");
			return;
		}
		
		//분기 1 : 주어진 숫자에 3,4,7이 들어있는가?
		if(isContained(str)) {
			System.out.println("no");
			return;
		}
		
		//분기 2 : 주어진 숫자는 소수인가?
		if(!isPrime(N)) {
			System.out.println("no");
			return;
		}
		
		//분기 3 : 주어진 숫자를 뒤집어도 소수인가?
		String flipNum = flip(str);
		
		if(!isPrime(Long.parseLong(flipNum))) {
			System.out.println("no");
			return;
		}
		
		System.out.println("yes");
		
		
	}//main
	
	//숫자 뒤집기 
	static String flip(String str) {
		
		for(int i=0; i<str.length();i++) {
			//뒤집으면 순서도 reverse되기 때문에 StringBuilder 맨 앞으로 계속 넣어준다.
			if(str.charAt(i)=='6') {
				sb.insert(0, 9);
			}
			else if(str.charAt(i)=='9') {
				sb.insert(0, 6);
			}
			else {
				sb.insert(0,str.charAt(i));
			}
		}
		
		return sb.toString();
	}
	
	
	//주어진 숫자가 소수인지 아닌지 
	static boolean isPrime(long N) {
		long sq = (long) Math.sqrt(N);
		
		for(long i=2L; i<=sq; i++) {
			if(N%i ==0)
				return false;
		}
		return true;
	}
	
	//주어진 숫자에 3,4,7이 들어있는지 
	static boolean isContained(String str) {
		
		HashSet<Integer> set = new HashSet<>();
		set.add(3);
		set.add(4);
		set.add(7);
		
		for(int i=0; i<str.length(); i++) {
			if(set.contains(str.charAt(i)-'0')) {
				return true;
			}
		}
		
		return false;
	}

}
