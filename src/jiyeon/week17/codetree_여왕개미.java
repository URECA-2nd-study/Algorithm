package week17;

import java.util.*;
import java.io.*;

public class codetree_여왕개미 {

    static ArrayList<Integer> home = new ArrayList<>(); //개미집 좌표
    static ArrayList<Boolean> isDeleted = new ArrayList<>(); //제거되었는지 여부
    static int Q; 

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int command = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        //여왕개미 
        home.add(0);
        isDeleted.add(false);

        for(int i = 0 ; i<N; i++){
            int x = Integer.parseInt(st.nextToken());
            home.add(x);
            isDeleted.add(false);
        }

        for(int i=1; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            command = Integer.parseInt(st.nextToken());

            if(command == 200){
                int p = Integer.parseInt(st.nextToken());
                addHome(p);
            }
            else if(command == 300){
                int q = Integer.parseInt(st.nextToken());
                delHome(q);
            }
            else if(command == 400){
                int r = Integer.parseInt(st.nextToken());
                reconHome(r);
            }
        }


    }

    //개미집 건설
    public static void addHome(int p){
        home.add(p);
        isDeleted.add(false);
    }

    //개미집 철거 
    public static void delHome(int q){
        isDeleted.set(q,true);
    }

    //개미집 정찰
    public static void reconHome(int r){
        // 문제 조건에 따라 이미 정렬되어 있으므로 별도 정렬은 필요 없음
        
        int minTime = 0; 

        int low = 0;
        int high = 1000000000;

        while(low <= high){
            int mid = (low + high) / 2;

            int tempAntNum = 0; 
            int start = -1000000000 ;


            for(int i = 1; i < home.size(); i++){ 

                if(isDeleted.get(i)){
                    continue;
                }

                int cur = home.get(i);
                
                if(cur - start > mid){
                    start = cur;
                    tempAntNum++;
                }
            }

            if(tempAntNum <= r){
                minTime = mid;
                high = mid - 1; 
            } else {
                low = mid + 1;
            }
        }

        System.out.println(minTime);
    }
}