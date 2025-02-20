import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static final int mod = 1000000007;
    static int[] parent;
    static int[] cnt; // 집합에 속하는 교육생 수 카운트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        cnt = new int[N+1];

        // 초기 부모는 자기 자신
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            cnt[i] = 1;
        }

        // 유니온
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 1번 집합에서 튜터를 선정하는 경우의 수 * 2번 집합에서 튜터를 선정하는 경우의 수 * ...
        long answer = 1;
        for(int i=0; i<=N; i++){
            if(cnt[i] > 0){
                answer = (answer * cnt[i]) % mod;
            }
        }

        System.out.println(answer);
    }

    static void union(int first, int sec){
        int firstP = findParent(first);
        int secondP = findParent(sec);

        // 같은 집합이 아니면 유니온
        if (firstP != secondP) {
            // 더 작은 값 쪽으로 편입
            if (firstP < secondP) {
                parent[secondP] = firstP;
                cnt[firstP] += cnt[secondP];
                cnt[secondP] = 0;
            } else {
                parent[firstP] = secondP;
                cnt[secondP] += cnt[firstP];
                cnt[firstP] = 0;
            }
        }
    }

    static int findParent(int idx) {
        if (idx == parent[idx]) {
            return idx;
        }
        return parent[idx] = findParent(parent[idx]);
    }
}