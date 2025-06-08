package jinu.week15;
import java.util.*;

public class Week15_순위검색 {

    class Volunteer{
        String language;
        String applyPart;
        String career;
        String soulFood;
        int score;

        public Volunteer(String language,String applyPart,String career,String soulFood,String score){
            this.language = language;
            this.applyPart = applyPart;
            this.career = career;
            this.soulFood = soulFood;
            this.score = Integer.valueOf(score);
        }

        public String getColumnByOrder(int order){
            if(order == 0){
                return language;
            }
            else if(order ==1){
                return applyPart;
            }
            else if(order ==2){
                return career;
            }
            else if(order == 3){
                return soulFood;
            }

            return null;
        }

    }
    class Volunteers{

        // String 형태: javabacekdjuniorpizza ,
        // value : 3,4,6,8...
        private Map<String,List<Integer>> map = new HashMap<>();

        public Volunteers(){

        }

        // 지원자 추가.
        public void addVolunteer(Volunteer v){

            List<String> keyList = new ArrayList<>();
            makeKeyString(0,"",v,keyList);

            keyList.forEach(key->{

                if(!this.map.containsKey(key)){
                    this.map.put(key,new ArrayList<>());
                }

                this.map.get(key).add(v.score);

            });

        }

        // 재귀적으로 될 수 있는 KeyList를 가지고 오는 것이 목표:
        // javabackendjuniorpizza 를 key로 한다면
        // -backend-pizza 도 포함이 되어야 한다.
        // 등등의 KeyString의 List를 가지고 오는 메소드.
        private void makeKeyString(int count,String sentence,Volunteer v,List<String> keyString){

            if(count==4){
                keyString.add(sentence);
                return;
            }

            makeKeyString(count+1,sentence+v.getColumnByOrder(count),v,keyString);
            if(!v.getColumnByOrder(count).equals("-")){
                makeKeyString(count+1,sentence+"-",v,keyString);
            }

        }

        // 각 Key마다 포함되어 있는 점수 리스트를 정렬
        public void sortKeyByKey(){
            for(String key: map.keySet()){
                Collections.sort(map.get(key));
            }
        }

        // 쿼리를 바탕으로 몇 명의 인원이 있는지 파악 .
        public int getCountByQuery(String query){
            String queryInfo[] = query.split(" and ");
            String QLanguage = queryInfo[0];
            String QApplyPart = queryInfo[1];
            String QCareer = queryInfo[2];
            String QSoulFood = queryInfo[3].split(" ")[0];
            int QScore = Integer.valueOf(queryInfo[3].split(" ")[1]);

            String key = QLanguage+QApplyPart+QCareer+QSoulFood;

            if(!this.map.containsKey(key)){
                return 0;
            }

            return binarySearch(key,QScore);

        }

        // 이분 탐색을 바탕으로 몇명의 인원이 key를 만족하는지 파악.
        private int binarySearch(String key,int QScore){


            List<Integer> list = map.get(key);

            int start = 0;
            int end = list.size()-1;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (list.get(mid) < QScore)
                    start = mid + 1;
                else
                    end = mid - 1;
            }

            return list.size() - start;
        }

    }

    class Solution {
        public int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            Volunteers volunteers = new Volunteers();

            for(int i=0;i<info.length;i++){
                String []uInfo = info[i].split(" ");
                Volunteer v = new Volunteer(uInfo[0],uInfo[1],uInfo[2],uInfo[3],uInfo[4]);
                volunteers.addVolunteer(v);
            }

            volunteers.sortKeyByKey();

            for(int i=0;i<query.length;i++){
                answer[i] = volunteers.getCountByQuery(query[i]);
            }

            return answer;
        }
    }

}
