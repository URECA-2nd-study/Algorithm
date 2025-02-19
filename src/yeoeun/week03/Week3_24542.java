package yeoeun.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Week3_24542 {
    static int[] root; // 부모(루트) 정보를 관리
    static HashMap<Integer, Integer> map = new HashMap<>(); // 그래프 별 노드 개수 관리 (root 기준)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        root = new int[N+1];
        for (int i = 1; i <= N; i++) {
            root[i] = i; // 초기 설정 : 루트를 자기 자신으로 초기화
        }

        for (int i = 0; i < M; i++) {
            // 연결 정보 받아오기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a - b 연결 시 변화 계산
            union(a, b);
        }

        long answer = 1;
        for (Integer cnt : map.values()) {
            // 각 그룹별 1명씩 주면 됨 -> 그래프 별 경우의 수 == 노드 수
            // 총 경우의 수 == 그래프 별 노드 수를 모두 곱한 값
            answer = (answer * cnt) % 1000000007;
        }
        System.out.println(answer);
    }

    private static void union(int a, int b) {
        // 각각의 루트노드 값으로 갱신
        a = find(a);
        b = find(b);

        if(a != b) { // 서로 다른 그래프에 속해 있는 경우
            root[b] = a; // 연결 정보 업데이트

            // 경우의 수를 줄이기 위해 map에 항상 a, b가 있도록 설정
            if(!map.containsKey(a)) map.put(a, 1);
            if(!map.containsKey(b)) map.put(b, 1);

            // 노드의 수 계산 (합치기)
            map.replace(a, map.get(a) + map.get(b));
            map.remove(b);
        }
    }

    private static int find(int node) {
        if(root[node] == node) { // 자신이 루트인 경우
            return node;
        } else { // 재귀로 root 노드 탐색
            root[node] = find(root[node]); // 재탐색 시 시간 단축용
            return root[node];
        }
    }
}
