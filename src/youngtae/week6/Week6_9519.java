package youngtae.week6;

import java.io.*;
import java.util.*;

public class Week6_9519 {

	static int X, tempX;
	static String word;
	static StringBuilder head, tail;
	static HashMap<String, Integer> mapWord;
	static List<String> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		X = Integer.parseInt(br.readLine());
		tempX = X;
		word = br.readLine();

		head = new StringBuilder();		// 잘린 앞 부분
		tail = new StringBuilder();		// 잘린 뒷 부분
		mapWord = new HashMap<>();		// 주기를 찾기 위한 Map

		list = new ArrayList<>();
		list.add(word);

		int cnt = 0;
		int size = word.length();
		while(tempX-- > 0) {
			head.setLength(0);
			tail.setLength(0);

			// 문자열 길이에 따른 이전 단계 문자열 복구
			if(size % 2 == 0) {
				for(int i = size-1; i >= 0; i-=2) {
					tail.append(word.charAt(i));
				}
				for(int i = 0; i < size; i+=2) {
					head.append(word.charAt(i));
				}
			} else {
				for(int i = size-2; i >= 0; i-=2) {
					tail.append(word.charAt(i));
				}
				for(int i = 0; i < size; i+=2) {
					head.append(word.charAt(i));
				}
			}

			// 문자 합치기
			head.append(tail);
			word = head.toString();
			list.add(word);

			// 같은 문자가 존재 == 주기 발견
			if(mapWord.containsKey(word)) {
				break;
			}
			mapWord.put(word, cnt++);
		}

		// tempX > 0 == 주기 발견으로 인한 반복문 조기 종료
		if(tempX > 0) {
			tempX = X % cnt;
			bw.write(list.get(tempX));
		} else {
			bw.write(word);
		}

		bw.flush();
		br.close();
		bw.close();
	}

}