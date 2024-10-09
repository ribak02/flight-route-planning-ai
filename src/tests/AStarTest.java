package tests;

import org.junit.Before;
import org.junit.Test;

import Node;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AStarTest {
    Node start;
    Node goal;
    ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        start = new Node(0, 0, null); // Assume Node(int distance, int angle, Node parent)
        goal = new Node(2, 0, null); // Set a goal node that's directly reachable

        // Redirect System.out to capture outputs for assertion
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testSearchAStar_Success() {
        // Manually setting nodes in a straight line for simplicity
        Node middle = new Node(1, 0, start);
        middle.setGCost(Util.calculateMoveCost(start, middle));
        middle.setHCost(Util.heuristic(middle, goal));
        middle.setFCost(middle.getGCost() + middle.getHCost());

        AStar.searchAStar(3, start, goal);

        // Check if the output includes the goal node string representation
        String output = outContent.toString();
        assertTrue(output.contains(goal.toString()));
        assertTrue(output.contains(String.format("%.3f", middle.getGCost() + Util.calculateMoveCost(middle, goal))));
        assertTrue(output.contains("3")); // Checks if the node count matches expected path
    }

    @Test
    public void testSearchAStar_Failure() {
        // Set up a test case where the goal is not reachable
        Node blocked = new Node(1, 0, start); // This node blocks the direct path to the goal
        blocked.setGCost(Double.MAX_VALUE); // Simulate blocking by setting high travel cost

        AStar.searchAStar(3, start, new Node(0, 2, null));

        // Check if the output indicates the search has failed
        String output = outContent.toString();
        assertTrue(output.contains("fail"));
        assertTrue(output.contains("1")); // Only the start node is visited if blocked
    }
}
