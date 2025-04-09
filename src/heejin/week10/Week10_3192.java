package heejin.week10;

import java.io.*;
import java.util.*;

public class Week10_3192 {
    static int[][] map = new int[3][3];
    static int sum = 0;
    static boolean hasZero;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum += map[i][j];
            }
        }
        sum /= 2; // 0이 3개일 때


        // 0이 없는 가로 찾아서 합 구하기
        for (int i = 0; i < 3; i++) {
            hasZero = false;
            int tempSum = 0;

            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 0) {
                    hasZero = true;
                    break;
                }

                tempSum += map[i][j];
            }

            if(!hasZero){
                sum = tempSum;
                break;
            }
        }

        // 0이 없는 세로 찾아서 합 구하기
        if(hasZero){
            for (int j = 0; j < 3; j++) {
                hasZero = false;
                int tempSum = 0;

                for (int i = 0; i < 3; i++) {
                    if (map[i][j] == 0) {
                        hasZero = true;
                        break;
                    }

                    tempSum += map[i][j];
                }

                if(!hasZero){
                    sum = tempSum;
                    break;
                }
            }
        }

        // 0이 없는 좌상향 대각선 찾아서 합 구하기
        if(hasZero){
            int i=0, j=0;
            hasZero = false;
            int tempSum = 0;

            while(i < 3 && j < 3){
                if(map[i][j] == 0){
                    hasZero = true;
                    break;
                }

                tempSum += map[i][j];
                i++;
                j++;
            }

            if(!hasZero){
                sum = tempSum;
            }
        }

        // 0이 없는 우상향 대각선 찾아서 합 구하기
        if(hasZero){
            int i=0, j=2;
            hasZero = false;
            int tempSum = 0;

            while(i < 3 && j >= 0){
                if(map[i][j] == 0){
                    hasZero = true;
                    break;
                }

                tempSum += map[i][j];
                i++;
                j--;
            }

            if(!hasZero){
                sum = tempSum;
            }
        }

        // 지워진 숫자 행 기준 채우기
        for (int i = 0; i < 3; i++) {
            int zeroCnt = 0;
            int zeroIdx = -1;
            int tempSum = 0;

            for (int j = 0; j < 3; j++) {
                tempSum += map[i][j];

                if (map[i][j] == 0) {
                    zeroCnt++;
                    zeroIdx = j;
                }
            }

            // 0이 하나면, 값 채우기
            if (zeroCnt == 1) {
                map[i][zeroIdx] = sum - tempSum;
            }
        }

        // 지워진 숫자 열 기준 채우기
        for (int j = 0; j < 3; j++) {
            int zeroCnt = 0;
            int zeroIdx = -1;
            int tempSum = 0;

            for (int i = 0; i < 3; i++) {
                tempSum += map[i][j];

                if (map[i][j] == 0) {
                    zeroCnt++;
                    zeroIdx = i;
                }
            }

            if (zeroCnt == 1) {
                map[zeroIdx][j] = sum - tempSum;
            }
        }

        // 출력
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}