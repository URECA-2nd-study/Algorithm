import java.util.*;

public class Week13_388354 {
    public int[] solution(int[] nodes, int[][] edges) {
        // 1. 그래프(인접 리스트) 생성
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int u : nodes) {
            adj.put(u, new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        // 2. 방문 체크를 위한 set
        Set<Integer> visited = new HashSet<>();
        int properCount = 0;   // 홀짝 트리 개수
        int reverseCount = 0;  // 역홀짝 트리 개수

        // 3. 각 컴포넌트별 BFS 탐색
        for (int start : nodes) {
            if (visited.contains(start)) continue;

            int P = 0, R = 0;
            Queue<Integer> q = new ArrayDeque<>();
            visited.add(start);
            q.add(start);

            while (!q.isEmpty()) {
                int u = q.poll();
                // forest 가정하에 자식 개수 = degree(u) - 1
                int childCnt = adj.get(u).size() - 1;

                // proper 분류(P) / reverse 분류(R)
                if (u % 2 == childCnt % 2) {
                    P++;
                } else {
                    R++;
                }

                for (int v : adj.get(u)) {
                    if (!visited.contains(v)) {
                        visited.add(v);
                        q.add(v);
                    }
                }
            }

            // R == 1 이면 proper(홀짝) 트리로 만들 수 있음
            if (R == 1) properCount++;
            // P == 1 이면 reverse(역홀짝) 트리로 만들 수 있음
            if (P == 1) reverseCount++;
        }

        return new int[]{ properCount, reverseCount };
    }
}