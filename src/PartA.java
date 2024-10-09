import java.util.*;

public class PartA {
    public static int[] searchDFS(int N, Node start, Node goal) {
        Deque<Node> stack = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        Set<Node> inStack = new HashSet<>(); // Set to track nodes in the stack
        Map<Node, Double> costMap = new HashMap<>();
        int nodesVisited = 0;

        stack.push(start);
        inStack.add(start);

        costMap.put(start, 0.0);

        while (!stack.isEmpty()) {
            printFrontier(stack);
            Node current = stack.pop();
            inStack.remove(current);
            nodesVisited++;

            if (current.equals(goal)) {
                printPath(current);
                double pathCost = costMap.get(current);
                System.out.println(String.format("%.3f", pathCost));
                System.out.println(nodesVisited);
                // Return the metrics for analysis
                return new int[] { (int) Math.round(pathCost), nodesVisited };
            }

            if (!visited.contains(current)) {
                visited.add(current);
                List<Node> successors = Util.getSuccessors(current, N);
                Collections.reverse(successors); // Assuming successors are in natural order
                for (Node child : successors) {
                    if (!visited.contains(child) && !inStack.contains(child)) {
                        stack.push(child);
                        inStack.add(child); // Mark this node as in the stack
                        double newCost = costMap.get(current) + Util.calculateMoveCost(current, child);
                        costMap.put(child, newCost);
                    }
                }
            }
        }

        System.out.println("fail");
        System.out.println(nodesVisited);
        return new int[] { Integer.MAX_VALUE, nodesVisited };
    }

    public static int[] searchBFS(int N, Node start, Node goal) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Double> costMap = new HashMap<>();
        int nodesVisited = 0;

        queue.add(start);
        visited.add(start);
        costMap.put(start, 0.0);

        while (!queue.isEmpty()) {
            printFrontier(queue);
            Node current = queue.poll();
            nodesVisited++;

            if (current.equals(goal)) {
                printPath(current);
                double pathCost = costMap.get(current);
                System.out.println(String.format("%.3f", pathCost));
                System.out.println(nodesVisited);
                return new int[] { (int) Math.round(pathCost), nodesVisited };

            }

            List<Node> successors = Util.getSuccessors(current, N);
            for (Node child : successors) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    queue.add(child);
                    double newCost = costMap.get(current) + Util.calculateMoveCost(current, child);
                    costMap.put(child, newCost);
                }
            }
        }

        System.out.println("fail");
        System.out.println(nodesVisited);
        return new int[] { Integer.MAX_VALUE, nodesVisited };
    }

    private static void printPath(Node node) {
        List<Node> path = new ArrayList<>();
        for (Node current = node; current != null; current = current.parent) {
            path.add(current);
        }
        Collections.reverse(path);
        path.forEach(System.out::print);
        System.out.println();
    }

    private static void printFrontier(Collection<Node> frontier) {
        System.out.print("[");
        Iterator<Node> iterator = new LinkedList<>(frontier).iterator(); // Adjust based on FIFO or LIFO
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
