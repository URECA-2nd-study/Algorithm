package jinu.week14;
import java.util.*;
public class Week14_nplus1카드게임 {

    class Solution {
        public int solution(int coin, int[] cards) {

            int answer = 0;

            // 내가 가지고 있는 myCard 집합.
            Set<Integer> mCard = new HashSet<>();

            //내가 뽑은 newCard 집합
            Set<Integer> nCard = new HashSet<>();

            // 처음 부터 n/3 만큼 myCard 에 저장
            for(int i=0;i<(cards.length/3);i++){
                mCard.add(cards[i]);
            }

            int index = cards.length/3;

            // 만족시켜야 하는 총 합은 n+1
            int totalSum = cards.length+1;

            //목표: 일단 뽑은 카드는 mCard 에 저장하고
            // 필요할 때마다 mCard 에서 코인을 소모해서 쓰는 것이 문제의 의도 .
            while(true){
                answer++;
                if(index >= cards.length){
                    break;
                }
                nCard.add(cards[index]);
                nCard.add(cards[index+1]);
                index+=2;

                // 최대한 먼저 이미 가지고 있는 것으로 (N+1) 을 만족시키는 것을 찾는다.
                boolean alreadyProceed = false;
                for(int myCardNumber: mCard){
                    if(mCard.contains(totalSum-myCardNumber)){
                        mCard.remove(myCardNumber);
                        mCard.remove(totalSum-myCardNumber);
                        alreadyProceed = true;
                        break;
                    }
                }

                // 코인이 한개 이상이고 이전 과정을 수행하지 않았다면 코인 한개를 사용해서
                // 다음 단계로 진행한다.
                if(coin>=1){
                    if(!alreadyProceed){
                        for(int myCardNumber: mCard){
                            if(nCard.contains(totalSum-myCardNumber)){
                                nCard.remove(totalSum-myCardNumber);
                                mCard.remove(myCardNumber);
                                alreadyProceed = true;
                                coin--;
                                break;
                            }
                        }
                    }
                }

                // 코인이 2개이상이고 이전 과정을 수행하지 않았다면 최후의 보루로 코인 2개로 다음 단계로 진행한다.
                if(coin>=2){
                    if(!alreadyProceed){
                        for(int newCard: nCard){
                            if(nCard.contains(totalSum-newCard)){
                                nCard.remove(newCard);
                                nCard.remove(totalSum-newCard);
                                alreadyProceed = true;
                                coin -=2;
                                break;
                            }
                        }
                    }
                }


                // 코인 0개, 1개 , 2개 모두 다 사용했는데 다음 단계로 진행하지 못했다면
                // 여기가 마지막 지점
                if(!alreadyProceed){
                    break;
                }

            }

            return answer;

        }
    }
//어려웠던 점: 단계별로 차례대로 생각하다 보니 방법이 없어 보였다.
// 필요할 때 그 때 가지고 오는 것을 보니까..
// 아 이걸 어떻게 생각하지를 느꼈다... 뭐가 필요할까? 경험?

}
