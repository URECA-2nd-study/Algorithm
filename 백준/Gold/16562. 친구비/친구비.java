import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static int[] cost;
    static int N;
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());
        int needMoney = 0;

        parent = new int[N + 1];
        cost = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for (int i = 1; i <= N; i++) {
            int p = find(i);

            if (!map.containsKey(p)) {
                map.put(p, cost[i]);
            } else {
                map.put(p, Math.min(map.get(p), cost[i]));
            }
            
        }


        for (Integer key : map.keySet()) {
            needMoney += map.get(key);
        }

        if (needMoney > money) System.out.println("Oh no");
        else System.out.println(needMoney);
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        parent[aRoot] = bRoot;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

}
