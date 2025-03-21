package jinu.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week7_17940 {

    // 정렬 시 환승 횟수가 가장 적은 것 , 환승 횟수가 같다면 시간 비용이 작은 것 선택
    static class Node implements Comparable<Node> {
        private int stationNumber;

        private int timeCost;
        private int transferCount;

        public Node(int stationNumber,int timeCost,int transferCount){
            this.stationNumber = stationNumber;
            this.timeCost = timeCost;
            this.transferCount = transferCount;
        }


        @Override
        public int compareTo(Node n) {

            if(this.transferCount == n.transferCount){
                return this.timeCost-n.timeCost;
            }

            return this.transferCount - n.transferCount;
        }
    }

    public static class Station {

        private int stationNumber;
        private int timeCost;

        public Station(int stationNumber, int timeCost) {
            this.stationNumber = stationNumber;
            this.timeCost = timeCost;
        }
    }




    static List<Station> graph[];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        // 배열 초기화
        int stationCompanyInfo[] = new int[N];
        boolean isVisited[] = new boolean[N];
        graph = new List[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int cInfo = Integer.parseInt(st.nextToken());
            stationCompanyInfo[i] = cInfo;
        }


        // 출발점, 도착점 ,비용을 가진 그래프 형성
        for(int i=0;i<N;i++){
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for(int dest=0;dest<N;dest++){

                int timeCost = Integer.parseInt(st.nextToken());
                if(timeCost!=0){
                    graph[i].add(new Station(dest,timeCost));
                }
            }
        }



        // 환승 횟수가 가장 적은 것, 시간 비용이 가장 적은 것 부터 차례대로 꺼냄.
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,0));



        // dist[i] : 출발점 부터 i 까지 갔을 때 최소 시간비용 , transferNum[i] : 출발점 부터 i까지 갔을 때 최소 환승 횟수
        int dist[] = new int[N];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = 0;
        int transferNum[] = new int[N];
        Arrays.fill(transferNum,Integer.MAX_VALUE);
        transferNum[0] = 0;




        while(!pq.isEmpty()){

            Node n = pq.poll();
            if(isVisited[n.stationNumber]){
                continue;
            }

            isVisited[n.stationNumber] = true;

            // n의 stationNumber 에서 연결되는 Station s
            for(Station s : graph[n.stationNumber]){

                // 기존 역과 같은 회사에서 관리된다면 changeCount = 0 아니면 1
                int changeCount = stationCompanyInfo[n.stationNumber]==stationCompanyInfo[s.stationNumber]?0:1;

                // 갱신 조건: 환승횟수가 갱신되거나, 환승 횟수가 갱신되지 않아도 시간비용이 갱신되거나
                if(transferNum[s.stationNumber]>transferNum[n.stationNumber]+changeCount ||
                        ((transferNum[s.stationNumber]==transferNum[n.stationNumber] + changeCount) && (dist[s.stationNumber]>dist[n.stationNumber]+s.timeCost))){

                    if(transferNum[s.stationNumber]>transferNum[n.stationNumber]+changeCount){
                        transferNum[s.stationNumber] = transferNum[n.stationNumber] + changeCount;
                    }

                    dist[s.stationNumber] = dist[n.stationNumber]+s.timeCost;

                    pq.add(new Node(s.stationNumber,dist[s.stationNumber],transferNum[s.stationNumber]));
                }
            }



        }

        System.out.println(transferNum[M]+" "+dist[M]);




    }
}
