package yeoeun.week14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Week14_상담원_인원 {
    static int[][] waitingTime; // [n][m] : n명이 배치될 시 기다리는 최소 시간 m
    static List<List<Info>> timeTable = new ArrayList<>();

    public int solution(int k, int n, int[][] reqs) {
        // 유형별 req 리스트 정리
        for (int i = 0; i <= k; i++) {
            timeTable.add(new ArrayList<>());
        }

        for (int[] r : reqs) {
            timeTable.get(r[2]).add(new Info(r[0], r[0] + r[1]));
        }

        // 배열 초기화
        waitingTime = new int[n - k + 1][k + 1]; // 1 ~ 1+n-k명 (idx를 위해 +1)

        // i 타입에 대해, j명일 때 최소 대기 시간 구하기
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n - k + 1; j++) {
                waitingTime[j][i] = getMin(i, j + 1);
            }
        }

        // 현재 타입별 대기 시간 idx 저장 (초기 idx : 0)
        int[] curTime = new int[k + 1];

        // idx 계산 : n - k명 만큼 추가 배치 실시
        if (n > k) {
            // type, 차이 저장 (차이 큰 순 정렬)
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing((int[] o) -> o[1]).reversed());

            for (int i = 1; i <= k; i++) {
                pq.offer(new int[]{i, waitingTime[0][i] - waitingTime[1][i]});
            }

            for (int i = 0; i < n - k; i++) {
                int[] maxCase = pq.poll();
                int type = maxCase[0];
                curTime[type]++; // 인원 증가

                if (curTime[type] < n - k) {
                    pq.offer(new int[]{type, waitingTime[curTime[type]][type] - waitingTime[curTime[type] + 1][type]});
                }
            }
        }

        // 최종 시간 저장
        int answer = 0;
        for (int i = 1; i <= k; i++) {
            answer += waitingTime[curTime[i]][i];
        }
        return answer;
    }

    public int getMin(int type, int mentoCnt) {
        // 해당 타입 신청X면 0 반환 (안기다림)
        if (timeTable.get(type).isEmpty()) return 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int time = 0; // 대기 시간

        for (Info info : timeTable.get(type)) {
            // 처음 or 추가 신청 가능 멘토가 남아 있는 경우
            // 먼저 온 사람이 먼저이므로 앞에서부터 채우기
            if (pq.isEmpty() || pq.size() < mentoCnt) {
                pq.add(info.end);
                continue;
            }

            // 가장 일찍 끝나는 상담 시간
            int etime = pq.poll();
            if (info.start < etime) { // 대기 필요
                time += etime - info.start;
                pq.add(etime + (info.end - info.start)); // 끝나는 시간 계산 후 삽입
            } else { // 대기 X
                pq.add(info.end);
            }
        }
        return time;
    }

    public class Info {
        int start;
        int end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
