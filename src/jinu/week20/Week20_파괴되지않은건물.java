package jinu.week20;

public class Week20_파괴되지않은건물 {

    class Solution {
        public int solution(int[][] board, int[][] skill) {

            int sum[][] = new int[board.length+1][board[0].length+1];



            for(int i =0; i< skill.length; i++){
                int type = skill[i][0];
                int r1 = skill[i][1];
                int c1 = skill[i][2];
                int r2 = skill[i][3];
                int c2 = skill[i][4];
                int degree = skill[i][5];

                if(type == 1){
                    // attack(board, r1, c1, r2, c2, degree);
                    sum[r1][c1] -= degree;
                    sum[r2+1][c2+1] -= degree;
                    sum[r1][c2+1] += degree;
                    sum[r2+1][c1] += degree;
                    continue;
                }
                sum[r1][c1] += degree;
                sum[r2+1][c2+1] += degree;
                sum[r1][c2+1] -= degree;
                sum[r2+1][c1] -= degree;
                //  heal(board, r1, c1, r2, c2, degree);

            }


            adjustSum(sum);


            return getAnswerBySumAndBoard(sum,board);
        }

        public void attack(int [][]board, int r1,int c1, int r2, int c2,int degree){
            for(int i = r1; i<= r2; i++){
                for(int j = c1; j<=c2; j++){
                    board[i][j] -= degree;
                }
            }
        }
        public void heal(int [][]board, int r1,int c1, int r2, int c2,int degree){
            for(int i = r1; i<= r2; i++){
                for(int j = c1; j<=c2; j++){
                    board[i][j] += degree;
                }
            }
        }

        public void adjustSum(int [][]sum){

            for(int i = 1; i<sum.length; i++){
                for(int j=0;j < sum[0].length; j++){
                    sum[i][j] += sum[i-1][j];
                }
            }

            for(int j = 1; j<sum[0].length; j++){
                for(int i =0; i<sum.length; i++){
                    sum[i][j] += sum[i][j-1];
                }
            }
        }

        public int getAnswerBySumAndBoard(int [][]sum, int [][]board){
            int answer = 0;
            for(int i= 0;i<board.length; i++){
                for(int j = 0; j< board[0].length; j++){
                    if(board[i][j]+sum[i][j]>0){
                        answer ++;
                    }
                }
            }
            return answer;
        }


    }

}
