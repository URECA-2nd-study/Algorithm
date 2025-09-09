import java.io.*;
import java.util.*;

public class Week17_여왕개미 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());

        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(st.nextToken());

            switch(order) {
                case 100:
                    int N = Integer.parseInt(st.nextToken());
                    int[] coordinate = new int[N];
                    for(int i = 0; i < N; i++) {
                        coordinate[i] = Integer.parseInt(st.nextToken());
                    }
                    init(N, coordinate);
                    break;
                case 200:
                    int x = Integer.parseInt(st.nextToken());
                    construct(x);
                    break;
                case 300:
                    x = Integer.parseInt(st.nextToken());
                    remove(x);
                    break;
                case 400:
                    int r = Integer.parseInt(st.nextToken());
                    sb.append(search(r)).append("\n");
                    break;
            }
        }
        System.out.println(sb.toString());
    }

    private static int[] antHouse;
    private static boolean[] state;
    private static int count;

    private static void init(int N, int[] coordinate) {
        antHouse = new int[20050];
        state = new boolean[20050];
        count = 0;

        for(int i = 0; i < N; i++) {
            count++;
            antHouse[count] = coordinate[i];
            state[count] = true;
        }
    }

    private static void construct(int x) {
        count++;
        antHouse[count] = x;
        state[count] = true;
    }

    private static void remove(int x) {
        state[x] = false;
    }

    private static int search(int r) {
        if(!isExistAntHouse()) return 0;

        int s = 0;
        int e = 1_000_000_000;
        int answer = 0;

        while(s <= e) {
            int mid = s + (e - s) / 2;
            
            if(check(mid) <= r) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return answer;
    }

    private static int check(int mid) {
        int first = -1;
        for(int i = 1; i <= count; i++) {
            if(!state[i]) continue;

            first = i;
            break;
        }
        if(first == -1) return 0;

        int ants = 1;
        int pre = antHouse[first];

        for(int i = first + 1; i <= count; i++) {
            if(!state[i]) continue;

            int curr = antHouse[i];
            if(curr - pre > mid) {
                ants++;
                pre = curr;
            }
        }

        return ants;
    }

    private static boolean isExistAntHouse() {
        for(boolean b : state) {
            if(b) return true;
        }
        return false;
    }
}
