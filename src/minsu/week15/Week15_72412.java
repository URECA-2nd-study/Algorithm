import java.util.*;

class Week15_72412 {
    // 속성 조합(key) → 해당 조합을 만족하는 지원자 점수 리스트
    private HashMap<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        // 1) info 전처리: 재귀를 통해 16가지(2^4) 속성 조합 생성하여 map에 점수 저장
        for (String entry : info) {
            // entry 예: "java backend junior pizza 150"
            String[] tokens = entry.split(" ");
            String[] attrs = new String[] { tokens[0], tokens[1], tokens[2], tokens[3] };
            int score = Integer.parseInt(tokens[4]);

            // 재귀 호출을 위해 임시 배열 생성
            String[] temp = new String[4];
            generateCombinations(attrs, temp, 0, score);
        }

        // 2) map 안에 쌓인 각 리스트를 오름차순 정렬
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }

        // 3) query 처리
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            // 예: "java and backend and junior and pizza 100"
            String[] tokens = query[i].split(" ");
            // 속성만 뽑아서 키 생성
            String key = tokens[0] + " " + tokens[2] + " " + tokens[4] + " " + tokens[6];
            int targetScore = Integer.parseInt(tokens[7]);

            List<Integer> list = map.getOrDefault(key, Collections.emptyList());
            if (list.isEmpty()) {
                answer[i] = 0;
            } else {
                int idx = lowerBound(list, targetScore);
                answer[i] = list.size() - idx;
            }
        }
        return answer;
    }

    // 재귀(DFS)로 “현재 속성을 실제 값 or '-'” 두 가지로 선택하며 4단계(언어→직군→경력→음식) 탐색
    private void generateCombinations(String[] attrs, String[] temp, int depth, int score) {
        // depth == 4이면 “temp”에 담긴 속성 4개를 공백으로 이어붙여 key를 만들고 map에 점수 추가
        if (depth == 4) {
            String key = String.join(" ", temp);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            return;
        }

        // 1) 실제 속성값 선택
        temp[depth] = attrs[depth];
        generateCombinations(attrs, temp, depth + 1, score);

        // 2) 와일드카드("-") 선택
        temp[depth] = "-";
        generateCombinations(attrs, temp, depth + 1, score);
    }

    // 오름차순 정렬된 리스트에서 target 이상인 첫 인덱스를 이분 탐색(lower bound)으로 찾기
    private int lowerBound(List<Integer> arr, int target) {
        int lo = 0, hi = arr.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}