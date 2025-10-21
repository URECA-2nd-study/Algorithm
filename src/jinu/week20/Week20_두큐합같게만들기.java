package jinu.week20;
import java.util.*;

public class Week20_두큐합같게만들기 {

    class Solution {
        public int solution(int[] queue1, int[] queue2) {

            long sum1 = 0;
            long sum2 = 0;

            for(int queue: queue1){
                sum1 += queue;
            }

            for (int queue: queue2){
                sum2 += queue;
            }

            if(sum1<=sum2){
                return slidingWindow(queue1, queue2, sum1+sum2,sum1);
            }

            return slidingWindow(queue2, queue1, sum1+sum2, sum2);


        }
        public int slidingWindow(int []queue1, int[] queue2, long sum,long smallSum){

            if(sum % 2 != 0){
                return -1;
            }

            long target = sum / 2;
            long currentSum = smallSum;
            int count = 0;
            Queue<Integer> que1 = new LinkedList<>();
            Queue<Integer> que2 = new LinkedList<>();

            for(int queue: queue1){
                que1.add(queue);
            }
            for(int queue: queue2){
                que2.add(queue);
            }

            while(true){
                if((currentSum == target)){
                    return count;
                }

                if(count == (queue1.length + queue2.length) * 2){
                    break;
                }

                if(currentSum < target){
                    currentSum += que2.peek();
                    que1.add(que2.poll());
                    count++;
                }
                else{
                    currentSum -= que1.peek();
                    que2.add(que1.poll());
                    count++;
                }

            }

            return -1;


        }
    }

}
