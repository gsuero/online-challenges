package com.hackerrank.challenges.warmup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightSegments {
    public static void main(String... argvs) {
        List<Segment> segments = new ArrayList<>(); 
        segments.add(new Segment("SFO", "PEK"));
        segments.add(new Segment("BOS", "JFK"));
        segments.add(new Segment("JFK", "SFO")); 
        segments.add(new Segment("PEK", "HAV"));

        Itinerary iti = new Itinerary(segments);
        System.out.println("Unordered: " + iti);
        
        reorder(iti, "BOS");
        System.out.println("Ordered: " + iti);
        
    }

    static class Segment {

        public String origin; // "BOS"
        public String destination; // "JFK"

        public Segment(String origin, String destination) {
            super();
            this.origin = origin;
            this.destination = destination;
        }

        @Override
        public String toString() {
            return "Segment [origin=" + origin + ", destination=" + destination + "]";
        }
        
    }

    static class Itinerary {

        public Itinerary(List<Segment> segments) {
            super();
            this.segments = segments;
        }

        @Override
        public String toString() {
            return "Itinerary [segments=" + segments + "]";
        }

        public List<Segment> segments;
    }

    // [(JFK, SFO), (BOS, JFK), (SFO, PEK)]
    // (BOS, JFK),

    // [(), (JFK, SFO), (BOS, JFK)]
    private static Itinerary reorder(Itinerary disorder, String firstOrigin) {
        Segment[] segments = disorder.segments.toArray(new Segment[disorder.segments.size()]);
        Segment[] ordered = new Segment[segments.length];

        int orderedIndex = 0;
        for (int a = 0; a < segments.length; a++) {
            Segment current = segments[a];
            if (firstOrigin.equals(current.origin)) {
                ordered[orderedIndex++] = current;
            }
        }

        int unorderedIndex = 0;
        while (orderedIndex < segments.length) {
            Segment current = segments[unorderedIndex++];

            if (ordered[orderedIndex - 1].destination.equals(current.origin)) {
                ordered[orderedIndex++] = current;
            }
            if (unorderedIndex >= segments.length) {
                unorderedIndex = 0;
            }
        }
        disorder.segments = Arrays.asList(ordered);
        return disorder;
    }
}
