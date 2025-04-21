import java.io.*;
import java.util.*;

class Solution {
    private static int N = 1_000_001;
    // in은 해동 노드에서 들어오는 간선의 갯수를 저장
    // out은 해당 노드에서 나가는 간선의 갯수를 저장
    // 문제에 주어진 "생성한 정점"은 들어오는 간서은 0개이며, 나가는 간선은 2개 이상이여야한다.
    private static int[] in, out;
    
    public int[] solution(int[][] edges) {
        in = new int[N];
        out = new int[N];
        
        // 노드 번호 중 가장 큰 노드 번호를 찾기 위해 max 변수를 활용하였다.
        int max = 0;
        for(int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
            
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }
        
        int start = 0;
        int eight = 0;
        int stick = 0;
        
        for(int i = 1; i<=max; i++) {
            
            if(out[i] >= 2) {
                if(in[i] == 0) { // 나가는 갯수는 2개 이상, 들어오는 갯수는 0개 이면 생성된 정점
                    start = i;
                }else { // 그게 아니라면 8자모양이다.
                    eight++;
                }
            } else if(out[i] == 0 && in[i] > 0) { // 나가는게 없고 들어오는것만 있다면 막대 모양의 맨 끝에 도달한 것.
                stick++;
            }
            
        }
        // 도넛 모양은 생성된 정점에서 나가는 간선의 갯수에서 8자와, 막대 모양의 합을 뺀 것과 같다.
        return new int[] {start, out[start] - (eight + stick), stick, eight};
    }
}