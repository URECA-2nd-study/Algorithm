package baekjoon;

import java.io.*;
import java.util.*;


public class boj_31938 {

	
	static class Node implements Comparable<Node>{
		
		private int v; //이어진 정점 
		private long w; //가중치 
		
		public Node(int v, long w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o1) {
			
			if(this.w == o1.w)
				return this.v - o1.v;
			
			return Long.compare(this.w, o1.w);
		}
		
	}//Node
	
	
	static int N, M; //도시 개수, 도로 개수 
	static long answer;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static boolean [] visited; //방문 배열 
	static long [] dist; // dist[i] : i 번째 노드까지의 최소 누적 비용 
	static Node [] prev; 
	// prev[i].v : i 번째 노드의 바로 전 노드 / prev[i].w : 직전 간선 비용 (prev[i].v 와 자기 자신의 비용)
	static int [] childNodeNum; //childNodeNum[i] = n ; 정점 i 자기 자신을 포함한 자식 노드의 개수는 n이다. 
	static long [] bestParentCost; //i번째 노드로 오기 위해 사용한 경로에서 직전 노드까지의 누적비용 

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		dist = new long[N+1];
		childNodeNum = new int[N+1];
		prev = new Node[N+1];
		bestParentCost = new long[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(bestParentCost, -1);
		
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Node>());
		}//initialize
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			//양방향 연결 
			graph.get(a).add(new Node(b,c));
			graph.get(b).add(new Node(a,c));
			
		}
		//input
		
		prev[1] = new Node(0,0); // 출발지 초기화
		
		dij(); 
		

		//prev 배열을 이용해서 다익스트라 이후 트리 구성
		
		List<Integer>[] tree = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			tree[i] = new ArrayList<>(); 
		}
		
		for(int i=2; i<=N; i++) {
			tree[prev[i].v].add(i);
		}
		
		
		//위의 트리를 기준으로 DFS 이용하여 자식 노드 개수 구하기
		dfs(1,tree);

		
		//최종 비용 계산하기
		for(int i=2; i<=N; i++) {
			
			answer += (9*childNodeNum[i]+1)*prev[i].w/10;
		}
		//정답 출력
		System.out.println(answer);
		

	}//main
	
	
	//DFS : node의 자식 노드 개수를 카운트 하는 용도 
	static int dfs(int node, List<Integer>[] tree) {
		int count = 1; 
		for(int child : tree[node]) {
			count += dfs(child,tree);
		}
		
		childNodeNum[node] = count;
		return count;
		
	}
	
	
	//다익스트라 메서드 
	static void dij() {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[1] = 0; //출발지는 무조건 1
		pq.add(new Node(1,0));
		
		while(!pq.isEmpty()) {
			
			Node current = pq.poll();
			
			if(visited[current.v])
				continue;
			
			visited[current.v] = true;

			for(Node next : graph.get(current.v)) {
				long newCost = dist[current.v] + next.w;
				
				//조건 1 : 더 짧은 경로를 발견하면 무조건 갱신 
				if(dist[next.v] > newCost) {
					
					dist[next.v] = newCost;
					prev[next.v] = new Node(current.v,next.w);
					bestParentCost[next.v] = dist[current.v]; // 부모 누적 비용 저장
					pq.add(new Node(next.v, newCost));

				}
				//조건 2 : 만약 총 비용이 같을 경우, 현재 경로의 누적 비용(dist 배열)이 이전에 저장한 부모 누적 비용(bestParentCost)보다 크면 갱신
				/*
				 * 예제를 보면 5번째 노드로 가는 경우의 수는 
				 * 1. 1 -> 2 -> 3 -> 5
				 * 2. 1 -> 2 -> 4 -> 5 (정답)
				 * 
				 * 이렇게 2가지가 있습니다. 우리가 최적의 할인을 받기 위해서는 5 번째 노드까지 오는 경로 중에서 직전 노드까지의 누적비용이 "큰" 경로를 선택해야 할인을 많이 받을 수 있습니다.
				 * 
				 * 다익스트라를 진행하게 되면 조건 1에 의해서 먼저  1 -> 2 -> 3 -> 5의 경로 중 5의 직전 노드인 3까지 오는 비용이 bestParent[5]에 저장됩니다. (bestParent[5] = 30)
				 * 이후 1 -> 2 -> 4 -> 5 경로를 검증하는 구간에서 두 경로의 총 비용이 같으니깐 조건 2로 오게 됩니다. 이 때 1->2->4 까지의 비용에 해당하는 dist[4]의 비용이 40으로, bestParent[5]의 값보다 크니 
				 * 해당 경로를 선택하게 되고, prev배열과 bestParentCost배열을 갱신합니다.
				 * 
				 * */
				else if(dist[next.v] == newCost) {
                    if (bestParentCost[next.v] == -1 || dist[current.v] > bestParentCost[next.v]) {
                    	
                        prev[next.v] = new Node(current.v, next.w);
                        bestParentCost[next.v] = dist[current.v];
                    }
				}
				
			}
		}
		
		
	}//dij
	
	

}