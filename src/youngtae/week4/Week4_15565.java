package youngtae.week4;

import java.io.*;
import java.util.*;

public class Week4_15565 {

	static final int INF = Integer.MAX_VALUE;
	static int N, K, result;
	static int[] arr;
	static Queue<Integer> q = new LinkedList<>();		// 중간 값(1)의 index를 기억할 queue

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		K = Integer.parseInt(str.nextToken());

		arr = new int[N];

		str = new StringTokenizer(br.readLine());

		int st = INF;		// 가장 먼저 나온 라이언 == 시작 구간(조금이나마 시간 단축)
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(str.nextToken());
			if(num == 1) {
				st = Math.min(i, st);
			}
			arr[i] = num;
		}

		result = INF;

		if(st != INF) {		// 라이언이 있다면 sol 돌리기
			sol(st);
		}

		sb.append(result == INF ? "-1" : result);

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	private static void sol(int i) {
		int st = i;	// 가장 앞에 나온 라이언
		int ed = st+1;	// 그 이후부터 탐색하기
		int count = 1;	// 가장 앞에 나온 라이언이 있어 1부터 시작

		if(1 == K) {		// 집합의 크기가 1인 경우
			result = 1;
			return;
		}

		while(ed < N && st < N) {		// st가 마지막 집합 값일 수도 있어서 다음 조건 내일 때 반복
			if(arr[ed] == 1) {		// 라이언이 나온 위치마다 count+1과 라이언의 위치를 저장
				q.add(ed);
				count++;
			}

			if(count == K) {
				result = Math.min(result, ed-st+1);		// 최소 연속된 집합의 크기
				st = q.poll();		// 구간의 시작을 다음 라이언 위치로 바로 이동(엄청난 시간 단축)
				count -= 1;		// 기존 st 위치 -> 다음 라이언 위치로 변경되었기 때문에 count 감소
			}
			ed++;		// ed포인터 이동
		}
	}

}