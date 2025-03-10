import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week5_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Subject> subjects = new PriorityQueue<>();
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            subjects.add(new Subject(start, end));
        }

        endTimes.add(subjects.poll().end);

        while (!subjects.isEmpty()) {
            Subject curSubject = subjects.poll();
            if (curSubject.start >= endTimes.peek()) {
                endTimes.poll();
            }
            endTimes.add(curSubject.end);
        }

        System.out.println(endTimes.size());
    }
}

class Subject implements Comparable<Subject> {
    int start;
    int end;

    public Subject(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Subject o) {
        if (this.start == o. start) return this.end - o.end;
        return this.start - o.start;
    }
}