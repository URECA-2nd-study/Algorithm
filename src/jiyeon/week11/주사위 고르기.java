package jiyeon.week11;
import java.util.*;

class Solution {
    
    static int n,groupSize,max=-1;
    static int[] nums;
    static ArrayList<Integer> ans; 
    static ArrayList<ArrayList<Integer>> selectedA = new ArrayList<>(); //A가 선택할 주사위의 조합
    static ArrayList<ArrayList<Integer>> selectedB = new ArrayList<>(); //B가 선택할 주사위의 조합
    static ArrayList<ArrayList<ArrayList<Integer>>> zipped = new ArrayList<>(); 
    //주사위 숫자 압축 결과 저장 zipped.get(diceNum(#N)) =  [주사위 숫자, 나온 횟수]
    static HashMap<Integer,Integer> sumA = new HashMap<>(); //key : sum, value : count 
    static HashMap<Integer,Integer> sumB = new HashMap<>();
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        nums = new int[n-1];
        groupSize = n/2; //가져갈 주사위 그룹의 주사위 개수 
        ArrayList<Integer> temp = new ArrayList<>(); //조합 임시 저장용
        
        for(int i=2; i<=n; i++){
            nums[i-2] = i;
        }
        
        for(int [] d : dice){
            Arrays.sort(d); //주사위에 나오는 동일한 값을 압축하기 위해 정렬
        }
        
        zip(dice);
        
        //A가 가져가는 주사위의 그룹 구하기 
        combi(0,0,temp);
        
        for(ArrayList<Integer> a : selectedA){
            a.add(0,1); // 모든 그룹에 1 추가 
            ArrayList<Integer> b = getB(a);
            ArrayList<ArrayList<ArrayList<Integer>>> selectedZippedA = new ArrayList<>(); 
            for(int diceNum : a){
                selectedZippedA.add(zipped.get(diceNum-1));
            }
            ArrayList<ArrayList<ArrayList<Integer>>> selectedZippedB= new ArrayList<>(); 
            for(int diceNum : b){
                selectedZippedB.add(zipped.get(diceNum-1));
            }
            
            combi2(0,0,1,selectedZippedA,sumA); //A 합구하기 
            combi2(0,0,1,selectedZippedB,sumB); //B 합구하기 
            
            //A,B 합 비교하여 A 승리횟수 구하기 
            getAwin(a,b);
            
            sumA.clear();
            sumB.clear();
            
        }//for 
        
        int [] answer = new int[groupSize];
        for(int i=0; i<groupSize; i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    } //main
    
    //A,B 합 비교하여 A의 승리횟수 구하는 메서드
    public void getAwin(ArrayList<Integer> a, ArrayList<Integer> b){

        int winNum = 0;
        int lossNum = 0;
        
        //hash map끼리 비교 
        for(int as : sumA.keySet()){
            for(int bs : sumB.keySet()){
                if(as > bs){
                    winNum += sumA.get(as)*sumB.get(bs);
                }
                else if(as < bs){
                    lossNum += sumA.get(as)*sumB.get(bs);
                }
            }
        }

        //제일 승률이 높은 주사위 고르기 
        if(max<winNum){
            max = winNum;
            ans = new ArrayList<>(a);
        }
        
        if(max<lossNum){
            max = lossNum;
            ans = new ArrayList<>(b);
        }
        
    }//getAwin
    
    //뽑은 주사위의 합, 카운트 계산하는 메서드
    public void combi2(int index,int sum, int cnt, ArrayList<ArrayList<ArrayList<Integer>>> selectedZipped, HashMap<Integer,Integer> sumMap){
        if(index == selectedZipped.size()){
            sumMap.put(sum, sumMap.getOrDefault(sum,0)+cnt);
            return;
        }
        
        for(ArrayList<Integer> selected : selectedZipped.get(index)){
            int n = selected.get(0);
            int c = selected.get(1);
            
            combi2(index+1, sum+n, cnt*c, selectedZipped, sumMap);
            
        }
        
    }//combi2
    
    //selectedB 구하는 메서드 
    public ArrayList<Integer> getB(ArrayList<Integer> a){
        ArrayList<Integer> full = new ArrayList<>();
        for(int i=1; i<=n; i++){
            full.add(i);
        }
        full.removeAll(a);
        
        return full; 
    }
    
    
    //A가 가져가는 주사위 조합 구하는 메소드 (주사위 1을 포함하는 조합만 구하기)
    public void combi(int start, int index, ArrayList<Integer> temp){
        if(index == groupSize-1){
            selectedA.add(new ArrayList<>(temp));
        }
        
        for(int i=start; i<nums.length; i++){
            temp.add(nums[i]);
            combi(i+1, index+1, temp);
            temp.remove(temp.size() -1);
        }
        
        
    }//combi 
    
    //주사위마다 동일한 숫자 압축시켜서 저장 
    public void zip(int [][] dice){
        
        for(int i=0; i<n; i++){
            int temp = dice[i][0];
            int count = 0;
            zipped.add(new ArrayList<ArrayList<Integer>>());
            for(int j=0; j<6; j++){
                if(temp == dice[i][j]){
                    count++;
                }
                else{
                    zipped.get(i).add(new ArrayList<>(Arrays.asList(temp,count)));
                    temp = dice[i][j];
                    count = 1;
                }
            }
            zipped.get(i).add(new ArrayList<>(Arrays.asList(temp,count))); //마지막으로 나온 숫자 저장
        }
        
    }//zip
}

//1. A가 가져가는 주사위 조합을 구하기 모두 구하면 효율적이지 않으므로 대칭의 원리를 이용해서 절반만 O 
//2. 주사위마다 동일한 숫자는 압축시켜서 계산횟수 줄이기 [1,1,1,1,1,1] => [1,6]으로 표현 O
//3. A, B 합을 비교해서 승리 횟수 구하기 