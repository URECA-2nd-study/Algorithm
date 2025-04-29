class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        
        int answer = getCount(h2,m2,s2) - getCount(h1,m1,s1);
        
        if((h1==0 || h1==12) && m1 == 0 && s1 == 0){
            answer ++;
        }
        
        return answer;
    
    }//main 
    
    //정각 기준 시침과 분침이 초침과 만나는 횟수 구하는 메서드 
    public int getCount(int h, int m, int s){
        int cnt = 0;
        cnt += (h*60 + m) * 2; //기본적으로 1분 당 2번 만난다. 
        
        /* X시 59분 00초부터 59분 59초까지는 절대 분침과 초침이 만나지 않음 
        앞서 1분당 횟수를 계산했기 때문에 분침과 초침이 만나지 않는 경우는 빼준다. 
        */
        cnt -= h; 
        
        //이 함수는 정각 기준이기 때문에 h가 12 이상이라는 것은 11시 59분 -> 12시가 발생했다는 뜻 
        if(h>=12){
            cnt -= 2; 
            // 11시 59분 -> 12시 0분 이 1분간은 시, 분, 초침이 만나지 않고 12시 정각에 딱 정확히 한 번 만나기 때문에 -2
        }
        
        //분 단위로 체크하였기 때문에 마지막 시점의 초침의 위치와, 시/분침 비교
        double [] d = getDegree(h,m,s);
        if(d[2] >= d[0]){
            cnt ++;
        }
        
        if(d[2] >= d[1]){
            cnt ++;
        }
        
        return cnt;
    }
    
    //시,분,초의 각도 구하는 메서드
    public double [] getDegree (int h, int m, int s){
        double hDegree = (h*30 + m*0.5 + (s*0.5)/60) % 360;
        double mDegree = (m*6 + s*0.1)%360;
        double sDegree = s*6;
        
        return new double[] {hDegree, mDegree, sDegree};
    }
}