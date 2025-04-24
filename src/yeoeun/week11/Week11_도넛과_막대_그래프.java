package yeoeun.week11;

public class Week11_도넛과_막대_그래프 {
    static int N = 1000000;
    static int[] out = new int[N+1]; // 진출 노드 개수
    static int[] in = new int[N+1]; // 진입 노드 개수

    public int[] solution(int[][] edges) {
        int[] answer = new int[4]; // 0: 루트, 1: 도넛, 2: 막대, 3: 8자

        // 노드 정보 입력
        for(int[] e : edges) {
            out[e[0]]++;
            in[e[1]]++;
        }

        for(int i = 1; i <= N; i++) {
            // root: 진입이 0개임, 막대의 시작도 0이지만 그건 최대 진입이 1개
            if(in[i] == 0 && out[i] >= 2) answer[0] = i;
            // 8자: (중앙)진출이 항상 2개, root와 달리 진입이 존재
            else if(in[i] > 0 && out[i] == 2) answer[3]++;
            // 막대: (맨끝)진출이 없는 유일한 노드, 단 진입은 반드시 존재
            else if(in[i] > 0 && out[i] == 0) answer[2]++;
        }

        // root의 out 수 == 전체 그래프 개수이므로 나머지를 뺀게 도넛의 수
        answer[1] = out[answer[0]] - answer[2] - answer[3];
        return answer;
    }
}
