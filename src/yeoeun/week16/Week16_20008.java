package yeoeun.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week16_20008 {
    static int N;
    static int minTime = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int HP = Integer.parseInt(st.nextToken());

        Skill[] skills = new Skill[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            skills[i] = new Skill(C, D, 0);
        }

        dfs(0, HP, skills);
        System.out.println(minTime);
    }

    private static void dfs(int time, int HP, Skill[] skills) {
        if(HP <= 0) {
            minTime = Math.min(minTime, time);
            return;
        }

        boolean isUsed = false;
        for (int i = 0; i < N; i++) {
            if(skills[i].leftTime > 0) continue;
            isUsed = true;

            // 배열 깊은 복사
            Skill[] tmpSkill = new Skill[skills.length];
            for (int j = 0; j < skills.length; j++) {
                tmpSkill[j] = new Skill(skills[j].coolTime, skills[j].damage, skills[j].leftTime);
            }

            HP -= tmpSkill[i].damage;
            tmpSkill[i].leftTime = tmpSkill[i].coolTime;

            for (int j = 0; j < N; j++) {
                if(tmpSkill[j].leftTime > 0) tmpSkill[j].leftTime--;
            }

            dfs(time + 1, HP, tmpSkill);
            HP += skills[i].damage;
        }

        // 스킬 사용할 수 없으면 시간 조정 후 재시도
        if(!isUsed) {
            for (int i = 0; i < N; i++) {
                if(skills[i].leftTime > 0) skills[i].leftTime--;
            }
            dfs(time+1, HP, skills);
        }
    }

    public static class Skill {
        int coolTime;
        int damage;
        int leftTime;

        public Skill (int coolTime, int damage, int leftTime){
            this.coolTime = coolTime;
            this.damage = damage;
            this.leftTime = leftTime;
        }
    }
}
