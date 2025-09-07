package minsu.week16;

import java.io.*;
import java.util.*;

public class Week16_2987 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		double[][] coordinate = new double[3][2];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			coordinate[i][0] = Double.parseDouble(st.nextToken());
			coordinate[i][1] = Double.parseDouble(st.nextToken());
		}

		String answer = String.valueOf(cal(coordinate[0], coordinate[1], coordinate[2]));

		double[] cor = new double[2];
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cor[0] = Double.parseDouble(st.nextToken());
			cor[1] = Double.parseDouble(st.nextToken());

			double x = cal(coordinate[0], coordinate[1], cor);
			double y = cal(coordinate[1], coordinate[2], cor);
			double z = cal(coordinate[2], coordinate[0], cor);

			double tmp = (x + y + z);
			String comp = String.valueOf(tmp);

			if (answer.equals(comp)) {
				count++;
			}
		}
		sb.append(answer).append("\n");
		sb.append(count).append("\n");
		System.out.println(sb.toString());
	}

	private static double cal(double[] cor1, double[] cor2, double[] cor3) {
		double extent = Math
				.abs(cor1[0] * (cor2[1] - cor3[1]) + cor2[0] * (cor3[1] - cor1[1]) + cor3[0] * (cor1[1] - cor2[1])) / 2;
		return Double.parseDouble(String.format("%.2f", extent));
	}
}
