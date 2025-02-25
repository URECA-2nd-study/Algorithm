package src.jaehyeon.week3;

import java.util.*;
import java.io.*;

public class week3_1647 {

    public static class Node {
        int start;
        int end;
        int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.offer(new Node(A, B, C));
        }


        int answer = 0;
        int edgeCount = 0;
        if (M == 1) {
            bw.write("0");
        } else {
            while (!pq.isEmpty()) {
                Node node = pq.poll();

                int start = node.start;
                int end = node.end;
                int weight = node.weight;

                if (find(start) == find(end)) continue;

                union(start, end);
                answer += weight;
                edgeCount++;

                if (edgeCount >= N - 2) {
                    break;
                }

            }
            bw.write(answer + "");
        }


        bw.flush();
        bw.close();
        br.close();

    }

    private static void union(int a, int b) {
        int n1 = find(a);
        int n2 = find(b);

        if (n1 != n2) arr[n1] = n2;
    }

    private static int find(int a) {
        if (a == arr[a]) return a;
        else return arr[a] = find(arr[a]);
    }
}
