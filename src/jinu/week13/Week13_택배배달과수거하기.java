package jinu.week13;
import java.util.*;

public class Week13_택배배달과수거하기 {

    class Solution {

        public class DeliverHouse{
            private int number;
            private int leftDeliver;

            public DeliverHouse(int number,int leftDeliver){
                this.number = number;
                this.leftDeliver = leftDeliver;
            }

        }
        public class LeftHouse{
            private int number;
            private int leftPickUp;

            public LeftHouse(int number,int leftPickUp){
                this.number = number;
                this.leftPickUp = leftPickUp;
            }

        }
        public long solution(int cap, int n, int[] deliveries, int[] pickups) throws InterruptedException {

            // Stack 2개 관리 : 배달용 Stack, 수거용 Stack
            Stack<DeliverHouse> deliverStack = new Stack<>();
            Stack<LeftHouse> leftStack = new Stack<>();

            // 총 배달 갯수 , 총 수거 개수 관리
            long totalDeliveryCount =0;
            long totalLeftCount = 0;

            for(int i=0;i<n;i++){
                if(deliveries[i]==0 && pickups[i]==0){
                    continue;
                }
                totalDeliveryCount += deliveries[i];
                totalLeftCount += pickups[i];
                if(deliveries[i]>0){
                    deliverStack.add(new DeliverHouse(i+1,deliveries[i]));
                }
                if(pickups[i]>0){
                    leftStack.add(new LeftHouse(i+1,pickups[i]));
                }
            }

            long count = 0;

            // stack 이 빌 때 까지
            while(!deliverStack.isEmpty() || !leftStack.isEmpty()){

                // 현재 가지고 가는 택배 개수는: cap 과 배달 남은 갯수의 최솟값
                long currentDeliverCount = Math.min(cap,totalDeliveryCount);

                // 현재 수거하는 택배 개수는 cap과 남은 수거 개수의 최솟값
                long currentPickUpCount = Math.min(cap,totalLeftCount);

                int deliverDistance = deliverStack.isEmpty()?1:deliverStack.peek().number;
                int leftDistance = leftStack.isEmpty()?1:leftStack.peek().number;

                // 왔다갔다 하는 비용은 거리의 최대 * 2
                count+=Math.max(deliverDistance,leftDistance) * 2;

                // 현재 가지고 가는 택배 개수가 0개가 될 때까지
                while(currentDeliverCount!=0){
                    if(deliverStack.isEmpty()){
                        break;
                    }
                    DeliverHouse h = deliverStack.pop();

                    if(h.leftDeliver>0){
                        // h에 필요한 모든 배달을 진행하였다면
                        if(currentDeliverCount>h.leftDeliver){
                            currentDeliverCount -= h.leftDeliver;
                            totalDeliveryCount -= h.leftDeliver;
                            h.leftDeliver = 0;
                        }
                        else{
                            h.leftDeliver -= currentDeliverCount;
                            totalDeliveryCount -= currentDeliverCount;
                            currentDeliverCount=0;
                            // 만일 h에서 모두 배달하지 못했다면 다시 stack 에 저장.
                            if(h.leftDeliver!=0){
                                deliverStack.add(h);
                            }
                        }
                    }

                }

                // 현재 수거하는 택배 개수가 0개가 될 때까지
                while(currentPickUpCount!=0){
                    if(leftStack.isEmpty()){
                        break;
                    }
                    LeftHouse h = leftStack.pop();

                    if(h.leftPickUp>0){
                        // h에 필요한 모든 수거하였다면
                        if(currentPickUpCount>h.leftPickUp){
                            currentPickUpCount -= h.leftPickUp;
                            totalLeftCount -= h.leftPickUp;
                            h.leftPickUp = 0;
                        }
                        else{
                            h.leftPickUp -= currentPickUpCount;
                            totalLeftCount -= currentPickUpCount;
                            currentPickUpCount = 0;
                            // 만일 h에서 모두 수거하지 못했다면 다시 stack 에 저장
                            if(h.leftPickUp!=0){
                                leftStack.add(h);
                            }
                        }

                    }

                }

            }

            return count;
        }


    }
}
