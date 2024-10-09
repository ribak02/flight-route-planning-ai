import java.util.*;

public class BestFirst {
    public static int[] searchBestFirst(int N, Node start, Node goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getFCost)
                .thenComparingInt(n -> n.distance)
                .thenComparingInt(n -> n.angle));
        Set<Node> explored = new HashSet<>();
        Map<Node, Double> gCost = new HashMap<>();

        start.setFCost(Util.heuristic(start, goal));
        gCost.put(start, 0.0);
        frontier.add(start);

        int nodesVisited = 0;

        while (!frontier.isEmpty()) {
            printFrontier(frontier);
            Node current = frontier.poll();
            nodesVisited++;

            if (current.equals(goal)) {
                printPath(current);
                double pathCost = gCost.get(current);
                System.out.println(String.format("%.3f", pathCost));
                System.out.println(nodesVisited);
                return new int[] { (int) Math.round(pathCost), nodesVisited };

            }

            explored.add(current);

            List<Node> successors = Util.getSuccessors(current, N);
            for (Node child : successors) {
                if (!explored.contains(child) && !frontier.contains(child)) {
                    child.setFCost(Util.heuristic(child, goal));
                    gCost.put(child, current.getGCost() + Util.calculateMoveCost(current, child));
                    frontier.add(child);
                }
            }
        }

        System.out.println("fail");
        System.out.println(nodesVisited);
        return new int[] { Integer.MAX_VALUE, nodesVisited };
    }

    private static void printPath(Node goal) {
        List<Node> path = new ArrayList<>();
        Node current = goal;
        while (current != null) {
            path.add(current);
            // The parent of each node must have been set during the search process
            current = current.parent;
        }
        Collections.reverse(path);

        // Print the path from start to goal
        path.forEach(node -> System.out.print(node));
        System.out.println();
    }

    private static void printFrontier(PriorityQueue<Node> frontier) {
        System.out.print("[");
        List<Node> nodes = new ArrayList<>(frontier);
        nodes.sort(Comparator.comparingDouble(Node::getFCost)
                .thenComparingInt(Node::getDistance)
                .thenComparingInt(Node::getAngle));
        for (Node node : nodes) {
            System.out.print(String.format("%s%.3f", node, node.getFCost()));
            if (!node.equals(nodes.get(nodes.size() - 1))) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
