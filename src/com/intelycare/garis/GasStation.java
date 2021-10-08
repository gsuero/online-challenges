package com.intelycare.garis;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**

 *
 * Gas Station
 * Have the function GasStation(strArr) take strArr which will be an an array consisting of the following elements:
 * N which will be the number of gas stations in a circular route and each subsequent element will be the string g:c
 * where g is the amount of gas in gallons at that gas station and c will be the amount of gallons of gas needed to get to the following gas station.
 *
 * For example strArr may be: ["4","3:1","2:2","1:2","0:1"]. Your goal is to return the index of the starting gas station that will allow you to travel
 * around the whole route once, otherwise return the string impossible. For the example above, there are 4 gas stations, and your program should return
 * the string 1 because starting at station 1 you receive 3 gallons of gas and spend 1 getting to the next station. Then you have 2 gallons + 2 more at
 * he next station and you spend 2 so you have 2 gallons when you get to the 3rd station. You then have 3 but you spend 2 getting to the final station,
 * and at the final station you receive 0 gallons and you spend your final gallon getting to your starting point. Starting at any other gas station
 * would make getting around the route impossible, so the answer is 1. If there are multiple gas stations that are possible to start at, return the
 * smallest index (of the gas station). N will be >= 2.
 * Examples
 * Input: new String[] {"4","1:1","2:2","1:2","0:1"}
 * Output: impossible
 * Input: new String[] {"4","0:1","2:2","1:2","3:1"}
 * Output: 4
 *
 *
 * Run tests by providing first input parameter as -1
 * @author Garis Suero http://www.garissuero.me
 */
public class GasStation {

    private static final String IMPOSSIBLE_VALUE = "impossible";


    private static String calculate(List<StationRoute> input) {

        if (input == null || input.size() < 2) {
            throw new IllegalArgumentException("Invalid values provided");
        }

        int gas = 0;
        StationRoute start = null;
        int stations = input.size();


        int a = 0;
        do {
            StationRoute route = input.get(a++);
            if (start == null) {
                start = route;
            }

            gas = (gas + route.gallons) - route.cost;
            stations--;
            if (gas >= 0) {

                if (a >= input.size()) {
                    a = 0;
                }
            } else {

                if (stations > 0 && a < input.size()) {
                    // lets reset starting point
                    start = null;
                    stations = input.size();
                    gas = 0;

                } else {
                    // nope, bye
                    break;
                }

            }
        } while (stations > 0);

        if (gas >= 0) {
            return String.valueOf(start.index);
        }

        return IMPOSSIBLE_VALUE;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<StationRoute> input = new LinkedList<>();
        long gasStations = 0;
        boolean shouldContinue = false;
        int tries = 0;

        while (!shouldContinue && tries++ < 3) {
            String data = scanner.next();

            if ("tests".equals(data)) {
                runTests();
                System.exit(0);
            }

            gasStations = Long.valueOf(data);
            if (gasStations >= 0 && gasStations < 2) {
                System.err.println("Invalid value provided, must be greater than 1.");
            } else {
                shouldContinue = true;
            }
        }

        if (gasStations < 2) {
            throw new IllegalArgumentException("Invalid value provided. expected value >= 2");
        }

        for (int a = 0; a < gasStations; a++) {
            String data = scanner.next("\\d+\\:\\d+");
            input.add(getStationRoute(a + 1, data));
        }


        System.out.println(calculate(input));
    }


    private static void runTests() {
        Map<List<StationRoute>, String> tests = new LinkedHashMap<>();
        // "3:1","2:2","1:2","0:1"

        tests.put(getStationRoutesFrom(new String[] {"0:1","2:2","1:2","3:1"}), "4");
        tests.put(getStationRoutesFrom(new String[] {"0:1","2:2"}), IMPOSSIBLE_VALUE);
        tests.put(getStationRoutesFrom(new String[] {"3:1","2:2","1:2","0:1"}), "1");
        tests.put(getStationRoutesFrom(new String[] {"0:1"}), IllegalArgumentException.class.getName());
        tests.put(getStationRoutesFrom(new String[] {"1:1","2:2","1:2","0:1"}), IMPOSSIBLE_VALUE);



        for(Map.Entry<List<StationRoute>, String> entry : tests.entrySet()) {
            boolean passed;
            String result = null;
            try {
                result  = calculate(entry.getKey());

                passed = entry.getValue().equals(result);
            } catch (Exception ex) {
                if (ex.getClass().getName().equals(entry.getValue())) {
                    passed = true;
                } else {
                    throw ex;
                }
            }

            if (passed) {
                System.out.println(String.format("Test PASSED for %s", entry.getKey()));
            } else {
                System.err.println(String.format("Test FAILED, expected %s and got %s for %s", entry.getValue(), result, entry.getKey()));
            }
        }
    }

    private static List<StationRoute> getStationRoutesFrom(String[] stations) {
        List<StationRoute> routes = new LinkedList<>();
        for (int a = 0; a < stations.length; a++) {
            routes.add(getStationRoute(a + 1, stations[a]));
        }
        return routes;
    }
    private static StationRoute getStationRoute(int index, String formattedString) {
        String[] value = formattedString.split("\\:");
        return new StationRoute(index, Integer.valueOf(value[0]), Integer.valueOf(value[1]));
    }



    static class StationRoute  {
        int index;
        int gallons;
        int cost;

        public StationRoute(int index, int gallons, int needed) {
            this.index = index;
            this.gallons = gallons;
            this.cost = needed;
        }

        @Override
        public String toString() {
            return "" + gallons +  ':' + cost;
        }
    }

}
