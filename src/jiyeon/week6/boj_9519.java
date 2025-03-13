package jiyeon.week6;
import java.util.*;
import java.io.*;

public class boj_9519 {
	
	static int X;
	static LinkedList<Character> word = new LinkedList<>();
	static int cnt, length; //옮겨야하는 횟수, 단어의 길이
	static StringBuilder sb = new StringBuilder();
	static ArrayList<String> results = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		X = Integer.parseInt(br.readLine());
		String str = br.readLine(); //입력단어 
		cnt = str.length()/2;
		length = str.length();
		
		for(int i=0; i<length; i++) {
			word.add(str.charAt(i));
		} // input 
		
		
		int k = 0 ; //주기 
		
		while(true) {
			
			setForward();
			String result = getString();
			sb.setLength(0);
			results.add(result);
			
			if(result.equals(str)) {
				k = results.size();
				break;
			}
			
		}
		
		int index = 0;
		
		if(X%k != 0)
			index = X%k - 1;

		System.out.println(results.get(index));

	} //main 
	

	//다시 단어를 되돌리는 메서드 
	static void setForward() {
		
		for(int i=0; i<cnt; i++) {
			
			char forward = word.remove(i+1);
			word.add(length-(i+1), forward);
		}

	}//setForward
	
	
	//LinkedList를 String으로 만드는 메서드 
	static String getString() {
		
		for(int i=0; i<length; i++) {
			sb.append(word.get(i));
		}
		
		return sb.toString();
	}
	 
}
