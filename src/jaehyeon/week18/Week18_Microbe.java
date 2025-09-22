package src.jaehyeon.week18;

import java.io.*;
import java.util.*;

public class Week18_Microbe {

    public static final int[] dy = {0,0,-1,1};
    public static final int[] dx = {-1,1,0,0};

    public static class MicrobeOrder{
        private int numbering;
        private int size;

        public MicrobeOrder(int numbering, int size){
            this.numbering = numbering;
            this.size = size;
        }
    }

    public static class Microbe{
        private int y;
        private int x;

        public Microbe(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static final int COORDINATE = 4;
    public static int[][] input, map, newIncubator;
    public static boolean[][] visited;
    public static int N, Q;
    public static int[] microbeSize;
    public static Microbe[] microbeRangeStart, microbeRangeEnd;

    public static void main(String[] args) throws IOException{

        init();

        String answer = sol();

        print(answer);
    }

    private static String sol(){

        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=Q; i++){
            int[] coordinate = input[i];
            int numbering = i;

            putMicrobe(coordinate, numbering); // 미생물 투입

            moveIncubator(numbering); // 배양 용기 이동

            int result = recordResult(numbering); // 결과 기록

            sb.append(result).append("\n");
        }

        return sb.toString();
    }

    private static void initForMoving(){

        newIncubator = new int[N][N];
        
        microbeSize = new int[Q+1];
        microbeRangeStart = new Microbe[Q+1];
        microbeRangeEnd = new Microbe[Q+1];

        for(int i=1; i<=Q; i++){

            microbeSize[i] = 0;
            microbeRangeStart[i] = new Microbe(Integer.MAX_VALUE, Integer.MAX_VALUE);
            microbeRangeEnd[i] = new Microbe(0, 0);
        }

        for(int i=0; i<N; i++){

            for(int j=0; j<N; j++){

                if(isEmpty(i,j)) continue;

                int numbering = map[i][j];

                microbeSize[numbering]++;
                
                microbeRangeStart[numbering].y = Math.min(microbeRangeStart[numbering].y, i);
                microbeRangeStart[numbering].x = Math.min(microbeRangeStart[numbering].x, j);
                microbeRangeEnd[numbering].y = Math.max(microbeRangeEnd[numbering].y, i);
                microbeRangeEnd[numbering].x = Math.max(microbeRangeEnd[numbering].x, j);
            }
        }
    }

    private static ArrayList<MicrobeOrder> sortMicrobeOrder(int microCount){

        ArrayList<MicrobeOrder> order = new ArrayList<>();

        for(int i = 1; i <= microCount; i++){

            if(microbeSize[i] == 0) continue;

            order.add(new MicrobeOrder(i, microbeSize[i]));
        }

        Collections.sort(order, Comparator.comparingInt(o->-o.size));

        return order;
    }

    private static void startMoving(ArrayList<MicrobeOrder> order){

        for(MicrobeOrder o : order){

            int numbering = o.numbering;

            Microbe start = microbeRangeStart[numbering];
            Microbe end = microbeRangeEnd[numbering];

            int rowCount = end.y - start.y + 1;
            int colCount = end.x - start.x + 1;

            boolean placed = false;

            for(int newRow = 0; newRow <= N - rowCount && !placed; newRow++){

                for(int newCol = 0; newCol <= N - colCount; newCol++){
                    
                    boolean canPlace = true;

                    for(int dr = 0; dr < rowCount && canPlace; dr++){

                        for(int dc = 0; dc < colCount && canPlace; dc++){

                            int originalRow = start.y + dr;
                            int originalCol = start.x + dc;
                            
                            if(map[originalRow][originalCol] != numbering) continue;
                            
                            if(newIncubator[newRow + dr][newCol + dc] != 0){

                                canPlace = false;
                            }
                        }
                    }

                    if(canPlace){

                        for(int dr = 0; dr < rowCount; dr++){

                            for(int dc = 0; dc < colCount; dc++){

                                int originalRow = start.y + dr;
                                int originalCol = start.x + dc;
                                
                                if(map[originalRow][originalCol] != numbering) continue;

                                newIncubator[newRow + dr][newCol + dc] = numbering;
                            }
                        }

                        placed = true;
                        break;
                    }
                }
            }
        }
    }

    private static void moveIncubator(int microCount){

        initForMoving();

        ArrayList<MicrobeOrder> order = sortMicrobeOrder(microCount);
        
        startMoving(order);

        replaceIncubator();
    }

    private static void replaceIncubator(){

        for(int i=0; i<N; i++){

            for(int j=0; j<N; j++){

                map[i][j] = newIncubator[i][j];
            }
        }
    }

    private static int recordResult(int microCount){

        boolean[][] isAdjacent = new boolean[Q+1][Q+1];
        
        int[] currentMicrobeSize = new int[Q+1];

        for(int i=0; i<N; i++){

            for(int j=0; j<N; j++){

                if(!isEmpty(i,j)){
                    
                    currentMicrobeSize[map[i][j]]++;
                }
            }
        }

        for(int i=0; i<N; i++){

            for(int j=0; j<N; j++){

                if(isEmpty(i,j)) continue;

                for(int k=0; k<4; k++){
                    int py = i + dy[k];
                    int px = j + dx[k];

                    if(isOutOfRange(py,px) || isEmpty(py,px)) continue;

                    if(map[i][j] != map[py][px]){
                        int idA = map[i][j];
                        int idB = map[py][px];
                        isAdjacent[idA][idB] = true;
                        isAdjacent[idB][idA] = true;
                    }
                }
            }
        }

        int result = 0;

        for(int idA = 1; idA <= microCount; idA++){
            for(int idB = idA + 1; idB <= microCount; idB++){

                if(isAdjacent[idA][idB]){

                    result += currentMicrobeSize[idA] * currentMicrobeSize[idB];
                }
            }
        }

        return result;
    }

    private static void dfs(int i, int j, int numbering, boolean[][] visited){

        ArrayDeque<int[]> stack = new ArrayDeque<>();

        stack.push(new int[]{i,j});

        visited[i][j] = true;

        while(!stack.isEmpty()){
            int[] arr = stack.pop();
            int y = arr[0];
            int x = arr[1];

            for(int k=0; k<4; k++){
                int py = y+dy[k];
                int px = x+dx[k];

                if(isOutOfRange(py,px) || visited[py][px] || isAnotherMicrobe(py,px,numbering)){
                    continue; 
                }

                visited[py][px] = true;
                stack.push(new int[]{py,px});
            }
        }                
    }

    private static boolean isOutOfRange(int y, int x){

        return y < 0 || x < 0 || y>=N || x>=N;
    }

    private static boolean isAnotherMicrobe(int y, int x, int numbering){

        return map[y][x] != numbering;
    }

    private static boolean isEmpty(int y, int x){

        return map[y][x] == 0;
    }

    private static void putMicrobe(int[] coordinate, int numbering){

        int r1 = coordinate[0];
        int c1 = coordinate[1];
        int r2 = coordinate[2];
        int c2 = coordinate[3];

        for(int row = r1; row < r2; row++){
            for(int col = c1; col < c2; col++){
                map[row][col] = numbering;
            }
        }

        removeSeparation(numbering); // 분리된 미생물 제거
    }

    private static void removeSeparation(int currentNumbering){
        visited = new boolean[N][N];
        int[] microbeGroupCount = new int[Q+1];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(isEmpty(i,j) || visited[i][j]) continue;
                
                int numbering = map[i][j];
                microbeGroupCount[numbering]++;
                dfs(i, j, numbering, visited);
            }
        }

        for(int numbering=1; numbering<=currentNumbering; numbering++){

            if(microbeGroupCount[numbering] >= 2){

                removeMicrobe(numbering);
            }
        }
    }

    private static void removeMicrobe(int numbering){

        for(int i=0; i<N; i++){

            for(int j=0; j<N; j++){

                if(map[i][j] == numbering){

                    map[i][j] = 0;
                }
            }
        }
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        input = new int[Q+1][COORDINATE];
        map = new int[N][N];

        for(int i=1; i<=Q; i++){

            st = new StringTokenizer(br.readLine());

            for(int j=0; j<COORDINATE; j++){

                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void print(String answer) throws IOException{
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(answer);
        bw.close();
    }
}