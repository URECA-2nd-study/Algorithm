package jinu.week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Week10_3192 {

    static int square[][];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 3 X 3 크기의 정사각형
        square = new int[3][3];
        List<int[]> empty = new ArrayList<>();

        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                square[i][j] = Integer.parseInt(st.nextToken());
                // 빈값일 때 List에 채워넣는다.
                if(square[i][j]==0){
                    empty.add(new int[]{i,j});
                }
            }
        }

        // 빈칸의 형태를 기준으로 삼아 3개의 수의 합을 구한다.
        int sum = getSum(empty);
        fillAndPrint(empty,sum);








    }


    // empty(0이 채워진 ) 의 정보를 바탕으로 숫자를 채우고 출력하는 메서드
    public static void fillAndPrint(List<int[]> empty,int sum){

        List<int[]> numberInfo = new ArrayList<>();

        // numberInfo 의 size 와 빈칸의 size를 같게 만들 때까지 반복문을 돌립니다.
        // 반복을 하는 이유는 0 0 이 연속할 떄 값을 구할수도 없다고 생각했기 때문이당.
        while(numberInfo.size()!=empty.size()){

            // 빈 position
            for(int position[] : empty){

                boolean ok = true;
                int tmpSum = 0;
                for(int j=0;j<3;j++){
                    if(position[1]==j) continue;

                    tmpSum += square[position[0]][j];
                    // 또 값이 0 인 것이 나오면 false로 하여 판정 불가
                    if(square[position[0]][j]==0){
                        ok = false;
                        break;
                    }
                }

                //판정 가능 시
                if(ok){
                    numberInfo.add(new int[]{position[0],position[1],sum-tmpSum});
                    continue;
                }

                ok = true;
                tmpSum = 0;
                for(int i=0;i<3;i++){
                    if(position[0]==i) continue;

                    tmpSum += square[i][position[1]];
                    if(square[i][position[1]]==0){
                        ok = false;
                        break;
                    }
                }

                if(ok){
                    numberInfo.add(new int[]{position[0],position[1],sum-tmpSum});
                }


            }

        }


        // 빈칸을 값으로 채운다.
        for(int []emptyWithValue: numberInfo){
            square[emptyWithValue[0]][emptyWithValue[1]] = emptyWithValue[2];
        }

        // 출력한다.
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(square[i][j]+" ");
            }
            System.out.println();
        }
    }

    // 3개의 합을 구하는 메서드
    public static int getSum(List<int[]> empty){

        List<Integer> y = new ArrayList<>(Arrays.asList(0,1,2));
        List<Integer> x = new ArrayList<>(Arrays.asList(0,1,2));

        for(int emptyPosition[] : empty){
            y.remove(Integer.valueOf(emptyPosition[0]));
            x.remove(Integer.valueOf(emptyPosition[1]));
        }

        // y에 남아있는 값의 의미: y에 k가 남아있다면(k=0,1,2 중 하나) 그 행은 3자리 수를 온전피 파악할 수 있다.
        // x에 남아있는 값의 의미: x에 k가 남아있다면(k=0,1,2 중 하나) 그 열은 3자리 수를 온전히 파악할 수 있다.


        // 남아있는 값이 없다면 ...
        if(x.size()==0 && y.size()==0){
            // 대각선 합
            int daeGakSum = getDaeGakSum();
            // 대각선이 온전한 형태(대각선에 0이 없다) 를 띄는 경우는 3개의 합을 대각선의 합으로 반환한다.
            if(daeGakSum!=-1){
                return daeGakSum;
            }
            // 대각선의 형태로 3개의 0 이 연속하는 경우는 전체 배열 원소의 합 / 2 를 반환한다.
            else{
                return getTotalSum()/2;
            }

        }
        // 남아있는 값을 기준으로 sum 을 반환합니다. 남아있는 값은 그 행 또는 열이 0의 형태가 없는 온전한 형태이다. 라는 의미 .
        else{

            if(x.size()==0){
                int targetRow = y.get(0);
                int sum = 0;
                for(int j=0;j<3;j++){
                    sum += square[targetRow][j];
                }
                return sum;
            }

            int targetColumn = x.get(0);
            int sum = 0;
            for(int i=0;i<3;i++){
                sum += square[i][targetColumn];
            }
            return sum;

        }

    }

    public static int getTotalSum(){
        int sum = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                sum+=square[i][j];
            }
        }
        return sum;
    }

    //- 1 return 시 대각 형태가 이루어지지 않는다.
    // leftSum 은 왼쪽 상단에서 오른쪽 하단으로 이어지는 대각선의 합을 의미
    // righSum 은 오른쪽상단에서 왼쪽 하단으로 이어지는 대각선의 합을 의미
    public static int getDaeGakSum(){

        boolean leftDaeGak = true;
        int leftSum = 0;
        boolean rightDaeGak = true;
        int rightSum = 0;

        for(int i=0;i<3;i++){
            leftSum+=square[i][i];
            rightSum+=square[i][2-i];
            if(square[i][i]==0){
                leftDaeGak = false;
            }
            if(square[i][2-i]==0){
                rightDaeGak = false;
            }
        }

        if(leftDaeGak){
            return leftSum;
        }
        else if(rightDaeGak){
            return rightSum;
        }
        else{
            return -1;
        }




    }

}
