import java.util.*;

/*라이언 => 어피치가 쏜 화살의 수 +1 쏴서 점수 얻거나 혹은 해당 과녁은 맞추지 않아서 점수 얻지 않기
 ==>> 두 가지 가능성 밖에 존재하지 않음 

1. 일단 비트마스킹으로 조합 구하기 
2. 해당 조합을 선택했을 때, 가장 점수 차이가 많이나는 조합 구하기

*/

class Solution {
    static int diff = 0; // 라이언과 어피치의 최대 점수 차이 
    static int[] answer = {-1};

    public int[] solution(int n, int[] info) {

        for (int bit = 0; bit < (1 << 11); bit++) {
            int[] arr = new int[11];
            int used = 0; //라이언이 쏜 화살 개수
            int lionScore = 0;
            int apeachScore = 0;

            // 1. 모든 조합 구하기 
            for (int i = 0; i < 11; i++) {
                if ((bit & (1 << i)) != 0) {
                    arr[i] = info[i] + 1; // 어피치보다 한 발 더 쏘기 
                    used += arr[i];
                    lionScore += 10 - i;
                }
            }

            // 사용한 화살 개수가 n보다 크면 가능한 경우가 아니므로 continue
            if (used > n) continue;

            // 남은 화살은 0점에 몰아넣기
            arr[10] += (n - used);

            // 어피치 점수 계산 
            for (int i = 0; i < 11; i++) {
                if ((bit & (1 << i)) == 0 && info[i] > 0) {
                    apeachScore += 10 - i;
                }
            }

            // 값 갱신
            int d = lionScore - apeachScore;
            if (d > diff) {
                diff = d;
                answer = arr.clone();
            } else if (d == diff && d > 0) {
                if (isBetter(arr, answer)) {
                    answer = arr.clone();
                }
            }
        }

        return answer;
    }

    //점수 차이가 같을 경우 가장 낮은 점수를 맞힌 경우를 찾기
    static boolean isBetter(int[] a, int[] b) {
        for (int i = 10; i >= 0; i--) {
            if (a[i] != b[i]) {
                return a[i] > b[i];
            }
        }
        return false;
    }
}
