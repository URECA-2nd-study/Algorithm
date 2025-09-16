package jinu.week18;

import java.util.*;
import java.io.*;

public class Week18_microbe_research {

    static int map[][];

    static int dy[]={-1,1,0,0};
    static int dx[]={0,0,-1,1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        map=new int[n][n];
        int count = Integer.parseInt(st.nextToken());

        for(int seq=1;seq<=count;seq++){
            st = new StringTokenizer(br.readLine());

            int lowX = Integer.parseInt(st.nextToken());
            int lowY = Integer.parseInt(st.nextToken());
            int highX = Integer.parseInt(st.nextToken());
            int highY = Integer.parseInt(st.nextToken());

            List<Integer> areaList = insert(lowX,lowY,highX,highY,seq,n);
            //     print(n);
            //   System.out.println("----------");
            moveToOtherMap(areaList,n);
            // print(n);
            // System.out.println("------------------");
            calculate(n);
        }


    }

    public static List<Integer> insert(int lowX,int lowY, int highX,int highY, int seq, int n){

        // 삽입 시 겹치는 부분 제거
        for(int j=lowX;j<=highX-1;j++){
            for(int i=lowY;i<=highY-1;i++){
                map[i][j] = seq;
            }
        }



        // 각 무리가 두 영역으로 나누어 지는 지 확인 및 제거

        boolean isVisit[][] = new boolean[n][n];
        Set<Integer> set = new HashSet<>();
        Set<Integer> removeSet = new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(isVisit[i][j] || map[i][j]==0) continue;

                if(!isVisit[i][j] && set.contains(map[i][j])){
                    removeSet.add(map[i][j]);
                }

                if(!isVisit[i][j] && !set.contains(map[i][j])){
                    set.add(map[i][j]);
                    BFS(isVisit,i,j,n,map[i][j]);
                }
            }
        }

        //     System.out.println("여기: "+set.size());


        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(removeSet.contains(map[i][j])){
                    map[i][j] = 0;
                }
            }
        }


        // 반환값 : 각 넓이가 큰 거 순서대로
        Map<Integer,Integer> areaMap = new HashMap<>();
        for(int s : set){
            if(!removeSet.contains(s)){
                areaMap.put(s,0);
            }
        }


        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(areaMap.containsKey(map[i][j])){
                    areaMap.put(map[i][j],areaMap.get(map[i][j])+1);
                }
            }
        }

        List<Integer> list = new ArrayList<>(areaMap.keySet());

        Collections.sort(list,(o1,o2)->{
            if(areaMap.get(o1)==areaMap.get(o2)){
                return o1-o2;
            }

            return areaMap.get(o2)-areaMap.get(o1);
        });

        return list;

    }

    public static void BFS(boolean isVisit[][],int i,int j,int n, int seq){
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{i,j});

        while(!queue.isEmpty()){
            int current[] = queue.poll();

            if(isVisit[current[0]][current[1]]) continue;

            isVisit[current[0]][current[1]] = true;

            for(int k=0;k<4;k++){
                int nextY = current[0]+dy[k];
                int nextX = current[1]+dx[k];

                if(nextY>=0 && nextY<n && nextX>=0 && nextX<n && map[nextY][nextX]==seq){
                    queue.add(new int[]{nextY,nextX});
                }
            }


        }
    }

    public static void moveToOtherMap(List<Integer> list,int n){

        int tmpMap[][] = new int[n][n];
        for(int seq: list){
            int lowX = n+1;
            int lowY = n+1;
            int highX = -1;
            int highY = -1;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j]==seq){
                        lowX = Math.min(lowX,j);
                        lowY = Math.min(lowY,i);
                        highX = Math.max(highX,j);
                        highY = Math.max(highY,i);
                    }
                }
            }
            //       System.out.println("seq: "+seq);
            //     System.out.println("lowX: "+lowX);
            //    System.out.println("lowY: "+lowY);
            //   System.out.println("highX: "+highX);
            //  System.out.println("highY: "+highY);
            doMigrate(n,tmpMap,lowX,lowY,highX,highY,seq);

        }


        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[i][j]=tmpMap[i][j];
            }
        }



    }

    public static void doMigrate(int n,int tmpMap[][],int lowX,int lowY,int highX,int highY,int seq){

        for(int j=0;j<n;j++){
            for(int i=0;i<n;i++){
                if(canEnter(tmpMap,i,j,lowX,lowY,highX,highY,n,seq)){
                    //     System.out.println("seq: "+seq+"성공");
                    migrate(tmpMap,lowX,lowY,highX,highY,i,j,seq);
                    return;
                }
            }
        }
    }

    public static boolean canEnter(int [][]tmpMap,int basicI,int basicJ,int lowX,int lowY,int highX, int highY, int n,int seq){
        if(basicJ+highX-lowX>=n || basicI+highY-lowY>=n){
            return false;
        }



        for(int i=lowY;i<=highY;i++){
            for(int j=lowX;j<=highX;j++){
                if(map[i][j]==seq && tmpMap[i-lowY+basicI][j-lowX+basicJ]!=0){
                    //   System.out.println()
                    return false;
                }

            }
        }

        return true;
    }

    public static void migrate(int [][]tmpMap, int lowX,int lowY,int highX,int highY,int basicI,int basicJ,int seq){

        for(int i=lowY;i<=highY;i++){
            for(int j=lowX;j<=highX;j++){
                if(map[i][j]==seq){
                    tmpMap[i-lowY+basicI][j-lowX+basicJ]=seq;
                }
            }
        }

    }

    public static void print(int n){

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }

    public static void calculate(int n){

        Map<Integer,Integer> calculateMap = new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]!=0){
                    calculateMap.put(map[i][j], calculateMap.getOrDefault(map[i][j], 0) + 1);
                }
            }
        }

        //    System.out.println(calculateMap.keySet());

        Set<TwoNumber> twSet = new HashSet<>();

        long sum = 0;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<4;k++){
                    int friendY = i+dy[k];
                    int friendX = j+dx[k];

                    if(friendY<0 || friendY>=n || friendX<0 || friendX>=n || (map[i][j]*map[friendY][friendX]==0)){
                        continue;
                    }

                    if(map[i][j]!=map[friendY][friendX] && !twSet.contains(new TwoNumber(Math.min(map[i][j],map[friendY][friendX]),Math.max(map[i][j],map[friendY][friendX])))){

                        twSet.add(new TwoNumber(Math.min(map[i][j],map[friendY][friendX]),Math.max(map[i][j],map[friendY][friendX])));
                        sum += calculateMap.get(map[i][j]) * calculateMap.get(map[friendY][friendX]);
                    }


                }
            }
        }

        System.out.println(sum);
    }

    public static class TwoNumber{
        int first;
        int second;
        public TwoNumber(int first,int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o){
            TwoNumber tw = (TwoNumber) o;
            if(this.first == tw.first && this.second == tw.second){
                return true;
            }
            return false;
        }

        @Override
        public int hashCode(){
            return Objects.hash(this.first, this.second);
        }
    }

}
