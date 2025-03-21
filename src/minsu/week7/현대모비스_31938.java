import java.io.*;
import java.util.*;

/*
* 다익스트라로 최단 경로를 구하고
* 최단 경로에서 나온 트리를 가지고 각 정점별로 자기를 포함한 서브트리 노드 갯수를 구합니다.
* 그 갯수가 트럭을 보내야 할 숫자.
* 최단 경로를 구하는 경로는
* 1번 노드부터 N번 노드까지의 길이 가장 최적 길이여야하며
* 1번을 제외한 모든 노드는 자신으로부터 이어진 노드의 간선의 길이 중
* 가장 짧은 간선을 골라야함.
*/

public class 현대모비스_31938 {
    // ArrayLIst<int[]>[] arr 은 각 노드별 연결된 노드와 간선의 가중치를 담음
    // dist는 다익스트라 알고리즘에서 사용할 메모이제이션 배열
    // cost는 각 노드에서 연결된 노드들 중 가장 짧은 간선의 가중치를 담음
    // p는 다익스트라를 통해 만들어진 트리에서 현재 자기의 부모를 가리키는 배열
	private static int N, M;
	private static ArrayList<int[]>[] arr;
	private static long[] dist, cost;
	private static int[] p;
	private static int[] childCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N + 1];
		childCnt = new int[N + 1];
		dist = new long[N + 1];
		cost = new long[N + 1];
		p = new int[N + 1];

        // 유니온 파인드의 개념을 사용함
        // 현재 노드는 지금 자기 노드를 가리키게 설정
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

        // 다익스트라에서 사용할 메모이제이션 배열에
        // Long의 최대값을 다 넣어줌
		Arrays.fill(dist, Long.MAX_VALUE);

        // 각 노드별 연결된 노드와 간선의 가중치를 담을 배열의 ArrayList 배열을 만들어서
        // 각 인덱스마다 ArrayList 선언
        for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

        // 입력 예제 설정
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[from].add(new int[] { to, w });
			arr[to].add(new int[] { from, w });
		}

        // 다익스트라 실행
		dijkstra();
		
        // 트리의 정보를 담기 위해 만든 배열
		ArrayList<Integer>[] adj = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
        // 부모노드와 자식노드의 단방향 연결을 만듬
        // dfs를 돌아서 현재 노드 + 자식 노드들 갯수를 구하기 위해
		for(int i = 2; i<=N; i++) {
			adj[p[i]].add(i);
		}
		
		dfs(1, new boolean[N +1], adj);
		
		long sum = 0;

        // 조건에 맞는 계산
		for (int i = 2; i <= N; i++) {
			long cal = (long) (cost[i] * childCnt[i]) - ((cost[i] / 10) * (childCnt[i] - 1));
			sum += cal;
		}
		System.out.println(sum);
	}
	
    // 현재 노드에서 자신을 포함한 서브트리의 노드 갯수를 구하는 메서드
	private static int dfs(int v, boolean[] visited, ArrayList<Integer>[] adj) {
		int cnt = 1;
		visited[v] = true;
		
		for(int w : adj[v]) {
			if(visited[w]) continue;
			cnt += dfs(w, visited, adj);
		}
		
		childCnt[v] = cnt;
		
		return cnt;
		
	}

    // 다익스트라 실행
	private static void dijkstra() {
		boolean[] visited = new boolean[N + 1];
		dist[1] = 0;

        // 우선순위 큐의 정렬 조건은 dist 값의 오름차순이다.
		PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {

			@Override
			public int compare(long[] o1, long[] o2) {

				return Long.compare(o1[1], o2[1]);
			}
		});

        // 현재 노드, dist 값, 연결된 가중치, 부모노드
		pq.add(new long[] { 1, 0, 0, 1 });

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int idx = (int) curr[0];

			if (visited[idx])
				continue;

			visited[idx] = true;

			for (int[] w : arr[idx]) {
				if (dist[w[0]] > dist[idx] + w[1]) {
					dist[w[0]] = dist[idx] + w[1];
					union(idx, w[0]);
					cost[w[0]] = (long) w[1];
					pq.offer(new long[] { w[0], dist[w[0]], w[1], idx });
				} else if (dist[w[0]] == dist[idx] + w[1] && cost[w[0]] > (long) w[1]) {
                        // dist의 값이 같다면, 노드 간 연결된 가중치가 더 작을 경우 최신화를 해주는 작업
						union(idx, w[0]);
						cost[w[0]] = (long) w[1];
				}
			}
		}
		

	}
	
	private static void union(int x, int y) {
		p[y] = x;
	}

}