import java.util.*;

public class AStar {
    public static int[] searchAStar(int N, Node start, Node goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getFCost)
                .thenComparingInt(Node::getDistance)
                .thenComparingInt(Node::getAngle));
        Map<Node, Node> cameFrom = new HashMap<>();
        Map<Node, Double> gCost = new HashMap<>(); // Cost from start to node
        // Map<Node, Double> fCost = new HashMap<>(); // Estimated cost from start to
        // goal through node
        Set<Node> closedSet = new HashSet<>();

        // gCost.put(start, 0.0);
        // fCost.put(start, heuristic(start, goal));
        start.setGCost(0);
        start.setFCost(Util.heuristic(start, goal));
        frontier.add(start);

        int nodesVisited = 0;

        while (!frontier.isEmpty()) {
            printFrontier(frontier);
            Node current = frontier.poll();
            nodesVisited++;

            if (current.equals(goal)) {
                printPath(current, cameFrom);
                double pathCost = gCost.get(current);
                System.out.println(String.format("%.3f", pathCost));
                System.out.println(nodesVisited);
                return new int[] { (int) Math.round(pathCost), nodesVisited };

            }

            closedSet.add(current);

            List<Node> successors = Util.getSuccessors(current, N);
            for (Node neighbor : successors) {
                if (closedSet.contains(neighbor))
                    continue;

                double tentativeGCost = current.getGCost() + Util.calculateMoveCost(current, neighbor);
                if (!gCost.containsKey(neighbor) || tentativeGCost < gCost.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gCost.put(neighbor, tentativeGCost);
                    double fCost = tentativeGCost + Util.heuristic(neighbor, goal);
                    neighbor.setGCost(tentativeGCost);
                    neighbor.setFCost(fCost);
                    if (!frontier.contains(neighbor)) {
                        frontier.add(neighbor);
                    }
                }
            }
        }

        System.out.println("fail");
        System.out.println(nodesVisited);
        return new int[] { Integer.MAX_VALUE, nodesVisited };
    }

    private static void printPath(Node current, Map<Node, Node> cameFrom) {
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        for (Node node : path) {
            System.out.print(node);
        }
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
