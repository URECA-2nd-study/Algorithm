package jinu.week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Week16_20008 {

    static int HP;

    static List<int[]> skillInfo;

    static int MIN_TIME = Integer.MAX_VALUE;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        HP = Integer.parseInt(st.nextToken());

        skillInfo = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int coolTime = Integer.parseInt(st.nextToken());
            int damage = Integer.parseInt(st.nextToken());
            skillInfo.add(new int[]{coolTime,damage});
        }

        BT(new ArrayList<>(),0);

        System.out.println(MIN_TIME);

    }

    // 백트래킹으로 먼저 순서를 구한다.
    public static void BT(List<Integer> orders, int sum){
        if(sum>= HP){
            //     System.out.println(orders);
            //   System.out.println("time: "+calculateTimeByOrders(orders));
            MIN_TIME = Math.min(MIN_TIME,calculateTimeByOrders(orders));
            return;
        }

        for(int i=0;i<skillInfo.size();i++){
            orders.add(i);
            BT(orders,sum+skillInfo.get(i)[1]);
            orders.remove((Integer) orders.size()-1);
        }
    }

    public static int calculateTimeByOrders(List<Integer> orders){
        int time = 0;
        Map<Integer,Integer> leftTimeMap = new HashMap<>();

        for(int order : orders){
            leftTimeMap.put(order,0);
        }

        int orderIndex = 0;

        while(true){

            if(orderIndex == orders.size()){
                break;
            }

            int key = orders.get(orderIndex);
            if(leftTimeMap.get(key)>time){
                time++;
                continue;
            }
            leftTimeMap.put(key, time+skillInfo.get(key)[0]);
            orderIndex++;
            time++;
        }

        return time;
    }

}
