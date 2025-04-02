import java.io.*;
import java.util.*;

public class Week9_10728 {
	private static int N;
	private static int pick, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			dfs(1, 0);
			
            // 출력문
			sb.append(ans + "\n");
			for (int i = 0; i < N; i++) {
				if ((pick & (1 << i)) != 0) {
					sb.append(i + 1).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
    /*
    * dfs 동작 과정
    * 1 ~ N번까지 dfs 탐색
    * 해당 번호를 사용할 지 안할지 경우의 수 나누고
    * 사용할꺼면 해당 조건에 맞으면 사용할 수 있고 불가능하면 사용할 수 없다.
    * 깊이 까지 내려왔다면 값 최신화
    * remain은 백트래킹용 내가 이미 선택한 번호와 남은 번호가 정답보다 작으면 그냥 리턴시킴.
    */
	private static void dfs(int num, int select) {
		if(num > N) {
			int cnt = Integer.bitCount(select);
			
			if(cnt > ans) {
				ans = cnt;
				pick = select;
			}
		}else {
			int remain = N - num +1;
			if(Integer.bitCount(select) + remain <= ans) {
				return;
			}
			
			dfs(num + 1, select);
			
			if(isPoss(num, select)) {
				dfs(num + 1, select |= (1 << (num - 1)));
			}
			
		}
	}

    /*
     * 현재 들어간 번호들을 모두 xor 연산을 합니다.
     * 1 xor 2, 1 xor 3, ... 2 xor 3, ... 이런식으로 완탐
     * xor 연산한 값이 x와 같게 나오면 false를 리턴
     * 안나오면 true를 리턴
     * if((select & ( 1 << i)) == 0) select에 없는 번호입니다.
     */
    
	private static boolean isPoss(int x, int select) {
		
		for(int i = 0; i<N; i++) {
			if((select & ( 1 << i)) == 0) continue;
			
			for(int j = i + 1; j<N; j++) {
				if((select & (1 << j)) == 0) continue;
				if(((i + 1) ^ (j + 1)) == x) return false;
			}
		}
		
		return true;
	}

}

// 상위비트 개념과 관련된 계산 방식입니다.
// 가장 긴 수열의 범위는 [L ~ 3L - 1]입니다. 이것이 어떻게 되는 것인지 설명하겠습니다.
// 우리가 다루는 조건은 숫자 두 개 i, j를 골랐을 떄
// i ^ j 연산 결과가 수열 안에 없어야합니다.
// xor은 상위 비트가 같고 하위 비트가 다르면, 작은 값이 됩니다.
// 상위비트는 1000100 이라면 왼쪽에서 가장 먼저 나오는 1을 상위비트라고 합니다.
// 그렇다면 어떻게 해야 xor 결과가 수열 밖으로 튈까를 고민하면
// 어느정도 큰 값들만 모아놓으면 xor 연산 결과가 작은 값이 되어 수열 밖으로 나갑니다.
// 즉 하위 비트는 다르지만 상위비트가 고정된 수들끼리 XOR하면 결과가 작아질 수 있습니다.
// 상위비트는 같지만 하위비트가 다른 범위는 "2의 k승 ~ 2의 (k + 1) - 1" 입니다.
// ex) 100 ~ 111 (8 ~ 15)
// 그러면 2의 k승을 L로 치환하면 수열 범위는 [L ~ 2L - 1] 입니다.
// 여기서 이제 상위비트를 한 개 더 늘려 
// 기존에 있던 상위비트와 추가된 상위비트를 고정시키면서 하위비트가 달라지는 범위까지 확장 할 수 있습니다.
// ex) 100 ^ 1000 => 1100  => 기존 상위비트와 새로 추가된 상위 비트가 고정되면서 나머지 하위 비트만 바뀔 수 있다.
// 기존 상위비트와 새로 추가된 상위비트가 고정되면서 나머지 하위비트만 바뀔 수 있는 범위는
// [2L ~ 2L + L - 1]이다. [2L ~ 3L -1]이 되는 것이다.
// 왜 2L + L - 1이 나오냐면, 기존 상위비트를 유지하면서 나머지 하위비트를 바꿀 수 있는 경우의 수는
// L - 1의 경우의 수이다.
// 그렇기 때문에 새로운 상위비트를 키고 기존 상위비트를 고정시킬 수 있으면서 하위비트가 바뀔 수 있는 경우의 수까지 더한 범위가
// [L ~ 3L - 1]인 것이다.
// 이제 L을 구하는 방법인데
// L은 일단 2의 제곱 수이다. 왜냐면 비트의 경우의 수는 2의 제곱과 계속 연관 되어 있기 때문이다.
// L의 가장 맞는 최적의 해는 L = 2의 k승
// 3L - 1 <= N을 만족하는 가장 큰 L을 찾으면 됩니다.
// N이 100일 때, L은 64이하의 수가 될 수 있습니다.
// L이 64일 때 3L - 1은 191
// L이 32일 때 3L - 1은 95입니다.
// 위의 조건 중 성립하는 것은 L이 32일 때 입니다.
// 그러면 수열의 길이는 95 - 32  + 1 = 64가 되며
// 수열은 [32 ~ 95]가 됩니다.

// import java.io.*;
// import java.util.*;

// public class Main {
// 	public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringBuilder sb = new StringBuilder();

// 		int t = Integer.parseInt(br.readLine());
// 		while (t-- > 0) {
// 			int n = Integer.parseInt(br.readLine());

// 			if (n == 1) {
// 				sb.append("1\n1\n");
// 				continue;
// 			}

// 			int m = (int) (Math.log(n) / Math.log(2));
// 			int s = 1 << (m - 1);
// 			int e = Math.min(3 * s - 1, n);
			
// 			sb.append(e - s + 1).append("\n");
// 			for (int i = s; i <= e; i++) {
// 				sb.append(i).append(" ");
// 			}
// 			sb.append("\n");
// 		}
// 		System.out.print(sb.toString());
// 	}
// }