import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Evaluate {

    public static void main(String[] args) {
        List<String[]> testCases = new ArrayList<>();
        Map<String, List<int[]>> results = new LinkedHashMap<>(); // Stores algorithm results

        // test cases
        testCases.add(new String[] { "5", "2:0", "2:135" });
        testCases.add(new String[] { "5", "1:180", "4:180" });
        testCases.add(new String[] { "5", "1:315", "4:45" });
        testCases.add(new String[] { "5", "3:90", "0:0" });

        testCases.add(new String[] { "8", "1:135", "5:315" });
        testCases.add(new String[] { "8", "4:0", "7:90" });
        testCases.add(new String[] { "8", "6:270", "0:45" });

        testCases.add(new String[] { "10", "9:225", "2:45" });
        testCases.add(new String[] { "10", "2:45", "8:255" });
        testCases.add(new String[] { "10", "7:270", "7:90" });

        // Initialize storage for each search algorithm
        results.put("BFS", new ArrayList<>());
        results.put("DFS", new ArrayList<>());
        results.put("BestFirst", new ArrayList<>());
        results.put("AStar", new ArrayList<>());
        results.put("SMAStar", new ArrayList<>());

        for (String[] testCase : testCases) {
            int N = Integer.parseInt(testCase[0]);
            String[] startCoords = testCase[1].split(":");
            Node start = new Node(Integer.parseInt(startCoords[0]), Integer.parseInt(startCoords[1]), null);
            String[] goalCoords = testCase[2].split(":");
            Node goal = new Node(Integer.parseInt(goalCoords[0]), Integer.parseInt(goalCoords[1]), null);

            results.get("BFS").add(PartA.searchBFS(N, start, goal));
            results.get("DFS").add(PartA.searchDFS(N, start, goal));
            results.get("BestFirst").add(BestFirst.searchBestFirst(N, start, goal));
            results.get("AStar").add(AStar.searchAStar(N, start, goal));
            results.get("SMAStar").add(SMAStar.searchSMAStar(N, start, goal, N));

            try (PrintWriter writer = new PrintWriter(new FileWriter("search_results.csv"))) {
                // Write CSV header
                writer.println("Algorithm,Path Cost,Nodes Visited");

                // Output results for analysis
                for (Map.Entry<String, List<int[]>> result : results.entrySet()) {
                    System.out.println("Results for " + result.getKey() + ":");
                    for (int[] metrics : result.getValue()) {
                        System.out.println("Path Cost: " + metrics[0] + ", Nodes Visited: " + metrics[1]);
                        writer.printf("%s,%d,%d\n", result.getKey(), metrics[0], metrics[1]);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            // PartA.searchBFS(N, start, goal);
            // PartA.searchDFS(N, start, goal);
            // BestFirst.searchBestFirst(N, start, goal);
            // AStar.searchAStar(N, start, goal);
            // SMAStar.searchSMAStar(N, start, goal, N);
        }
    }
}