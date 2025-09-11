package src.jaehyeon.week17;

import java.io.*;
import java.util.*;

public class Week17_QueenAnt {

    public static final String BUILD_VILLAGE = "100";
    public static final String BUILD_ANT_HOUSE = "200";
    public static final String DESTORY_ANT_HOUSE = "300";
    public static final String PATROL_ANT_HOUSE = "400";

    public static ArrayList<Integer> antVillage = new ArrayList<>();
    public static ArrayList<Boolean> destroyedAntVillage = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        
        String result = performCommand();

        System.out.println(result);
    }

    private static String patrolAntHouse(StringTokenizer st){

        int r = Integer.parseInt(st.nextToken());

        // 개미 수 만큼 균등하게 순찰할 수 있는 지점을 찾는다.
        // 이분 탐색

        int start = 0;
        int end = 1_000_000_000;

        int min = 0;

        while(start <= end){

            int mid = (start + end) / 2;

            int previous = -1_000_000_000;

            int intervalCount = 0;

            for(int i=0; i<antVillage.size(); i++){
                
                if(destroyedAntVillage.get(i)) continue;

                int current = antVillage.get(i);

                if(current - previous > mid){

                    previous = current;
                    intervalCount++;
                }
            }

            if(intervalCount <= r){

                min = mid;
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return min + "";
    }

    private static void destroyAntHouse(StringTokenizer st){

        int q = Integer.parseInt(st.nextToken());

        destroyedAntVillage.set(q-1, true);
    }

    private static void buildAntHouse(StringTokenizer st){

        int p = Integer.parseInt(st.nextToken());

        antVillage.add(p);
        destroyedAntVillage.add(false);
    }

    private static void buildVillage(StringTokenizer st){
        
        int N = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){

            antVillage.add(Integer.parseInt(st.nextToken()));
            destroyedAntVillage.add(false);
        }
    }

    private static String performCommand() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int i = 0; i < Q; i++){
            
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            switch(command){

                case BUILD_VILLAGE:

                    buildVillage(st);

                    break;
                case BUILD_ANT_HOUSE:

                    buildAntHouse(st);

                    break;
                case DESTORY_ANT_HOUSE:

                    destroyAntHouse(st);

                    break;
                case PATROL_ANT_HOUSE:

                    String result = patrolAntHouse(st);
                    
                    sb.append(result).append("\n");
            }

        }

        return sb.toString();
    }
}