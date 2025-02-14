import java.io.*;
import java.util.*;

public class Main {


    static boolean[] used, visited;
    static int[] edges;
    static ArrayList<Integer> answers = new ArrayList<>();
    static int num = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        num = Integer.parseInt(br.readLine());

        used = new boolean[num + 1];
        edges = new int[num + 1];

        for (int i = 1; i <= num; i++) {
            edges[i] = Integer.parseInt(br.readLine());
        }


        for (int i = 1; i <= num; i++) {
            if (!used[i]) { // 순환 사이클에 사용되지 않았다면
                visited = new boolean[num + 1];
                find(new ArrayList<>(), i, i);
            }
        }

        Collections.sort(answers);

        bw.write(answers.size() + "\n");
        for (int answer : answers) bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();

    }


    static void find(ArrayList<Integer> list, int cur, int start) {
        if (visited[cur]) return;

        int next = edges[cur];
        list.add(cur);
        visited[cur] = true; // 방문처리

        if (next == start) { // 시작점으로 되돌아왔을 때 원형임
            answers.addAll(list); // 원형인 경우 경로 다 넣기
            for (int i : list) used[i] = true;
        }
        else {
            if (!used[next]) find(list, next, start);
        }

    }

}