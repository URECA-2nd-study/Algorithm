package jinu.week14;
import java.util.*;
public class Week14_양궁대회 {
    class Solution {

        int maxDiff = -1;
        List<Integer> lionList = new ArrayList<>();


        public int[] solution(int n, int[] info) {

            BT(new ArrayList<>(),n,info);

            //     System.out.println(lionList);
            if(lionList.size()==0){
                return new int[]{-1};
            }

            int answer[] = new int[11];
            for(int i=0;i<lionList.size();i++){
                answer[i] = lionList.get(i);
            }
            return answer;
        }

        // 백트래킹으로 조합을 완성시킴.

        public void BT(List<Integer> lionScoreCombo, int n,int[] apecheInfo){

            if(lionScoreCombo.size()==11){
                // 완성된 조합을 바탕으로 라이언과 어피치 간의 비교를 시작한다.
                compare(apecheInfo,lionScoreCombo);
                return;
            }


            if(lionScoreCombo.size()==10){
                // 마지막 원소에서는 현재의 n값을 넣음으로써 백트래킹으로 만든 리스트의
                // 원소의 총합이 초기 n과 같음을 보장하게 한다.
                lionScoreCombo.add(n);
                BT(lionScoreCombo,0,apecheInfo);
                lionScoreCombo.remove(lionScoreCombo.size()-1);
                return;
            }


            for(int i=0;i<=n;i++){

                lionScoreCombo.add(i);
                BT(lionScoreCombo,n-i,apecheInfo);
                lionScoreCombo.remove(lionScoreCombo.size()-1);
            }

        }

        // apache 값과 현재 lionList값 비교
        public void compare(int []apecheInfo,List<Integer> lionScoreCombo){
            int lionScore = 0;
            int apecheScore = 0;

            for(int i=0;i<11;i++){
                if(apecheInfo[i]<lionScoreCombo.get(i)){
                    lionScore+=(10-i);
                }
                else if(apecheInfo[i]>lionScoreCombo.get(i)){
                    apecheScore+=(10-i);
                }
                // 둘다 0 일 때는 누구도 점수를 가지고 가지 않는다.
                else if(apecheInfo[i]==0 && lionScoreCombo.get(i)==0){
                    continue;
                }
                // 둘이 같은 경우는 어피치가 점수를 받는다.
                else{
                    apecheScore+=(10-i);
                }
            }


            // 라이언 점수가 어피치 점수보다 클 때 그리고
            // 최대 점수 차이가 갱신될 여지가 있을 때 실행
            if(lionScore>apecheScore && maxDiff <= lionScore-apecheScore){

                // 점수 차이가 같고 , 가장 낮은 점수를 많이 맞춘게 아니라면 그냥 종료.
                if(maxDiff == (lionScore-apecheScore) && !compareLow(lionScoreCombo)){
                    return;
                }

                //점수 차이 갱신
                maxDiff = lionScore-apecheScore;

                // 정답 List 갱신
                lionList.clear();
                for(int i=0;i<lionScoreCombo.size();i++){
                    lionList.add(lionScoreCombo.get(i));
                }

            }
        }

        // 가장 낮은 점수를 많이 맞혔다면 true 반환
        public boolean compareLow(List<Integer> lionScoreCombo){
            for(int i=lionScoreCombo.size()-1;i>=0;i--){
                if(lionScoreCombo.get(i)>lionList.get(i)){
                    return true;
                }
                else if(lionScoreCombo.get(i)<lionList.get(i)){
                    return false;
                }
            }

            return false;
        }

    }
}
