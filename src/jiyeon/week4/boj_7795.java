package week4;
import java.util.*;
import java.io.*;


public class boj_7795 {
	
	static int testNum, N, M;
	static int [] aArr, bArr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testNum = Integer.parseInt(br.readLine());
		
		for(int i=0; i<testNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			aArr = new int[N];
			bArr = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) {
				aArr[n] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int n=0; n<M; n++) {
				bArr[n] = Integer.parseInt(st.nextToken());
			}
			
			int total = 0;
			
			//시간 복잡도를 줄이기 위한 배열 정렬 
			Arrays.sort(aArr);
			Arrays.sort(bArr);
			
			for(int n=0; n<N; n++) {
				for (int m=0; m<M; m++) {
					if(aArr[n]>bArr[m])
						total++;
					else
						break; 
					//정렬을 했으므로, aArr[n]<=bArr[m]이 되는 순간 그 이후도 무조건 A가 B보다 작기때문에 break
				}
			}
			
			sb.append(total).append("\n");
			
			
		}//for - testNum
		
		System.out.println(sb);
		
	}

}
