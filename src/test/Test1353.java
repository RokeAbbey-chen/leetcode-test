package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test1353 {
    public int maxEvents(int[][] events) {

        if (events.length <= 0) { return 0; }

        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int startTime = events[0][0];
        int eventIndex = 1;
        int result = 0;
        queue.add(events[0][1]);
        for (int i = startTime; ; ) {
            boolean outterContinue = false;
            for (; eventIndex < events.length
                    && events[eventIndex][0] == i;
                 eventIndex++) {
                queue.add(events[eventIndex][1]);
            }
            while(!queue.isEmpty()) {
                int end = queue.poll();
//                System.out.println("end: " + end + ", i = " + i);
                if (end >= i) {
                    result ++;
                    i ++;
                    outterContinue = true;
                    break;
                }
            }
            if (outterContinue) { continue; }
            if (eventIndex >= events.length) { break; }
            i = events[eventIndex][0];
            queue.add(events[eventIndex][1]);
            eventIndex ++;
        }
        return result;
    }

    public static void main(String[] args) {
        Test1353 test = new Test1353();
        int[][] events = {{1, 2}, {2, 3}, {3, 4}};
//        int[][] events = {{3, 3}, {2, 2}, {2, 4}, {3, 4}};
        int result = test.maxEvents(events);
        System.out.println(result);
    }
}
