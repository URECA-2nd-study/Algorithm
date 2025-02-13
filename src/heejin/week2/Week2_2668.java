import java.io.*;
import java.util.*;

/**
 * [문제]
 * 아래 조건 만족시키면서 최대로 많이 뽑는 방법
 *
 * [조건]
 * 첫째 줄에서 숫자를 적절히 뽑으면,
 * 그 뽑힌 정수들이 이루는 집합과
 * 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치
 *
 * [풀이]
 * 탐색했을 때 시작점으로 다시 돌아오면 답에 포함
 *
 * [예제]
 * 1 2 3 4 5 6 7
 * 2 3 1 4 7 7 6
 *
 * 1->2->3->1(O) 답 가능
 * 4->4(O) 답 가능 -> 시작점으로 돌아오면, 탐색 종료 + 시작점이 답에 포함
 * 5->7->6->7(X) 답 불가능 -> 시작점이 아니며 전에 방문했던 곳으로 돌아오면, 그냥 탐색 종료
 */

public class Main {
    static int N;
    static List<Integer> answer = new ArrayList<>();
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        // 입력
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 시작점 달리하여 탐색
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;

            dfs(i, i);
        }

        // 출력
        System.out.println(answer.size());
        for (int ans : answer) {
            System.out.println(ans);
        }
    }

    static void dfs(int start, int cur) {
        int next = arr[cur];

        // 처음으로 돌아오면 탐색 종료 + 시작점 답에 포함
        if (next == start) {
            answer.add(start);
            return;
        }

        // 시작점이 아니며 전에 방문했던 곳으로 돌아오면 그냥 탐색 종료
        if (visited[next]) {
            return;
        }

        visited[next] = true;
        dfs(start, next);
    }
}