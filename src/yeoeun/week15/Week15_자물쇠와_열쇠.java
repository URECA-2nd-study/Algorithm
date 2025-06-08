package yeoeun.week15;

public class Week15_자물쇠와_열쇠 {
    static int N, M;
    static int[][] slock;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        // 전역변수화
        N = lock.length;
        M = key.length;
        slock = lock;

        // 정답 확인
        for(int i = 0; i < 4; i++) {
            answer = checkAnswer(key);
            if(answer) break;
            key = rotate(key); // 현재 키 돌려서 확인
        }
        return answer;
    }

    // key 회전
    public int[][] rotate (int[][] arr) {
        int[][] rotate = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                rotate[j][arr.length - i - 1] = arr[i][j];
            }
        }
        return rotate;
    }

    // 각 key로 자물쇠 풀 수 있는 지 검증
    public boolean checkAnswer(int[][] key) {
        for(int i = 0; i < N+M-1; i++) {
            for(int j = 0; j < N+M-1; j++) {
                // KEY 설정
                int[][] bigKey = getKey(key, i, j);

                // lock 확인
                if(checkLock(bigKey)) return true;
            }
        }
        return false;
    }

    // 원하는 위치에 key 배치
    public int[][] getKey(int[][] key, int i, int j) {
        int[][] bigKey = new int[N+(M-1)*2][N+(M-1)*2];
        for(int k = 0; k < M; k++) {
            for(int l = 0; l < M; l++) {
                bigKey[i+k][j+l] = key[k][l];
            }
        }
        return bigKey;
    }

    // key로 lock 풀 수 있는 지 검증
    public boolean checkLock(int[][] bigKey) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(slock[i][j] + bigKey[i+M-1][j+M-1] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
