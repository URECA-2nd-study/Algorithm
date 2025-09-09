package jinu.week17;
import java.util.*;
import java.io.*;

public class Week17_QueenAnt {

    static List<Integer> antHouses = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Set<Integer> deleteSet = new HashSet<>();

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        antHouses.add(0);

        int orderCount = Integer.parseInt(st.nextToken());
        for(int i=0;i<orderCount;i++){
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());

            if(type == 100){
                init();
            }
            else if(type == 400){
                binarySearch();
            }
            else if(type == 200){
                int p = Integer.parseInt(st.nextToken());
                antHouses.add(p);
            }
            else if(type == 300){
                int q = Integer.parseInt(st.nextToken());
                deleteSet.add(q);

            }
        }
    }

    public static void init(){
        int n = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            antHouses.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static void binarySearch(){
        int antCount = Integer.parseInt(st.nextToken());
        int low = 0;
        int high = antHouses.get(antHouses.size()-1);

        while(low<=high){
            int mid = (low + high) / 2; // 벌어질 수 있는 최대 간격이라 생각
            int currentPosition = 0;
            int needAntCount = 0;
            boolean isFirst = true;

            for(int i=1;i<antHouses.size();i++){

                if(deleteSet.contains(i)){
                    continue;
                }

                if(isFirst || antHouses.get(i)-currentPosition>mid){
                    needAntCount ++ ;
                    currentPosition = antHouses.get(i);
                    isFirst = false;
                }
            }

            if(needAntCount <= antCount){
                high = mid - 1;
            }
            else if(needAntCount > antCount){
                low = mid + 1;
            }
        }
        System.out.println(high+1);
    }

}
