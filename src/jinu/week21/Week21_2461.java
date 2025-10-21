package jinu.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week21_2461 {

    static List<Integer> abilities[];

    static class Node implements Comparable<Node>{
        int index;
        int ability;

        public Node(int index, int ability){
            this.index = index;
            this.ability = ability;
        }

        public int compareTo(Node n){
            return this.ability-n.ability;
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        abilities = new List[N];
        for(int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            abilities[i] = new ArrayList<>();
            for(int j=0;j<M;j++){
                abilities[i].add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(abilities[i]);
        }

        int []abilityIndex = new int[N];

        Arrays.fill(abilityIndex, 0);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        int max = Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            pq.add(new Node(i, abilities[i].get(abilityIndex[i])));
            max = Math.max(max, abilities[i].get(abilityIndex[i]));
        }

        int minDiff = Integer.MAX_VALUE;

        while(!pq.isEmpty()){

            Node n = pq.poll();
            minDiff = Math.min(minDiff, (max-n.ability));

//            System.out.println("max: "+max);
//            System.out.println("min: "+n.ability);

            if(abilityIndex[n.index]<=M-2){
                abilityIndex[n.index]++;
                max = Math.max(max, abilities[n.index].get(abilityIndex[n.index]));
                pq.add(new Node(n.index, abilities[n.index].get(abilityIndex[n.index])));
            }
            else{
                break;
            }
        }

        System.out.println(minDiff);
    }

}
