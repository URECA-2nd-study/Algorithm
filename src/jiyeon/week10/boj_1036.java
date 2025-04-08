package jiyeon.week10;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class boj_1036 {
	
	static HashMap<Character,Integer> dict = new HashMap<>(); //'A'-'Z'를 10~35로 매핑하는 HashMap
	static BigInteger maxSum = new BigInteger("0"); //정답
	static String [] nums; //index 역순으로 character 저장
	static ArrayList<ArrayList<Character>> chars = new ArrayList<>(); // 역순으로 알파벳 인덱스 쪼개서 넣기
	static int N, K, maxLen = -1; 
	
	static class Bit36 implements Comparable<Bit36>{
		
		private char digit; // 0-9 , A-Z;
		private BigInteger value; //바꾸면 얼마나 가치가 있는지 
		
		public Bit36(char digit) {
			this.digit = digit;
			this.value = BigInteger.valueOf(0);
		}
		
		@Override
		public int compareTo(Bit36 o) {
			
			return o.value.compareTo(this.value);
		}
		
		
	}//inner class
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new String[N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			maxLen = Math.max(maxLen, str.length());
			nums[i] = str;
		}
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<maxLen; i++) {
			chars.add(new ArrayList<>());
		}
		init(); //input & init
		
		
		/*역순으로 알파벳 위치에 따라 넣기
		 * 예를 들어 "GOOD" 이면 char.get(0) = D / get(1) = O, get (2) = O, get(3) = G
		 * */ 
		for(String n : nums) {
			splitStr(n);
		}

		
		//0-9, A-Z를 바꾸었을 때 최대 가치인 진수 구하기 
		
		ArrayList<Bit36> bestValue = new ArrayList<>();
		for(int i=0; i<10; i++) {
			bestValue.add(new Bit36((char)('0'+i)));
		}
		for(int i=0; i<26; i++) {
			bestValue.add(new Bit36((char) ('A'+i)));
		}
		getValue(bestValue);
		
		
		//가치가 높은 알파벳 K개를 바꾸기
		Collections.sort(bestValue);
		
		for(int i=0; i<K; i++) {
			char digit = bestValue.get(i).digit;
			change(digit);
		}

		
		//더하기 
		maxSum = add();
		
		//더한 후 36진수 변환 
		System.out.println(maxSum.toString(36).toUpperCase()); //BigInteger.toString(N)의 결과 문자는 소문자로 나오기 땜에 Upper case로 변환

		
	}//main
	
	//가치가 높은 상위 K개의 digit을 Z로 변환하기
	static void change(char digit) {
		
		for(int i=0; i<maxLen; i++) {
			for(int j=0; j<chars.get(i).size(); j++) {
				char ch = chars.get(i).get(j);
				if(ch == digit) {
					chars.get(i).set(j, 'Z');
				}
			}
		}
		
	}//change
	
	//어떤 알파벳을 바꿨을 때 가치가 높은지 확인하기 위해 바꿨을 때의 가치 차이를 구하는 메서드
	static void getValue(ArrayList<Bit36> bestValue) {
		
		BigInteger base = BigInteger.valueOf(36);
		
		for(int i=0; i<maxLen; i++) {
			
			BigInteger valueSum = BigInteger.valueOf(0);
			BigInteger mul = base.pow(i); //36진수
			
			for(int j=0; j<chars.get(i).size(); j++) {
				char ch = chars.get(i).get(j);
				int value = 0;
				int index ; 
				
				if(Character.isDigit(ch)) {
					value = ch - '0';
					index = ch - '0';

				}
				else {
					value = dict.get(ch);
					index = ch - 'A' + 10;
				}
				
				valueSum = mul.multiply(BigInteger.valueOf(35-value));
				bestValue.get(index).value = bestValue.get(index).value.add(valueSum); //가치 누적 합 
			}
		}
		
	}//getValue
	
	
	//36진수 더하는 메서드 
	static BigInteger add() {
		BigInteger sum = BigInteger.valueOf(0);
		BigInteger base = BigInteger.valueOf(36);
		
		for(int i=0; i<maxLen; i++) {
			BigInteger mul = base.pow(i); //36진수
			
			for(int j=0; j<chars.get(i).size(); j++) {
				char ch = chars.get(i).get(j);
				int value = 0;
				
				if(Character.isDigit(ch)) {
					value = ch - '0';
				}
				else {
					value = dict.get(ch);
				}
				
				sum = sum.add(mul.multiply(BigInteger.valueOf(value)));
				
			}
		}
		
		return sum;
	}
	
	
	
	//역순으로 arrayList에 알파벳 넣는 메서드 
	static void splitStr(String str) {
		
		int len = str.length();
		for(int i=0; i<len; i++) {
			chars.get(Math.abs(i-len+1)).add(str.charAt(i));
		}
		
	}
	
	
	//초기 매핑 HashMap 만들기 
	static void init() {
		
		for(int i=0; i<10; i++) {
			dict.put((char)('0'+i), i);
		}
		
		for(int i=0; i<26; i++) {
			dict.put((char) ('A'+i), i+10);
		}
	}//init

}
