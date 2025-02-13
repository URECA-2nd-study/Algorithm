package jiyeon.week2;

import java.util.*;
import java.io.*;


public class boj_2668 {

	
	static int n;
	static ArrayList<Integer> result;
	static boolean [] visited;
	static int count;
	static ArrayList<ArrayList<Integer>> graphs;
	static int start;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		//연결리스트를 이용하여 그래프 생성
		graphs = new ArrayList<>();
		visited = new boolean [n+1];
		
		for(int i=0; i<=n; i++) {
			graphs.add(new ArrayList<>());
		}
		for(int i=1; i<=n; i++) {
			int n1 = Integer.parseInt(br.readLine());
			graphs.get(i).add(n1);
		}
		
		result = new ArrayList<Integer>();
		
		for(int i=1 ; i<=n; i++) {
			visited = new boolean[n+1]; 
			//방문 배열을 초기화했습니다.. 제가 짠 로직은 i번째 값을 뽑을 수 있냐/없냐만 판단하기때문에..
			//더 좋은 방법이 있을까요??
			start = i;
			if(dfs(i)) {
				result.add(i);
			}

		}
		
		count = result.size();
		
		Collections.sort(result);
		
		System.out.println(count);
		for(int num: result)
			System.out.println(num);
		
		
	}//main
	
	
	//해당 정수를 뽑을 수 있는지 확인하는 함수 
	static boolean dfs(int vertex) {
		
		visited[vertex] = true;
		
		for(int node : graphs.get(vertex)) {
			
			if(!visited[node]) {
				if(dfs(node))
					return true;
//				else if(node==start)
//					return true;
			}else if(node==start) //node를 방문했는데 start 값과 같으면 해당 숫자를 고를 수 있다.
				return true;
				
		}
		return false;
		
	}
	


}
