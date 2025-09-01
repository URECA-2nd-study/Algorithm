package src.jaehyeon.week16;

import java.io.*;
import java.util.*;

public class Week16_20008 {

    public static class Skill{

        private int coolTime, damage, untilCoolTime;

        public Skill(int coolTime, int damage){

            this.coolTime = coolTime;
            this.damage = damage;
            this.untilCoolTime = 0;
        }
    }

    public static int answer = Integer.MAX_VALUE;
    public static Skill[] skillSet;

    public static void main(String[] args) throws IOException{

        int monsterHp = init();

        attack(0, monsterHp);

        print();
    }

    private static void attack(int currentTime, int monsterHp){

        if (monsterHp <= 0){

            answer = Math.min(answer, currentTime);
            
            return;
        }

        if (answer != 0 && answer <= currentTime) {
            
            return;
        }
        
        for(Skill skill : skillSet){

            if (skill.untilCoolTime <= currentTime) {
                
                int originalUntilCoolTime = skill.untilCoolTime;
                skill.untilCoolTime = currentTime + skill.coolTime;

                attack(currentTime+1, monsterHp-skill.damage);

                skill.untilCoolTime = originalUntilCoolTime;
            }
        }
        
        if (isAllCoolTime(currentTime)) {
            
            attack(currentTime+1, monsterHp);
        }
    }

    private static boolean isAllCoolTime(int currentTime){

        for(Skill skill : skillSet){

            if (skill.untilCoolTime <= currentTime) {
                
                return false;
            }
        }

        return true;
    }

    private static int init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int monsterHp = Integer.parseInt(st.nextToken());

        skillSet = new Skill[n];

        for(int i=0; i<n; i++){

            st = new StringTokenizer(br.readLine());

            int coolTime = Integer.parseInt(st.nextToken());
            int damage = Integer.parseInt(st.nextToken());

            skillSet[i] = new Skill(coolTime, damage);
        }

        return monsterHp;
    }

    private static void print() throws IOException{

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(answer+"");
        bw.close();
    }
}
