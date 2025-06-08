import java.util.*;

class Solution {

    class User {
        String lang, job, exp, food;
        int score;

        public User(String lang, String job, String exp, String food, int score) {
            this.lang = lang;
            this.job = job;
            this.exp = exp;
            this.food = food;
            this.score = score;
        }
    }

    static Map<String, List<Integer>> userMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        List<User> userList = new ArrayList<>();

        // 유저 정보 저장
        for (String s : info) {
            String[] arr = s.split(" ");
            userList.add(new User(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4])));
        }

        // 유저 조건 조합 만들기
        for (User user : userList) {
            String[] arr = { user.lang, user.job, user.exp, user.food };
            combi(arr, 0, "", user.score);
        }

        //점수 정렬
        for (String key : userMap.keySet()) {
            Collections.sort(userMap.get(key));
        }

        int[] answer = new int[query.length];

        //쿼리 반복
        for (int q = 0; q < query.length; q++) {
            String[] cond = query[q].split(" ");
            String key = cond[0] + cond[2] + cond[4] + cond[6];
            int score = Integer.parseInt(cond[7]);

            List<Integer> scoreList = userMap.getOrDefault(key, new ArrayList<>());
            answer[q] = getUserNum(scoreList, score);
        }

        return answer;
    }

    // 가능한 모든 조합 구하는 메서드 
    public void combi(String[] arr, int depth, String cur, int score) {
        if (depth == 4) {
            if (!userMap.containsKey(cur)) {
                userMap.put(cur, new ArrayList<>());
            }
            userMap.get(cur).add(score);
            return;
        }

        combi(arr, depth + 1, cur + arr[depth], score);
        combi(arr, depth + 1, cur + "-", score);
    }

    //score 이상인 사람 수 세는 메서드
    public int getUserNum(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return list.size() - left;
    }
}
