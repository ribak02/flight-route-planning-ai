import java.util.*;

public class SMAStar {
    private static int maxDepth;

    public static int[] searchSMAStar(int N, Node start, Node goal, int maxNodesInMemory) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingDouble(Node::getFCost)
                .thenComparingInt(Node::getDistance)
                .thenComparingInt(Node::getAngle));
        Map<Node, Node> cameFrom = new HashMap<>();
        Map<Node, Double> gCost = new HashMap<>();
        Map<Node, Double> fCost = new HashMap<>();
        Set<Node> closedSet = new HashSet<>();

        start.setGCost(0);
        start.setFCost(Util.heuristic(start, goal));
        start.setIsLeaf(true);
        start.setDepth(0);
        frontier.add(start);
        gCost.put(start, 0.0);
        fCost.put(start, start.getFCost());

        int nodesVisited = 0;
        maxDepth = 1;

        while (!frontier.isEmpty()) {
            printFrontier(frontier);

            if (frontier.size() > maxNodesInMemory) {
                Node leastPromising = removeLeastPromisingLeafNode(frontier);
                if (leastPromising != null) {
                    closedSet.add(leastPromising);
                    leastPromising.getParent().getForgottenChildren().add(leastPromising);
                    updateParentLeafStatus(leastPromising.getParent());
                }
            }

            Node current = frontier.poll();
            if (current == null)
                continue;
            current.setIsLeaf(false); // Once polled, it's no longer a leaf
            nodesVisited++;

            if (current.equals(goal)) {
                printPath(current, cameFrom);
                double pathCost = gCost.get(current);
                System.out.println(String.format("%.3f", pathCost));
                System.out.println(nodesVisited);
                return new int[] { (int) Math.round(pathCost), nodesVisited };
            }

            List<Node> successors = Util.getSuccessors(current, N);
            for (Node neighbor : successors) {
                if (closedSet.contains(neighbor))
                    continue;

                double tentativeGCost = current.getGCost() + Util.calculateMoveCost(current, neighbor);
                double fCostEstimate = tentativeGCost + Util.heuristic(neighbor, goal);
                neighbor.setDepth(current.getDepth() + 1);

                if (neighbor.getDepth() > maxDepth) { // Assume maxDepth is defined elsewhere
                    continue; // Discard this neighbor due to depth limit
                }

                if (!gCost.containsKey(neighbor) || tentativeGCost < gCost.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gCost.put(neighbor, tentativeGCost);
                    fCost.put(neighbor, fCostEstimate);
                    neighbor.setGCost(tentativeGCost);
                    neighbor.setFCost(fCostEstimate);
                    neighbor.setIsLeaf(true);
                    frontier.add(neighbor);
                } else if (frontier.contains(neighbor) && fCost.get(neighbor) > fCostEstimate) {
                    frontier.remove(neighbor);
                    neighbor.setFCost(fCostEstimate);
                    frontier.add(neighbor);
                }
            }
        }

        System.out.println("fail");
        System.out.println(nodesVisited);
        return new int[] { Integer.MAX_VALUE, nodesVisited };
    }

    private static Node removeLeastPromisingLeafNode(PriorityQueue<Node> frontier) {
        Node leastPromising = null;
        for (Node node : frontier) {
            if (node.isLeaf() && (leastPromising == null || node.getFCost() > leastPromising.getFCost())) {
                leastPromising = node;
            }
        }
        if (leastPromising != null) {
            frontier.remove(leastPromising);
        }
        return leastPromising;
    }

    private static void updateParentLeafStatus(Node parent) {
        // Check all children, not just forgotten ones
        for (Node child : parent.getChildren()) {
            if (!child.isLeaf()) {
                parent.setIsLeaf(false);
                return;
            }
        }
        // Check forgotten children if no active child disrupts the leaf status
        for (Node child : parent.getForgottenChildren()) {
            if (!child.isLeaf()) {
                parent.setIsLeaf(false);
                return;
            }
        }
        parent.setIsLeaf(true); // Only set to true if all children are leaves
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
