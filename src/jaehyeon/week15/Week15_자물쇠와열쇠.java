package src.jaehyeon.week15;

import java.util.ArrayList;
import java.util.List;

public class Week15_자물쇠와열쇠 {

    class Solution {

        int lockEmptyCount;
        int[][] Lock;
        int m;
        int n;

        public boolean solution(int[][] key, int[][] lock) {

            m = key.length;
            n = lock.length;

            Lock = new int[n][n];
            int[][] Key = new int[m][m];

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {

                    if (lock[y][x] == 0) {
                        lockEmptyCount++;
                    }
                    Lock[y][x] = lock[y][x];
                }
            }

            if (lockEmptyCount == 0) {
                return true;
            }

            for (int y = 0; y < m; y++) {
                for (int x = 0; x < m; x++) {
                    Key[y][x] = key[y][x];
                }
            }

            for (int i = 0; i < 4; i++) {
                if (isRightKey(Key)) {
                    return true;
                }

                if (i == 3) {
                    break;
                }

                Key = rotate(Key);
            }

            return false;
        }//solution

        public int[][] rotate(int[][] Key) {

            int[][] temp = new int[m][m];

            for (int y = 0; y < m; y++) {
                for (int x = 0; x < m; x++) {
                    temp[y][x] = Key[m - x - 1][y];
                }
            }

            return temp;
        }

        public boolean isRightKey(int[][] Key) {

            List<int[]> keyPos = new ArrayList<>();

            //열쇠 좌표 추출
            for (int y = 0; y < m; y++) {
                for (int x = 0; x < m; x++) {
                    if (Key[y][x] == 1) {
                        keyPos.add(new int[]{y, x});
                    }
                }
            }

            //자물쇠 탐색 시작
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (Lock[y][x] == 1) {
                        continue;
                    }

                    for (int[] pos : keyPos) {
                        int dy = pos[0];
                        int dx = pos[1];

                        boolean flag = true;

                        //키가 아물린 수
                        int rightCount = 1;

                        for (int[] pos2 : keyPos) {
                            int nextY = pos2[0] - dy;
                            int nextX = pos2[1] - dx;

                            if (nextY == 0 && nextX == 0) {
                                continue;
                            }

                            int keyPosOnLockY = y + nextY;
                            int keyPosOnLockX = x + nextX;

                            // 자물쇠 바깥 영역은 무시
                            if (isOutOfRange(keyPosOnLockY, keyPosOnLockX)) {
                                continue;
                            }

                            if (Lock[keyPosOnLockY][keyPosOnLockX] == 1) {
                                flag = false;
                                break;
                            }

                            rightCount++;
                        }
                        if (flag && rightCount == lockEmptyCount) {
                            return true;
                        }
                    }
                }
            }

            return false;

        }//isRightKey

        public boolean isOutOfRange(int y, int x) {
            return y < 0 || x < 0 || y >= n || x >= n;
        }
    }
}
