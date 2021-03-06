package com.future.experience.gugou;

import java.util.*;

/**
 * Created by xingfeiy on 4/26/18.
 */
public class EventsCounter {
    private static class Event{
        public float start;
        public float end;
        public int id;

        public Event(int start, int end, int id) {
            this.start = start;
            this.end = end;
            this.id = id;
        }
    }

    /**
     * The problem is, we are given a set of events, each event has a time range(start and end), and this event
     * could happen at any time during this time range.
     * And a given integer which represents nth event based on timeline.
     *
     * example:
     * event1: 3-7
     * event2: 5-8
     * event3: 9-10
     * event4: 11-12
     *
     * n = 1, that means the first happened event, that could be either event1 or event2, but can't be event3 and event4.
     * so we should return event1 and event2.
     *
     * n = 2, only event3 should be returned.
     *
     * - Assume the inputs are valid, for each Event, the start is always smaller than end
     * - Assume there is no duplicated events.
     * - The collection of events is not sorted.
     *
     * Why event3 can't be first one, because there are at least one event ended before event3 starts.
     * Why event3 can't be second, because there are at least two events ended before event3 starts.
     * ...
     * We care about how many events ended before current event starts.
     *
     * So for each event, we check the earliest end of the events before current one.
     * - if earliest end < current start, possible position ++
     * - else continue do the same thing
     *
     *
     * Explanation:
     * - For each node, we care about how many events ended before current node starts.
     * - There are two sorts required, sort by start time and sort by end time.
     * - We can use two priority queue, let's say start queue and end queue.
     * - For each event, if its start time >= end queue.peek.end time
     *      - Poll from end queue until end queue.peek.end > its start time
     * - Else
     *      - Add to start queue
     *
     * It's not a correct solution, please check findPossibleEventsV2
     * @param events
     * @param n
     * @return
     */
    public Set<Event> findPossibleEvents(Set<Event> events, int n) {
        Set<Event> results = new HashSet<>();
        if(events == null || events.size() < 1 || n < 1) return results;
        PriorityQueue<Event> queue = new PriorityQueue<>(new Comparator<Event>(){
            @Override
            public int compare(Event o1, Event o2) {
                return Float.compare(o1.start, o2.start);
            }
        });

        for(Event event : events) queue.offer(event);
        float earliestEnd = Float.MAX_VALUE;
        while (!queue.isEmpty()) {
            if(n == 1) {
                while (!queue.isEmpty() && queue.peek().start < earliestEnd) {
                    earliestEnd = Math.min(earliestEnd, queue.peek().end);
                    results.add(queue.poll());
                }
                return results;
            }

            if(queue.peek().start > earliestEnd) {
                n--;
                earliestEnd = queue.peek().end;
            } else {
                earliestEnd = Math.min(earliestEnd, queue.peek().end);
                queue.poll();
            }
        }
        return results;
    }

    public Set<Event> findPossibleEventsV2(Set<Event> events, int n) {
        Set<Event> results = new HashSet<>();
        if(events == null || events.size() < 1 || n < 1) return results;
        PriorityQueue<Event> startQueue = new PriorityQueue<>((a, b)->Float.compare(a.start, b.start));
        PriorityQueue<Event> endQueue = new PriorityQueue<>((a, b)->Float.compare(a.end, b.end));

        for(Event event : events) {
            startQueue.offer(event);
        }
        while (!startQueue.isEmpty()) {
            while (!endQueue.isEmpty() && startQueue.peek().start >= endQueue.peek().end) {
                if(n == 1) {
                    return new HashSet<>(endQueue);
                }
                endQueue.poll();
                n--;
            }
            endQueue.offer(startQueue.poll());
        }
        return new HashSet<>(endQueue);
    }

    public static void main(String[] args) {
        Set<Event> events = new HashSet<>();
        events.add(new Event(2, 7, 1));
        events.add(new Event(5, 8, 2));
        events.add(new Event(9, 10, 3));
        events.add(new Event(12, 17, 4));
        events.add(new Event(13, 14, 5));
        events.add(new Event(13, 24, 6));
        EventsCounter counter = new EventsCounter();
        System.out.println("Find first possible events");
        for(Event event : counter.findPossibleEvents(events, 1)) {
            System.out.println(event.id);
        }

        System.out.println("Find second possible events");
        for(Event event : counter.findPossibleEvents(events, 2)) {
            System.out.println(event.id);
        }

        System.out.println("Find third possible events");
        for(Event event : counter.findPossibleEvents(events, 2)) {
            System.out.println(event.id);
        }

        System.out.println("=============================");
        Set<Event> events2 = new HashSet<>();
        events2.add(new Event(1, 5, 1));
        events2.add(new Event(2, 4, 2));
        events2.add(new Event(2, 5, 3));
        events2.add(new Event(7, 8, 4));
        System.out.println("Find first possible events");
        for(Event event : counter.findPossibleEventsV2(events2, 1)) {
            System.out.println(event.id);
        }

        System.out.println("Find second possible events");
        for(Event event : counter.findPossibleEventsV2(events2, 2)) {
            System.out.println(event.id);
        }

        System.out.println("Find third possible events");
        for(Event event : counter.findPossibleEventsV2(events2, 3)) {
            System.out.println(event.id);
        }

        System.out.println("Find fourth possible events");
        for(Event event : counter.findPossibleEventsV2(events2, 4)) {
            System.out.println(event.id);
        }
    }
}
