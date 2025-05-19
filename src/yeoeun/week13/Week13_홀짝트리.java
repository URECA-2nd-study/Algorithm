package yeoeun.week13;

import java.util.*;

public class Week13_홀짝트리 {
    static HashMap<Integer, List<Integer>> graph = new HashMap<>();
    static HashSet<Integer> visited = new HashSet<>();
    static int yellow, red;

    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];

        // 그래프 생성
        for (int n : nodes) { // 노드 번호 X 연속적이므로
            graph.put(n, new ArrayList<>());
        }

        for (int[] e : edges) { // 연결 정보 삽입
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        // 루트마다 돌면서 확인
        for(int n : nodes) {
            if (visited.contains(n)) continue;

            yellow = 0;
            red = 0;

            // 모든 노드를 자식이라 가정하고 탐색
            bfs(n);

            // 즉, 하나만 다르면 그건 root라 반대로 봐도 됨
            if(red == 1) answer[0]++; // red가 1개면 모두 yellow 가능 (홀짝)
            if(yellow == 1) answer[1]++; // yellow가 1개면 모두 red 가능 (역홀짝)
        }
        return answer;
    }

    public void bfs(int cur) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(cur);
        visited.add(cur);

        while(!queue.isEmpty()) {
            int now = queue.poll();

            // 홀짝, 역홀짝 count
            if(checkIfYellow(now)) yellow++;
            else red++;

            for(int child : graph.get(now)) {
                if(visited.contains(child)) continue;
                visited.add(child);
                queue.offer(child);
            }
        }
    }

    public boolean checkIfYellow(int n) {
        return (n % 2) == ((graph.get(n).size() - 1) % 2);
    }
}
