package src.jaehyeon.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Week2_2668 {
    static ArrayList<Integer> list;
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {

        init();

        for(int i=1; i<=N; i++) {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            dfs(i, i, stack);
        }

        System.out.println(list.size());
        list.stream().sorted().forEach(System.out::println);
    }

    private static void dfs(int start, int end, ArrayDeque<Integer> stack) {
        //start 값은 계속 변하고 end는 변하지 않음

        //처음 들어올 떄 stack은 비어 있어서 처음 들어온 값은 추가되지 않음
        if(!stack.isEmpty() && start==end) {
            list.add(end);
        }

        if(!visited[arr[start]]) {

            if(!stack.contains(arr[start])) {
                stack.push(arr[start]);
            }

            //start로 들어온 노드로 탐색 시작
            visited[arr[start]] = true;
            dfs(arr[start],end,stack);

            //탐색이 끝나면 백트래킹으로 방문 제거
            visited[arr[start]] = false;
        }
    }

    private static void init(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            N = Integer.parseInt(br.readLine());

            arr = new int[N+1];
            visited = new boolean[N+1];
            list = new ArrayList<>();

            //1차원 배열에 그림의 두번째 줄 데이터 입력
            for(int i=1; i<=N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
