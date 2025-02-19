import java.io.*;
import java.util.*;

public class Main {

    static List<List<Integer>> graph = new ArrayList<>();
    static int nodeNum;
    static int edgeNum;
    static BufferedWriter bw;

    static void BFS(int startNode) throws IOException {
        boolean[] isVisited = new boolean[nodeNum];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        isVisited[startNode] = true;
        bw.write((startNode + 1) + " ");

        while (!queue.isEmpty()) {
            int curPos = queue.poll();
            for (int next : graph.get(curPos)) {
                if (isVisited[next]) continue;
                isVisited[next] = true;
                queue.add(next);
                bw.write((next + 1) + " ");
            }
        }
        bw.write("\n");
    }

    static void DFS(int startNode, boolean[] isVisited) throws IOException{
        isVisited[startNode] = true;
        bw.write((startNode + 1) + " ");

        for (int next : graph.get(startNode)) {
            if (!isVisited[next]) DFS(next, isVisited);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        nodeNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < nodeNum; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        for (int i = 0; i < nodeNum; i++) {
            Collections.sort(graph.get(i));
        }

        boolean[] isVisited = new boolean[nodeNum];
        DFS(startNode, isVisited);
        bw.write("\n");
        BFS(startNode);

        bw.flush();
        bw.close();
        br.close();

    }
}
