import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Util {
    // Rule iv: Aircrafts can only move in one of the 4 directions along the grid.
    public static List<Node> getSuccessors(Node node, int N) {
        List<Node> successors = new ArrayList<>();
        int d = node.distance;
        int angle = node.angle;

        // Rule v: Check if movement is towards the pole and valid.
        if (d > 1) {
            successors.add(new Node(d - 1, angle, node)); // North
        }
        // Rule iii: Increment angle by 45 degrees for East movement.
        int eastAngle = (angle + 45) % 360;
        successors.add(new Node(d, eastAngle, node)); // East

        // Rule vi: Check if movement is away from the pole and within bounds.
        if (d < N - 1) {
            successors.add(new Node(d + 1, angle, node)); // South
        }
        // Rule iii: Decrement angle by 45 degrees for West movement.
        int westAngle = (angle - 45 + 360) % 360;
        successors.add(new Node(d, westAngle, node)); // West

        // successors.sort(Comparator.comparingInt((Node n) ->
        // n.distance).thenComparingInt(n -> n.angle).reversed());

        // Sort by distance first, then by angle
        Collections.sort(successors, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                if (n1.distance != n2.distance) {
                    return Integer.compare(n1.distance, n2.distance);
                }
                return Integer.compare(n1.angle, n2.angle);
            }
        });

        return successors;
    }

    public static double calculateMoveCost(Node current, Node target) {
        if (current.distance == target.distance) {
            // Moving East or West, calculate cost based on the circle at that distance
            return Math.PI * current.distance / 4; // Using the formula (2πd/8), simplified to πd/4
        } else {
            // Moving North or South, fixed cost of 1.0 since the distance between parallels
            return 1.0;
        }
    }

    public static double heuristic(Node current, Node goal) {
        double dA = current.distance;
        double dB = goal.distance;
        double angleA = Math.toRadians(current.angle);
        double angleB = Math.toRadians(goal.angle);

        // Calculate the cosine of the difference in angles
        double angleDifference = Math.cos(angleB - angleA);

        // Apply the Euclidean distance formula for polar coordinates
        double distance = Math.sqrt(dA * dA + dB * dB - 2 * dA * dB * angleDifference);
        return distance;
    }
}
