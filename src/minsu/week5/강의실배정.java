import java.io.*;
import java.util.*;

public class 강의실배정 {
	
	
	// size 변수는 현재 heap의 크기와, 맨 뒤의 idx를 가리킵니다.
	private static int N, size;
	private static int[][] lec;
	private static int[] heap;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		lec = new int[N][2];
		heap = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			lec[i][0] = Integer.parseInt(st.nextToken());
			lec[i][1] = Integer.parseInt(st.nextToken());

		}

		Arrays.sort(lec, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}

		});

		push(lec[0][1]);

		for (int i = 1; i < N; i++) {

			if (heap[1] <= lec[i][0]) {
				pop();
			}
			push(lec[i][1]);

		}
		
		System.out.println(size);

	}

	private static void swap(int i, int j) {
		
		int tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
		
	}
	
	// 현재 이 heap 구현은 오름차순입니다.
	private static void push(int data) {
		
		
		heap[++size] = data;
		// 현재 heap의 맨 마지막 idx를 증가시킨 후 data를 넣습니다.

		int p = size / 2; // data가 들어간 노드의 부모 노드 인덱스입니다.
		int ch = size; // data의 인덱스, 즉 자식 노드 인덱스입니다.
		
		// 자식 노드가 만약 1이라면, 루트 노드이므로 스왑을 하지 않습니다.
		// 그 이외는 다 값을 비교해서 스왑해줍니다.
		// data를 밑에서부터 올라가면서 정렬
		while (ch != 1 && heap[p] > heap[ch]) {
			
			
			// 만약 부모 노드가 자식 노드보다 크다면, 서로 위치를 바꿔줍니다.
			swap(p, ch);
			
			// 맨 밑에서부터 올라가면서 정렬하는 방법
			// 이제 자식노드는 부모노드가 되며,
			ch = p;
			
			// 부모노드는 변경된 자식 노드의 값에 2를 나눠줍니다.
			// 나눠주는 이유는 항상 부모노드는 (자식 노드 인덱스의 / 2 )번째 위치 하기 떄문입니다.
			// root 노드 인덱스를 1로 기준 잡았습니다.
			// level 1 :     1
			// level 2 :   2   3
			// level 3 : 4  5 6  7
			// 현재 heap 배열 상태 : [0, 1, 2, 3, 4, 5, 6]
			// 부모 노드는 항상 자식 노드의 / 2 번째 idx를 가집니다.
			p = ch / 2;

		}

	}

	private static int pop() {
		
		// 가장 작은 값은 heap[1] 값입니다. 이 값을 pop 합니다.
		int popNum = heap[1];
		
		// heap에서 가장 큰 값을 heap[1]에 넣어줍니다.
		// 루트 노드를 최신화 해주기 위해서 입니다.
		heap[1] = heap[size--];
		
		// 부모 노드를 1로 셋팅하고, 자식 노드는 부모 노드의 * 2로 셋팅합니다.
		int p = 1;
		int ch = p * 2;
		
		// 만약 오른쪽 자식이 존재하고, 오른쪽 자식이 왼쪽 자식보다 작다면, ch를 1 증가해줍니다.
		// 그 이유는 루트에 가장 작은 값이 올라가야 하기 때문입니다.
		
		if (ch + 1 <= size && heap[ch] > heap[ch + 1]) {
			ch++;
		}
		
		// 가장 큰 값을 루트로 바꾸고 내려가면서 정렬
		while (ch <= size && heap[p] > heap[ch]) {

			swap(p, ch);
			
			// 부모 노드와 자식 노드를 최신화 해줍니다.
			// push와 반대로 pop은 루트부터 내려가면서 정렬하기 때문에
			// p는 자식으로 설정하고 자식은 p의 두배로 설정합니다.
			// 부모노드의 인덱스는 자식 노드의 두배 인덱스를 가집니다.
			p = ch;
			ch = p * 2;
			
			
			// 만약 오른쪽 자식이 존재하고,오른쪽 자식이 왼쪽 자식보다 작다면 오른쪽 자식으로 바꿉니다.
			if (ch + 1 <= size && heap[ch] > heap[ch + 1]) {
				ch++;
			}

		}

		return popNum;

	}

}