package yeoeun.week20;

public class Week_20_파괴되지_않은_건물 {
    public int solution(int[][] board, int[][] skill) {
        int[][] sum = new int[board.length + 1][board[0].length + 1];

        // 누적합 설정
        for(int[] sk : skill) {
            int d = sk[0] == 1 ? sk[5] * -1 : sk[5];
            sum[sk[1]][sk[2]] += d;
            sum[sk[1]][sk[4] + 1] -= d;
            sum[sk[3] + 1][sk[2]] -= d;
            sum[sk[3] + 1][sk[4] + 1] += d;
        }

        // 세로 누적합
        for(int i = 0; i < sum[0].length; i++) {
            for(int j = 0; j < sum.length - 1; j++) {
                sum[j+1][i] += sum[j][i];
            }
        }

        // 가로 누적합
        for(int i = 0; i < sum.length; i++) {
            for(int j = 0; j < sum[0].length - 1; j++) {
                sum[i][j+1] += sum[i][j];
            }
        }

        // 결과 계산
        int left = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] + sum[i][j] > 0) left++;
            }
        }
        return left;
    }
}
