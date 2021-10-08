package com.intelycare.garis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Weighted Path
 * Have the function WeightedPath(strArr) take strArr which will be an array of strings which models a non-looping weighted Graph.
 * The structure of the array will be as follows: The first element in the array will be the number of nodes N (points) in the array
 * as a string. The next N elements will be the nodes which can be anything (A, B, C .. Brick Street, Main Street .. etc.). Then after
 * the Nth element, the rest of the elements in the array will be the connections between all of the nodes along with their weights (integers)
 * separated by the pipe symbol (|). They will look like this: (A|B|3, B|C|12 .. Brick Street|Main Street|14 .. etc.). Although, there may
 * exist no connections at all.
 *
 * An example of strArr may be: ["4","A","B","C","D","A|B|1","B|D|9","B|C|3","C|D|4"]. Your program should return the shortest path when
 * the weights are added up from node to node from the first Node to the last Node in the array separated by dashes. So in the example
 * above the output should be A-B-C-D. Here is another example with strArr being
 * ["7","A","B","C","D","E","F","G","A|B|1","A|E|9","B|C|2","C|D|1","D|F|2","E|D|6","F|G|2"].
 * The output for this array should be A-B-C-D-F-G. There will only ever be one shortest path for the array.
 * If no path between the first and last node exists, return -1. The array will at minimum have two nodes.
 * Also, the connection A-B for example, means that A can get to B and B can get to A. A path may not go through any Node more than once.
 * Examples
 * Input: new String[] {"4","A","B","C","D", "A|B|2", "C|B|11", "C|D|3", "B|D|2"}
 * Output: A-B-D
 * Input: new String[] {"4","x","y","z","w","x|y|2","y|z|14", "z|y|31"}
 * Output: -1
 */
public class WeightedPath {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Node> nodes = new LinkedHashMap<>();

        String input = scanner.next();
        if (input.equals("tests")) {
            runTests();
            System.exit(0);
        }
        int differentNodes = Integer.parseInt(input);

        for (int a = 0; a < differentNodes; a++) {
            String data = scanner.next();
            if (data.equals("tests")) {
                runTests();
                System.exit(0);
            }
            nodes.put(data, new Node(data));
        }

        Pattern connectionPattern = Pattern.compile("[a-zA-Z]+\\|[a-zA-Z]+\\|\\d+");
        // lets get the connections
        while(!scanner.hasNext("exit")) {
            String data = scanner.next(connectionPattern);

            addConnectionFromString(nodes, data);
        }

        System.out.println(calculateShortestPath(nodes, calculateEdges(nodes.keySet())));
    }

    private static void runTests() {
        Map<Map<String, Node>, String> tests = new LinkedHashMap<>();

        tests.put(getNodesFrom(new String[]{"4", "A", "B", "C", "D", "A|B|1", "B|D|9", "B|C|3", "C|D|4"}), "A-B-C-D");
        tests.put(getNodesFrom(new String[]{"7", "A", "B", "C", "D", "E", "F", "G", "A|B|1", "A|E|9", "B|C|2", "C|D|1", "D|F|2", "E|D|6", "F|G|2"}), "A-B-C-D-F-G");
        tests.put(getNodesFrom(new String[]{"4", "A", "B", "C", "D", "A|B|2", "C|B|11", "C|D|3", "B|D|2"}), "A-B-D");
        tests.put(getNodesFrom(new String[]{"4", "x", "y", "z", "w", "x|y|2", "y|z|14", "z|y|31"}), "-1");

        for (Map.Entry<Map<String, Node>, String> test : tests.entrySet()) {

            String result = calculateShortestPath(test.getKey(), calculateEdges(test.getKey().keySet()));

            if (test.getValue().equals(result)) {
                System.out.println(String.format("Test PASSED with %s", result, test.getValue()));
            } else {
                System.out.println(String.format("Test FAILED with %s (expected: %s)", result, test.getValue()));
            }
        }
    }


    private static String calculateShortestPath(Map<String, Node> nodes, Edges fromTo) {

        List<Node> path = getShortestPath(nodes, fromTo.from, fromTo.to);

        if (path.stream().noneMatch(v -> v.name.equals(fromTo.from) || v.name.equals(fromTo.to))) {
            return "-1";
        }

        return path.stream().map(v -> v.name).collect(Collectors.joining("-"));
    }

    private static Edges calculateEdges(Set<String> keySet) {
        String from = null;
        String to = null;
        for (String key : keySet) {
            if (from == null) {
                from = key;
            }

            to = key;
        }

        return Edges.from(from, to);
    }
    private static Map<String, Node> getNodesFrom(String[] test) {
        int differentNodes = Integer.parseInt(test[0]);

        Map<String, Node> nodes = new LinkedHashMap<>();
        int a;
        for (a = 1; a <= differentNodes; a++) {
            String data = test[a];
            nodes.put(data, new Node(data));
        }
        for (; a < test.length; a++) {
            addConnectionFromString(nodes, test[a]);
        }


        return nodes;
    }

    private static void addConnectionFromString(Map<String, Node> nodes, String formattedString) {
        String[] connection = formattedString.split("\\|");
        Node source = nodes.get(connection[0]);
        Node target = nodes.get(connection[1]);
        int weight = Integer.parseInt(connection[2]);

        source.addConnection(target, weight);
    }

    public static List<Node> getShortestPath(Map<String, Node> nodes, String source, String destination) {

        PriorityQueue<ShortestPathNode> shortestPathQueue = new PriorityQueue<>(Comparator.comparing(node -> node.distance));
        Map<String, ShortestPathNode> shortestPathMap = new HashMap<>(nodes.size());
        Map<String, String> predecessor = new HashMap<>(nodes.size());

        //Initialize data-structures to keep track of shortest-path & predecessor
        for (Node v : nodes.values()) {
            if (v.name.equals(source)) {
                shortestPathMap.put(v.name, ShortestPathNode.of(v.name, 0));
            } else {
                shortestPathMap.put(v.name, ShortestPathNode.of(v.name, Integer.MAX_VALUE));
            }
            predecessor.put(v.name, null);
        }

        //Add all vertices to the priority queue (prioritized based on shortest-path distance)
        for (Node v : nodes.values()) {
            shortestPathQueue.add(shortestPathMap.get(v.name));
        }

        while (!shortestPathQueue.isEmpty()) {

            ShortestPathNode shortestPathNode = shortestPathQueue.poll();

            Node u = nodes.get(shortestPathNode.name);
            for (Connection edge : u.connections) {
                relax(u, edge.node, shortestPathQueue, shortestPathMap, predecessor);
            }
        }
        return getShortestPathResult(nodes, destination, predecessor);
    }


    private static List<Node> getShortestPathResult(Map<String, Node> nodes, String dest, Map<String, String> predecessor) {

        List<Node> path = new ArrayList<>();

        String lookupNext = dest;
        while (predecessor.get(lookupNext) != null) {
            if (lookupNext.equals(dest)) {
                path.add(nodes.get(dest));
            }
            String pred = predecessor.get(lookupNext);
            lookupNext = pred;
            path.add(nodes.get(pred));
        }
        Collections.reverse(path);
        return path;
    }

    private static void relax(Node u, Node v, PriorityQueue<ShortestPathNode> queue,
                       Map<String, ShortestPathNode> sp, Map<String, String> pred) {

        int shortestPathWeight = sp.get(u.name).distance;
        int weight = u.findWeight(v);

        int cumulativeWeight = Math.abs(shortestPathWeight + weight);

        if (cumulativeWeight < sp.get(v.name).distance) {
            ShortestPathNode vNode = sp.get(v.name);
            vNode.distance = shortestPathWeight + weight;

            //need to extract and add elements to reorder
            queue.remove(vNode);
            queue.add(vNode);

            pred.put(v.name, u.name);
        }
    }

    static class Node  {
        String name;

        Set<Connection> connections = new HashSet<>();

        public Node(String name) {
            this.name = name;
        }

        public Integer findWeight(Node node) {
            for (Connection connection : connections) {
                if (connection.node.equals(node)) {
                    return connection.weight;
                }
            }
            return Integer.MAX_VALUE;
        }
        public void addConnection(Node connected, int weight) {
            connections.add(Connection.from(connected, weight));
        }

        @Override
        public String toString() {
            return "" + name + ": "  + connections ;
        }

    }

    static class Connection {
        int weight;
        Node node;

        private Connection(Node node, int weight) {
            this.weight = weight;
            this.node = node;
        }

        public static Connection from(Node node, int weight) {
            return new Connection(node, weight);
        }

        public int getWeight() {
            return weight;
        }

        public Node getNode() {
            return node;
        }

        @Override
        public String toString() {
            return "Connection{" +
                    "weight=" + weight +
                    ", node=" + node.name +
                    '}';
        }
    }

    static class ShortestPathNode {
        private String name;
        private int distance;

        private ShortestPathNode(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }

        static ShortestPathNode of(String name, int distance) {
            return new ShortestPathNode(name, distance);
        }
    }

    static class Edges {
        private String from;
        private String to;

        private Edges(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public static Edges from(String from, String to) {
            return new Edges(from, to);
        }
    }
}
