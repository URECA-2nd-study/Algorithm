package yeoeun.week15;

import java.util.*;

public class Week15_순위_검색 {
    static Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (String str : info) {
            String[] temp = str.split(" ");
            putCondition(0, "", temp);
        }

        for (List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }

        for(int i = 0; i < query.length; i++) {
            // key 생성
            String[] q = query[i].split(" ");
            String key = q[0] + q[2] + q[4] + q[6];

            // 가능한 scores 찾기
            List<Integer> scores = map.getOrDefault(key, new ArrayList<>());

            // query에 해당하는 지원자 수 계산
            int sIdx = findStart(scores, Integer.parseInt(q[7]));
            answer[i] = scores.size() - sIdx;
        }
        return answer;
    }

    public void putCondition(int idx, String str, String[] arr) {
        if(idx == 4) {
            map.computeIfAbsent(str, k -> new ArrayList<>()).add(Integer.parseInt(arr[4]));
            return;
        }

        // - or 완전일치인 경우 가능함
        putCondition(idx + 1, str + "-", arr);
        putCondition(idx + 1, str + arr[idx], arr);
    }

    // n 이상의 수가 시작하는 부분 찾기
    public int findStart(List<Integer> arr, int n) {
        int start = 0;
        int end = arr.size();

        while (start < end) {
            int mid = (start + end) / 2;
            if (arr.get(mid) < n) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
