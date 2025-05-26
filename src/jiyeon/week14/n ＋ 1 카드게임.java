/*
그리디 
우선순위 1 : 처음에 뽑은 카드 중에서 두 장 내기 
우선 순위 2 : 처음에 뽑은 카드 중에서 한 장 내기 + 새로 뽑은 카드 중에서 한 장 내기 (- 코인 1)
우선 순위 3 : 새로 뽑은 카드 중에서 두 장 내기 (- 2코인)

주의)) 당장 코인을 내고 가지지 않는 카드라고 해도 미래의 턴을 위해 미리 살 수도 있기 때문에 후보 덱에 포함시켜야함 

코인이 < 0 || 가진 카드(처음에 뽑은 카드 + 새로 뽑은 카드) 중에서 낼 수 없을 때 까지 반복

*/

import java.util.*;

class Solution {
    
    static ArrayList<Integer> deck = new ArrayList<>(); //처음에 무료로 뽑은 카드 
    static ArrayList<Integer> candi = new ArrayList<>(); //새로 뽑은 카드 (== 뽑을 수 있는 카드 후보)
    static int n = -1;
    
    public int solution(int coin, int[] cards) {
        int answer = 1;
        
        //n 찾기 
        for(int card : cards ){
            n = Math.max(n,card);
        }
        
        //처음 무료뽑기를 deck 리스트에 add 
        for(int i=0; i<n/3; i++){
            deck.add(cards[i]);
        }
        
        
        for(int i=n/3; i<n-1; i+=2){
            int c1 = cards[i];
            int c2 = cards[i+1];
            
            candi.add(c1);
            candi.add(c2);
            
            if(deck.isEmpty() && candi.isEmpty()){
                break;
            }
            
            //우선순위 1
            if(prior1()){
            }
            else if(coin>0 && prior2and3(deck,candi)){//우선순위 2
                coin --; 
            }
            else if(coin>=2 && prior2and3(candi,candi)){//우선순위 3
                coin -= 2;
            }
            else{
                break;
            }
            
            answer++;
            
        }
        
        
        return answer;
    }//main 
    
    //우선순위 1
    static boolean prior1(){
        ArrayList <Integer> copy = new ArrayList<>(deck);
        for(int card : copy){
            if(copy.contains(n+1-card)){
                deck.remove(Integer.valueOf(card));
                deck.remove(Integer.valueOf(n+1-card));
                return true;
            }
        }
        return false;
    }//prior1 
    
    static boolean prior2and3(ArrayList<Integer> list1, ArrayList<Integer> list2){
        ArrayList<Integer> copy1 = new ArrayList<>(list1);
        ArrayList<Integer> copy2 = new ArrayList<>(list2);
        
        for(int card : copy1){
            if(copy2.contains(n+1-card)){
                deck.remove(Integer.valueOf(card));
                candi.remove(Integer.valueOf(n+1-card));
                return true;
            }
        }
        return false; 
    }//prior2and3
    
}